package com.muyou.service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
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
import com.muyou.mapper.TbRecipesMapper;
import com.muyou.pojo.TbCate;
import com.muyou.pojo.TbCateExample;
import com.muyou.pojo.TbCateExample.Criteria;
import com.muyou.pojo.TbItemRelaCate;
import com.muyou.pojo.TbItemRelaCateExample;
import com.muyou.pojo.TbRecipes;
import com.muyou.pojo.TbRecipesExample;
import com.muyou.service.ItemRecService;
import com.muyou.vo.RecipesVo;

@Service
public class ItemRecServiceImpl implements ItemRecService {

	private final static Logger log = LoggerFactory.getLogger(ItemRecServiceImpl.class);

	@Autowired
	private JedisClient jedisClient;

	@Autowired
	private JmsTemplate jmsTemplate;

	@Resource
	private Destination topicDestination;

	@Autowired
	private TbRecipesMapper recipesMapper;

	@Autowired
	private TbCateMapper cateMapper;

	@Autowired
	private TbItemRelaCateMapper itemRelaCateMapper;

	@Value("${ITEM_COUNT}")
	private String ITEM_COUNT;

	@Value("${ITEM_ID}")
	private String ITEM_ID;

	@Value("${RELA_REC}")
	private Integer RELA_REC;

	@Value("${ITEM_EXPIRE}")
	private Integer ITEM_EXPIRE;

	@Value("${ITEM_REC_DEF_IMG}")
	private String ITEM_REC_DEF_IMG;

	@Value("${ITEM_DETAIL_ID}")
	private String ITEM_DETAIL_ID;

	@Value("${RECIPES}")
	private String RECIPES;

