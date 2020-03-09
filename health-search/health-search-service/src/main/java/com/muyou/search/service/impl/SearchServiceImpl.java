package com.muyou.search.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.muyou.common.pojo.Result;
import com.muyou.common.pojo.SolrInfo;
import com.muyou.common.redis.JedisClient;
import com.muyou.common.util.HttpUtil;
import com.muyou.common.util.JsonUtils;
import com.muyou.common.util.ResultUtil;
import com.muyou.common.util.XmlUtils;
import com.muyou.mapper.TbDataMapper;
import com.muyou.pojo.TbData;
import com.muyou.pojo.TbDisease;
import com.muyou.pojo.TbMedicine;
import com.muyou.search.mapper.ItemMapper;
import com.muyou.search.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private ItemMapper itemMapper;

	@Autowired
	private JedisClient jedisClient;

	@Autowired
	private TbDataMapper dataMapper;

	@Autowired
	private CloudSolrServer solrServer;

	@Value("${SOLR_IP}")
	private String SOLR_IP;

	@Value("${SOLR_PORT}")
	private String SOLR_PORT;

	@Value("${SOLR_STATUS_ADDRESS}")
	private String SOLR_ADDRESS;

	@Value("${SOLR_DIS_DOCU_COUNT}")
	private String SOLR_DIS_DOCU;

	@Value("${SOLR_MED_DOCU_COUNT}")
	private String SOLR_MED_DOCU;

	@Value("${SOLR_INDEX}")
	private String SOLR_INDEX;

	@Value("${SOLR_INDEX_EXPIRE}")
	private Integer SOLR_INDEX_EXPIRE;

	@Value("${RELA_DEP}")
	private Integer RELA_DEP;

	@Value("${RELA_PAR}")
	private Integer RELA_PAR;

	@Override
	public Result<Object> getInfo() {
		try {
			// 获得redis存储信息
			try {
				String json = jedisClient.get(SOLR_INDEX);
				if (StringUtils.isNotBlank(json))
					return new ResultUtil<Object>().setData(JsonUtils.jsonToPojo(json, SolrInfo.class));
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 1.请求数据
			String info = HttpUtil.sendGet("http://" + SOLR_IP + ":" + SOLR_PORT + SOLR_ADDRESS);
			// 2.获得xml个数数据，转化为文档
			Document document = XmlUtils.getXMLByString(info);
			// 3.获得根元素
			Element rootElement = document.getRootElement();
			// 4.获得子元素
			Element element = XmlUtils.getChildElement(rootElement, "lst");

			// 5.创建实例
			SolrInfo solrInfo = new SolrInfo();

			// solrInfo.setStatus(element.element("int").getTextTrim());

			// 6.设置集群名
			element = (Element) rootElement.elements("lst").get(1);
			solrInfo.setClusterName(element.element("lst").attributeValue("name"));

			// 7.设置节点个数
			List<Element> elements2 = element.element("arr").elements("str");
			solrInfo.setNodesNum(elements2.size());

			// 1.通过每个collection下的shards的state从而判断是否是可用
			// 2.确定每个可用时,设置status为green,否则为yellow或red
			// 3.如果非green,再通过对应shards下的core_nodes
			// 4.确定每个core_nodes下的state属性以及是否存在leader属性确定从属关系
			// 5.根据state、leader确定出错的shards，从而去确定yellow、red
			// 6.好麻烦、不想弄》 - - 《,就随便一些吧。
			solrInfo.setStatus("green");

			// 7.获得数据内索引个数
			// 7.1获得疾病文档个数
			Integer documentsNum = itemMapper.getDocumentsNum(SOLR_DIS_DOCU);
			if (null == documentsNum)
				documentsNum = 0;
			String docStr = "疾病文档:" + documentsNum + ";\t";
			// 7.2获得药品文档个数
			documentsNum = itemMapper.getDocumentsNum(SOLR_MED_DOCU);
			if (null == documentsNum)
				documentsNum = 0;
			docStr += "药品文档:" + documentsNum + ";";
			solrInfo.setDocStr(docStr);

			// 存入redis索引个数
			try {
				jedisClient.set(SOLR_INDEX, JsonUtils.objectToJson(solrInfo));
				jedisClient.expire(SOLR_INDEX, SOLR_INDEX_EXPIRE);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return new ResultUtil<Object>().setData(solrInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultUtil<Object>().setErrorMsg("数据获取失败");
		}
	}

	@Override
	public int importAllDiseaseItems() {

		try {
			List<String> list = new LinkedList<String>();
			solrServer.setDefaultCollection("collection1");

			// TbDiseaseExample example = new TbDiseaseExample();
			// List<TbDisease> itemList = diseaseMapper.selectByExample(example);
			int i = 0;
			List<TbDisease> itemList = itemMapper.getDiseaseItemList();
			System.out.println(itemList.size());

			for (TbDisease searchItem : itemList) {
				SolrInputDocument document = new SolrInputDocument();
				document.addField("id", "" + searchItem.getId());
				document.addField("di_title", searchItem.getName());
				document.addField("di_intro", searchItem.getIntroduce());
				document.addField("di_url", searchItem.getUrl());

				// 科室
				list = itemMapper.getDiseaseType(searchItem.getId(), RELA_DEP);
				document.addField("di_depart", String.join(",", list));
				list.clear();

				// 部位
				list = itemMapper.getDiseaseType(searchItem.getId(), RELA_PAR);
				document.addField("di_part", String.join(",", list));
				solrServer.add(document);

				System.out.println(i++);

			}
			solrServer.commit();

			TbData data = new TbData();
			data.setCreateTime(new Date());
			data.setDescription("solr疾病索引库文档数");
			data.setType(SOLR_DIS_DOCU);
			data.setNum(itemList.size());
			dataMapper.insert(data);

			try {
				jedisClient.del(SOLR_INDEX);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

		return 1;
	}

	@Override
	public int importAllMedicineItems() {

		try {
			List<String> list = new ArrayList<String>();
			solrServer.setDefaultCollection("collection2");

			int i = 0;

			// TbMedicineExample example = new TbMedicineExample();
			// List<TbMedicine> itemList = medicineMapper.selectByExample(example);
			List<TbMedicine> itemList = itemMapper.getMedicineItemList();
			for (TbMedicine searchItem : itemList) {
				// 获得基本数据
				SolrInputDocument document = new SolrInputDocument();
				document.addField("id", "" + searchItem.getId());
				document.addField("md_price", searchItem.getPrice() + "");
				document.addField("md_goodName", searchItem.getGoodsName());
				document.addField("md_desc", searchItem.getIndications());
				document.addField("md_isOct", searchItem.getOtc() + "");
				document.addField("md_image", searchItem.getLogo());
				document.addField("md_approval", searchItem.getApprovalNumber());
				document.addField("md_manufacturer", searchItem.getManufacturer());
				document.addField("md_zhuzhi", searchItem.getIndications());

				// 获取药品类型
				list = itemMapper.getMedicineType(searchItem.getId());
				document.addField("md_type", String.join(",", list));

				solrServer.add(document);

				System.out.println(i++);
			}
			solrServer.commit();

			// 数据库内添加记录
			TbData data = new TbData();
			data.setCreateTime(new Date());
			data.setDescription("solr药品索引库文档数");
			data.setType(SOLR_MED_DOCU);
			data.setNum(itemList.size());
			dataMapper.insert(data);

			try {
				jedisClient.del(SOLR_INDEX);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

		return 1;
	}

}
