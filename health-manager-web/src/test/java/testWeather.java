import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.muyou.common.util.HttpUtil;
import com.muyou.common.util.ResultUtil;
import com.muyou.common.util.WeatherUtils;
import com.muyou.service.pojo.Weather;

public class testWeather {

	@Test
	public void testWeather() {
		try {
			// 获得用户所属的ip地址
			String jsonIp = HttpUtil.sendGet(WeatherUtils.LOC_URL);
			String ip = jsonIp.substring(jsonIp.indexOf(":") + 3, jsonIp.indexOf(",") - 1);
			System.out.println(ip);

			String cityName = jsonIp.substring(jsonIp.indexOf("省") + 1, jsonIp.indexOf("市"));
			System.out.println(cityName);

			// 根据用户ip地址，获得城市名称
			// String jsonCity = HttpUtil.sendGet(WeatherUtils.CITY_URL1 + ip);
			// JSONObject jsonStr = JSONObject.parseObject(jsonCity);
			// String cityName = (String) jsonStr.getJSONObject("data").get("city");
			// System.out.println(cityName);
			// 根据城市名称，获得对应城市编码，获得天气信息
			String jsonWeather = HttpUtil.sendGet(WeatherUtils.WEA_URL + WeatherUtils.CITY_MAP.get(cityName) + ".html");
			jsonWeather = jsonWeather.substring(jsonWeather.indexOf("(") + 1, jsonWeather.indexOf(")"));

			JSONObject root = JSONObject.parseObject(jsonWeather);
			JSONObject jsonStr = (JSONObject) root.get("weatherinfo");
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
			System.out.println(weather);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testData() {
		String jsonIp = HttpUtil.sendGet(WeatherUtils.LOC_URL);
		String ip = jsonIp.substring(jsonIp.indexOf(":") + 3, jsonIp.indexOf(",") - 1);

		System.out.println(ip);

		String jsonCity = HttpUtil.sendGet(WeatherUtils.CITY_URL + ip);

		JSONObject jsonStr = JSONObject.parseObject(jsonCity);

		String cityName = (String) jsonStr.getJSONObject("data").get("city");

		System.out.println(cityName);

		String jsonWeather = HttpUtil.sendGet(WeatherUtils.WEA_URL + WeatherUtils.CITY_MAP.get(cityName) + ".html");

		jsonWeather = jsonWeather.substring(jsonWeather.indexOf("(") + 1, jsonWeather.indexOf(")"));

		System.out.println(jsonWeather);

		jsonStr = JSONObject.parseObject(jsonWeather);

		System.out.println(jsonStr);

		jsonStr = (JSONObject) jsonStr.get("weatherinfo");

		System.out.println("**" + jsonStr);

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
