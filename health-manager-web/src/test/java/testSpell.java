import java.io.File;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.muyou.common.util.DateUtil;
import com.muyou.common.util.HttpUtil;
import com.muyou.common.util.StringUtil;
import com.muyou.common.util.WeatherUtils;

public class testSpell {

	@Test
	public void testSpell() {
		String[] str = { "肺炎", "肺大泡", "肺水肿", "非典", "肺转移瘤", "肺炎性假瘤", "肺隐球菌病", "肺癌", "镉中毒", "感冒", "过敏性休克",
				"Goodpasture综合征", "汞中毒", "呼吸性细支气管相关的间质性肺疾病", "呼吸道合胞病毒肺炎", "减压病", "急性呼吸衰竭", "军团病", "结核性脓胸", "金黄色葡萄球菌肺炎",
				"咖啡因与氨茶碱中毒", "卡氏肺囊虫肺炎", "咯血", "硫化氢中毒", "老年肺炎", "免疫缺陷者肺炎", "镍中毒", "铍中毒", "缺血缺氧性脑病", "气胸", "球孢子菌病", "禽流感",
				"迁延性肺嗜酸粒细胞浸润症", "气管肿瘤", "气管先天性疾病", "气管、支气管狭窄", "乳酸性酸中毒", "热带性肺嗜酸粒细胞浸润症", "手术后和创伤后肺炎", "鼾症", "特发性肺纤维化",
				"特发性阻塞性细支气管炎伴机化性肺炎", "特发性含铁血黄素沉着症", "小儿结核病", "新生儿呼吸窘迫综合征", "吸入性损伤", "小儿金黄色葡萄球菌肺炎", "小儿急性支气管炎", "小儿急性喉炎",
				"矽肺", "新生儿窒息", "小儿支原体肺炎", "新生儿肺不张", "小儿感冒", "哮喘", "小儿哮喘", "小儿肺炎", "新生儿呼吸暂停", "先天性肺囊肿", "休克型肺炎", "吸入性肺炎",
				"腺病毒肺炎", "哮喘性肺嗜酸粒细胞浸润症", "细菌性肺炎", "新生儿湿肺", "胸壁结核", "乙醚中毒", "衣原体肺炎", "羊水与胎粪吸入综合征", "阻塞性肺气肿", "支原体肺炎",
				"支气管扩张", "支气管肺炎", "病毒性心肌炎", "病态窦房结综合征", "川崎病", "穿透性心脏外伤", "窦性停搏", "单心室", "单心房", "动脉硬化", "动脉导管未闭",
				"窦性心动过速", "动脉瘤", "二尖瓣关闭不全", "二尖瓣狭窄", "二尖瓣脱垂综合征", "二尖瓣环钙化", "肺性脑病1", "风湿性二尖瓣狭窄", "风湿性二尖瓣关闭不全",
				"肥厚型梗阻性心肌病", "房室管畸形", "法洛四联症", "风湿热", "肺动脉口狭窄", "肥厚型心肌病", "房室传导阻滞", "肺动静脉瘘", "高血压危象", "高血压", "冠状动脉终止异常",
				"冠状动脉异位起源", "感染性心内膜炎", "冠心病", "后天性三尖瓣关闭不全", "后天性动静脉瘘" };

		for (String string : str) {
			System.out.println(StringUtil.getFirstSpell(string));
		}
	}

	@Test
	public void testIp() {
		String jsonIp = HttpUtil.sendGet(WeatherUtils.LOC_URL);
		// System.out.println(jsonIp);
		if (StringUtils.isNotBlank(jsonIp)) {
			// 请求IP
			System.out.println(jsonIp.substring(jsonIp.lastIndexOf(":") + 3, jsonIp.indexOf("}") - 1));
		}
	}

	@Test
	public void testDate() {
		long dateStr = Long.valueOf("1584098617000");
		Date date = new Date(dateStr);
		String stringDate = DateUtil.getStringDate(date);
		System.out.println(stringDate);
	}
	
	@Test
	public void testDelFile() {
		File file = new File("H:\\FreeMaeker\\9531.html");
		if (!file.isDirectory()) { 	// 表示该文件不是文件夹
			file.delete();
		}
	}

}
