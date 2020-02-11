package com.muyou.search.message;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import com.muyou.pojo.TbDisease;
import com.muyou.search.mapper.ItemMapper;

public class ItemDiseaseListener implements MessageListener {

	@Autowired
	private ItemMapper itemMapper;

	@Autowired
	private SolrServer solrServer;
	
	
	@Override
	public void onMessage(Message message) {

		try {
			TextMessage textMessage = (TextMessage) message;
			Long itemId = Long.parseLong(textMessage.getText());
			// 等待事务提交
			Thread.sleep(1000);
			// 根据商品id查询商品信息。
			TbDisease searchItem = itemMapper.getDiseaseItemById(itemId);
			// 创建一SolrInputDocument对象。
			SolrInputDocument document = new SolrInputDocument();
			document.addField("di_id", searchItem.getId());
			document.addField("di_title", searchItem.getDiseaseName());
			document.addField("di_intro", searchItem.getDiseaseIntroduce());
			document.addField("di_url", searchItem.getDiseaseUrl());
			document.addField("di_part", searchItem.getDiseasePart());
			document.addField("di_depart", searchItem.getCureDepart());
			solrServer.add(document);
			solrServer.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
