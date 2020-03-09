package com.muyou.search.message;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.muyou.common.redis.JedisClient;
import com.muyou.pojo.TbDisease;
import com.muyou.pojo.TbMedicine;
import com.muyou.search.mapper.ItemMapper;

public class ItemListener implements MessageListener {

	@Autowired
	private ItemMapper itemMapper;

	@Autowired
	private CloudSolrServer solrServer;

	@Autowired
	private JedisClient jedisClient;

	@Value("${SOLR_INDEX}")
	private String SOLR_INDEX;

	@Value("${RELA_DEP}")
	private Integer RELA_DEP;

	@Value("${RELA_PAR}")
	private Integer RELA_PAR;

	@Override
	public void onMessage(Message message) {
		try {
			TextMessage textMessage = (TextMessage) message;
			String str = textMessage.getText();

			System.out.println(str);

			String[] split = str.split(",");

			// 等待事务提交
			Thread.sleep(1000);

			if ("MEDICINE".equals(split[0])) {
				solrServer.setDefaultCollection("collection2");
				if ("add".equals(split[1])) {
					addMedicine(split[2]);
				} else if ("delete".equals(split[1])) {
					delMedicine(split[2]);
				} else if ("update".equals(split[1])) {
					if (("0").equals(split[3])) {
						addMedicine(split[2]);
					} else if (("1").equals(split[3])) {
						addMedicine(split[2]);
					} else if (("2").equals(split[3])) {
						delMedicine(split[2]);
					}

				}
			} else if ("DISEASE".equals(split[0])) {
				solrServer.setDefaultCollection("collection1");
				if ("add".equals(split[1])) {
					addDisease(split[2]);
				} else if ("delete".equals(split[1])) {
					delDisease(split[2]);
				} else if ("update".equals(split[1])) {
					if (("0").equals(split[3])) {
						addDisease(split[2]);
					} else if (("1").equals(split[3])) {
						addDisease(split[2]);
					} else if (("2").equals(split[3])) {
						delDisease(split[2]);
					}
				}
			}

			if ("add".equals(split[1]) || "delete".equals(split[1])) {
				try {
					jedisClient.del(SOLR_INDEX);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addDisease(String itemId) throws SolrServerException, IOException {
		List<String> list = new LinkedList<String>();
		TbDisease disease = itemMapper.getDiseaseItemById(Integer.valueOf(itemId));
		// 创建一SolrInputDocument对象。
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", itemId);
		document.addField("di_title", disease.getName());
		document.addField("di_intro", disease.getIntroduce());
		document.addField("di_url", disease.getUrl());
		// 科室
		list = itemMapper.getDiseaseType(disease.getId(), RELA_DEP);
		document.addField("di_depart", String.join(",", list));
		list.clear();
		// 部位
		list = itemMapper.getDiseaseType(disease.getId(), RELA_PAR);
		document.addField("di_part", String.join(",", list));
		solrServer.add(document);
		solrServer.commit();
		
		System.out.println("****");
		
	}

	public void delDisease(String itemId) throws SolrServerException, IOException {
		solrServer.deleteById(itemId);
		solrServer.commit();
	}

	public void addMedicine(String itemId) throws SolrServerException, IOException {
		List<String> list = new LinkedList<String>();
		TbMedicine medicine = itemMapper.getMedicineItemById(Integer.valueOf(itemId));
		// 获得基本数据
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", itemId);
		document.addField("md_price", medicine.getPrice() + "");
		document.addField("md_goodName", medicine.getGoodsName());
		document.addField("md_desc", medicine.getIndications());
		document.addField("md_isOct", medicine.getOtc() + "");
		document.addField("md_image", medicine.getLogo());
		document.addField("md_approval", medicine.getApprovalNumber());
		document.addField("md_manufacturer", medicine.getManufacturer());
		document.addField("md_zhuzhi", medicine.getIndications());
		// 获取药品类型
		list = itemMapper.getMedicineType(medicine.getId());
		document.addField("md_type", String.join(",", list));
		solrServer.add(document);
		solrServer.commit();
	}

	public void delMedicine(String itemId) throws SolrServerException, IOException {
		solrServer.deleteById(itemId);
		solrServer.commit();
	}
}
