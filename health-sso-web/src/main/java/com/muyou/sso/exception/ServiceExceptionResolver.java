package com.muyou.sso.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.dubbo.common.json.JSONObject;
import com.muyou.common.exception.ServiceException;
import com.muyou.common.pojo.ResponseBuilder;
import com.muyou.common.util.JsonUtil;

public class ServiceExceptionResolver implements HandlerExceptionResolver{

	@Override
	@ResponseBody
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ModelAndView model = new ModelAndView();
		ServiceException serviceException ;
		ex.printStackTrace();
		if (ex instanceof ServiceException) {
			serviceException = (ServiceException)ex;
		}else
		{
			System.out.println("ex.getCause()"+ex.getCause());
			System.out.println("ex.getMessage()"+ex.getMessage());
			serviceException = new ServiceException(ResponseBuilder.ERROR_BAD_SERVER);
		}
	    /*  使用response返回    */  
        response.setStatus(ResponseBuilder.STATUS_BAD_REQUEST); //设置状态码  
        response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache, must-revalidate");  
        try {  
            response.getWriter().write(JsonUtil.toJson(serviceException.getResponseBuilder()));  
        } catch (IOException e) {  
        }  

		return model;
	}

}
