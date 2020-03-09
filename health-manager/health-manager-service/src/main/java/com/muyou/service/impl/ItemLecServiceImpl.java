package com.muyou.service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.muyou.common.exception.GlobalException;
import com.muyou.common.pojo.DataTablesResult;
import com.muyou.common.redis.JedisClient;
import com.muyou.common.util.JsonUtils;
import com.muyou.dto.DtoUtil;
import com.muyou.mapper.TbCateMapper;
import com.muyou.mapper.TbItemRelaCateMapper;
import com.muyou.mapper.TbLectureMapper;
import com.muyou.pojo.TbCate;
import com.muyou.pojo.TbItemRelaCate;
import com.muyou.pojo.TbItemRelaCateExample;
import com.muyou.pojo.TbLecture;
import com.muyou.pojo.TbLectureExample;
import com.muyou.service.ItemLecService;
import com.muyou.vo.LectureVo;

@Service
public class ItemLecServiceImpl implements ItemLecService {

	@Autowired
	private JedisClient jedisClient;

	@Autowired
	private TbLectureMapper lectureMapper;

	@Autowired
	private TbCateMapper cateMapper;

	@Autowired
	private TbItemRelaCateMapper itemRelaCateMapper;

	@Value("${ITEM_COUNT}")
	private String ITEM_COUNT;

	@Value("${ITEM_ID}")
	private String ITEM_ID;

	@Value("${RELA_LEC}")
	private Integer RELA_LEC;

	@Value("${ITEM_EXPIRE}")
	private Integer ITEM_EXPIRE;

	@Value("${ITEM_LEC_DEF_IMG}")
	private String ITEM_LEC_DEF_IMG;

	@Value("${ITEM_DETAIL_ID}")
	private String ITEM_DETAIL_ID;

	@Value("${LECTURE}")
	private String LECTURE;

