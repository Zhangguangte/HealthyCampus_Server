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
import com.muyou.mapper.TbArticleMapper;
import com.muyou.pojo.TbArticle;
import com.muyou.pojo.TbArticleExample;
import com.muyou.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private TbArticleMapper articleMapper;

	@Autowired
	private JedisClient jedisClient;

	@Value("${ITEM_ID}")
	private String ITEM_ID;

	@Value("${ARTICLE}")
	private String ARTICLE;

	@Value("${ITEM_EXPIRE}")
	private Integer ITEM_EXPIRE;

	@Value("${ITEM_COUNT}")
	private String ITEM_COUNT;

	@Override
	public DataTablesResult getItemList(int draw, int start, int length, String orderCol, String orderDir) {
		DataTablesResult result = new DataTablesResult();

		// 分页执行查询返回结果
		PageHelper.startPage(start / length + 1, length);
		TbArticleExample example = new TbArticleExample();
		List<TbArticle> list = articleMapper.selectByExample(example);
		PageInfo<TbArticle> pageInfo = new PageInfo<>(list);
		result.setRecordsFiltered((int) pageInfo.getTotal());
		result.setRecordsTotal((int) pageInfo.getTotal());
		result.setSuccess(true);
		result.setData(list);

		return result;
	}

	@Override
	public DataTablesResult getAllItemCount() {

		try {
			String json = jedisClient.get(ITEM_COUNT + ":" + ARTICLE);
			if (StringUtils.isNotBlank(json))
				return JsonUtils.jsonToPojo(json, DataTablesResult.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbArticleExample example = new TbArticleExample();
		int count = articleMapper.countByExample(example);
		DataTablesResult result = new DataTablesResult();
		result.setRecordsTotal(count);

		try {
			jedisClient.set(ITEM_COUNT + ":" + ARTICLE, JsonUtils.objectToJson(result));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public int addItem(TbArticle article) {
		article.setStatus(true);
		article.setCreated(new Date());
		article.setUpdated(new Date());
		
		if (articleMapper.insert(article) < 1) {
			throw new GlobalException("添加文章失败");
		}
		try {
			jedisClient.del(ITEM_COUNT + ":" + ARTICLE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return article.getId();
	}

	@Override
	public int deleteItem(int id) {
		if (articleMapper.deleteByPrimaryKey(id) != 1) {
			throw new GlobalException("删除文章失败");
		}

		try {
			jedisClient.del(ITEM_ID + ":" + ARTICLE + ":" + id);
			jedisClient.del(ITEM_COUNT + ":" + ARTICLE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 1;
	}

	@Override
	public int alertItemState(Integer id, Boolean state) {

		TbArticle item = getItemById(id);
		item.setStatus(state);
		item.setUpdated(new Date());

		if (articleMapper.updateByPrimaryKey(item) < 1) {
			throw new GlobalException("修改文章状态失败");
		}

		try {
			jedisClient.del(ITEM_ID + ":" + ARTICLE + ":" + id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 1;
	}

	@Override
	public int updateItem(Integer id, TbArticle article) {
		TbArticle oldArticle = getItemById(id);
		article.setId(id);
		article.setCreated(oldArticle.getCreated());
		article.setUpdated(new Date());
		article.setStatus(true);
		
		if (articleMapper.updateByPrimaryKey(article) < 1) {
			throw new GlobalException("更新文章失败");
		}
		// 同步缓存
		try {
			jedisClient.del(ITEM_ID + ":" + ARTICLE + ":" + id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 1;
	}

	@Override
	public TbArticle getItemById(Integer itemId) {
		try {
			String json = jedisClient.get(ITEM_ID + ":" + ARTICLE + ":" + itemId);
			if (StringUtils.isNotBlank(json))
				return JsonUtils.jsonToPojo(json, TbArticle.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbArticle result = articleMapper.selectByPrimaryKey(itemId);

		try {
			jedisClient.set(ITEM_ID + ":" + ARTICLE + ":" + itemId, JsonUtils.objectToJson(result));
			jedisClient.expire(ITEM_ID + ":" + ARTICLE + ":" + itemId, ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}
