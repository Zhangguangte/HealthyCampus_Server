package com.muyou.service.impl;

import java.util.Date;
import java.util.LinkedList;
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
import com.muyou.dto.DtoUtil;
import com.muyou.mapper.TbCateMapper;
import com.muyou.mapper.TbItemRelaCateMapper;
import com.muyou.mapper.TbLibraryMapper;
import com.muyou.pojo.TbCate;
import com.muyou.pojo.TbItemRelaCate;
import com.muyou.pojo.TbItemRelaCateExample;
import com.muyou.pojo.TbLibrary;
import com.muyou.pojo.TbLibraryExample;
import com.muyou.service.ItemLibService;
import com.muyou.vo.LibraryVo;

@Service
public class ItemLibServiceImpl implements ItemLibService {

	@Autowired
	private JedisClient jedisClient;

	@Autowired
	private TbLibraryMapper libraryMapper;

	@Autowired
	private TbCateMapper cateMapper;

	@Autowired
	private TbItemRelaCateMapper itemRelaCateMapper;

	@Value("${ITEM_COUNT}")
	private String ITEM_COUNT;

	@Value("${ITEM_ID}")
	private String ITEM_ID;

	@Value("${RELA_LIB}")
	private Integer RELA_LIB;

	@Value("${ITEM_EXPIRE}")
	private Integer ITEM_EXPIRE;

	@Value("${ITEM_LIB_DEF_IMG}")
	private String ITEM_LIB_DEF_IMG;

	@Value("${ITEM_DETAIL_ID}")
	private String ITEM_DETAIL_ID;

	@Value("${LIBRARY}")
	private String LIBRARY;

