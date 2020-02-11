package com.muyou.search.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import com.muyou.pojo.TbMedicine;
import com.muyou.search.mapper.ItemMapper;
import com.muyou.search.service.SearchMedicineService;

public class SearchMedicineServiceImpl implements SearchMedicineService {

	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private CloudSolrServer solrServer;
	
	@Override
	public int importAllItems() {
		try {
			solrServer.setDefaultCollection("collection0");
			List<TbMedicine> itemList = itemMapper.getMedicineItemList();
			for (TbMedicine searchItem : itemList) {
				SolrInputDocument document = new SolrInputDocument();
				document.addField("md_id", searchItem.getGoodsId());
				document.addField("md_price", searchItem.getPrice()+"");
				document.addField("md_goodName", searchItem.getGoodsName());
				document.addField("md_desc", searchItem.getExplainBook());
				document.addField("md_isOct", searchItem.getIsOtc()+"");
				document.addField("md_image", searchItem.getLogo());
				document.addField("md_approval", searchItem.getApprovalNumber());
				document.addField("md_manufacturer", searchItem.getManufacturer());
				document.addField("md_zhuzhi", searchItem.getZhuzhi());
				document.addField("md_type", searchItem.getC2());
				solrServer.add(document);
			}
			solrServer.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

		return 1;
	}

}
