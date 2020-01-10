package com.muyou.service;

import java.util.List;

import com.muyou.common.form.RequestForm;
import com.muyou.common.vo.DiseaseDetailVo;
import com.muyou.common.vo.DiseaseSortListVo;
import com.muyou.common.vo.DiseaseSortVo;

public interface DiseaseService {

	// 疾病分类
	public List<DiseaseSortListVo> getDiseaseSortList(RequestForm requestForm);

	// 分类疾病
	public List<DiseaseSortVo> getDiseaseSort(RequestForm requestForm);

	// 疾病详细
	public DiseaseDetailVo getDiseaseDetail(RequestForm requestForm);

}
