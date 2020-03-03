package com.muyou.service.impl;

import java.util.Calendar;
import java.util.Date;
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
import com.muyou.mapper.TbDataMapper;
import com.muyou.mapper.TbLogMapper;
import com.muyou.mapper.TbShiroFilterMapper;
import com.muyou.pojo.TbBase;
import com.muyou.pojo.TbData;
import com.muyou.pojo.TbDataExample;
import com.muyou.pojo.TbDataExample.Criteria;
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
	private TbDataMapper dataMapper;

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
		//以下部分,可以去找个网站计数的插件、API这些，更加方便
		
		DataTablesResult result = new DataTablesResult();
		result.setSuccess(true);

		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);

		List<Integer> list = null;

		// 数据库
		List<TbData> datas = null;

		// 操作switch
		int r = 0;

		// 缓存中是否有数据
		try {
			String json = jedisClient.get(PAGE_BROWSE + ":" + year);
			if (StringUtils.isNotBlank(json)) {
				list = JsonUtils.jsonToList(json, Integer.class);
				r = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 如果无数据,查询数据库
		if (0 == r) {
			TbDataExample example = new TbDataExample();
			example.setOrderByClause("create_time desc");
			Criteria criteria = example.createCriteria();
			criteria.andTypeEqualTo(PAGE_BROWSE + ":" + year);
			datas = dataMapper.selectByExample(example);
			if (null != datas && datas.size() > 0)
				r = 2;
		}

		// 操作
		switch (r) {
		// mysql、redis都没有数据,初始化数据,且两个数据库正常，把数据加入redis、msql
		case 0:
			try {
				list = new LinkedList<>();
				for (int i = 0; i < 12; i++) {
					list.add(new Integer(0));
				}
				list.set(month, list.get(month) + 1);
				result.setRecordsTotal(datas.get(0).getNum() + 1);

				// mysql
				TbData data = new TbData();
				data.setCreateTime(new Date());
				data.setDescription(JsonUtils.objectToJson(jedisClient.get(PAGE_BROWSE + ":" + year)));
				data.setNum(Integer.valueOf(jedisClient.get(PAGE_BROWSE + ":" + year + "NUM")));
				data.setType(PAGE_BROWSE + ":" + year);
				dataMapper.insert(data);

				// redis
				jedisClient.set(PAGE_BROWSE + ":" + year, JsonUtils.objectToJson(list));
				jedisClient.incr(PAGE_BROWSE + ":" + year + "NUM");
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		// redis正常
		case 1:
			try {
				// 是否增加
				if (isIncr) {
					list.set(month, list.get(month) + 1);
					jedisClient.set(PAGE_BROWSE + ":" + year, JsonUtils.objectToJson(list));
					result.setRecordsTotal(jedisClient.incr(PAGE_BROWSE + ":" + year + "NUM").intValue());

					// 如果访问量到底100的倍数,添加入mysql
					if (0 == list.get(month) % 100) {
						TbData data = new TbData();
						data.setCreateTime(new Date());
						data.setDescription(JsonUtils.objectToJson(jedisClient.get(PAGE_BROWSE + ":" + year)));
						data.setNum(Integer.valueOf(jedisClient.get(PAGE_BROWSE + ":" + year + "NUM")));
						data.setType(PAGE_BROWSE + ":" + year);
						dataMapper.insert(data);
					}
				} else
					result.setRecordsTotal(Integer.valueOf(jedisClient.get(PAGE_BROWSE + ":" + year + "NUM")));
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		// redis没有数据,mysql有数据
		case 2:
			list = JsonUtils.jsonToList(datas.get(0).getDescription(), Integer.class);
			// 是否增加
			if (isIncr) {
				list.set(month, list.get(month) + 1);
				result.setRecordsTotal(datas.get(0).getNum() + 1);
			} else
				result.setRecordsTotal(datas.get(0).getNum());
			
			//如果redis正常,且无数据
			try {
				jedisClient.set(PAGE_BROWSE + ":" + year, JsonUtils.objectToJson(list));
				jedisClient.incr(PAGE_BROWSE + ":" + year + "NUM");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//至于redis异常
			//不进行mysql插入,因为这只有在redis报废的情况下,这样的redis已经异常,访问量+1过于频繁,会增加mysql的压力,所以,还不如不添加。个人理解。
			break;
		}

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
