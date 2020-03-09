package com.muyou.service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.muyou.common.exception.GlobalException;
import com.muyou.common.pojo.DataTablesResult;
import com.muyou.common.redis.JedisClient;
import com.muyou.common.util.JsonUtils;
import com.muyou.common.util.StringUtil;
import com.muyou.dto.DtoUtil;
import com.muyou.mapper.TbCateMapper;
import com.muyou.mapper.TbDiseaseMapper;
import com.muyou.mapper.TbItemRelaCateMapper;
import com.muyou.pojo.TbCate;
import com.muyou.pojo.TbDisease;
import com.muyou.pojo.TbDiseaseExample;
import com.muyou.pojo.TbItemRelaCate;
import com.muyou.pojo.TbItemRelaCateExample;
import com.muyou.service.ItemDisService;
import com.muyou.vo.DiseaseVo;

@Service
public class ItemDisServiceImpl implements ItemDisService {

	private final static Logger log = LoggerFactory.getLogger(ItemDisServiceImpl.class);

	@Autowired
	private JedisClient jedisClient;

	@Autowired
	private JmsTemplate jmsTemplate;

	@Resource
	private Destination topicDestination;

	@Autowired
	private TbDiseaseMapper diseaseMapper;

	@Autowired
	private TbCateMapper cateMapper;

	@Autowired
	private TbItemRelaCateMapper itemRelaCateMapper;

	@Value("${ITEM_COUNT}")
	private String ITEM_COUNT;

	@Value("${ITEM_ID}")
	private String ITEM_ID;

	@Value("${RELA_DEP}")
	private Integer RELA_DEP;

	@Value("${RELA_PAR}")
	private Integer RELA_PAR;

	@Value("${ITEM_EXPIRE}")
	private Integer ITEM_EXPIRE;

	@Value("${ITEM_DIS_DEF_IMG}")
	private String ITEM_DIS_DEF_IMG;

	@Value("${ITEM_DETAIL_ID}")
	private String ITEM_DETAIL_ID;

	@Value("${DISEASE}")
	private String DISEASE;

