package com.muyou.front.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.muyou.common.form.RequestForm;
import com.muyou.common.redis.JedisClient;
import com.muyou.common.util.JsonUtils;
import com.muyou.front.service.DiseaseService;
import com.muyou.front.vo.DiseaseDetailVo;
import com.muyou.front.vo.DiseaseSortListVo;
import com.muyou.mapper.TbClassifyMapper;
import com.muyou.mapper.TbDiseaseMapper;
import com.muyou.pojo.TbClassify;
import com.muyou.pojo.TbClassifyExample;
import com.muyou.pojo.TbDisease;
import com.muyou.pojo.TbDiseaseExample;
import com.muyou.pojo.TbDiseaseExample.Criteria;

@Service
public class DiseaseServiceImpl implements DiseaseService {

	@Autowired
	private TbDiseaseMapper diseaseMapper;

	@Autowired
	private TbClassifyMapper classifyMapper;

	@Autowired
	private JedisClient jedisClient;

	@Value("${DISEASE_CLASS}")
	private String DISEASE_CLASS;

	@Value("${DISEASE_CLASS_LIST}")
	private String DISEASE_CLASS_LIST;

	@Value("${DISEASE_DETAIL}")
	private String DISEASE_DETAIL;

	@Value("${DISEASE_EXPIRE}")
	private Integer DISEASE_EXPIRE;

	// 疾病分类
	@Override
	public List<DiseaseSortListVo> getDiseaseSortList(RequestForm requestForm) {
		try {
			String json = jedisClient.hget(DISEASE_CLASS, requestForm.getQuest_id());
			if (StringUtils.isNotBlank(json)) {
				return (List<DiseaseSortListVo>) JsonUtils.jsonToList(json, DiseaseSortListVo.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		//查询数据
		TbClassifyExample example = new TbClassifyExample();
		TbClassifyExample.Criteria criteria = example.createCriteria();
		criteria.andTypeEqualTo(Integer.valueOf(requestForm.getQuest_id()));
		List<TbClassify> list = classifyMapper.selectByExample(example);
		
		//封装数据
		List<DiseaseSortListVo> result = new LinkedList<DiseaseSortListVo>();
		for (TbClassify classify : list) {
			result.add(new DiseaseSortListVo(classify));	
		}

		try {
			jedisClient.hset(DISEASE_CLASS, requestForm.getQuest_id(), JsonUtils.objectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// // 分类疾病
	// @Override
	// public List<DiseaseSortVo> getDiseaseSort(RequestForm requestForm) {
	//
	// try {
	// List<String> json = jedisClient.lrange(
	// DISEASE_CLASS_LIST + ":" + requestForm.getQuest_id() + ":" +
	// requestForm.getContent(),
	// requestForm.getRow() * 15, (requestForm.getRow() + 1) * 15 - 1);
	//
	// if (null != json && json.size() > 0) {
	// List<DiseaseSortVo> list = new LinkedList<>();
	// for (String string : json) {
	// list.add(JsonUtils.jsonToPojo(string, DiseaseSortVo.class));
	// }
	// return list;
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// TbDiseaseExample diseaseExample = new TbDiseaseExample();
	// diseaseExample.setOrderByClause("id");
	// diseaseExample.setRow(requestForm.getRow());
	// diseaseExample.setSize(15);
	// TbDiseaseExample.Criteria criteria = diseaseExample.createCriteria();
	//
	// // 0.代表部位;1.代表科室;其余代表关键字
	// if ("0".equals(requestForm.getQuest_id())) { // 部位
	// criteria.andDiseasePartLike(requestForm.getContent());
	// } else if ("1".equals(requestForm.getQuest_id())) { // 科室
	// criteria.andCureDepartLike(requestForm.getContent());
	// } else { // 关键字
	// criteria.andDiseaseNameLike(requestForm.getContent());
	// }
	// List<TbDisease> diseases = diseaseMapper.selectByExample(diseaseExample);
	// if (null == diseases || diseases.size() == 0)
	// return null;
	// List<DiseaseSortVo> result = new LinkedList<DiseaseSortVo>();
	// for (TbDisease disease : diseases) {
	// result.add(new DiseaseSortVo(disease));
	// }
	//
	// try {
	// for (DiseaseSortVo item : result) {
	// jedisClient.rpush(DISEASE_CLASS_LIST + ":" + requestForm.getQuest_id() + ":"
	// + requestForm.getContent(),
	// JsonUtils.objectToJson(item));
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return result;
	// }

	// 疾病详细
	@Override
	public DiseaseDetailVo getDiseaseDetail(RequestForm requestForm) {
		try {
			String json = jedisClient.hget(DISEASE_DETAIL + ":" + requestForm.getQuest_id(), requestForm.getContent());
			if (StringUtils.isNotBlank(json)) {
				return JsonUtils.jsonToPojo(json, DiseaseDetailVo.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbDisease disease = null;
		if ("ID".equals(requestForm.getQuest_id())) {
			disease = diseaseMapper.selectByPrimaryKey(Integer.parseInt(requestForm.getContent()));
			if (null == disease)
				return null;
		} else if ("NAME".equals(requestForm.getQuest_id())) {
			TbDiseaseExample example = new TbDiseaseExample();
			Criteria criteria = example.createCriteria();
			criteria.andNameEqualTo(requestForm.getContent());
			List<TbDisease> diseases = diseaseMapper.selectByExample(example);
			if (null == diseases || diseases.size() == 0)
				return null;
			else
				disease = diseases.get(0);
		}
		DiseaseDetailVo detailVo = new DiseaseDetailVo(disease);

		try {
			jedisClient.hset(DISEASE_DETAIL + ":" + requestForm.getQuest_id(), requestForm.getContent(),
					JsonUtils.objectToJson(detailVo));
			jedisClient.expire(DISEASE_DETAIL + ":" + requestForm.getQuest_id(), DISEASE_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return detailVo;
	}

}
