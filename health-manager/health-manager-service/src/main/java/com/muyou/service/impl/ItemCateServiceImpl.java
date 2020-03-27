package com.muyou.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.muyou.common.constant.ItemConstant;
import com.muyou.common.constant.RedisConstant;
import com.muyou.common.exception.GlobalException;
import com.muyou.common.pojo.ZTreeNode;
import com.muyou.common.redis.JedisClient;
import com.muyou.common.util.JsonUtils;
import com.muyou.dto.DtoUtil;
import com.muyou.mapper.TbCateMapper;
import com.muyou.mapper.TbRecipesMapper;
import com.muyou.pojo.TbCate;
import com.muyou.pojo.TbCateExample;
import com.muyou.pojo.TbCateExample.Criteria;
import com.muyou.service.ItemCateService;

@Service
public class ItemCateServiceImpl implements ItemCateService {

	@Autowired
	private TbCateMapper itemCatMapper;

	@Autowired
	private JedisClient jedisClient;

	@Autowired
	private TbRecipesMapper recipesMapper;

	@Value("${ITEM_CATE_ID}")
	private String ITEM_CATE_ID;

	@Value("${ITEM_CATE_LIST}")
	private String ITEM_CATE_LIST;

	@Value("${ITEM_CATE_EXPIRE}")
	private Integer ITEM_CATE_EXPIRE;

