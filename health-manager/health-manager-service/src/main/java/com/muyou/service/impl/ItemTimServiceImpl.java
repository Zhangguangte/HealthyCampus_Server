package com.muyou.service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.muyou.common.exception.GlobalException;
import com.muyou.common.pojo.DataTablesResult;
import com.muyou.common.redis.JedisClient;
import com.muyou.common.util.JsonUtils;
import com.muyou.mapper.TbAttendMapper;
import com.muyou.mapper.TbCateMapper;
import com.muyou.mapper.TbItemRelaCateMapper;
import com.muyou.mapper.TbTimeAttendMapper;
import com.muyou.mapper.TbTimetableMapper;
import com.muyou.pojo.TbAdminExample;
import com.muyou.pojo.TbAttend;
import com.muyou.pojo.TbAttendExample;
import com.muyou.pojo.TbCate;
import com.muyou.pojo.TbItemRelaCate;
import com.muyou.pojo.TbItemRelaCateExample;
import com.muyou.pojo.TbTimeAttend;
import com.muyou.pojo.TbTimeAttendExample;
import com.muyou.pojo.TbTimetable;
import com.muyou.pojo.TbTimetableExample;
import com.muyou.pojo.TbTimetableExample.Criteria;
import com.muyou.service.ItemTimService;
import com.muyou.vo.AttendDateVo;
import com.muyou.vo.AttendListVo;
import com.muyou.vo.AttendVo;
import com.muyou.vo.TimeTableVo;

@Service
public class ItemTimServiceImpl implements ItemTimService {

	@Autowired
	private TbCateMapper cateMapper;

	@Autowired
	private TbTimeAttendMapper timeAttendMapper;

	@Autowired
	private TbAttendMapper attendMapper;

	@Autowired
	private TbTimetableMapper timetableMapper;

	@Autowired
	private TbItemRelaCateMapper itemRelaCateMapper;

	@Autowired
	private JedisClient jedisClient;

	@Value("${ITEM_DETAIL_ID}")
	private String ITEM_DETAIL_ID;

	@Value("${TIMETABLE}")
	private String TIMETABLE;

	@Value("${ATTEND_LIST}")
	private String ATTEND_LIST;

	@Value("${ATTEND_DATE}")
	private String ATTEND_DATE;

	@Value("${RELA_TIM}")
	private Integer RELA_TIM;

	@Value("${ITEM_CLASS}")
	private Integer ITEM_CLASS;

	@Value("${ITEM_EXPIRE}")
	private Integer ITEM_EXPIRE;

