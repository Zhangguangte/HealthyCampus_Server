package com.muyou.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.muyou.common.exception.ServiceException;
import com.muyou.common.form.RequestForm;
import com.muyou.common.util.StringUtil;
import com.muyou.common.vo.MedicineDetailVo;
import com.muyou.common.vo.MedicineListVo;
import com.muyou.common.vo.MedicineVo;
import com.muyou.common.vo.ResponseBuilder;
import com.muyou.service.MedicineService;

@Controller
@RequestMapping("/medicine")
public class MedicineController {

	@Autowired
	private MedicineService medicineService;

	// 获得所有的分类
	@RequestMapping("/getClassify")
	@ResponseBody
	public List<MedicineVo> getClassify() throws ServiceException {
		List<MedicineVo> result = medicineService.getClassify();
		if (null == result)
			throw new ServiceException(ResponseBuilder.ERROR_DATA_LOSE);
		return result;
	}

	// 获得所有的药品根据分类
	@RequestMapping("/getAllMedicine")
	@ResponseBody
	public List<MedicineListVo> getAllMedicine(@RequestBody RequestForm form) throws ServiceException {
		if (StringUtil.isEmpty(form.getContent()))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		List<MedicineListVo> result = medicineService.getAllMedicine(form);
		if (null == result)
			throw new ServiceException(ResponseBuilder.ERROR_DATA_LOSE);
		return result;
	}

	// 获得所有的药品根据关键字和范围
	@RequestMapping("/getAllMedicineByKey")
	@ResponseBody
	public List<MedicineListVo> getAllMedicineByKey(@RequestBody RequestForm form) throws ServiceException {
		
		if (StringUtil.isEmpty(form.getQuest_id()) || StringUtil.isEmpty(form.getContent()))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		List<MedicineListVo> result = medicineService.getAllMedicineByKey(form);
		if (null == result)
			throw new ServiceException(ResponseBuilder.ERROR_MEDICINE_NOT_FOUND);
		return result;
	}

	// 获得药品详细信息
	@RequestMapping("/getMedicineDetail")
	@ResponseBody
	public MedicineDetailVo getMedicineDetail(@RequestBody RequestForm form) throws ServiceException {
		if (StringUtil.isEmpty(form.getQuest_id()) || StringUtil.isEmpty(form.getContent()))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		MedicineDetailVo result = medicineService.getMedicineDetail(form);
		if (null == result)
			throw new ServiceException(ResponseBuilder.ERROR_MEDICINE_NOT_FOUND);
		return result;
	}

}
