package com.muyou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.muyou.common.pojo.Result;
import com.muyou.common.util.ResultUtil;
import com.muyou.service.RedisService;

@Controller
@RequestMapping(value = "/redis")
public class RedisController {

	@Autowired
	private RedisService redisService;

	@RequestMapping(value = "/lecture/list", method = RequestMethod.GET)
	@ResponseBody
	public Result<Object> getLectureRedis() {
		String json = redisService.getLectureRedis();
		return new ResultUtil<Object>().setData(json);
	}

	@RequestMapping(value = "/lecture/update", method = RequestMethod.GET)
	@ResponseBody
	public Result<Object> updateLectureRedis() {
		redisService.updateLectureRedis();
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/timeTable/list", method = RequestMethod.GET)
	@ResponseBody
	public Result<Object> getTimeTableRedis() {
		String json = redisService.getTimeTableRedis();
		return new ResultUtil<Object>().setData(json);
	}

	@RequestMapping(value = "/timeTable/update", method = RequestMethod.GET)
	@ResponseBody
	public Result<Object> updateTimeTableRedis() {
		redisService.updateTimeTableRedis();
		return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/disease/list", method = RequestMethod.GET)
	@ResponseBody
	public Result<Object> getDiseaseRedis() {
		String json = redisService.getDiseaseRedis();
		return new ResultUtil<Object>().setData(json);
	}

	@RequestMapping(value = "/disease/update", method = RequestMethod.GET)
	@ResponseBody
	public Result<Object> updateDiseaseRedis() {
		redisService.updateDiseaseRedis();
		return new ResultUtil<Object>().setData(null);
	}
	
	@RequestMapping(value = "/medicine/list", method = RequestMethod.GET)
	@ResponseBody
	public Result<Object> getMedicineRedis() {
		String json = redisService.getMedicineRedis();
		return new ResultUtil<Object>().setData(json);
	}

	@RequestMapping(value = "/medicine/update", method = RequestMethod.GET)
	@ResponseBody
	public Result<Object> updateMedicineRedis() {
		redisService.updateMedicineRedis();
		return new ResultUtil<Object>().setData(null);
	}
	
	@RequestMapping(value = "/recipes/list", method = RequestMethod.GET)
	@ResponseBody
	public Result<Object> getRecipesRedis() {
		String json = redisService.getRecipesRedis();
		return new ResultUtil<Object>().setData(json);
	}

	@RequestMapping(value = "/recipes/update", method = RequestMethod.GET)
	@ResponseBody
	public Result<Object> updateRecipesRedis() {
		redisService.updateRecipesRedis();
		return new ResultUtil<Object>().setData(null);
	}
	
	@RequestMapping(value = "/shiro/list", method = RequestMethod.GET)
	@ResponseBody
	public Result<Object> getShiroRedis() {
		String json = redisService.getShiroRedis();
		return new ResultUtil<Object>().setData(json);
	}

	@RequestMapping(value = "/shiro/update", method = RequestMethod.GET)
	@ResponseBody
	public Result<Object> updateShiroRedis() {
		redisService.updateShiroRedis();
		return new ResultUtil<Object>().setData(null);
	}
	
	/*@RequestMapping(value = "/log/list", method = RequestMethod.GET)
	@ResponseBody
	public Result<Object> getLogRedis() {
		String json = redisService.getLogRedis();
		return new ResultUtil<Object>().setData(json);
	}

	@RequestMapping(value = "/log/update", method = RequestMethod.GET)
	@ResponseBody
	public Result<Object> updateLogRedis() {
		redisService.updateLogRedis();
		return new ResultUtil<Object>().setData(null);
	}*/
}
