package com.muyou.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.muyou.common.constant.HealthConstant;
import com.muyou.common.exception.GlobalException;
import com.muyou.common.pojo.DataTablesResult;
import com.muyou.common.redis.JedisClient;
import com.muyou.common.util.JsonUtils;
import com.muyou.mapper.TbAdminMapper;
import com.muyou.mapper.TbCateMapper;
import com.muyou.mapper.TbItemRelaCateMapper;
import com.muyou.pojo.TbAdmin;
import com.muyou.pojo.TbAdminExample;
import com.muyou.pojo.TbAdminExample.Criteria;
import com.muyou.pojo.TbCate;
import com.muyou.pojo.TbCateExample;
import com.muyou.service.ItemTeaService;

@Service
public class ItemTeaServiceImpl implements ItemTeaService {

	@Autowired
	private JedisClient jedisClient;

	@Autowired
	private TbCateMapper cateMapper;

	@Autowired
	private TbItemRelaCateMapper itemRelaCateMapper;

	@Autowired
	private TbAdminMapper adminMapper;
	
	@Value("${ITEM_COUNT}")
	private String ITEM_COUNT;

	@Value("${TEACHER}")
	private String TEACHER;

	@Value("${ITEM_ID}")
	private String ITEM_ID;

	@Value("${ITEM_DETAIL_ID}")
	private String ITEM_DETAIL_ID;

	@Value("${ITEM_EXPIRE}")
	private Integer ITEM_EXPIRE;
	
	@Override
	public TbAdmin getNormalItemById(Integer id) {

		try {
			String json = jedisClient.get(ITEM_ID + ":" + TEACHER + ":" + id);
			if (StringUtils.isNotBlank(json))
				return JsonUtils.jsonToPojo(json, TbAdmin.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbAdmin result = adminMapper.selectByPrimaryKey(id);
		result.setPassword(null);
		try {
			jedisClient.set(ITEM_ID + ":" + TEACHER + ":" + id, JsonUtils.objectToJson(result));
			jedisClient.expire(ITEM_ID + ":" + TEACHER + ":" + id, ITEM_EXPIRE);
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
		TbAdminExample example = new TbAdminExample();
		example.setOrderByClause(orderCol + " " + orderDir);
		Criteria criteria = example.createCriteria();
		criteria.andRoleidEqualTo(HealthConstant.ROLE_TEA);
		List<TbAdmin> list = adminMapper.selectByExample(example);
		for (TbAdmin admin : list) {
			admin.setPassword(null);
		}

		PageInfo<TbAdmin> pageInfo = new PageInfo<>(list);
		result.setRecordsFiltered((int) pageInfo.getTotal());
		result.setRecordsTotal(getAllItemCount().getRecordsTotal());
		result.setDraw(draw);
		result.setData(list);

		return result;
	}

	@Override
	public DataTablesResult getAllItemCount() {

		try {
			String json = jedisClient.get(ITEM_COUNT + ":" + TEACHER);
			if (StringUtils.isNotBlank(json))
				return JsonUtils.jsonToPojo(json, DataTablesResult.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbAdminExample example = new TbAdminExample();
		Criteria criteria = example.createCriteria();
		criteria.andRoleidEqualTo(HealthConstant.ROLE_TEA);
		int count = adminMapper.countByExample(example);
		DataTablesResult result = new DataTablesResult();
		result.setRecordsTotal(count);

		try {
			jedisClient.set(ITEM_COUNT + ":" + TEACHER, JsonUtils.objectToJson(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public DataTablesResult getItemCollege(int draw, int start, int length, int cid, String search, String orderCol,
			String orderDir) {
		DataTablesResult result = new DataTablesResult();

		// 分页执行查询返回结果
		PageHelper.startPage(start / length + 1, length);
		// 获取该父节点下所有子节点
		TbCateExample example = new TbCateExample();
		TbCateExample.Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(cid);
		List<TbCate> cateList = cateMapper.selectByExample(example);
		// 合并所有id
		StringBuilder sBuilder = new StringBuilder("");
		for (TbCate tbCate : cateList) {
			sBuilder.append(tbCate.getRemark() + ",");
		}
		if (sBuilder.length() > 0)
			sBuilder.setLength(sBuilder.length() - 1);

		// 获取所需数据
		List<TbAdmin> list = adminMapper.selectItemByIds(sBuilder.toString(), orderCol, orderDir);
		for (TbAdmin admin : list) {
			admin.setPassword(null);
		}

		PageInfo<TbAdmin> pageInfo = new PageInfo<>(list);
		result.setRecordsFiltered((int) pageInfo.getTotal());
		result.setRecordsTotal(getAllItemCount().getRecordsTotal());
		result.setDraw(draw);
		result.setData(list);

		return result;
	}

	@Override
	public int alertItemState(Integer id, Boolean state) {
		TbAdmin item = getNormalItemById(id);
		item.setState(state ? 1 : 0);
		item.setUpdateTime(new Date());

		if (adminMapper.updateByPrimaryKey(item) < 1) {
			throw new GlobalException("修改教师状态失败");
		}

		try {
			jedisClient.del(ITEM_ID + ":" + TEACHER + ":" + id);
			jedisClient.del(ITEM_DETAIL_ID + ":" + TEACHER + ":" + id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 1;
	}

	@Override
	public int updateItem(Integer id, TbAdmin admin) {
		

		return 1;
	}

	@Override
	public void updateRedis(Integer id) {
		try {
			jedisClient.del(ITEM_ID + ":" + TEACHER + ":" + id);
			jedisClient.del(ITEM_DETAIL_ID + ":" + TEACHER + ":" + id);
			jedisClient.del(ITEM_COUNT + ":" + TEACHER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
