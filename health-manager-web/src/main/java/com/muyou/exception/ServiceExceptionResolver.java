package com.muyou.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.muyou.common.exception.GlobalException;
import com.muyou.common.pojo.ResponseBuilder;
import com.muyou.common.util.JsonUtils;
import com.muyou.common.util.ResultUtil;

public class ServiceExceptionResolver implements HandlerExceptionResolver {

	@Override
	@ResponseBody
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ModelAndView model = new ModelAndView();
		GlobalException globaException;
		if (ex instanceof GlobalException) {
			globaException = (GlobalException) ex;
		} else {
			System.out.println("ex.getCause()" + ex.getCause());
			System.out.println("ex.getMessage()" + ex.getMessage());
			globaException = new GlobalException("Bad Server");
		}
		/* 使用response返回 */
		response.setStatus(ResponseBuilder.STATUS_BAD_REQUEST); // 设置状态码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache, must-revalidate");
		try {
			response.getWriter()
					.write(JsonUtils.objectToJson(new ResultUtil<>().setErrorMsg(globaException.getMessage())));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

}