	@Override
	public LectureVo getItemById(Integer id) {

		try {
			String json = jedisClient.get(ITEM_DETAIL_ID + ":" + LECTURE + ":" + id);
			if (StringUtils.isNotBlank(json))
				return JsonUtils.jsonToPojo(json, LectureVo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(id);
		
		// 设置讲座数据
		LectureVo result = DtoUtil.TbLecture2LectureVo(lectureMapper.selectByPrimaryKey(id));

		List<String> cateList = new LinkedList<String>();
		List<String> cidList = new LinkedList<String>();

		// 获得分类数据
		List<TbCate> list = cateMapper.selectItemCate(id, RELA_LEC);
		for (TbCate tbCate : list) {
			cateList.add(tbCate.getName());
			cidList.add(tbCate.getId() + "");
		}
		result.setCid(cidList);
		result.setCname(cateList);

		try {
			jedisClient.set(ITEM_DETAIL_ID + ":" + LECTURE + ":" + id, JsonUtils.objectToJson(result));
			jedisClient.expire(ITEM_DETAIL_ID + ":" + LECTURE + ":" + id, ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}

	@Override
	public TbLecture getNormalItemById(Integer id) {

		try {
			String json = jedisClient.get(ITEM_ID + ":" + LECTURE + ":" + id);
			if (StringUtils.isNotBlank(json))
				return JsonUtils.jsonToPojo(json, TbLecture.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbLecture result = lectureMapper.selectByPrimaryKey(id);

		try {
			jedisClient.set(ITEM_ID + ":" + LECTURE + ":" + id, JsonUtils.objectToJson(result));
			jedisClient.expire(ITEM_ID + ":" + LECTURE + ":" + id, ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public DataTablesResult getAllItemCount() {

		try {
			String json = jedisClient.get(ITEM_COUNT + ":" + LECTURE);
			if (StringUtils.isNotBlank(json))
				return JsonUtils.jsonToPojo(json, DataTablesResult.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbLectureExample example = new TbLectureExample();
		int count = lectureMapper.countByExample(example);
		DataTablesResult result = new DataTablesResult();
		result.setRecordsTotal(count);

		try {
			jedisClient.set(ITEM_COUNT + ":" + LECTURE, JsonUtils.objectToJson(result));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public DataTablesResult getItemList(int draw, int start, int length, int cid, String search, String orderCol,
			String orderDir) {
		DataTablesResult result = new DataTablesResult();

		// 分页执行查询返回结果
		PageHelper.startPage(start / length + 1, length);

		
		
		List<TbLecture> list = lectureMapper.selectItemByCondition(cid, orderCol, orderDir);

		List<String> cateList;
		for (TbLecture lecture : list) {
			// 获得分类数据
			cateList = cateMapper.selectCateNameByItemIdAndType(lecture.getId(), RELA_LEC);
			lecture.setCname(String.join(",", cateList));
		}

		PageInfo<TbLecture> pageInfo = new PageInfo<>(list);

		result.setRecordsFiltered((int) pageInfo.getTotal());
		result.setRecordsTotal(getAllItemCount().getRecordsTotal());

		result.setDraw(draw);
		result.setData(list);

		return result;
	}

	@Override
	public DataTablesResult getItemSearchList(int draw, int start, int length, int cid, String search, String minDate,
			String maxDate, String orderCol, String orderDir) {

		DataTablesResult result = new DataTablesResult();
		// 分页执行查询返回结果
		PageHelper.startPage(start / length + 1, length);
		List<TbLecture> list = lectureMapper.selectItemByMultiCondition(cid, "%" + search + "%", minDate, maxDate,
				orderCol, orderDir);

		List<String> cateList;
		for (TbLecture lecture : list) {
			// 获得分类数据
			cateList = cateMapper.selectCateNameByItemIdAndType(lecture.getId(), RELA_LEC);
			lecture.setCname(String.join(",", cateList));
		}

		PageInfo<TbLecture> pageInfo = new PageInfo<>(list);
		result.setRecordsFiltered((int) pageInfo.getTotal());
		result.setRecordsTotal(getAllItemCount().getRecordsTotal());

		result.setDraw(draw);
		result.setData(list);

		return result;
	}

	@Override
	public int addItem(LectureVo lectureVo) {
		TbLecture lecture = DtoUtil.LectureVo2TbLecture(lectureVo);
		lecture.setStatus(true);
		lecture.setCreated(new Date());
		lecture.setUpdated(new Date());
		
		if (lectureMapper.insert(lecture) < 1) {
			throw new GlobalException("添加讲座失败");
		}

		TbItemRelaCate itemRelaCate = new TbItemRelaCate();

		// 分类数据
		itemRelaCate.setItemId(lecture.getId());
		itemRelaCate.setType(RELA_LEC);
		for (String cid : lectureVo.getCid()) {

			System.out.println(cid);

			if (StringUtils.isBlank(cid))
				continue;
			itemRelaCate.setCateId(Integer.valueOf(cid));
			itemRelaCateMapper.insert(itemRelaCate);
		}

		try {
			jedisClient.del(ITEM_COUNT + ":" + LECTURE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public int deleteItem(int id) {
		if (lectureMapper.deleteByPrimaryKey(id) != 1) {
			throw new GlobalException("删除讲座失败");
		}

		TbItemRelaCateExample example = new TbItemRelaCateExample();
		TbItemRelaCateExample.Criteria criteria = example.createCriteria();
		criteria.andCateIdEqualTo(id);
		itemRelaCateMapper.deleteByExample(example);

		try {
			jedisClient.del(ITEM_ID + ":" + LECTURE + ":" + id);
			jedisClient.del(ITEM_DETAIL_ID + ":" + LECTURE + ":" + id);
			jedisClient.del(ITEM_COUNT + ":" + LECTURE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 1;
	}

	@Override
	public int alertItemState(Integer id, Boolean state) {

		TbLecture item = getNormalItemById(id);
		item.setStatus(state);
		item.setUpdated(new Date());

		if (lectureMapper.updateByPrimaryKey(item) < 1) {
			throw new GlobalException("修改讲座状态失败");
		}

		try {
			jedisClient.del(ITEM_ID + ":" + LECTURE + ":" + id);
			jedisClient.del(ITEM_DETAIL_ID + ":" + LECTURE + ":" + id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 1;
	}

	@Override
	public int updateItem(Integer id, LectureVo lectureVo) {

		TbLecture oldRecipes = getNormalItemById(id);
		TbLecture lecture = DtoUtil.LectureVo2TbLecture(lectureVo);
		lecture.setId(id);
		lecture.setCreated(oldRecipes.getCreated());
		lecture.setUpdated(new Date());
		lecture.setStatus(true);
		if (lectureMapper.updateByPrimaryKey(lecture) < 1) {
			throw new GlobalException("更新讲座失败");
		}

		TbItemRelaCateExample example = new TbItemRelaCateExample();
		TbItemRelaCateExample.Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(id);
		if (itemRelaCateMapper.deleteByExample(example) < 1) {
			throw new GlobalException("删除讲座&分类关系失败");
		}

		TbItemRelaCate itemRelaCate = new TbItemRelaCate();

		// 分类数据
		itemRelaCate.setItemId(id);
		itemRelaCate.setType(RELA_LEC);
		for (String did : lectureVo.getCid()) {
			if (StringUtils.isBlank(did))
				continue;
			itemRelaCate.setCateId(Integer.valueOf(did));
			itemRelaCateMapper.insert(itemRelaCate);
		}

		// 同步缓存
		try {
			jedisClient.del(ITEM_ID + ":" + LECTURE + ":" + id);
			jedisClient.del(ITEM_DETAIL_ID + ":" + LECTURE + ":" + id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 1;
	}

}
