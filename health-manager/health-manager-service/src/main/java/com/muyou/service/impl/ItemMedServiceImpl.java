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
import com.muyou.common.constant.ItemConstant;
import com.muyou.common.exception.GlobalException;
import com.muyou.common.pojo.DataTablesResult;
import com.muyou.common.redis.JedisClient;
import com.muyou.common.util.JsonUtils;
import com.muyou.common.util.StringUtil;
import com.muyou.dto.DtoUtil;
import com.muyou.mapper.TbCateMapper;
import com.muyou.mapper.TbItemRelaCateMapper;
import com.muyou.mapper.TbMedicineMapper;
import com.muyou.pojo.TbCate;
import com.muyou.pojo.TbItemRelaCate;
import com.muyou.pojo.TbItemRelaCateExample;
import com.muyou.pojo.TbMedicine;
import com.muyou.pojo.TbMedicineExample;
import com.muyou.service.ItemMedService;
import com.muyou.vo.MedicineVo;

@Service
public class ItemMedServiceImpl implements ItemMedService {

	private final static Logger log = LoggerFactory.getLogger(ItemMedServiceImpl.class);

	@Autowired
	private JedisClient jedisClient;

	@Autowired
	private TbMedicineMapper medicineMapper;

	@Autowired
	private TbCateMapper cateMapper;

	@Autowired
	private TbItemRelaCateMapper itemRelaCateMapper;

	@Autowired
	private JmsTemplate jmsTemplate;

	@Resource
	private Destination topicDestination;

	@Value("${ITEM_COUNT}")
	private String ITEM_COUNT;

	@Value("${MEDICINE}")
	private String MEDICINE;

	@Value("${ITEM_DETAIL_ID}")
	private String ITEM_DETAIL_ID;

	@Value("${ITEM_ID}")
	private String ITEM_ID;

	@Value("${ITEM_MED_DEF_IMG}")
	private String ITEM_MED_DEF_IMG;

	@Value("${ITEM_EXPIRE}")
	private Integer ITEM_EXPIRE;

