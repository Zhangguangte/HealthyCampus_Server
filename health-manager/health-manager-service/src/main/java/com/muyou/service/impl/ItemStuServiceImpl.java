package com.muyou.service.impl;

import java.util.Date;
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
import com.muyou.mapper.TbItemRelaCateMapper;
import com.muyou.mapper.TbStudentMapper;
import com.muyou.pojo.TbItemRelaCateExample;
import com.muyou.pojo.TbStudent;
import com.muyou.pojo.TbStudentExample;
import com.muyou.pojo.TbStudentExample.Criteria;
import com.muyou.service.ItemStuService;

@Service
public class ItemStuServiceImpl implements ItemStuService {

	@Autowired
	private JedisClient jedisClient;

	@Autowired
	private TbStudentMapper studentMapper;

	@Autowired
	private TbItemRelaCateMapper itemRelaCateMapper;

	@Value("${ITEM_COUNT}")
	private String ITEM_COUNT;

	@Value("${ITEM_ID}")
	private String ITEM_ID;

	@Value("${ITEM_EXPIRE}")
	private Integer ITEM_EXPIRE;

	@Value("${ITEM_LEC_DEF_IMG}")
	private String ITEM_LEC_DEF_IMG;

	@Value("${ITEM_DETAIL_ID}")
	private String ITEM_DETAIL_ID;

	@Value("${STUDENT}")
	private String STUDENT;

	@Override
	public TbStudent getItemById(Integer id) {

		try {
			String json = jedisClient.get(ITEM_DETAIL_ID + ":" + STUDENT + ":" + id);
			if (StringUtils.isNotBlank(json))
				return JsonUtils.jsonToPojo(json, TbStudent.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 设置学生数据
		TbStudent result = studentMapper.selectByPrimaryKey(id);

		try {
			jedisClient.set(ITEM_DETAIL_ID + ":" + STUDENT + ":" + id, JsonUtils.objectToJson(result));
			jedisClient.expire(ITEM_DETAIL_ID + ":" + STUDENT + ":" + id, ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public TbStudent getNormalItemById(Integer id) {

		try {
			String json = jedisClient.get(ITEM_ID + ":" + STUDENT + ":" + id);
			if (StringUtils.isNotBlank(json))
				return JsonUtils.jsonToPojo(json, TbStudent.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbStudent result = studentMapper.selectByPrimaryKey(id);

		try {
			jedisClient.set(ITEM_ID + ":" + STUDENT + ":" + id, JsonUtils.objectToJson(result));
			jedisClient.expire(ITEM_ID + ":" + STUDENT + ":" + id, ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public DataTablesResult getAllItemCount() {

		try {
			String json = jedisClient.get(ITEM_COUNT + ":" + STUDENT);
			if (StringUtils.isNotBlank(json))
				return JsonUtils.jsonToPojo(json, DataTablesResult.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbStudentExample example = new TbStudentExample();
		int count = studentMapper.countByExample(example);
		DataTablesResult result = new DataTablesResult();
		result.setRecordsTotal(count);

		try {
			jedisClient.set(ITEM_COUNT + ":" + STUDENT, JsonUtils.objectToJson(result));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public DataTablesResult getItemList(int draw, int start, int length, int cid, String orderCol, String orderDir) {
		DataTablesResult result = new DataTablesResult();

		// 分页执行查询返回结果
		PageHelper.startPage(start / length + 1, length);

		TbStudentExample example = new TbStudentExample();
		example.setOrderByClause(orderCol + " " + orderDir);
		Criteria criteria = example.createCriteria();
		criteria.andClassidEqualTo(cid);

		List<TbStudent> list = studentMapper.selectByExample(example);

		PageInfo<TbStudent> pageInfo = new PageInfo<>(list);

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

		List<TbStudent> list = studentMapper.selectItemByMultiCondition(cid, "%" + search + "%", minDate, maxDate,
				orderCol, orderDir);

		PageInfo<TbStudent> pageInfo = new PageInfo<>(list);
		result.setRecordsFiltered((int) pageInfo.getTotal());
		result.setRecordsTotal(getAllItemCount().getRecordsTotal());

		result.setDraw(draw);
		result.setData(list);

		return result;
	}

	@Override
	public int addItem(TbStudent student) {
		student.setState(true);
		student.setCreated(new Date());
		student.setUpdated(new Date());

		if (studentMapper.insert(student) < 1) {
			throw new GlobalException("添加学生失败");
		}

		try {
			jedisClient.del(ITEM_COUNT + ":" + STUDENT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public int deleteItem(int id) {
		if (studentMapper.deleteByPrimaryKey(id) != 1) {
			throw new GlobalException("删除学生失败");
		}

		TbItemRelaCateExample example = new TbItemRelaCateExample();
		TbItemRelaCateExample.Criteria criteria = example.createCriteria();
		criteria.andCateIdEqualTo(id);
		itemRelaCateMapper.deleteByExample(example);

		try {
			jedisClient.del(ITEM_ID + ":" + STUDENT + ":" + id);
			jedisClient.del(ITEM_DETAIL_ID + ":" + STUDENT + ":" + id);
			jedisClient.del(ITEM_COUNT + ":" + STUDENT);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 1;
	}

	@Override
	public int alertItemState(Integer id, Boolean state) {

		TbStudent item = getNormalItemById(id);
		item.setState(state);
		item.setUpdated(new Date());

		if (studentMapper.updateByPrimaryKey(item) < 1) {
			throw new GlobalException("修改学生状态失败");
		}

		try {
			jedisClient.del(ITEM_ID + ":" + STUDENT + ":" + id);
			jedisClient.del(ITEM_DETAIL_ID + ":" + STUDENT + ":" + id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 1;
	}

	@Override
	public int updateItem(Integer id, TbStudent student) {

		TbStudent oldStudent = getNormalItemById(id);
		student.setId(id);
		student.setCreated(oldStudent.getCreated());
		student.setUpdated(new Date());
		student.setState(true);
		student.setuId(oldStudent.getuId());
		student.setNo(oldStudent.getNo());
		if (studentMapper.updateByPrimaryKey(student) < 1) {
			throw new GlobalException("更新学生失败");
		}

		// 同步缓存
		try {
			jedisClient.del(ITEM_ID + ":" + STUDENT + ":" + id);
			jedisClient.del(ITEM_DETAIL_ID + ":" + STUDENT + ":" + id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 1;
	}

}
