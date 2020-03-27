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
import com.muyou.front.service.DiseaseService;
import com.muyou.front.vo.DiseaseDetailVo;
import com.muyou.front.vo.DiseaseSortListVo;
import com.muyou.search.service.SearchResultService;
import com.muyou.sso.service.CollectService;
import com.muyou.vo.DiseaseSortVo;

@Controller
@RequestMapping("/disease")
public class DiseaseController {

	@Autowired
	private DiseaseService diseaseService;

	@Autowired
	private CollectService collectService;

	@Autowired
	private SearchResultService searchResultService;

	@Value("${DISEASE_SEARCH_COUNT}")
	private Integer DISEASE_SEARCH_COUNT;
	
	// 疾病分类
	@RequestMapping("/getDiseaseSortList")
	@ResponseBody
	public List<DiseaseSortListVo> getDiseaseSortList(@RequestBody RequestForm requestForm) throws ServiceException {
		if (StringUtil.isEmpty(requestForm.getQuest_id()))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);

		// 封装数据
		List<DiseaseSortListVo> result = diseaseService.getDiseaseSortList(requestForm);

		if (null == result)
			throw new ServiceException(ResponseBuilder.ERROR_DATA_LOSE);
		return result;
	}

	// 分类疾病
	@RequestMapping("/getDiseaseSort")
	@ResponseBody
	public List<DiseaseSortVo> getDiseaseSort(@RequestBody RequestForm form) throws ServiceException, Exception {
		if (StringUtil.isEmpty(form.getQuest_id()) || StringUtil.isEmpty(form.getContent()))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		List<DiseaseSortVo> result = diseaseService.getDiseaseSort(form);
		if (null == result)
			throw new ServiceException(ResponseBuilder.ERROR_DISEASE_NOT_FOUND);
		return result;
	}
	
	
	//疾病查询
	@RequestMapping("/searchDisease")
	@ResponseBody
	public List<DiseaseSortVo> searchDisease(@RequestBody RequestForm form) throws ServiceException, Exception {
		
		System.out.println("*****");
		
		if (StringUtil.isEmpty(form.getQuest_id()) || StringUtil.isEmpty(form.getContent()))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		List<DiseaseSortVo> result = searchResultService.searchDisease(form.getContent(), form.getQuest_id(),
				form.getRow(), DISEASE_SEARCH_COUNT);
		if (null == result)
			throw new ServiceException(ResponseBuilder.ERROR_DISEASE_NOT_FOUND);
		return result;
	}

	// 疾病详细
	@RequestMapping("/getDiseaseDetail")
	@ResponseBody
	public DiseaseDetailVo getDiseaseDetail(@RequestBody RequestForm requestForm,
			@RequestHeader(value = "User", required = false) String user) throws ServiceException {
		if (StringUtil.isEmpty(requestForm.getQuest_id()))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		DiseaseDetailVo result = diseaseService.getDiseaseDetail(requestForm);
		if (null == result)
			throw new ServiceException(ResponseBuilder.ERROR_DISEASE_NOT_FOUND);

		if (StringUtils.isNotBlank(user))
			result.setCollect(collectService.isCollect("DISEASE", result.getId(), user));

		return result;
	}
}