	@Override
	public TbCate getItemCateById(int id) {
		try {
			String json = jedisClient.get(ITEM_CATE_ID + ":" + id);
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
			jedisClient.set(ITEM_CATE_ID + ":" + id, JsonUtils.objectToJson(result));
			jedisClient.expire(ITEM_CATE_ID + ":" + id, ITEM_CATE_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<ZTreeNode> getItemCateList(int parentId, int type) {
		try {
			String json = jedisClient.hget(ITEM_CATE_LIST + ":" + type, "" + parentId);
			if (StringUtils.isNotBlank(json))
				return JsonUtils.jsonToList(json, ZTreeNode.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbCateExample example = new TbCateExample();
		TbCateExample.Criteria criteria = example.createCriteria();
		// 排序
		example.setOrderByClause("sort_order asc,id asc");
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

		try {
			jedisClient.hset(ITEM_CATE_LIST + ":" + type, "" + parentId, JsonUtils.objectToJson(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<ZTreeNode> getClsCate(int parentId, int year) {
		try {
			String json = jedisClient.hget(ITEM_CATE_LIST + ":" + ItemConstant.CATE_CLASS, year + ":" + parentId);
			if (StringUtils.isNotBlank(json))
				return JsonUtils.jsonToList(json, ZTreeNode.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbCateExample example = new TbCateExample();
		TbCateExample.Criteria criteria = example.createCriteria();
		// 排序
		example.setOrderByClause("sort_order asc,id asc");
		// 条件查询
		criteria.andParentIdEqualTo(parentId);
		criteria.andTypeEqualTo(ItemConstant.CATE_CLASS);
		criteria.andRemarkEqualTo("" + year);
		List<TbCate> list = itemCatMapper.selectByExample(example);

		// 转换成ZtreeNode
		List<ZTreeNode> result = new ArrayList<>();
		for (TbCate TbCate : list) {
			result.add(DtoUtil.TbCate2ZTreeNode(TbCate));
		}
		try {
			jedisClient.hset(ITEM_CATE_LIST + ":" + ItemConstant.CATE_CLASS, year + ":" + parentId, JsonUtils.objectToJson(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int addItemCate(TbCate tbCate, int type) {
		tbCate.setCreated(new Date());
		tbCate.setUpdated(new Date());
		tbCate.setSortOrder(1);
		tbCate.setStatus(1);
		tbCate.setType(type);

		if (itemCatMapper.insert(tbCate) != 1) {
			throw new GlobalException("添加分类失败");
		}

		try {
			jedisClient.hdel(ITEM_CATE_LIST + ":" + type, "" + tbCate.getParentId());
			redisClear(type);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 1;
	}

	@Override
	public int addCanteenItemCate(int id, int parentId, String name) {
		// 是否已经存在食谱
		TbCateExample example = new TbCateExample();
		Criteria criteria = example.createCriteria();
		criteria.andRemarkEqualTo(String.valueOf(id));
		criteria.andTypeEqualTo(ItemConstant.CATE_CATTEN);
		criteria.andParentIdEqualTo(parentId);
		List<TbCate> list = itemCatMapper.selectByExample(example);
		if (null != list && list.size() > 0)
			return 0;
		TbCate cate = new TbCate();
		cate.setSortOrder(1);
		cate.setStatus(1);
		cate.setCreated(new Date());
		cate.setUpdated(new Date());
		cate.setType(ItemConstant.CATE_CATTEN);
		cate.setParentId(parentId);
		cate.setIsParent(false);
		cate.setRemark(String.valueOf(id));
		cate.setName(name);

		if (itemCatMapper.insert(cate) != 1) {
			throw new GlobalException("添加分类失败");
		}

		try {
			jedisClient.del(ITEM_CATE_ID + ":" + id);
			jedisClient.hdel(ITEM_CATE_LIST + ":" + ItemConstant.CATE_CATTEN, "" + parentId);
		} catch (Exception e) {
			e.printStackTrace();
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
			jedisClient.del(ITEM_CATE_ID + ":" + TbCate.getId());
			jedisClient.hdel(ITEM_CATE_LIST + ":" + old.getType(), "" + TbCate.getParentId());
			redisClear(old.getType());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 1;
	}

	@Override
	public int updateItemCate(int id, int type) {
		// 获得原菜单数据
		TbCateExample example = new TbCateExample();
		Criteria criteria = example.createCriteria();
		criteria.andRemarkEqualTo("" + id);
		criteria.andTypeEqualTo(type);
		List<TbCate> list = itemCatMapper.selectByExample(example);
		if (null != list && list.size() > 0) {
			// 获得新菜单名
			String name = recipesMapper.selectByPrimaryKey(id).getName();
			for (TbCate tbCate : list) {
				// 菜单名是否变化
				if (tbCate.getName().equals(name))
					break;
				tbCate.setName(name);
				itemCatMapper.updateByPrimaryKey(tbCate);
			}
		}

		try {
			jedisClient.del(ITEM_CATE_LIST + ":" + ItemConstant.CATE_CATTEN);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 1;
	}

	@Override
	public int delCanteenItemCate(int id, int parentId) {
		TbCateExample example = new TbCateExample();
		Criteria criteria = example.createCriteria();
		criteria.andRemarkEqualTo("" + id);
		criteria.andParentIdEqualTo(parentId);

		if (itemCatMapper.deleteByExample(example) < 1) {
			throw new GlobalException("删除食谱失败");
		}

		try {
			jedisClient.del(ITEM_CATE_LIST + ":" + ItemConstant.CATE_CATTEN);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 1;
	}

	@Override
	public int delCanteen(int id, int type) {
		TbCateExample example = new TbCateExample();
		Criteria criteria = example.createCriteria();
		criteria.andRemarkEqualTo("" + id);
		criteria.andTypeEqualTo(type);

		if (itemCatMapper.deleteByExample(example) < 1) {
			throw new GlobalException("删除食谱失败");
		}

		try {
			jedisClient.del(ITEM_CATE_LIST + ":" + ItemConstant.CATE_CATTEN);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 1;
	}

	@Override
	public int deleteItemCate(int id, int type) {
		deleteZTree(id, type);
		return 1;
	}

	@Override
	public int deleteZTree(int id, int type) {
		// 查询该节点所有子节点
		List<ZTreeNode> node = getItemCateList(id, type);
		if (node.size() > 0) {
			// 如果有子节点，遍历子节点
			for (int i = 0; i < node.size(); i++) {
				deleteItemCate(node.get(i).getId(), type);
			}
		}
		// 没有子节点直接删除
		if (itemCatMapper.deleteByPrimaryKey(id) < 0) {
			throw new GlobalException("删除分类失败");
		}

		try {
			jedisClient.del(ITEM_CATE_ID + ":" + id);
			jedisClient.del(ITEM_CATE_LIST + ":" + type);
			redisClear(type);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 1;
	}

	
	private void redisClear(int type) {
		try {
			switch (type) {
			case ItemConstant.CATE_CLASS:
				jedisClient.del(ITEM_CATE_LIST + ":" + ItemConstant.CATE_CLASS);
				break;
			case ItemConstant.CATE_DISEASE:
				jedisClient.del(RedisConstant.DISEASE_CLASSIFY);
				break;
			case ItemConstant.CATE_MEDICINE:
				jedisClient.del(RedisConstant.MEDICINE_CLASSIFY);
				break;
			case ItemConstant.CATE_RECIPES:
				jedisClient.del(RedisConstant.RECIPES_CLASSIFY);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
