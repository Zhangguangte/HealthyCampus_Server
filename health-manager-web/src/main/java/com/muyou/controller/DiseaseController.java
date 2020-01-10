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
import com.muyou.common.vo.DiseaseDetailVo;
import com.muyou.common.vo.DiseaseSortListVo;
import com.muyou.common.vo.DiseaseSortVo;
import com.muyou.common.vo.ResponseBuilder;
import com.muyou.service.DiseaseService;

@Controller
@RequestMapping("/disease")
public class DiseaseController {

	@Autowired
	private DiseaseService diseaseService;

	// 疾病分类
	@RequestMapping("/getDiseaseSortList")
	@ResponseBody
	public List<DiseaseSortListVo> getDiseaseSortList(@RequestBody RequestForm requestForm) throws ServiceException {
		if(StringUtil.isEmpty(requestForm.getQuest_id()))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		List<DiseaseSortListVo> result = diseaseService.getDiseaseSortList(requestForm);
		if (null == result)
			throw new ServiceException(ResponseBuilder.ERROR_DATA_LOSE);
		return result;
	}

	// 分类疾病
	@RequestMapping("/getDiseaseSort")
	@ResponseBody
	public List<DiseaseSortVo> getDiseaseSort(@RequestBody RequestForm requestForm) throws ServiceException{

		if(StringUtil.isEmpty(requestForm.getQuest_id()) || StringUtil.isEmpty(requestForm.getContent()))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		List<DiseaseSortVo> result = diseaseService.getDiseaseSort(requestForm);
		if (null == result)
			throw new ServiceException(ResponseBuilder.ERROR_DISEASE_NOT_FOUND);
		return result;
	}

	// 疾病详细
	@RequestMapping("/getDiseaseDetail")
	@ResponseBody
	public DiseaseDetailVo getDiseaseDetail(@RequestBody RequestForm requestForm) throws ServiceException{
		if(StringUtil.isEmpty(requestForm.getQuest_id()))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		DiseaseDetailVo result = diseaseService.getDiseaseDetail(requestForm);
		if (null == result)
			throw new ServiceException(ResponseBuilder.ERROR_DISEASE_NOT_FOUND);
		return result;
	}
}
