package com.muyou.service;

import java.util.List;

import com.muyou.common.form.RequestForm;
import com.muyou.common.vo.MedicineDetailVo;
import com.muyou.common.vo.MedicineListVo;
import com.muyou.common.vo.MedicineVo;

public interface MedicineService {

	// 获得所有的分类
	public List<MedicineVo> getClassify();

	// 获得所有的药品根据分类
	public List<MedicineListVo> getAllMedicine(RequestForm requestForm);

	// 获得所有的药品根据关键字和范围
	public List<MedicineListVo> getAllMedicineByKey(RequestForm requestForm);

	// 获得药品详细信息
	public MedicineDetailVo getMedicineDetail(RequestForm requestForm);

}
