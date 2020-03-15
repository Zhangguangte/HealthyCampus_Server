package com.muyou.exception;

import java.net.BindException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.muyou.common.exception.GlobalException;
import com.muyou.common.pojo.Result;
import com.muyou.common.util.ResultUtil;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(BindException.class)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Result<Object> bindExceptionHandler(BindException e) {
		System.out.println("3" + e);
		String errorMsg = "请求数据校验不合法: ";
		if (e != null) {
			errorMsg = e.getMessage();
			log.warn(errorMsg);
		}
		return new ResultUtil<>().setErrorMsg(errorMsg);
	}

	@ResponseStatus(value = HttpStatus.OK)
	@ExceptionHandler(GlobalException.class)
	@ResponseBody
	public Result<Object> handleGlobalException(GlobalException e) {
		System.out.println("2" + e);
		String errorMsg = "Global exception: ";
		if (e != null) {
			errorMsg = e.getMsg();
			log.warn(e.getMessage());
		}
		return new ResultUtil<>().setErrorMsg(errorMsg);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Result<Object> handleException(Exception e) {

		System.out.println("1" + e);
		e.printStackTrace();
		String errorMsg = "Exception: ";
		if (e != null) {
			log.warn(e.getMessage());
			if (e.getMessage() != null && e.getMessage().contains("Maximum upload size")) {
				errorMsg = "上传文件大小超过5MB限制";
			} else if (e.getMessage().contains("GlobalException")) {
				errorMsg = e.getMessage();
				errorMsg = StringUtils.substringAfter(errorMsg, "GlobalException:");
				errorMsg = StringUtils.substringBefore(errorMsg, "\n");
			} else {
				errorMsg = e.getMessage();
			}
		}
		return new ResultUtil<>().setErrorMsg(errorMsg);
	}

}