	@Override
	public LibraryVo getItemById(Integer id) {

		try {
			String json = jedisClient.get(ITEM_DETAIL_ID + ":" + LIBRARY + ":" + id);
			if (StringUtils.isNotBlank(json))
				return JsonUtils.jsonToPojo(json, LibraryVo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 设置图书数据
		LibraryVo result = DtoUtil.TbLibrary2LibraryVo(libraryMapper.selectByPrimaryKey(id));

		List<String> cateList = new LinkedList<String>();
		List<String> cidList = new LinkedList<String>();

		// 获得分类数据
		List<TbCate> list = cateMapper.selectItemCate(id, RELA_LIB);
		for (TbCate tbCate : list) {
			cateList.add(tbCate.getName());
			cidList.add(tbCate.getId() + "");
		}
		result.setCid(cidList);
		result.setCname(cateList);

		try {
			jedisClient.set(ITEM_DETAIL_ID + ":" + LIBRARY + ":" + id, JsonUtils.objectToJson(result));
			jedisClient.expire(ITEM_DETAIL_ID + ":" + LIBRARY + ":" + id, ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}

	@Override
	public TbLibrary getNormalItemById(Integer id) {

		try {
			String json = jedisClient.get(ITEM_ID + ":" + LIBRARY + ":" + id);
			if (StringUtils.isNotBlank(json))
				return JsonUtils.jsonToPojo(json, TbLibrary.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbLibrary result = libraryMapper.selectByPrimaryKey(id);

		try {
			jedisClient.set(ITEM_ID + ":" + LIBRARY + ":" + id, JsonUtils.objectToJson(result));
			jedisClient.expire(ITEM_ID + ":" + LIBRARY + ":" + id, ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public DataTablesResult getAllItemCount() {

		try {
			String json = jedisClient.get(ITEM_COUNT + ":" + LIBRARY);
			if (StringUtils.isNotBlank(json))
				return JsonUtils.jsonToPojo(json, DataTablesResult.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbLibraryExample example = new TbLibraryExample();
		int count = libraryMapper.countByExample(example);
		DataTablesResult result = new DataTablesResult();
		result.setRecordsTotal(count);

		try {
			jedisClient.set(ITEM_COUNT + ":" + LIBRARY, JsonUtils.objectToJson(result));
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

		List<TbLibrary> list = libraryMapper.selectItemByCondition(cid, orderCol, orderDir);

		List<String> cateList;
		for (TbLibrary library : list) {
			// 获得分类数据
			cateList = cateMapper.selectCateNameByItemIdAndType(library.getId(), RELA_LIB);
			library.setCname(String.join(",", cateList));
		}

		PageInfo<TbLibrary> pageInfo = new PageInfo<>(list);

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
		List<TbLibrary> list = libraryMapper.selectItemByMultiCondition(cid, "%" + search + "%", minDate, maxDate,
				orderCol, orderDir);

		List<String> cateList;
		for (TbLibrary library : list) {
			// 获得分类数据
			cateList = cateMapper.selectCateNameByItemIdAndType(library.getId(), RELA_LIB);
			library.setCname(String.join(",", cateList));
		}

		PageInfo<TbLibrary> pageInfo = new PageInfo<>(list);
		result.setRecordsFiltered((int) pageInfo.getTotal());
		result.setRecordsTotal(getAllItemCount().getRecordsTotal());

		result.setDraw(draw);
		result.setData(list);

		return result;
	}

	@Override
	public int addItem(LibraryVo libraryVo) {
		TbLibrary library = DtoUtil.LibraryVo2TbLibrary(libraryVo);
		library.setStatus(true);
		library.setCreated(new Date());
		library.setUpdated(new Date());
		if (library.getUrl().isEmpty()) {
			library.setUrl(ITEM_LIB_DEF_IMG);
		}
		if (libraryMapper.insert(library) < 1) {
			throw new GlobalException("添加图书失败");
		}

		TbItemRelaCate itemRelaCate = new TbItemRelaCate();

		System.out.println(libraryVo.getCid());

		// 分类数据
		itemRelaCate.setItemId(library.getId());
		itemRelaCate.setType(RELA_LIB);
		for (String cid : libraryVo.getCid()) {

			System.out.println(cid);

			if (StringUtils.isBlank(cid))
				continue;
			itemRelaCate.setCateId(Integer.valueOf(cid));
			itemRelaCateMapper.insert(itemRelaCate);
		}

		try {
			jedisClient.del(ITEM_COUNT + ":" + LIBRARY);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public int deleteItem(int id) {
		if (libraryMapper.deleteByPrimaryKey(id) != 1) {
			throw new GlobalException("删除图书失败");
		}

		TbItemRelaCateExample example = new TbItemRelaCateExample();
		TbItemRelaCateExample.Criteria criteria = example.createCriteria();
		criteria.andCateIdEqualTo(id);
		itemRelaCateMapper.deleteByExample(example);

		try {
			jedisClient.del(ITEM_ID + ":" + LIBRARY + ":" + id);
			jedisClient.del(ITEM_DETAIL_ID + ":" + LIBRARY + ":" + id);
			jedisClient.del(ITEM_COUNT + ":" + LIBRARY);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 1;
	}

	@Override
	public int alertItemState(Integer id, Boolean state) {

		TbLibrary item = getNormalItemById(id);
		item.setStatus(state);
		item.setUpdated(new Date());

		if (libraryMapper.updateByPrimaryKey(item) < 1) {
			throw new GlobalException("修改图书状态失败");
		}

		try {
			jedisClient.del(ITEM_ID + ":" + LIBRARY + ":" + id);
			jedisClient.del(ITEM_DETAIL_ID + ":" + LIBRARY + ":" + id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 1;
	}

	@Override
	public int updateItem(Integer id, LibraryVo libraryVo) {

		TbLibrary oldRecipes = getNormalItemById(id);
		TbLibrary library = DtoUtil.LibraryVo2TbLibrary(libraryVo);
		library.setId(id);
		library.setCreated(oldRecipes.getCreated());
		library.setUpdated(new Date());
		library.setStatus(true);
		if (libraryMapper.updateByPrimaryKey(library) < 1) {
			throw new GlobalException("更新图书失败");
		}

		TbItemRelaCateExample example = new TbItemRelaCateExample();
		TbItemRelaCateExample.Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(id);
		if (itemRelaCateMapper.deleteByExample(example) < 1) {
			throw new GlobalException("删除图书&分类关系失败");
		}

		TbItemRelaCate itemRelaCate = new TbItemRelaCate();

		// 分类数据
		itemRelaCate.setItemId(id);
		itemRelaCate.setType(RELA_LIB);
		for (String did : libraryVo.getCid()) {
			if (StringUtils.isBlank(did))
				continue;
			itemRelaCate.setCateId(Integer.valueOf(did));
			itemRelaCateMapper.insert(itemRelaCate);
		}

		// 同步缓存
		try {
			jedisClient.del(ITEM_ID + ":" + LIBRARY + ":" + id);
			jedisClient.del(ITEM_DETAIL_ID + ":" + LIBRARY + ":" + id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 1;
	}

}