	@Override
	public DiseaseVo getItemById(Integer id) {

		try {
			String json = jedisClient.get(ITEM_DETAIL_ID + ":" + DISEASE + ":" + id);
			if (StringUtils.isNotBlank(json))
				return JsonUtils.jsonToPojo(json, DiseaseVo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 设置疾病数据
		DiseaseVo result = DtoUtil.TbDisease2DiseaseVo(diseaseMapper.selectByPrimaryKey(id));

		List<String> cateList = new LinkedList<String>();
		List<String> cidList = new LinkedList<String>();

		// 获得科室数据
		List<TbCate> list = cateMapper.selectItemCate(id, RELA_DEP);
		for (TbCate tbCate : list) {
			cateList.add(tbCate.getName());
			cidList.add(tbCate.getId() + "");
		}
		result.setDid(cidList);
		result.setDepart(cateList);

		// 获得部位数据
		list = cateMapper.selectItemCate(id, RELA_PAR);
		cateList = new LinkedList<String>();
		cidList = new LinkedList<String>();
		for (TbCate tbCate : list) {
			cateList.add(tbCate.getName());
			cidList.add(tbCate.getId() + "");
		}
		result.setPid(cidList);
		result.setPart(cateList);

		try {
			jedisClient.set(ITEM_DETAIL_ID + ":" + DISEASE + ":" + id, JsonUtils.objectToJson(result));
			jedisClient.expire(ITEM_DETAIL_ID + ":" + DISEASE + ":" + id, ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}

	@Override
	public TbDisease getNormalItemById(Integer id) {

		try {
			String json = jedisClient.get(ITEM_ID + ":" + DISEASE + ":" + id);
			if (StringUtils.isNotBlank(json))
				return JsonUtils.jsonToPojo(json, TbDisease.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbDisease result = diseaseMapper.selectByPrimaryKey(id);

		try {
			jedisClient.set(ITEM_ID + ":" + DISEASE + ":" + id, JsonUtils.objectToJson(result));
			jedisClient.expire(ITEM_ID + ":" + DISEASE + ":" + id, ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public DataTablesResult getItemList(int draw, int start, int length, int cid, String search, String orderCol,
			String orderDir) {
		DataTablesResult result = new DataTablesResult();

		// 分页执行查询返回结果
		PageHelper.startPage(start / length + 1, length);
		List<TbDisease> list = diseaseMapper.selectItemByCondition(cid, "%" + search + "%", orderCol, orderDir);

		List<String> cateList;
		for (TbDisease tbDisease : list) {
			// 获得科室数据
			cateList = cateMapper.selectCateNameByItemIdAndType(tbDisease.getId(), RELA_DEP);
			tbDisease.setDepart(String.join(",", cateList));
			// 获得部位数据
			cateList = cateMapper.selectCateNameByItemIdAndType(tbDisease.getId(), RELA_PAR);
			tbDisease.setPart(String.join(",", cateList));
		}

		PageInfo<TbDisease> pageInfo = new PageInfo<>(list);

		result.setRecordsFiltered((int) pageInfo.getTotal());
		result.setRecordsTotal(getAllItemCount().getRecordsTotal());

		result.setDraw(draw);
		result.setData(list);

		return result;
	}

	@Override
	public DataTablesResult getAllItemCount() {

		try {
			String json = jedisClient.get(ITEM_COUNT + ":" + DISEASE);
			if (StringUtils.isNotBlank(json))
				return JsonUtils.jsonToPojo(json, DataTablesResult.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbDiseaseExample example = new TbDiseaseExample();
		int count = diseaseMapper.countByExample(example);
		DataTablesResult result = new DataTablesResult();
		result.setRecordsTotal(count);

		try {
			jedisClient.set(ITEM_COUNT + ":" + DISEASE, JsonUtils.objectToJson(result));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public DataTablesResult getItemSearchList(int draw, int start, int length, int cid, String search, String minDate,
			String maxDate, String orderCol, String orderDir) {

		DataTablesResult result = new DataTablesResult();

		// 分页执行查询返回结果
		PageHelper.startPage(start / length + 1, length);
		List<TbDisease> list = diseaseMapper.selectItemByMultiCondition(cid, "%" + search + "%", minDate, maxDate,
				orderCol, orderDir);

		List<String> cateList;
		for (TbDisease tbDisease : list) {
			// 获得科室数据
			cateList = cateMapper.selectCateNameByItemIdAndType(tbDisease.getId(), RELA_DEP);
			tbDisease.setDepart(String.join(",", cateList));
			// 获得部位数据
			cateList = cateMapper.selectCateNameByItemIdAndType(tbDisease.getId(), RELA_PAR);
			tbDisease.setPart(String.join(",", cateList));
		}

		PageInfo<TbDisease> pageInfo = new PageInfo<>(list);
		result.setRecordsFiltered((int) pageInfo.getTotal());
		result.setRecordsTotal(getAllItemCount().getRecordsTotal());

		result.setDraw(draw);
		result.setData(list);

		return result;
	}

	@Override
	public int addItem(DiseaseVo diseaseVo) {
		TbDisease disease = DtoUtil.DiseaseVo2TbDisease(diseaseVo);
		disease.setSymbol(StringUtil.getFirstSpell(diseaseVo.getName()));
		disease.setStatus(true);
		disease.setCreated(new Date());
		disease.setUpdated(new Date());
		if (disease.getUrl().isEmpty()) {
			disease.setUrl(ITEM_DIS_DEF_IMG);
		}
		if (diseaseMapper.insert(disease) < 1) {
			throw new GlobalException("添加疾病失败");
		}

		TbItemRelaCate itemRelaCate = new TbItemRelaCate();

		// 部位数据
		itemRelaCate.setItemId(disease.getId());
		itemRelaCate.setType(RELA_PAR);
		for (String pid : diseaseVo.getPid()) {
			if (StringUtils.isBlank(pid))
				continue;
			itemRelaCate.setCateId(Integer.valueOf(pid));
			itemRelaCateMapper.insert(itemRelaCate);
		}

		// 科室数据
		itemRelaCate.setItemId(disease.getId());
		itemRelaCate.setType(RELA_DEP);
		for (String did : diseaseVo.getDid()) {
			if (StringUtils.isBlank(did))
				continue;
			itemRelaCate.setCateId(Integer.valueOf(did));
			itemRelaCateMapper.insert(itemRelaCate);
		}

		try {
			jedisClient.del(ITEM_COUNT + ":" + DISEASE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 发送消息同步索引库
		try {
			sendRefreshSolrMessage(DISEASE, "add", disease.getId(),"-1");
		} catch (Exception e) {
			log.error("同步索引出错");
		}

		return 1;
	}

	@Override
	public int deleteItem(int id) {
		if (diseaseMapper.deleteByPrimaryKey(id) != 1) {
			throw new GlobalException("删除疾病失败");
		}

		TbItemRelaCateExample example = new TbItemRelaCateExample();
		TbItemRelaCateExample.Criteria criteria = example.createCriteria();
		criteria.andCateIdEqualTo(id);
		itemRelaCateMapper.deleteByExample(example);

		try {
			jedisClient.del(ITEM_ID + ":" + DISEASE + ":" + id);
			jedisClient.del(ITEM_DETAIL_ID + ":" + DISEASE + ":" + id);
			jedisClient.del(ITEM_COUNT + ":" + DISEASE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 发送消息同步索引库
		try {
			sendRefreshSolrMessage(DISEASE, "delete", id,"-1");
		} catch (Exception e) {
			log.error("同步索引出错");
		}

		return 1;
	}

	@Override
	public int alertItemState(Integer id, Boolean state) {

		TbDisease item = getNormalItemById(id);
		item.setStatus(state);
		item.setUpdated(new Date());

		if (diseaseMapper.updateByPrimaryKey(item) < 1) {
			throw new GlobalException("修改疾病状态失败");
		}

		try {
			jedisClient.del(ITEM_ID + ":" + DISEASE + ":" + id);
			jedisClient.del(ITEM_DETAIL_ID + ":" + DISEASE + ":" + id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 发送消息同步索引库
		try {
			sendRefreshSolrMessage(DISEASE, "update", id, state ? "1" : "2");
		} catch (Exception e) {
			log.error("同步索引出错");
		}

		return 1;
	}

	@Override
	public int updateItem(Integer id, DiseaseVo diseaseVo) {

		TbDisease oldDisease = getNormalItemById(id);
		TbDisease disease = DtoUtil.DiseaseVo2TbDisease(diseaseVo);
		disease.setId(id);
		disease.setSymbol(StringUtil.getFirstSpell(diseaseVo.getName()));
		disease.setCreated(oldDisease.getCreated());
		disease.setUpdated(new Date());
		disease.setStatus(true);
		if (diseaseMapper.updateByPrimaryKey(disease) < 1) {
			throw new GlobalException("更新疾病失败");
		}

		TbItemRelaCateExample example = new TbItemRelaCateExample();
		TbItemRelaCateExample.Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(id);
		itemRelaCateMapper.deleteByExample(example);

		TbItemRelaCate itemRelaCate = new TbItemRelaCate();
		// 部位数据
		itemRelaCate.setItemId(id);
		itemRelaCate.setType(RELA_PAR);
		for (String pid : diseaseVo.getPid()) {
			if (StringUtils.isBlank(pid))
				continue;
			itemRelaCate.setCateId(Integer.valueOf(pid));
			itemRelaCateMapper.insert(itemRelaCate);
		}

		// 科室数据
		itemRelaCate.setItemId(id);
		itemRelaCate.setType(RELA_DEP);
		for (String did : diseaseVo.getDid()) {
			if (StringUtils.isBlank(did))
				continue;
			itemRelaCate.setCateId(Integer.valueOf(did));
			itemRelaCateMapper.insert(itemRelaCate);
		}

		// 同步缓存
		try {
			jedisClient.del(ITEM_ID + ":" + DISEASE + ":" + id);
			jedisClient.del(ITEM_DETAIL_ID + ":" + DISEASE + ":" + id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 发送消息同步索引库
		try {
			sendRefreshSolrMessage(DISEASE, "update", id, "0");
		} catch (Exception e) {
			log.error("同步索引出错");
		}

		return 1;
	}

	/**
	 * 发送消息同步索引库
	 * 
	 * @param type
	 * @param oper
	 * @param id
	 */
	public void sendRefreshSolrMessage(String type, String oper, int id, String fac) {
		jmsTemplate.send(topicDestination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = session
						.createTextMessage(type + "," + oper + "," + String.valueOf(id) + "," + fac);
				return textMessage;
			}
		});
	}

}
