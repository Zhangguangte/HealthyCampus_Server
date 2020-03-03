package com.muyou.front.service;

import java.util.List;

import com.muyou.common.form.RequestForm;
import com.muyou.front.vo.MedicineDetailVo;
import com.muyou.front.vo.MedicineVo;

/**
 * 药品服务
 * @author 木友茶
 *
 */
public interface MedicineService {

	// 获得所有的分类
	 List<MedicineVo> getClassify();

//	// 获得所有的药品根据分类
//	 List<MedicineListVo> getAllMedicine(RequestForm requestForm);
//
//	// 获得所有的药品根据关键字和范围
//	 List<MedicineListVo> getAllMedicineByKey(RequestForm requestForm);

	// 获得药品详细信息
	 MedicineDetailVo getMedicineDetail(RequestForm requestForm);

}
