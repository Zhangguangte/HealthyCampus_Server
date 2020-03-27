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
import com.muyou.front.service.DiseaseService;
import com.muyou.front.vo.DiseaseDetailVo;
import com.muyou.front.vo.DiseaseSortListVo;
import com.muyou.mapper.TbCateMapper;
import com.muyou.mapper.TbDiseaseMapper;
import com.muyou.pojo.TbCate;
import com.muyou.pojo.TbCateExample;
import com.muyou.pojo.TbDisease;
import com.muyou.pojo.TbDiseaseExample;
import com.muyou.pojo.TbDiseaseExample.Criteria;
import com.muyou.vo.DiseaseSortVo;

@Service
public class DiseaseServiceImpl implements DiseaseService {

	@Autowired
	private TbDiseaseMapper diseaseMapper;

	@Autowired
	private TbCateMapper cateMapper;

	@Autowired
	private JedisClient jedisClient;

	@Value("${DISEASE_CLASS_LIST}")
	private String DISEASE_CLASS_LIST;

	@Value("${DISEASE_DETAIL}")
	private String DISEASE_DETAIL;

	@Value("${DISEASE_EXPIRE}")
	private Integer DISEASE_EXPIRE;

	@Value("${DISEASE_SEARCH_COUNT}")
	private Integer DISEASE_SEARCH_COUNT;

	// 疾病分类
	@Override
	public List<DiseaseSortListVo> getDiseaseSortList(RequestForm requestForm) {
		try {
			String json = jedisClient.hget(RedisConstant.DISEASE_CLASSIFY, requestForm.getQuest_id());
			if (StringUtils.isNotBlank(json)) {
				return (List<DiseaseSortListVo>) JsonUtils.jsonToList(json, DiseaseSortListVo.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		//获得(科室/部位)子目录
		TbCateExample example = new TbCateExample();
		TbCateExample.Criteria criteria = example.createCriteria();
		criteria.andTypeEqualTo(ItemConstant.CATE_DISEASE);
		criteria.andIsParentEqualTo(true);
		criteria.andStatusEqualTo(ItemConstant.OPEN);
		if ("1".equals(requestForm.getQuest_id())) {
			criteria.andParentIdEqualTo(ItemConstant.CATE_DEPARTMENT);
		}else
			criteria.andParentIdEqualTo(ItemConstant.CATE_PART);
		List<TbCate> cateList = cateMapper.selectByExample(example);
		
		List<DiseaseSortListVo> result = new LinkedList<DiseaseSortListVo>();
		
		//获取子目录下的所有节点
		List<String> list;
		for (TbCate tbCate : cateList) {
			list = cateMapper.selectSubCateNameByPIdAndType(tbCate.getId(),ItemConstant.CATE_DISEASE);
			// 封装数据
			result.add(new DiseaseSortListVo(tbCate.getName(),list));
		}
		
		try {
			jedisClient.hset(RedisConstant.DISEASE_CLASSIFY, requestForm.getQuest_id(), JsonUtils.objectToJson(result));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// 分类疾病
	@Override
	public List<DiseaseSortVo> getDiseaseSort(RequestForm requestForm) {
		try {
			List<String> json = jedisClient.lrange(DISEASE_CLASS_LIST + ":" + requestForm.getContent(),
					requestForm.getRow() * 15, (requestForm.getRow() + 1) * 15);

			if (null != json && json.size() > 0) {
				List<DiseaseSortVo> list = new LinkedList<>();
				for (String string : json) {
					list.add(JsonUtils.jsonToPojo(string, DiseaseSortVo.class));
				}
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 获得疾病目录ID
		TbCateExample example = new TbCateExample();
		TbCateExample.Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(requestForm.getContent());
		criteria.andTypeEqualTo(ItemConstant.CATE_LECTURE);
		criteria.andIsParentEqualTo(false);
		List<TbCate> cateList = cateMapper.selectByExample(example);
		if (null == cateList || cateList.size() < 1)
			return null;

		// 获得疾病数据
		List<TbDisease> list = diseaseMapper.selectItemByClassify(cateList.get(0).getId(),
				requestForm.getRow() * DISEASE_SEARCH_COUNT, DISEASE_SEARCH_COUNT);
		if (null == list || list.size() == 0)
			return null;

		List<DiseaseSortVo> result = new LinkedList<DiseaseSortVo>();
		for (TbDisease disease : list) {
			if (null == disease)
				continue;
			result.add(new DiseaseSortVo(disease));
		}

		try {
			for (DiseaseSortVo item : result) {
				jedisClient.rpush(DISEASE_CLASS_LIST + ":" + requestForm.getContent(), JsonUtils.objectToJson(item));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

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

		// 获得科室数据
		List<String> list = cateMapper.selectCateNameByItemIdAndType(disease.getId(), ItemConstant.RELA_DEP);
		disease.setDepart(String.join(",", list));
		list.clear();
		// 获得部位数据
		list = cateMapper.selectCateNameByItemIdAndType(disease.getId(), ItemConstant.RELA_PAR);
		disease.setPart(String.join(",", list));

		DiseaseDetailVo result = new DiseaseDetailVo(disease);

		try {
			jedisClient.hset(DISEASE_DETAIL + ":" + requestForm.getQuest_id(), requestForm.getContent(),
					JsonUtils.objectToJson(result));
			jedisClient.expire(DISEASE_DETAIL + ":" + requestForm.getQuest_id(), DISEASE_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

}
