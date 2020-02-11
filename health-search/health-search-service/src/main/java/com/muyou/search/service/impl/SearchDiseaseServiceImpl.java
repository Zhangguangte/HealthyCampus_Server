package com.muyou.search.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import com.muyou.pojo.TbDisease;
import com.muyou.search.mapper.ItemMapper;
import com.muyou.search.service.SearchDiseaseService;

public class  SearchDiseaseServiceImpl implements SearchDiseaseService{

	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private CloudSolrServer solrServer;
	
	@Override
	public int importAllItems() {
		try {
			solrServer.setDefaultCollection("collection1");
			List<TbDisease> itemList = itemMapper.getDiseaseItemList();
			for (TbDisease searchItem : itemList) {
				SolrInputDocument document = new SolrInputDocument();
				document.addField("di_id", searchItem.getId());
				document.addField("di_title", searchItem.getDiseaseName());
				document.addField("di_intro", searchItem.getDiseaseIntroduce());
				document.addField("di_url", searchItem.getDiseaseUrl());
				document.addField("di_part", searchItem.getDiseasePart());
				document.addField("di_depart", searchItem.getCureDepart());
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
