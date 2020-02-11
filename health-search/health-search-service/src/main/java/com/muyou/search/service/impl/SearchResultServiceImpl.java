package com.muyou.search.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;

import com.muyou.common.pojo.DiseaseSortVo;
import com.muyou.common.pojo.MedicineListVo;
import com.muyou.search.dao.SearchDao;
import com.muyou.search.service.SearchResultService;

public class SearchResultServiceImpl implements SearchResultService {

	@Autowired
	private SearchDao searchDao;

	@Override
	public List<MedicineListVo> searchMedicine(String keyword, String field, int page, int rows) throws Exception  {

		SolrQuery query = new SolrQuery();

		query.set(field, keyword);
		// query.setQuery(keyword);
		// query.set("df", "item_title");

		if (page < 0)
			page = 1;
		query.setStart((page - 1) * rows);
		query.setRows(rows);

		if (!"md_type".equals(field)) {
			query.setHighlight(true);
			query.addHighlightField("md_goodName");
			query.setHighlightSimplePre("<em style=\"color:red\">");
			query.setHighlightSimplePost("</em>");
		}
		
		return searchDao.searchMedicine(query);
	}
	
	
	@Override
	public List<DiseaseSortVo> searchDisease(String keyword, String field, int page, int rows) throws Exception {

		SolrQuery query = new SolrQuery();

		query.set(field, keyword);
		// query.setQuery(keyword);
		// query.set("df", "item_title");

		if (page < 0)
			page = 1;
		query.setStart((page - 1) * rows);
		query.setRows(rows);

		if (!"md_type".equals(field)) {
			query.setHighlight(true);
			query.addHighlightField("di_title");
			query.addHighlightField("di_intro");
			query.setHighlightSimplePre("<em style=\"color:red\">");
			query.setHighlightSimplePost("</em>");
		}
		
		return searchDao.searchDisease(query);
	}
	

}
