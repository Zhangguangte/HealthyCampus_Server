package com.muyou.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.muyou.common.pojo.DataTablesResult;
import com.muyou.common.pojo.Result;
import com.muyou.common.util.CookieUtils;
import com.muyou.common.util.HttpUtil;
import com.muyou.common.util.ResultUtil;
import com.muyou.common.util.WeatherUtils;
import com.muyou.pojo.TbBase;
import com.muyou.pojo.TbShiroFilter;
import com.muyou.pojo.TbUser;
import com.muyou.service.SystemService;
import com.muyou.service.pojo.Weather;

@Controller
@RequestMapping("/system")
public class SystemController {

	@Value("${PAGE_EXPIRE}")
	private int PAGE_EXPIRE;

	@Value("${PAGE_BROWSE}")
	private String PAGE_BROWSE;

	@Autowired
	private SystemService systemService;

	@RequestMapping(value = "/shiro/list", method = RequestMethod.GET)
	@ResponseBody
	public DataTablesResult getShiroList(@ModelAttribute TbUser tbUser) {
		DataTablesResult result = new DataTablesResult();
		List<TbShiroFilter> list = systemService.getShiroFilter();

		if (null == list) {
			result.setSuccess(false);
		} else {
			result.setSuccess(true);
		}

		result.setData(list);
		return result;
	}

	@RequestMapping(value = "/shiro/count", method = RequestMethod.GET)
	@ResponseBody
	public Result<Integer> getUserCount() {
		return new ResultUtil<Integer>().setData(systemService.countShiroFilter());
	}

	@RequestMapping(value = "/shiro/add", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> addShiro(@ModelAttribute TbShiroFilter tbShiroFilter) {
		int result = systemService.addShiroFilter(tbShiroFilter);
		if (1 == result)
			return new ResultUtil<Object>().setData(null);
		return new ResultUtil<Object>().setErrorMsg("添加shiro过滤链失败");
	}
	
	@RequestMapping(value = "/shiro/del/{ids}",method = RequestMethod.DELETE)
	@ResponseBody
    public Result<Object> delShiro(@PathVariable int[] ids){
        for(int id:ids){
            systemService.deleteShiroFilter(id);
        }
        return new ResultUtil<Object>().setData(null);
    }
	

	@RequestMapping(value = "/shiro/update", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> updateShiro(@ModelAttribute TbShiroFilter tbShiroFilter) {
		int result = systemService.updateShiroFilter(tbShiroFilter);
		if (1 == result)
			return new ResultUtil<Object>().setData(null);
		return new ResultUtil<Object>().setErrorMsg("更新shiro过滤链失败");
	}

	@RequestMapping(value = "/browseCount", method = RequestMethod.GET)
	@ResponseBody
	public DataTablesResult getBrowseCount(HttpServletRequest request, HttpServletResponse response) {

		String cookie = CookieUtils.getCookieValue(request, PAGE_BROWSE);

		DataTablesResult result = null;
		if (StringUtils.isNotBlank(cookie))
			result = systemService.getBrowseCount(false);
		else
			result = systemService.getBrowseCount(true);

		CookieUtils.setCookie(request, response, PAGE_BROWSE, "1", PAGE_EXPIRE);

		return result;
	}

	@RequestMapping(value = "/base", method = RequestMethod.GET)
	@ResponseBody
	public Object getBase(String callback) {

		if (StringUtils.isNotBlank(callback)) {
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(
					new ResultUtil<Object>().setData(systemService.getBase()));
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		}

		return systemService.getBase();
	}

	@RequestMapping(value = "/base/update", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> updateBase(@ModelAttribute TbBase tbBase) {
		int result = systemService.updateBase(tbBase);
		if (1 != result)
			return new ResultUtil<Object>().setErrorMsg("更新基础设置失败");
		else
			return new ResultUtil<Object>().setData(null);
	}

	@RequestMapping(value = "/weather", method = RequestMethod.GET)
	@ResponseBody
	public Result<Object> getWeather(HttpServletRequest request) {

		try {
			// 获得用户所属的ip地址
			String jsonIp = HttpUtil.sendGet(WeatherUtils.LOC_URL);
			String ip = jsonIp.substring(jsonIp.indexOf(":") + 3, jsonIp.indexOf(",") - 1);
			// System.out.println(ip);
			// 根据用户ip地址，获得城市名称
			String jsonCity = HttpUtil.sendGet(WeatherUtils.CITY_URL + ip);
			JSONObject jsonStr = JSONObject.parseObject(jsonCity);
			String cityName = (String) jsonStr.getJSONObject("data").get("city");
			// System.out.println(cityName);
			// 根据城市名称，获得对应城市编码，获得天气信息
			String jsonWeather = HttpUtil.sendGet(WeatherUtils.WEA_URL + WeatherUtils.CITY_MAP.get(cityName) + ".html");
			jsonWeather = jsonWeather.substring(jsonWeather.indexOf("(") + 1, jsonWeather.indexOf(")"));

			JSONObject root = JSONObject.parseObject(jsonWeather);
			jsonStr = (JSONObject) root.get("weatherinfo");
			Weather weather = new Weather();
			weather.setCity(jsonStr.getString("city"));
			weather.setUpdateTime(root.getString("update_time"));
			weather.setPm(jsonStr.getString("pm"));
			weather.setTemp(jsonStr.getString("temp"));
			weather.setWd(jsonStr.getString("wd"));
			weather.setWs(jsonStr.getString("ws"));
			weather.setPm_level(jsonStr.getString("pm-level"));
			weather.setSd(jsonStr.getString("sd"));
			weather.setWeather1(jsonStr.getString("weather1"));
			weather.setWeek(jsonStr.getString("week"));
			weather.setTemp1(jsonStr.getString("temp1"));
			return new ResultUtil<Object>().setData(weather);
		} catch (Exception e) {
			// e.printStackTrace();
			return new ResultUtil<Object>().setErrorMsg("无法获取您的IP，天气信息获取失败");
		}
	}

	
	@RequestMapping(value = "/log",method = RequestMethod.GET)
	@ResponseBody
    public DataTablesResult getLog(int draw, int start, int length,@RequestParam("search[value]") String search,
                                   @RequestParam("order[0][column]") int orderCol, @RequestParam("order[0][dir]") String orderDir){
		//获取客户端需要排序的列
        String[] cols = {"checkbox","id", "name","type", "url", "requestType", "requestParam", "user", "ip", "ipInfo","time","createDate"};
        String orderColumn = cols[orderCol];
        //默认排序列
        if(orderColumn == null) {
            orderColumn = "createDate";
        }
        //获取排序方式 默认为desc(asc)
        if(orderDir == null) {
            orderDir = "desc";
        }
        DataTablesResult result= systemService.getLogList(draw,start,length,search,orderColumn,orderDir);
        return result;
    }
	
	@RequestMapping(value = "/log/count",method = RequestMethod.GET)
	@ResponseBody
    public Result<Object> countLog(){
        return new ResultUtil<Object>().setData(systemService.countLog());
    }
	
	@RequestMapping(value = "/log/del/{ids}",method = RequestMethod.DELETE)
	@ResponseBody
    public Result<Object> delLog(@PathVariable int[] ids){
        for(int id:ids){
            systemService.deleteLog(id);
        }
        return new ResultUtil<Object>().setData(null);
    }
	
	
	
}
