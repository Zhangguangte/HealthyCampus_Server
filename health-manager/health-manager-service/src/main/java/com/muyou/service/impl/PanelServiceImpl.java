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
import com.muyou.mapper.TbPanelMapper;
import com.muyou.pojo.TbPanel;
import com.muyou.pojo.TbPanelExample;
import com.muyou.service.PanelService;

@Service
public class PanelServiceImpl implements PanelService {

	@Autowired
	private TbPanelMapper panelMapper;

	@Autowired
	private JedisClient jedisClient;

	@Value("${PANEL_ID}")
	private String PANEL_ID;

	@Value("${PANEL_LIST}")
	private String PANEL_LIST;

	@Value("${PANEL_EXPIRE}")
	private Integer PANEL_EXPIRE;

	@Override
	public TbPanel getTbPanelById(int id) {

		try {
			String json = jedisClient.get(PANEL_ID + ":" + id);
			if (StringUtils.isNotBlank(json))
				return JsonUtils.jsonToPojo(json, TbPanel.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbPanel result = panelMapper.selectByPrimaryKey(id);
		if (result == null) {
			throw new GlobalException("通过id获得板块失败");
		}

		try {
			jedisClient.set(PANEL_ID + ":" + id, JsonUtils.objectToJson(result));
			jedisClient.expire(PANEL_ID + ":" + id, PANEL_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public List<ZTreeNode> getPanelList(int position, boolean showAll) {
		String key = showAll + ":" + position;
		try {
			String json = jedisClient.hget(PANEL_LIST, key);
			if (StringUtils.isNotBlank(json))
				return JsonUtils.jsonToList(json, ZTreeNode.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbPanelExample example = new TbPanelExample();
		TbPanelExample.Criteria criteria = example.createCriteria();
		if (position == 0 && !showAll) {
			// 除去非轮播
			criteria.andTypeNotEqualTo(0);
		} else if (position == -1) {
			// 仅含轮播
			position = 0;
			criteria.andTypeEqualTo(0);
		}
		// 首页板块
		criteria.andPositionEqualTo(position);
		example.setOrderByClause("sort_order");
		List<TbPanel> panelList = panelMapper.selectByExample(example);

		List<ZTreeNode> result = new ArrayList<>();
		for (TbPanel panel : panelList) {
			ZTreeNode zTreeNode = DtoUtil.TbPanel2ZTreeNode(panel);
			result.add(zTreeNode);
		}

		try {
			jedisClient.hset(PANEL_LIST, key, JsonUtils.objectToJson(result));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public int addPanel(TbPanel tbPanel) {
		if (tbPanel.getType() == 0) {
			TbPanelExample example = new TbPanelExample();
			TbPanelExample.Criteria criteria = example.createCriteria();
			criteria.andTypeEqualTo(0);
			List<TbPanel> list = panelMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				throw new GlobalException("已有轮播图板块,轮播图仅能添加1个!");
			}
		}

		tbPanel.setCreated(new Date());
		tbPanel.setUpdated(new Date());

		if (panelMapper.insert(tbPanel) != 1) {
			throw new GlobalException("添加板块失败");
		}

		try {
			jedisClient.del(PANEL_LIST);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 1;
	}

	@Override
	public int updatePanel(TbPanel tbPanel) {

		if (0 == tbPanel.getType()) {
			TbPanelExample example = new TbPanelExample();
			TbPanelExample.Criteria criteria = example.createCriteria();
			criteria.andTypeEqualTo(0);
			List<TbPanel> list = panelMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				throw new GlobalException("已有轮播图板块,轮播图仅能添加1个!");
			}
		}

		TbPanel old = getTbPanelById(tbPanel.getId());
		tbPanel.setCreated(old.getCreated());
		tbPanel.setUpdated(new Date());
		if (panelMapper.updateByPrimaryKey(tbPanel) != 1) {
			throw new GlobalException("更新板块失败");
		}

		try {
			jedisClient.del(PANEL_LIST);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 1;
	}

	@Override
	public int deletePanel(int id) {
		if (panelMapper.deleteByPrimaryKey(id) != 1) {
			throw new GlobalException("删除内容分类失败");
		}

		try {
			jedisClient.del(PANEL_LIST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}
}