	@Override
	public RecipesVo getItemById(Integer id) {

		try {
			String json = jedisClient.get(ITEM_DETAIL_ID + ":" + RECIPES + ":" + id);
			if (StringUtils.isNotBlank(json))
				return JsonUtils.jsonToPojo(json, RecipesVo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 设置食谱数据
		RecipesVo result = DtoUtil.TbRecipes2RecipesVo(recipesMapper.selectByPrimaryKey(id));

		List<String> cateList = new LinkedList<String>();
		List<String> cidList = new LinkedList<String>();

		// 获得分类数据
		List<TbCate> list = cateMapper.selectItemCate(id, RELA_REC);
		for (TbCate tbCate : list) {
			cateList.add(tbCate.getName());
			cidList.add(tbCate.getId() + "");
		}
		result.setCid(cidList);
		result.setCname(cateList);

		try {
			jedisClient.set(ITEM_DETAIL_ID + ":" + RECIPES + ":" + id, JsonUtils.objectToJson(result));
			jedisClient.expire(ITEM_DETAIL_ID + ":" + RECIPES + ":" + id, ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}

	@Override
	public RecipesVo getItemShowById(Integer id) {
		TbCate cate = cateMapper.selectByPrimaryKey(id);
		return getItemById(Integer.valueOf(cate.getRemark()));
	}

	@Override
	public TbRecipes getNormalItemById(Integer id) {

		try {
			String json = jedisClient.get(ITEM_ID + ":" + RECIPES + ":" + id);
			if (StringUtils.isNotBlank(json))
				return JsonUtils.jsonToPojo(json, TbRecipes.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbRecipes result = recipesMapper.selectByPrimaryKey(id);

		try {
			jedisClient.set(ITEM_ID + ":" + RECIPES + ":" + id, JsonUtils.objectToJson(result));
			jedisClient.expire(ITEM_ID + ":" + RECIPES + ":" + id, ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public DataTablesResult getAllItemCount() {

		try {
			String json = jedisClient.get(ITEM_COUNT + ":" + RECIPES);
			if (StringUtils.isNotBlank(json))
				return JsonUtils.jsonToPojo(json, DataTablesResult.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbRecipesExample example = new TbRecipesExample();
		int count = recipesMapper.countByExample(example);
		DataTablesResult result = new DataTablesResult();
		result.setRecordsTotal(count);

		try {
			jedisClient.set(ITEM_COUNT + ":" + RECIPES, JsonUtils.objectToJson(result));
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
		List<TbRecipes> list = recipesMapper.selectItemByCondition(cid, "%" + search + "%", orderCol, orderDir);

		List<String> cateList;
		for (TbRecipes recipes : list) {
			// 获得分类数据
			cateList = cateMapper.selectCateNameByItemIdAndType(recipes.getId(), RELA_REC);
			recipes.setCname(String.join(",", cateList));
		}

		PageInfo<TbRecipes> pageInfo = new PageInfo<>(list);

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
		List<TbRecipes> list = recipesMapper.selectItemByMultiCondition(cid, "%" + search + "%", minDate, maxDate,
				orderCol, orderDir);

		List<String> cateList;
		for (TbRecipes recipes : list) {
			// 获得分类数据
			cateList = cateMapper.selectCateNameByItemIdAndType(recipes.getId(), RELA_REC);
			recipes.setCname(String.join(",", cateList));
		}

		PageInfo<TbRecipes> pageInfo = new PageInfo<>(list);
		result.setRecordsFiltered((int) pageInfo.getTotal());
		result.setRecordsTotal(getAllItemCount().getRecordsTotal());

		result.setDraw(draw);
		result.setData(list);

		return result;
	}

	@Override
	public DataTablesResult getItemMealList(int draw, int start, int length, int cid, String search, String minDate,
			String maxDate, String orderCol, String orderDir) {

		// 获得三餐下的所有子id
		TbCateExample example = new TbCateExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(cid);
		List<TbCate> cates = cateMapper.selectByExample(example);

		String ids = "";

		for (TbCate tbCate : cates) {
			ids += tbCate.getRemark() + ",";
		}

		if (ids.length() > 0)
			ids = ids.substring(0, ids.length() - 1);
		else
			ids = "-1";

		// 分页执行查询返回结果
		PageHelper.startPage(start / length + 1, length);
		List<TbRecipes> list = recipesMapper.selectItemByIds(ids, orderCol, orderDir);

		List<String> cateList;
		for (TbRecipes recipes : list) {
			// 获得分类数据
			cateList = cateMapper.selectCateNameByItemIdAndType(recipes.getId(), RELA_REC);
			recipes.setCname(String.join(",", cateList));
		}

		PageInfo<TbRecipes> pageInfo = new PageInfo<>(list);

		DataTablesResult result = new DataTablesResult();
		result.setRecordsFiltered((int) pageInfo.getTotal());
		result.setRecordsTotal(getAllItemCount().getRecordsTotal());

		result.setDraw(draw);
		result.setData(list);

		return result;
	}

	@Override
	public int addItem(RecipesVo recipesVo) {
		TbRecipes recipes = DtoUtil.RecipesVo2TbRecipes(recipesVo);
		recipes.setStatus(true);
		recipes.setCreated(new Date());
		recipes.setUpdated(new Date());
		if (recipes.getUrl().isEmpty()) {
			recipes.setUrl(ITEM_REC_DEF_IMG);
		}
		if (recipesMapper.insert(recipes) < 1) {
			throw new GlobalException("添加食谱失败");
		}

		// 分类数据
		TbItemRelaCate itemRelaCate = new TbItemRelaCate();
		itemRelaCate.setItemId(recipes.getId());
		itemRelaCate.setType(RELA_REC);
		for (String cid : recipesVo.getCid()) {
			if (StringUtils.isBlank(cid))
				continue;
			itemRelaCate.setCateId(Integer.valueOf(cid));
			itemRelaCateMapper.insert(itemRelaCate);
		}

		try {
			jedisClient.del(ITEM_COUNT + ":" + RECIPES);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 1;
	}

	@Override
	public int deleteItem(int id) {
		if (recipesMapper.deleteByPrimaryKey(id) != 1) {
			throw new GlobalException("删除食谱失败");
		}

		TbItemRelaCateExample example = new TbItemRelaCateExample();
		TbItemRelaCateExample.Criteria criteria = example.createCriteria();
		criteria.andCateIdEqualTo(id);
		itemRelaCateMapper.deleteByExample(example);

		try {
			jedisClient.del(ITEM_ID + ":" + RECIPES + ":" + id);
			jedisClient.del(ITEM_DETAIL_ID + ":" + RECIPES + ":" + id);
			jedisClient.del(ITEM_COUNT + ":" + RECIPES);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 1;
	}

	@Override
	public int alertItemState(Integer id, Boolean state) {

		TbRecipes item = getNormalItemById(id);
		item.setStatus(state);
		item.setUpdated(new Date());

		if (recipesMapper.updateByPrimaryKey(item) < 1) {
			throw new GlobalException("修改食谱状态失败");
		}

		try {
			jedisClient.del(ITEM_ID + ":" + RECIPES + ":" + id);
			jedisClient.del(ITEM_DETAIL_ID + ":" + RECIPES + ":" + id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 1;
	}

	@Override
	public int updateItem(Integer id, RecipesVo recipesVo) {

		TbRecipes oldRecipes = getNormalItemById(id);
		TbRecipes recipes = DtoUtil.RecipesVo2TbRecipes(recipesVo);
		recipes.setId(id);
		recipes.setCreated(oldRecipes.getCreated());
		recipes.setUpdated(new Date());
		recipes.setStatus(true);
		if (recipesMapper.updateByPrimaryKey(recipes) < 1) {
			throw new GlobalException("更新食谱失败");
		}

		TbItemRelaCateExample example = new TbItemRelaCateExample();
		TbItemRelaCateExample.Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(id);
		if (itemRelaCateMapper.deleteByExample(example) < 1) {
			throw new GlobalException("删除食谱&分类关系失败");
		}

		TbItemRelaCate itemRelaCate = new TbItemRelaCate();

		// 分类数据
		itemRelaCate.setItemId(id);
		itemRelaCate.setType(RELA_REC);
		for (String did : recipesVo.getCid()) {
			if (StringUtils.isBlank(did))
				continue;
			itemRelaCate.setCateId(Integer.valueOf(did));
			itemRelaCateMapper.insert(itemRelaCate);
		}

		// 同步缓存
		try {
			jedisClient.del(ITEM_ID + ":" + RECIPES + ":" + id);
			jedisClient.del(ITEM_DETAIL_ID + ":" + RECIPES + ":" + id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 1;
	}

	/**
	 * 发送消息同步索引库
	 * 
	 * @param type
	 * @param id
	 */
	public void sendRefreshESMessage(String type, int id) {
		jmsTemplate.send(topicDestination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = session.createTextMessage(type + "," + String.valueOf(id));
				return textMessage;
			}
		});
	}

}
