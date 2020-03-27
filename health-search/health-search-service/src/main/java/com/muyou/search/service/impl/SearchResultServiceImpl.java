package com.muyou.search.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muyou.common.constant.RedisConstant;
import com.muyou.common.exception.ServiceException;
import com.muyou.common.redis.JedisClient;
import com.muyou.common.util.JsonUtils;
import com.muyou.search.dao.SearchDao;
import com.muyou.search.service.SearchResultService;
import com.muyou.vo.DiseaseSortVo;
import com.muyou.vo.MedicineListVo;

@Service
public class SearchResultServiceImpl implements SearchResultService {

	@Autowired
	private SearchDao searchDao;

	@Autowired
	private JedisClient jedisClient;

	@Override
	public List<MedicineListVo> searchMedicine(String keyword, String field, int page, int rows)
			throws ServiceException {

		try {
			String json = jedisClient.get(RedisConstant.SOLR_SEARCH_MED + ":" + field + ":" + keyword + ":" + page);
			if (StringUtils.isNotBlank(json)) {
				return JsonUtils.jsonToList(json, MedicineListVo.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("keyword" + keyword);
		System.out.println("field" + field);

		SolrQuery query = new SolrQuery();

		query.setQuery("*" + keyword + "*");
		query.set("df", field);

		query.setStart(page * rows);
		query.setRows(rows);

		query.setHighlight(true);
		query.addHighlightField(field);
		query.setHighlightSimplePre("<span style=\"color:red\">");
		query.setHighlightSimplePost("</span>");

		List<MedicineListVo> result = searchDao.searchMedicine(query, field);

		try {
			jedisClient.set(RedisConstant.SOLR_SEARCH_MED + ":" + field + ":" + keyword + ":" + page,
					JsonUtils.objectToJson(result));
			jedisClient.expire(RedisConstant.SOLR_SEARCH_MED + ":" + field + ":" + keyword + ":" + page,
					RedisConstant.SOLR_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public List<DiseaseSortVo> searchDisease(String keyword, String field, int page, int rows) throws ServiceException {

		try {
			String json = jedisClient.get(RedisConstant.SOLR_SEARCH_DIS + ":" + field + ":" + keyword + ":" + page);
			if (StringUtils.isNotBlank(json)) {
				return JsonUtils.jsonToList(json, DiseaseSortVo.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("keyword" + keyword);
		System.out.println("field" + field);

		SolrQuery query = new SolrQuery();
		query.setQuery("*" + keyword + "*");
		query.set("df", field);
		query.setStart(page * rows);
		query.setRows(rows);
		query.setHighlight(true);
		query.addHighlightField("di_intro");
		query.addHighlightField("di_title");
		query.setHighlightSimplePre("<span style=\"color:red\">");
		query.setHighlightSimplePost("</span>");

		List<DiseaseSortVo> result = searchDao.searchDisease(query);
		try {
			jedisClient.set(RedisConstant.SOLR_SEARCH_DIS + ":" + field + ":" + keyword + ":" + page,
					JsonUtils.objectToJson(result));
			jedisClient.expire(RedisConstant.SOLR_SEARCH_DIS + ":" + field + ":" + keyword + ":" + page,
					RedisConstant.SOLR_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

}
