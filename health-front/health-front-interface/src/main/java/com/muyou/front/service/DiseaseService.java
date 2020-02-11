package com.muyou.front.service;

import java.util.List;

import com.muyou.common.form.RequestForm;
import com.muyou.common.pojo.DiseaseSortVo;
import com.muyou.front.pojo.DiseaseDetailVo;
import com.muyou.front.pojo.DiseaseSortListVo;

/**
 * 疾病
 * @author 木友茶
 *
 */
public interface DiseaseService {

	// 疾病分类
	 List<DiseaseSortListVo> getDiseaseSortList(RequestForm requestForm);

//	// 分类疾病
//	 List<DiseaseSortVo> getDiseaseSort(RequestForm requestForm);

	// 疾病详细
	 DiseaseDetailVo getDiseaseDetail(RequestForm requestForm);

}
