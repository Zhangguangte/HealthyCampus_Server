package com.muyou.front.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.muyou.common.constant.ItemConstant;
import com.muyou.common.constant.RedisConstant;
import com.muyou.common.form.RequestForm;
import com.muyou.common.redis.JedisClient;
import com.muyou.common.util.JsonUtils;
import com.muyou.front.service.MedicineService;
import com.muyou.front.vo.MedicineDetailVo;
import com.muyou.front.vo.MedicineVo;
import com.muyou.mapper.TbCateMapper;
import com.muyou.mapper.TbClassifyMapper;
import com.muyou.mapper.TbMedicineMapper;
import com.muyou.pojo.TbCate;
import com.muyou.pojo.TbCateExample;
import com.muyou.pojo.TbMedicine;
import com.muyou.vo.MedicineListVo;

@Service
public class MedicineServiceImpl implements MedicineService {

	@Autowired
	private TbClassifyMapper classifyMapper;

	@Autowired
	private TbMedicineMapper medicineMapper;

	@Autowired
	private TbCateMapper cateMapper;

	@Autowired
	private JedisClient jedisClient;

	@Value("${MEDICINE_SEARCH}")
	private String MEDICINE_SEARCH;

	@Value("${MEDICINE_LIST}")
	private String MEDICINE_LIST;

	@Value("${MEDICINE_DETAIL}")
	private String MEDICINE_DETAIL;

	@Value("${CLASS_MED}")
	private Integer CLASS_MED;

	@Value("${MEDICINE_SEARCH_COUNT}")
	private Integer MEDICINE_SEARCH_COUNT;

	// 获得所有的分类
	@Override
	public List<MedicineVo> getClassify() {

		try {
			String json = jedisClient.get(RedisConstant.MEDICINE_CLASSIFY);
			if (StringUtils.isNotBlank(json)) {
				return JsonUtils.jsonToList(json, MedicineVo.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 获得药品子目录
		TbCateExample example = new TbCateExample();
		TbCateExample.Criteria criteria = example.createCriteria();
		criteria.andTypeEqualTo(ItemConstant.CATE_MEDICINE);
		criteria.andIsParentEqualTo(true);
		criteria.andStatusEqualTo(ItemConstant.OPEN);
		criteria.andParentIdEqualTo(ItemConstant.CATE_MEDICINE_PARENT);
		List<TbCate> cateList = cateMapper.selectByExample(example);

		// 获取子目录下的所有节点
		List<MedicineVo> result = new LinkedList<MedicineVo>();
		List<String> list;
		for (TbCate tbCate : cateList) {
			list = cateMapper.selectSubCateNameByPIdAndType(tbCate.getId(), ItemConstant.CATE_MEDICINE);
			// 封装数据
			result.add(new MedicineVo(tbCate.getName(), String.join(",", list)));
		}

		try {
			jedisClient.set(RedisConstant.MEDICINE_CLASSIFY, JsonUtils.objectToJson(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 获得所有的药品根据分类
	@Override
	public List<MedicineListVo> getAllMedicine(RequestForm requestForm) {
		try { // 根据分类名称
			List<String> json = jedisClient.lrange(MEDICINE_LIST + ":" + requestForm.getContent(),
					requestForm.getRow() * 15, (requestForm.getRow() + 1) * 15);
			if (null != json && json.size() > 0) {
				List<MedicineListVo> list = new LinkedList<>();
				for (String string : json) {
					list.add(JsonUtils.jsonToPojo(string, MedicineListVo.class));
				}
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 获得药品目录ID
		TbCateExample example = new TbCateExample();
		TbCateExample.Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(requestForm.getContent());
		criteria.andTypeEqualTo(ItemConstant.CATE_MEDICINE);
		criteria.andIsParentEqualTo(false);
		List<TbCate> cateList = cateMapper.selectByExample(example);
		if (null == cateList || cateList.size() < 1)
			return null;

		// 获得药品数据
		List<TbMedicine> list = medicineMapper.selectItemByClassify(cateList.get(0).getId(),
				requestForm.getRow() * MEDICINE_SEARCH_COUNT, MEDICINE_SEARCH_COUNT);
		if (null == list || list.size() == 0)
			return null;

		List<MedicineListVo> result = new LinkedList<MedicineListVo>();
		for (TbMedicine medicine : list) {
			if (null == medicine)
				continue;
			result.add(new MedicineListVo(medicine));
		}

		try {
			for (MedicineListVo item : result) {
				jedisClient.rpush(MEDICINE_LIST + ":" + requestForm.getContent(), JsonUtils.objectToJson(item));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	//
	// // 获得所有的药品根据关键字和范围
	// @Override
	// public List<MedicineListVo> getAllMedicineByKey(RequestForm requestForm) {
	//
	// try {
	// List<String> json = jedisClient.lrange(
	// MEDICINE_SEARCH + ":" + requestForm.getQuest_id() + ":" +
	// requestForm.getContent(),
	// requestForm.getRow() * 15, (requestForm.getRow() + 1) * 15);
	// if (null != json && json.size() > 0) {
	// List<MedicineListVo> list = new LinkedList<>();
	// for (String string : json) {
	// list.add(JsonUtils.jsonToPojo(string, MedicineListVo.class));
	// }
	// return list;
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// List<TbMedicine> list =
	// medicineMapper.getAllMedicineByKey(requestForm.getQuest_id(),
	// requestForm.getContent(),
	// requestForm.getRow() * 15);
	// if (null == list || list.size() == 0)
	// return null;
	// List<MedicineListVo> result = new LinkedList<MedicineListVo>();
	// for (TbMedicine medicine : list) {
	// result.add(new MedicineListVo(medicine));
	// }
	//
	// try {
	// for (MedicineListVo item : result) {
	// jedisClient.rpush(MEDICINE_SEARCH + ":" + requestForm.getQuest_id() + ":" +
	// requestForm.getContent(), JsonUtils.objectToJson(item));
	// jedisClient.expire(MEDICINE_SEARCH + ":" + requestForm.getQuest_id() + ":" +
	// requestForm.getContent(), 60*60*24*10);
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// return result;
	// }

	// 药品详细
	@Override
	public MedicineDetailVo getMedicineDetail(RequestForm requestForm) {
		try { // 根据分类名称
			String json = jedisClient.hget(MEDICINE_DETAIL, requestForm.getContent());
			if (StringUtils.isNotBlank(json)) {
				return JsonUtils.jsonToPojo(json, MedicineDetailVo.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbMedicine medicine = null;
		if ("ID".equals(requestForm.getQuest_id())) {
			medicine = medicineMapper.selectByPrimaryKey(Integer.parseInt(requestForm.getContent()));
		} else if ("NAME".equals(requestForm.getQuest_id())) {
			medicine = medicineMapper.getMedicineDetailByName(requestForm.getContent());
		} else if ("CODE".equals(requestForm.getQuest_id())) {
			medicine = medicineMapper.getMedicineDetailByCode(requestForm.getContent());
		}
		if (medicine == null) {
			return null;
		}
		MedicineDetailVo medicineDetailVo = new MedicineDetailVo(medicine);

		try {
			jedisClient.hset(MEDICINE_DETAIL, requestForm.getContent(), JsonUtils.objectToJson(medicineDetailVo));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return medicineDetailVo;
	}

}
