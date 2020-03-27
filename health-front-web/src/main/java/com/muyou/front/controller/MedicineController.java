package com.muyou.front.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.muyou.common.exception.ServiceException;
import com.muyou.common.form.RequestForm;
import com.muyou.common.pojo.ResponseBuilder;
import com.muyou.common.util.StringUtil;
import com.muyou.front.service.MedicineService;
import com.muyou.front.vo.MedicineDetailVo;
import com.muyou.front.vo.MedicineVo;
import com.muyou.search.service.SearchResultService;
import com.muyou.sso.service.CollectService;
import com.muyou.vo.MedicineListVo;

@Controller
@RequestMapping("/medicine")
public class MedicineController {

	@Autowired
	private MedicineService medicineService;

	@Autowired
	private CollectService collectService;

	@Autowired(required = false)
	private SearchResultService searchResultService;

	@Value("${MEDICINE_SEARCH_COUNT}")
	private Integer MEDICINE_SEARCH_COUNT;

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
	public List<MedicineListVo> getAllMedicine(@RequestBody RequestForm form) throws ServiceException, Exception {
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
	public List<MedicineListVo> getAllMedicineByKey(@RequestBody RequestForm form) throws ServiceException, Exception {
		if (StringUtil.isEmpty(form.getQuest_id()) || StringUtil.isEmpty(form.getContent()))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);

		List<MedicineListVo> result = searchResultService.searchMedicine(form.getContent(), form.getQuest_id(),
				form.getRow(), MEDICINE_SEARCH_COUNT);

		if (null == result)
			throw new ServiceException(ResponseBuilder.ERROR_MEDICINE_NOT_FOUND);
		return result;
	}

	// 获得药品详细信息
	@RequestMapping("/getMedicineDetail")
	@ResponseBody
	public MedicineDetailVo getMedicineDetail(@RequestBody RequestForm form,
			@RequestHeader(value = "User", required = false) String user) throws ServiceException {
		if (StringUtil.isEmpty(form.getQuest_id()) || StringUtil.isEmpty(form.getContent()))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		MedicineDetailVo result = medicineService.getMedicineDetail(form);
		if (null == result)
			throw new ServiceException(ResponseBuilder.ERROR_MEDICINE_NOT_FOUND);

		if (StringUtils.isNotBlank(user))
			result.setCollect(collectService.isCollect("MEDICINE", result.getId(), user));

		return result;
	}

}
