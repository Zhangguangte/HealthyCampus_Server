package com.muyou.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.muyou.common.exception.GlobalException;
import com.muyou.common.pojo.DataTablesResult;
import com.muyou.common.redis.JedisClient;
import com.muyou.common.util.JsonUtils;
import com.muyou.mapper.TbArticleMapper;
import com.muyou.mapper.TbPanelContentMapper;
import com.muyou.pojo.TbArticle;
import com.muyou.pojo.TbPanelContent;
import com.muyou.pojo.TbPanelContentExample;
import com.muyou.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private JedisClient jedisClient;

	@Autowired
	private TbPanelContentMapper panelContentMapper;

	@Autowired
	private TbArticleMapper articleMapper;

	@Value("${CONTENT_PANEL}")
	private String CONTENT_PANEL;

	@Override
	public DataTablesResult getPanelContentListByPanelId(int panelId) {
		try {
			String json = jedisClient.hget(CONTENT_PANEL, "" + panelId);
			if (StringUtils.isNotBlank(json))
				return JsonUtils.jsonToPojo(json, DataTablesResult.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		DataTablesResult result = new DataTablesResult();
		List<TbPanelContent> list = new ArrayList<>();

		TbPanelContentExample example = new TbPanelContentExample();
		TbPanelContentExample.Criteria criteria = example.createCriteria();
		// 条件查询
		criteria.andPanelIdEqualTo(panelId);
		list = panelContentMapper.selectByExample(example);
		for (TbPanelContent content : list) {
			if (content.getArticleId() != null) {
				TbArticle article = articleMapper.selectByPrimaryKey(content.getArticleId());
				content.setArticleName(article.getName());
			}
		}

		result.setData(list);
		try {
			jedisClient.hset(CONTENT_PANEL, "" + panelId, JsonUtils.objectToJson(result));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public int addPanelContent(TbPanelContent tbPanelContent) {
		tbPanelContent.setCreated(new Date());
		tbPanelContent.setUpdated(new Date());
		if (panelContentMapper.insert(tbPanelContent) < 1) {
			throw new GlobalException("添加首页板块内容失败");
		}

		try {
			jedisClient.del(CONTENT_PANEL);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 1;
	}

	@Override
	public int deletePanelContent(int id) {
		if (panelContentMapper.deleteByPrimaryKey(id) < 1) {
			throw new GlobalException("删除首页板块失败");
		}

		try {
			jedisClient.del(CONTENT_PANEL);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 1;
	}

	@Override
	public int updateContent(TbPanelContent tbPanelContent) {
		TbPanelContent old = getTbPanelContentById(tbPanelContent.getId());
		tbPanelContent.setCreated(old.getCreated());
		tbPanelContent.setUpdated(new Date());
		if (panelContentMapper.updateByPrimaryKey(tbPanelContent) != 1) {
			throw new GlobalException("更新板块内容失败");
		}

		try {
			jedisClient.del(CONTENT_PANEL);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 1;
	}

	@Override
	public TbPanelContent getTbPanelContentById(int id) {
		TbPanelContent tbPanelContent = panelContentMapper.selectByPrimaryKey(id);
		if (tbPanelContent == null) {
			throw new GlobalException("通过id获取板块内容失败");
		}
		return tbPanelContent;
	}

}
