import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.muyou.common.util.HttpUtil;
import com.muyou.common.util.WeatherUtils;

public class testWeather {

	@Test
	public void testWeather()
	{
		String string = WeatherUtils.CITY_MAP.get("温州");
		
		String url = "http://weather.123.duba.net/static/weather_info/"+string+".html";
		
		System.out.println(url);
		
		System.out.println(HttpUtil.sendGet(url));
	}
	
	@Test
	public void testData()
	{
		String jsonIp = HttpUtil.sendGet(WeatherUtils.LOC_URL);
		String ip = jsonIp.substring(jsonIp.indexOf(":")+3, jsonIp.indexOf(",")-1);
		
		System.out.println(ip);
		
		String jsonCity = HttpUtil.sendGet(WeatherUtils.CITY_URL+ip);
		
		JSONObject jsonStr = JSONObject.parseObject(jsonCity);
		
		String cityName = (String) jsonStr.getJSONObject("data").get("city");
		
		System.out.println(cityName);
		
		String jsonWeather = HttpUtil.sendGet(WeatherUtils.WEA_URL+WeatherUtils.CITY_MAP.get(cityName)+".html");
		
		jsonWeather = jsonWeather.substring(jsonWeather.indexOf("(") + 1, jsonWeather.indexOf(")"));
		
		System.out.println(jsonWeather);
		
		jsonStr = JSONObject.parseObject(jsonWeather);
		
		System.out.println(jsonStr);
		
		jsonStr = (JSONObject) jsonStr.get("weatherinfo");
		
		System.out.println("**"+jsonStr);
		
		System.out.println(jsonStr.get("city"));
		System.out.println(jsonStr.get("update_time"));
		System.out.println(jsonStr.get("pm"));
		System.out.println(jsonStr.get("temp"));
		System.out.println(jsonStr.get("wd"));
		System.out.println(jsonStr.get("ws"));
		System.out.println(jsonStr.get("pm-level"));
		System.out.println(jsonStr.get("sd"));
		System.out.println(jsonStr.get("weather1"));
		System.out.println(jsonStr.get("week"));
		System.out.println(jsonStr.get("temp1"));
		
		
		
		
	}
	
	
	
	
}
