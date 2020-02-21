package com.muyou.sso.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.muyou.common.util.JsonUtils;
import com.muyou.sso.service.TokenService;

@Controller
public class TokenController {

	@Autowired
	private TokenService tokenService;

	@RequestMapping(value="/admin/token/{token}",method = RequestMethod.GET)
	@ResponseBody
	public Object getUserByToken(@PathVariable String token, String callback) {
		
		if (StringUtils.isNotBlank(callback)) {
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(tokenService.getAdminByToken(token));
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		}
		return JsonUtils.objectToJson(tokenService.getAdminByToken(token));
		
	}
	
	
}
