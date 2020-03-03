package com.muyou.front.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.muyou.common.exception.ServiceException;
import com.muyou.common.form.RequestForm;
import com.muyou.common.pojo.ResponseBuilder;
import com.muyou.common.util.StringUtil;
import com.muyou.front.service.LibraryService;
import com.muyou.front.vo.BookDetailVo;
import com.muyou.front.vo.BookVo;
import com.muyou.sso.service.CollectService;

@Controller
@RequestMapping("/service")
public class LibraryController {

	@Autowired
	private CollectService collectService;
	
	@Autowired
	private LibraryService libraryService;
	
	@RequestMapping("/library/searchBookDetail")
	@ResponseBody
	public BookDetailVo searchBookDetail(@RequestBody RequestForm requestForm,
			@RequestHeader(value = "User", required = false) String user) throws ServiceException {
		if (StringUtil.isEmpty(requestForm.getQuest_id()))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		BookDetailVo book = libraryService.searchBookDetail(requestForm);
		if (null == book)
			throw new ServiceException(ResponseBuilder.ERROR_DATA_LOSE);
		
		if (StringUtils.isNotBlank(user))
			book.setCollect(collectService.isCollect("LIBRARY", book.getId(), user));

		return book;
	}

	@RequestMapping("/library/searchBook")
	@ResponseBody
	public List<BookVo> searchBook(@RequestBody RequestForm requestForm) throws ServiceException {
		if (StringUtil.isEmpty(requestForm.getContent()))
			throw new ServiceException(ResponseBuilder.ERROR_INVALID_PARAMETER);
		List<BookVo> books = libraryService.searchBook(requestForm);
		if (null == books)
			throw new ServiceException(ResponseBuilder.ERROR_DATA_LOSE);
		return books;
	}
	
}
