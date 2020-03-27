package com.muyou.search.dao;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.muyou.common.exception.ServiceException;
import com.muyou.common.pojo.ResponseBuilder;
import com.muyou.vo.DiseaseSortVo;
import com.muyou.vo.MedicineListVo;

@Repository
public class SearchDao {

	@Autowired
	private CloudSolrServer solrServer;

	public List<MedicineListVo> searchMedicine(SolrQuery query, String field) throws ServiceException {
		List<MedicineListVo> itemList = new LinkedList<MedicineListVo>();
		solrServer.setDefaultCollection("collection2");

		QueryResponse queryResponse;
		try {
			queryResponse = solrServer.query(query);

			SolrDocumentList documentList = queryResponse.getResults();

			Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
			for (SolrDocument document : documentList) {
				// 取商品信息
				MedicineListVo searchItem = new MedicineListVo();
				searchItem.setId((String) document.get("id"));
				searchItem.setGoodName((String) document.get("md_goodName"));
				searchItem.setPrice((String) document.get("md_price"));
				searchItem.setDescription((String) document.get("md_desc"));
				searchItem.setIsOct((String) document.get("md_isOct"));
				searchItem.setImage((String) document.get("md_image"));
				// 取高亮结果
				List<String> list = highlighting.get(document.get("id")).get(field);
				String itemTitle = "";
				if (list != null && list.size() > 0) {
					itemTitle = list.get(0);
				} else {
					itemTitle = (String) document.get(field);
				}

				if ("md_goodName".equals(field))
					searchItem.setGoodName(itemTitle);
				else
					searchItem.setDescription(itemTitle);
				// 添加到疾病列表
				itemList.add(searchItem);
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
			throw new ServiceException(new ResponseBuilder(400, 99, "Solr查询异常"));
		}
		return itemList;
	}

	public List<DiseaseSortVo> searchDisease(SolrQuery query) throws ServiceException {

		List<DiseaseSortVo> itemList = new LinkedList<DiseaseSortVo>();
		try {
			solrServer.setDefaultCollection("collection1");
			QueryResponse queryResponse = solrServer.query(query);
			SolrDocumentList documentList = queryResponse.getResults();
			Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();

			for (SolrDocument document : documentList) {
				// 取商品信息
				DiseaseSortVo searchItem = new DiseaseSortVo();
				searchItem.setId((String) document.get("id"));
				searchItem.setIntroduction((String) document.get("di_intro"));
				searchItem.setUrl((String) document.get("di_url"));

				// 取高亮结果
				List<String> list = highlighting.get(document.get("id")).get("di_title");
				List<String> list1 = highlighting.get(document.get("id")).get("di_intro");
				String itemTitle = "";
				if (list != null && list.size() > 0) {
					itemTitle = list.get(0);
				} else {
					itemTitle = (String) document.get("di_title");
				}

				searchItem.setTitle(itemTitle);
				if (list1 != null && list1.size() > 0) {
					itemTitle = list.get(0);
				} else {
					itemTitle = (String) document.get("di_intro");
				}

				// 添加到疾病列表
				itemList.add(searchItem);
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
			throw new ServiceException(new ResponseBuilder(400, 99, "Solr查询异常"));
		}
		return itemList;

	}

}
