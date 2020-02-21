package com.muyou.search.dao;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.muyou.common.pojo.DiseaseSortVo;
import com.muyou.common.pojo.MedicineListVo;

@Repository
public class SearchDao implements Serializable{

	@Autowired
	private SolrServer solrServer;

	public List<MedicineListVo> searchMedicine(SolrQuery query) throws Exception {
		QueryResponse queryResponse = solrServer.query(query);
		SolrDocumentList documentList = queryResponse.getResults();

		List<MedicineListVo> itemList = new LinkedList<MedicineListVo>();

		Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();

		for (SolrDocument document : documentList) {
			// 取商品信息
			MedicineListVo searchItem = new MedicineListVo();
			searchItem.setId((String) document.get("md_id"));
			searchItem.setPrice((String) document.get("md_price"));
			searchItem.setDescription((String) document.get("md_desc"));
			searchItem.setIsOct((String) document.get("md_isOct"));
			searchItem.setImage((String) document.get("md_image"));
			// 取高亮结果
			List<String> list = highlighting.get(document.get("md_id")).get("md_goodName");
			String itemTitle = "";
			if (list != null && list.size() > 0) {
				itemTitle = list.get(0);
			} else {
				itemTitle = (String) document.get("md_goodName");
			}
			
			searchItem.setGoodName(itemTitle);
			
			// 添加到商品列表
			itemList.add(searchItem);
		}

		return itemList;
	}
	
	
	public List<DiseaseSortVo> searchDisease(SolrQuery query) throws Exception {
		QueryResponse queryResponse = solrServer.query(query);
		SolrDocumentList documentList = queryResponse.getResults();
		
		List<DiseaseSortVo> itemList = new LinkedList<DiseaseSortVo>();
		
		Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
		
		for (SolrDocument document : documentList) {
			// 取商品信息
			DiseaseSortVo searchItem = new DiseaseSortVo();
			searchItem.setId((String) document.get("di_id"));
			searchItem.setIntroduction((String) document.get("di_intro"));
			searchItem.setUrl((String) document.get("di_url"));
			
			// 取高亮结果
			List<String> list = highlighting.get(document.get("di_id")).get("di_title");
			String itemTitle = "";
			if (list != null && list.size() > 0) {
				itemTitle = list.get(0);
			} else {
				itemTitle = (String) document.get("di_title");
			}
			
			searchItem.setTitle(itemTitle);
			
			// 添加到商品列表
			itemList.add(searchItem);
		}
		
		return itemList;
	}

}