	@Override
	public TimeTableVo getItemList(Integer cid, Integer year, Integer semester) {

		TbCate cate = cateMapper.selectByPrimaryKey(cid);
		String tid = cate.getRemark();
		TbTimetableExample example = new TbTimetableExample();
		Criteria criteria = example.createCriteria();
		criteria.andTIdEqualTo(Integer.valueOf(tid));
		criteria.andCYearEqualTo(year);
		criteria.andSemesterEqualTo(semester);
		List<TbTimetable> list = timetableMapper.selectByExample(example);

		String descs[][] = { { "", "", "", "", "", "", "", "", "", "", "", "" },
				{ "", "", "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "", "", "" },
				{ "", "", "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "", "", "" },
				{ "", "", "", "", "", "", "", "", "", "", "", "" },
				{ "", "", "", "", "", "", "", "", "", "", "", "" }, };

		String ids[][] = { { "", "", "", "", "", "", "", "", "", "", "", "" },
				{ "", "", "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "", "", "" },
				{ "", "", "", "", "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "", "", "", "", "" },
				{ "", "", "", "", "", "", "", "", "", "", "", "" },
				{ "", "", "", "", "", "", "", "", "", "", "", "" }, };

		List<String> cateList;
		StringBuffer sbBuffer = new StringBuffer();
		for (TbTimetable timetable : list) {
			sbBuffer.setLength(0);
			// 分类数据
			cateList = cateMapper.selectCateNameByItemIdAndType(timetable.getId(), RELA_TIM);
			timetable.setDescr(
					sbBuffer.append(timetable.getDescr()).append("\r\n").append(String.join(",", cateList)).toString());
			for (int i = timetable.getcStart(), j = 0; j < timetable.getPeriod(); i++, j++) {
				descs[timetable.getWeeks() - 1][i - 1] = timetable.getDescr();
				ids[timetable.getWeeks() - 1][i - 1] = "" + timetable.getId();
			}
		}

		TimeTableVo result = new TimeTableVo();
		result.setIds(ids);
		result.setDescs(descs);
		return result;
	}

	@Override
	public int delItem(Integer id) {
		if (timetableMapper.deleteByPrimaryKey(id) < 1)
			throw new GlobalException("删除课表异常");
		TbItemRelaCateExample example = new TbItemRelaCateExample();
		TbItemRelaCateExample.Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(id);
		if (itemRelaCateMapper.deleteByExample(example) < 1)
			throw new GlobalException("删除课表异常");

		return 1;
	}

	@Override
	public int addItem(TbTimetable timetable) {
		// 获得教师id
		TbCate cate = cateMapper.selectByPrimaryKey(timetable.gettId());
		timetable.settId(Integer.valueOf(cate.getRemark()));
		// 期间是否已经有课了
		TbTimetableExample example = new TbTimetableExample();
		Criteria criteria = example.createCriteria();
		criteria.andTIdEqualTo(Integer.valueOf(cate.getRemark()));
		criteria.andSemesterEqualTo(timetable.getSemester());
		criteria.andCYearEqualTo(timetable.getcYear());
		criteria.andWeeksEqualTo(timetable.getWeeks());
		criteria.andCStartBetween(timetable.getcStart(), timetable.getcStart() + timetable.getPeriod() - 1);
		List<TbTimetable> list = timetableMapper.selectByExample(example);
		if (null != list && list.size() > 0)
			return 0;
		// 插入基本数据
		timetable.setState(true);
		timetable.setDescr("'" + timetable.getDescr().replace(",", "\r").replace("，", "\r") + "'");
		timetable.setCreated(new Date());
		timetable.setUpdated(new Date());
		if (timetableMapper.insert(timetable) < 1)
			throw new GlobalException("添加课表异常");
		// 分类数据
		TbItemRelaCate itemRelaCate = new TbItemRelaCate();
		itemRelaCate.setItemId(timetable.getId());
		itemRelaCate.setType(RELA_TIM);
		for (String cid : timetable.getCid()) {
			if (StringUtils.isBlank(cid))
				continue;
			itemRelaCate.setCateId(Integer.valueOf(cid));
			itemRelaCateMapper.insert(itemRelaCate);
		}
		return 1;
	}

	@Override
	public int updateItem(Integer id, TbTimetable timetable) {

		TbTimetable oldtimetable = getNormalItemById(id);
		timetable.setId(id);
		timetable.setCreated(oldtimetable.getCreated());
		timetable.setUpdated(new Date());
		timetable.setState(true);
		if (timetableMapper.updateByPrimaryKey(timetable) < 1) {
			throw new GlobalException("更新课表失败");
		}

		TbItemRelaCateExample example = new TbItemRelaCateExample();
		TbItemRelaCateExample.Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(id);
		if (itemRelaCateMapper.deleteByExample(example) < 1) {
			throw new GlobalException("删除课表&分类关系失败");
		}

		TbItemRelaCate itemRelaCate = new TbItemRelaCate();
		// 分类数据
		itemRelaCate.setItemId(id);
		itemRelaCate.setType(RELA_TIM);
		for (String did : timetable.getCid()) {
			if (StringUtils.isBlank(did))
				continue;
			itemRelaCate.setCateId(Integer.valueOf(did));
			itemRelaCateMapper.insert(itemRelaCate);
		}
		return 1;
	}

	@Override
	public TbTimetable getNormalItemById(Integer id) {
		try {
			String json = jedisClient.get(ITEM_DETAIL_ID + ":" + TIMETABLE + ":" + id);
			if (StringUtils.isNotBlank(json))
				return JsonUtils.jsonToPojo(json, TbTimetable.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbTimetable result = timetableMapper.selectByPrimaryKey(id);

		try {
			jedisClient.set(ITEM_DETAIL_ID + ":" + TIMETABLE + ":" + id, JsonUtils.objectToJson(result));
			jedisClient.expire(ITEM_DETAIL_ID + ":" + TIMETABLE + ":" + id, ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public TbTimetable getItemById(Integer id) {

		TbTimetable result = getNormalItemById(id);
		// 获得分类数据
		List<String> cateList = new LinkedList<String>();
		List<String> cidList = new LinkedList<String>();
		List<TbCate> list = cateMapper.selectItemCate(id, RELA_TIM);
		for (TbCate tbCate : list) {
			cateList.add(tbCate.getName());
			cidList.add(tbCate.getId() + "");
		}
		result.setCid(cidList);
		result.setCname(cateList);

		return result;
	}

	@Override
	public int beginAttend(Integer id) {
		String no = UUID.randomUUID().toString();
		TbTimeAttend timeAttend = new TbTimeAttend();
		timeAttend.setCreated(new Date());
		timeAttend.setUpdated(new Date());
		timeAttend.setStatus(true);
		timeAttend.setaNo(no);
		timeAttend.settId(id);
		timeAttendMapper.insert(timeAttend);
		return 1;
	}

	@Override
	public int finishAttend(Integer id) {
		TbTimeAttendExample example = new TbTimeAttendExample();
		TbTimeAttendExample.Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(true);
		criteria.andTIdEqualTo(id);
		List<TbTimeAttend> list = timeAttendMapper.selectByExample(example);
		if (null == list || list.size() < 0)
			throw new GlobalException("结束考勤失败");

		TbTimeAttend timeAttend = list.get(0);
		timeAttend.setEndTime(new Date());
		timeAttend.setStatus(false);
		timeAttendMapper.updateByPrimaryKey(timeAttend);
		try {
			jedisClient.del(ATTEND_LIST + ":" + id);
			jedisClient.del(ATTEND_DATE + ":" + id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public AttendVo beforeAttend(Integer id) {
		TbTimeAttendExample example = new TbTimeAttendExample();
		TbTimeAttendExample.Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(true);
		criteria.andTIdEqualTo(id);
		List<TbTimeAttend> list = timeAttendMapper.selectByExample(example);
		AttendVo result = new AttendVo();
		if (null != list && list.size() > 0) {
			result.setIsFinsh(true);
			result.setTime(list.get(0).getCreated());
		} else
			result.setIsFinsh(false);
		return result;
	}

	@Override
	public List<AttendVo> attendList(Integer id) {
		try {
			String json = jedisClient.get(ATTEND_LIST + ":" + id);
			if (StringUtils.isNotBlank(json))
				return JsonUtils.jsonToList(json, AttendVo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<AttendVo> result = timeAttendMapper.selectAttendListByTid(id);
		try {
			jedisClient.set(ATTEND_LIST + ":" + id, JsonUtils.objectToJson(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<AttendDateVo> attendDate(Integer tid) {
		try {
			String json = jedisClient.get(ATTEND_DATE + ":" + tid);
			if (StringUtils.isNotBlank(json))
				return JsonUtils.jsonToList(json, AttendDateVo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<AttendDateVo> result = timeAttendMapper.selectDateListByTid(tid);

		try {
			jedisClient.set(ATTEND_DATE + ":" + tid, JsonUtils.objectToJson(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public DataTablesResult attendListByDate(String date, Integer tid) {

		List<AttendListVo> list = new LinkedList<AttendListVo>();

		// 获得已签到ID
		List<String> ids = timeAttendMapper.getAttendIds(date, tid);

		// 获得签到班级
		List<String> cls = timeAttendMapper.getAttendCls(tid, ITEM_CLASS);

		// 获得未签到数据
		List<AttendListVo> ablist = timeAttendMapper.attendAbsenceByTid("(" + String.join(",", ids) + ")",
				"(" + String.join(",", cls) + ")");
		if (null != ablist && ablist.size() > 0)
			list.addAll(ablist);
		
		// 获得已签到数据
		List<AttendListVo> dolist = timeAttendMapper.attendListByDate(date, tid);
		if (null != dolist && dolist.size() > 0)
			list.addAll(dolist);

		DataTablesResult result = new DataTablesResult();
		PageInfo<AttendListVo> pageInfo = new PageInfo<>(list);
		result.setRecordsFiltered((int) pageInfo.getTotal());
		result.setRecordsTotal(list.size());
		result.setData(list);
		return result;
	}

	@Override
	public int updateAttend(Integer id, String no, Integer type) {
		TbAttendExample example = new TbAttendExample();
		TbAttendExample.Criteria criteria = example.createCriteria();
		criteria.andNoEqualTo(no);
		criteria.andSIdEqualTo(id);
		List<TbAttend> list = attendMapper.selectByExample(example);
		if (null != list && list.size() > 0) {
			TbAttend attend = list.get(0);
			attend.setType(type);
			attendMapper.updateByPrimaryKey(attend);
		} else {
			TbAttend attend = new TbAttend();
			attend.setCreated(new Date());
			attend.setNo(no);
			attend.setsId(id);
			attend.setType(type);
			attendMapper.insert(attend);
		}

		return 1;
	}

}
