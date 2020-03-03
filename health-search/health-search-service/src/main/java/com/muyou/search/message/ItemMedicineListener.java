package com.muyou.search.message;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import com.muyou.pojo.TbMedicine;
import com.muyou.search.mapper.ItemMapper;

public class ItemMedicineListener implements MessageListener {

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
			TbMedicine searchItem = itemMapper.getMedicineItemById(itemId);
			// 创建一SolrInputDocument对象。
			SolrInputDocument document = new SolrInputDocument();
			document.addField("md_id", searchItem.getId());
			document.addField("md_price", searchItem.getPrice()+"");
			document.addField("md_goodName", searchItem.getGoodsName());
			document.addField("md_desc", searchItem.getInstruction());
			document.addField("md_isOct", searchItem.getOtc()+"");
			document.addField("md_image", searchItem.getLogo());
			document.addField("md_approval", searchItem.getApprovalNumber());
			document.addField("md_manufacturer", searchItem.getManufacturer());
			document.addField("md_zhuzhi", searchItem.getIndications());
			document.addField("md_type", searchItem.getcName());
			solrServer.add(document);
			solrServer.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
