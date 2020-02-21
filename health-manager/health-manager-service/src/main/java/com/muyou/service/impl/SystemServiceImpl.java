package com.muyou.service.impl;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.muyou.common.pojo.DataTablesResult;
import com.muyou.common.pojo.Result;
import com.muyou.common.redis.JedisClient;
import com.muyou.common.util.JsonUtils;
import com.muyou.common.util.ResultUtil;
import com.muyou.mapper.TbBaseMapper;
import com.muyou.mapper.TbLogMapper;
import com.muyou.mapper.TbShiroFilterMapper;
import com.muyou.pojo.TbBase;
import com.muyou.pojo.TbLog;
import com.muyou.pojo.TbLogExample;
import com.muyou.pojo.TbShiroFilter;
import com.muyou.pojo.TbShiroFilterExample;
import com.muyou.service.SystemService;

@Service
public class SystemServiceImpl implements SystemService {

	@Autowired
	private TbBaseMapper tbBaseMapper;

	@Autowired
	private TbShiroFilterMapper tbShiroFilterMapper;

	@Autowired
	private TbLogMapper tbLogMapper;

	@Autowired
	private JedisClient jedisClient;

	@Value("${PAGE_BROWSE}")
	private String PAGE_BROWSE;
	
	@Value("${SYTEM_PRE}")
	private String SYTEM_PRE;

	@Value("${SYTEM_BASE_ID}")
	private int SYTEM_BASE_ID;

	@Value("${SHIRO_COUNT}")
	private String SHIRO_COUNT;

	@Value("${SHIRO_LIST}")
	private String SHIRO_LIST;

	@Value("${LOG_COUNT}")
	private String LOG_COUNT;

	@Value("${LOG_LIST}")
	private String LOG_LIST;

	@Override
	public List<TbShiroFilter> getShiroFilter() {

		try {
			String json = jedisClient.get(SHIRO_LIST);
			if (StringUtils.isNotBlank(json))
				return JsonUtils.jsonToList(json, TbShiroFilter.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbShiroFilterExample example = new TbShiroFilterExample();
		example.setOrderByClause("sortOrder");

		List<TbShiroFilter> result = tbShiroFilterMapper.selectByExample(example);

		try {
			jedisClient.set(SHIRO_LIST, JsonUtils.objectToJson(result));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public int countShiroFilter() {
		try {
			String json = jedisClient.get(SHIRO_COUNT);
			if (StringUtils.isNotBlank(json))
				return Integer.valueOf(json);
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbShiroFilterExample example = new TbShiroFilterExample();
		int result = tbShiroFilterMapper.countByExample(example);

		try {
			jedisClient.set(SHIRO_COUNT, result + "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int addShiroFilter(TbShiroFilter tbShiroFilter) {
		int result = tbShiroFilterMapper.insert(tbShiroFilter);
		if (1 == result) {
			try {
				jedisClient.del(SHIRO_LIST);
				jedisClient.del(SHIRO_COUNT);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public int updateShiroFilter(TbShiroFilter tbShiroFilter) {
		int result = tbShiroFilterMapper.updateByPrimaryKey(tbShiroFilter);
		if (1 == result) {
			try {
				jedisClient.del(SHIRO_LIST);
				jedisClient.del(SHIRO_COUNT);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public int deleteShiroFilter(int id) {
		int result = tbShiroFilterMapper.deleteByPrimaryKey(id);
		if (1 == result) {
			try {
				jedisClient.del(SHIRO_LIST);
				jedisClient.del(SHIRO_COUNT);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public DataTablesResult getBrowseCount(boolean isIncr) {

		DataTablesResult result = new DataTablesResult();
		result.setSuccess(true);
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		
		List<Integer> list = null;

		boolean r = true;

		try {
			String json = jedisClient.get(PAGE_BROWSE + ":" + year);
			if (StringUtils.isNotBlank(json)) {
				list = JsonUtils.jsonToList(json, Integer.class);
				r = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (r) {
			list = new LinkedList<>();
			for (int i = 0; i < 12; i++) {
				list.add(new Integer(0));
			}
		}

		if (isIncr) {
			list.set(month, list.get(month) + 1);
			try {
				jedisClient.set(PAGE_BROWSE + ":" + year, JsonUtils.objectToJson(list));
			} catch (Exception e) {
				e.printStackTrace();
			}
			result.setRecordsTotal(jedisClient.incr(PAGE_BROWSE + ":" + year + "NUM").intValue());
		} else
			result.setRecordsTotal(Integer.valueOf(jedisClient.get(PAGE_BROWSE + ":" + year + "NUM")));
		result.setData(list);
		return result;
	}

	@Override
	public Result<TbBase> getBase() {
		try {
			String json = jedisClient.get(SYTEM_PRE + SYTEM_BASE_ID);
			if (StringUtils.isNotBlank(json))
				return new ResultUtil<TbBase>().setData(JsonUtils.jsonToPojo(json, TbBase.class));
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbBase base = tbBaseMapper.selectByPrimaryKey(SYTEM_BASE_ID);
		if (null == base)
			return null;

		try {
			jedisClient.set(SYTEM_PRE + SYTEM_BASE_ID, JsonUtils.objectToJson(base));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultUtil<TbBase>().setData(base);
	}

	@Override
	public int updateBase(TbBase tbBase) {
		int result = tbBaseMapper.updateByPrimaryKey(tbBase);
		if (result == 1)
			try {
				jedisClient.del(SYTEM_PRE + SYTEM_BASE_ID);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return result;
	}

	@Override
	public int countLog() {
		try {
			String json = jedisClient.get(LOG_COUNT);
			if (StringUtils.isNotBlank(json))
				return Integer.valueOf(json);
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbLogExample example = new TbLogExample();
		int result = tbLogMapper.countByExample(example);

		try {
			jedisClient.set(LOG_COUNT, result + "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public DataTablesResult getLogList(int draw, int start, int length, String search, String orderCol,
			String orderDir) {
		try {
			String json = jedisClient.get(LOG_LIST);
			if (StringUtils.isNotBlank(json))
				return JsonUtils.jsonToPojo(json, DataTablesResult.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		DataTablesResult result = new DataTablesResult();
		// 分页
		PageHelper.startPage(start / length + 1, length);
		List<TbLog> list = tbLogMapper.selectByMulti("%" + search + "%", orderCol, orderDir);
		PageInfo<TbLog> pageInfo = new PageInfo<>(list);

		result.setRecordsFiltered((int) pageInfo.getTotal());
		result.setRecordsTotal(Math.toIntExact(countLog()));

		result.setDraw(draw);
		result.setData(list);

		try {
			jedisClient.set(LOG_LIST, JsonUtils.objectToJson(result));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public int deleteLog(int id) {
		int result = tbLogMapper.deleteByPrimaryKey(id);
		if (result == 1)
			try {
				jedisClient.del(LOG_LIST);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return result;
	}

}
