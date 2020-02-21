package com.muyou.search.service;

import com.muyou.common.pojo.Result;
import com.muyou.common.pojo.SolrInfo;

public interface SearchService {
	
	/**
	 * 获得solr信息
	 * @return
	 */
	Result<Object> getInfo();
	
	/**
	 * 导入所有疾病数据
	 * @return
	 */
	int importAllDiseaseItems();
	
	/**
	 * 导入所有药品数据
	 * @return
	 */
	int importAllMedicineItems();
}

