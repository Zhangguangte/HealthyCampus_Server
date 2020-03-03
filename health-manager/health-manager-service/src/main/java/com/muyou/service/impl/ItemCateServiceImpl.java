package com.muyou.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.muyou.common.exception.GlobalException;
import com.muyou.common.pojo.ZTreeNode;
import com.muyou.common.redis.JedisClient;
import com.muyou.common.util.JsonUtils;
import com.muyou.dto.DtoUtil;
import com.muyou.mapper.TbCateMapper;
import com.muyou.pojo.TbCate;
import com.muyou.pojo.TbCateExample;
import com.muyou.service.ItemCateService;

@Service
public class ItemCateServiceImpl implements ItemCateService {

	@Autowired
	private TbCateMapper itemCatMapper;

	@Autowired
	private JedisClient jedisClient;

	@Value("${ITEM_CAT_ID}")
	private String ITEM_CAT_ID;

	@Value("${ITEM_CAT_EXPIRE}")
	private Integer ITEM_CAT_EXPIRE;

	@Override
	public TbCate getItemCateById(int id) {
		try {
			String json = jedisClient.get(ITEM_CAT_ID + ":" + id);
			if (StringUtils.isNotBlank(json))
				return JsonUtils.jsonToPojo(json, TbCate.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbCate result = itemCatMapper.selectByPrimaryKey(id);
		if (result == null) {
			throw new GlobalException("通过id获取分类失败");
		}

		try {
			jedisClient.set(ITEM_CAT_ID + ":" + id, JsonUtils.objectToJson(result));
			jedisClient.expire(ITEM_CAT_ID + ":" + id, ITEM_CAT_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
	@Override
	public List<ZTreeNode> getItemCateList(int parentId, int type) {
		TbCateExample example = new TbCateExample();
		TbCateExample.Criteria criteria = example.createCriteria();
		// 排序
		example.setOrderByClause("sort_order");
		// 条件查询
		criteria.andParentIdEqualTo(parentId);
		criteria.andTypeEqualTo(type);

		List<TbCate> list = itemCatMapper.selectByExample(example);

		// 转换成ZtreeNode
		List<ZTreeNode> result = new ArrayList<>();
		for (TbCate TbCate : list) {
			ZTreeNode node = DtoUtil.TbCate2ZTreeNode(TbCate);
			result.add(node);
		}
		return result;
	}

	@Override
	public int addItemCate(TbCate TbCate,int type) {
		if (TbCate.getParentId() == 0) {
			// 根节点
			TbCate.setSortOrder(1);
			TbCate.setStatus(1);
		} else {
			TbCate parent = itemCatMapper.selectByPrimaryKey(TbCate.getParentId());
			TbCate.setSortOrder(1);
			TbCate.setStatus(1);
			TbCate.setCreated(new Date());
			TbCate.setUpdated(new Date());
			TbCate.setType(type);
		}

		if (itemCatMapper.insert(TbCate) != 1) {
			throw new GlobalException("添加分类失败");
		}
		return 1;
	}

	@Override
	public int updateItemCate(TbCate TbCate) {
		TbCate old = getItemCateById(TbCate.getId());
		TbCate.setCreated(old.getCreated());
		TbCate.setType(old.getType());
		TbCate.setUpdated(new Date());
		if (itemCatMapper.updateByPrimaryKey(TbCate) != 1) {
			throw new GlobalException("添加分类失败");
		}

		try {
			jedisClient.del(ITEM_CAT_ID + ":" + TbCate.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 1;
	}

	@Override
	public void deleteItemCate(int id, int type) {
		deleteZTree(id, type);
	}

	@Override
	public void deleteZTree(int id, int type) {
		// 查询该节点所有子节点
		List<ZTreeNode> node = getItemCateList(id, type);
		if (node.size() > 0) {
			// 如果有子节点，遍历子节点
			for (int i = 0; i < node.size(); i++) {
				deleteItemCate(node.get(i).getId(), type);
			}
		}
		// 没有子节点直接删除
		if (itemCatMapper.deleteByPrimaryKey(id) != 1) {
			throw new GlobalException("删除分类失败");
		}

		try {
			jedisClient.del(ITEM_CAT_ID + ":" + id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