	@Override
	public DataTablesResult getAllItemCount() {

		try {
			String json = jedisClient.get(ITEM_COUNT + ":" + MEDICINE);
			if (StringUtils.isNotBlank(json))
				return JsonUtils.jsonToPojo(json, DataTablesResult.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbMedicineExample example = new TbMedicineExample();
		int count = medicineMapper.countByExample(example);
		DataTablesResult result = new DataTablesResult();
		result.setRecordsTotal(count);

		try {
			jedisClient.set(ITEM_COUNT + ":" + MEDICINE, JsonUtils.objectToJson(result));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public DataTablesResult getItemList(int draw, int start, int length, int cid, String search, String orderCol,
			String orderDir) {
		DataTablesResult result = new DataTablesResult();

		System.out.println(search);

		// 分页执行查询返回结果
		PageHelper.startPage(start / length + 1, length);
		List<TbMedicine> list = medicineMapper.selectItemByCondition(cid, "%" + search + "%", orderCol, orderDir);

		List<String> cateList;
		for (TbMedicine medicine : list) {
			// 获得分类数据
			cateList = cateMapper.selectCateNameByItemIdAndType(medicine.getId(), ItemConstant.RELA_MED);
			medicine.setcName(String.join(",", cateList));
		}

		PageInfo<TbMedicine> pageInfo = new PageInfo<>(list);

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
		List<TbMedicine> list = medicineMapper.selectItemByMultiCondition(cid, "%" + search + "%", minDate, maxDate,
				orderCol, orderDir);
		PageInfo<TbMedicine> pageInfo = new PageInfo<>(list);
		result.setRecordsFiltered((int) pageInfo.getTotal());
		result.setRecordsTotal(getAllItemCount().getRecordsTotal());

		result.setDraw(draw);
		result.setData(list);

		return result;
	}

	@Override
	public MedicineVo getItemById(Integer id) {
		try {
			String json = jedisClient.get(ITEM_DETAIL_ID + ":" + MEDICINE + ":" + id);
			if (StringUtils.isNotBlank(json))
				return JsonUtils.jsonToPojo(json, MedicineVo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 设置药品数据
		MedicineVo result = DtoUtil.TbMedicine2MedicineVo(medicineMapper.selectByPrimaryKey(id));

		// 获得分类数据
		List<String> cateList = new LinkedList<String>();
		List<String> cidList = new LinkedList<String>();
		List<TbCate> list = cateMapper.selectItemCate(id, ItemConstant.RELA_MED);
		for (TbCate tbCate : list) {
			cateList.add(tbCate.getName());
			cidList.add(tbCate.getId() + "");
		}

		result.setCid(cidList);
		result.setcName(cateList);

		try {
			jedisClient.set(ITEM_DETAIL_ID + ":" + MEDICINE + ":" + id, JsonUtils.objectToJson(result));
			jedisClient.expire(ITEM_DETAIL_ID + ":" + MEDICINE + ":" + id, ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public TbMedicine getNormalItemById(Integer id) {
		try {
			String json = jedisClient.get(ITEM_ID + ":" + MEDICINE + ":" + id);
			if (StringUtils.isNotBlank(json))
				return JsonUtils.jsonToPojo(json, TbMedicine.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbMedicine result = medicineMapper.selectByPrimaryKey(id);

		try {
			jedisClient.set(ITEM_ID + ":" + MEDICINE + ":" + id, JsonUtils.objectToJson(result));
			jedisClient.expire(ITEM_ID + ":" + MEDICINE + ":" + id, ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public int addItem(MedicineVo medicineVo) {
		TbMedicine medicine = DtoUtil.MedicineVo2TbMedicine(medicineVo);
		medicine.setSpell(StringUtil.getFirstSpell(medicineVo.getGoodsName()));
		medicine.setStatus(true);
		medicine.setCreated(new Date());
		medicine.setUpdated(new Date());
		if (medicine.getLogo().isEmpty()) {
			medicine.setLogo(ITEM_MED_DEF_IMG);
		}

		if (medicineMapper.insert(medicine) < 1) {
			throw new GlobalException("添加药品失败");
		}

		TbItemRelaCate itemRelaCate = new TbItemRelaCate();
		// 分类数据
		itemRelaCate.setItemId(medicine.getId());
		itemRelaCate.setType(ItemConstant.RELA_MED);
		for (String cid : medicineVo.getCid()) {
			if (StringUtils.isBlank(cid))
				continue;
			itemRelaCate.setCateId(Integer.valueOf(cid));
			itemRelaCateMapper.insert(itemRelaCate);
		}

		try {
			jedisClient.del(ITEM_COUNT + ":" + MEDICINE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 发送消息同步索引库
		try {
			sendRefreshSolrMessage(MEDICINE, "add", medicine.getId(), "-1");
		} catch (Exception e) {
			log.error("同步索引出错");
		}
		return 1;
	}

	@Override
	public int deleteItem(int id) {
		if (medicineMapper.deleteByPrimaryKey(id) != 1) {
			throw new GlobalException("删除药品失败");
		}

		TbItemRelaCateExample example = new TbItemRelaCateExample();
		TbItemRelaCateExample.Criteria criteria = example.createCriteria();
		criteria.andCateIdEqualTo(id);
		itemRelaCateMapper.deleteByExample(example);

		try {
			jedisClient.del(ITEM_ID + ":" + MEDICINE + ":" + id);
			jedisClient.del(ITEM_DETAIL_ID + ":" + MEDICINE + ":" + id);
			jedisClient.del(ITEM_COUNT + ":" + MEDICINE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 发送消息同步索引库
		try {
			sendRefreshSolrMessage(MEDICINE, "delete", id, "-1");
		} catch (Exception e) {
			log.error("同步索引出错");
		}

		return 1;
	}

	@Override
	public int alertItemState(Integer id, Boolean state) {

		TbMedicine item = getNormalItemById(id);
		item.setStatus(state);
		item.setUpdated(new Date());

		if (medicineMapper.updateByPrimaryKey(item) < 1) {
			throw new GlobalException("修改药品状态失败");
		}

		try {
			jedisClient.del(ITEM_ID + ":" + MEDICINE + ":" + id);
			jedisClient.del(ITEM_DETAIL_ID + ":" + MEDICINE + ":" + id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 发送消息同步索引库
		try {
			sendRefreshSolrMessage(MEDICINE, "update", id, state ? "1" : "2");
		} catch (Exception e) {
			log.error("同步索引出错");
		}

		return 1;
	}

	@Override
	public int updateItem(Integer id, MedicineVo medicineVo) {
		TbMedicine oldMedicine = getNormalItemById(id);
		TbMedicine medicine = DtoUtil.MedicineVo2TbMedicine(medicineVo);
		medicine.setId(id);
		medicine.setSpell(StringUtil.getFirstSpell(medicineVo.getGoodsName()));
		medicine.setCreated(oldMedicine.getCreated());
		medicine.setUpdated(new Date());
		medicine.setStatus(true);
		if (medicineMapper.updateByPrimaryKey(medicine) < 1) {
			throw new GlobalException("更新药品失败");
		}

		TbItemRelaCateExample example = new TbItemRelaCateExample();
		TbItemRelaCateExample.Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(id);
		itemRelaCateMapper.deleteByExample(example);

		TbItemRelaCate itemRelaCate = new TbItemRelaCate();
		// 分类数据
		itemRelaCate.setItemId(id);
		itemRelaCate.setType(ItemConstant.RELA_MED);
		for (String cid : medicineVo.getCid()) {
			if (StringUtils.isBlank(cid))
				continue;
			itemRelaCate.setCateId(Integer.valueOf(cid));
			itemRelaCateMapper.insert(itemRelaCate);
		}

		// 同步缓存
		try {
			jedisClient.del(ITEM_ID + ":" + MEDICINE + ":" + id);
			jedisClient.del(ITEM_DETAIL_ID + ":" + MEDICINE + ":" + id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 发送消息同步索引库
		try {
			sendRefreshSolrMessage(MEDICINE, "update", id, "0");
		} catch (Exception e) {
			log.error("同步索引出错");
		}

		return 1;
	}

	/**
	 * /** 发送消息同步索引库
	 * 
	 * @param type
	 * @param oper
	 * @param id
	 */
	public void sendRefreshSolrMessage(String type, String oper, int id, String fac) {
		jmsTemplate.send(topicDestination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = session
						.createTextMessage(type + "," + oper + "," + String.valueOf(id) + "," + fac);
				return textMessage;
			}
		});
	}
}
