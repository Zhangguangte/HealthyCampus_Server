package com.muyou.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muyou.common.form.RequestForm;
import com.muyou.common.vo.MedicineDetailVo;
import com.muyou.common.vo.MedicineListVo;
import com.muyou.common.vo.MedicineVo;
import com.muyou.mapper.TbMedicineClassifyMapper;
import com.muyou.mapper.TbMedicineMapper;
import com.muyou.pojo.TbMedicine;
import com.muyou.pojo.TbMedicineClassify;
import com.muyou.service.MedicineService;

@Service("medicineServiceImpl")
public class MedicineServiceImpl implements MedicineService {

	@Autowired
	private TbMedicineClassifyMapper classifyMapper;

	@Autowired
	private TbMedicineMapper medicineMapper;

	// 获得所有的分类
	@Override
	public List<MedicineVo> getClassify() {
		List<TbMedicineClassify> list = classifyMapper.getClassify();
		if (null == list || list.size() == 0) {
			return null;
		}
		List<MedicineVo> result = new LinkedList<MedicineVo>();
		MedicineVo medicineVo;
		for (TbMedicineClassify mClassify : list) {
			medicineVo = new MedicineVo();
			medicineVo.setClassifyName(mClassify.getClassify());
			medicineVo.setTypeName(mClassify.getType());
			result.add(medicineVo);
		}
		return result;
	}

	// 获得所有的药品根据分类
	@Override
	public List<MedicineListVo> getAllMedicine(RequestForm requestForm) {
		List<TbMedicine> list = medicineMapper.getAllMedicine(requestForm.getContent(), requestForm.getRow() * 15);
		if (null == list || list.size() == 0) {
			return null;
		}
		List<MedicineListVo> result = new LinkedList<MedicineListVo>();
		MedicineListVo medicineListVo;
		for (TbMedicine medicine : list) {
			medicineListVo = new MedicineListVo();
			medicineListVo.setId(medicine.getGoodsId() + "");
			medicineListVo.setPrice(medicine.getPrice() + "");
			medicineListVo.setDescription(medicine.getManufacturer());
			medicineListVo.setGoodName(medicine.getGoodsName());
			medicineListVo.setIsOct("1".equals(medicine.getIsOtc()) ? "处方药" : "非处方药");
			medicineListVo.setImage(medicine.getLogo());
			result.add(medicineListVo);
		}
		return result;
	}

	// 获得所有的药品根据关键字和范围
	@Override
	public List<MedicineListVo> getAllMedicineByKey(RequestForm requestForm) {

		List<TbMedicine> list = medicineMapper.getAllMedicineByKey(requestForm.getQuest_id(), requestForm.getContent(),
				requestForm.getRow() * 15);
		if (null == list || list.size() == 0)
			return null;
		List<MedicineListVo> result = new LinkedList<MedicineListVo>();
		MedicineListVo medicineListVo;
		for (TbMedicine medicine : list) {
			medicineListVo = new MedicineListVo();
			medicineListVo.setId(medicine.getGoodsId() + "");
			medicineListVo.setPrice(medicine.getPrice() + "");
			medicineListVo.setDescription(medicine.getManufacturer());
			medicineListVo.setGoodName(medicine.getGoodsName());
			medicineListVo.setIsOct("1".equals(medicine.getIsOtc()) ? "处方药" : "非处方药");
			medicineListVo.setImage(medicine.getLogo());
			result.add(medicineListVo);
		}
		return result;
	}

	@Override
	public MedicineDetailVo getMedicineDetail(RequestForm requestForm) {
		TbMedicine medicine = null;
		if ("ID".equals(requestForm.getQuest_id())) {
			medicine = medicineMapper.selectByPrimaryKey(Integer.parseInt(requestForm.getContent()));
		} else if ("NAME".equals(requestForm.getQuest_id())) {
			medicine = medicineMapper.getMedicineDetailByName(requestForm.getContent());
		} else if ("CODE".equals(requestForm.getQuest_id())) {
			medicine = medicineMapper.getMedicineDetailByCode(requestForm.getContent());
		}
		if (medicine == null) {
			return null;
		}
		MedicineDetailVo medicineDetailVo = new MedicineDetailVo();
		medicineDetailVo.setId(medicine.getGoodsId() + "");
		medicineDetailVo.setPrice(medicine.getPrice() + "");
		medicineDetailVo.setSpec(medicine.getSpec());
		medicineDetailVo.setUnit(medicine.getUnit());
		medicineDetailVo.setZhuzhi(medicine.getZhuzhi());
		medicineDetailVo.setDetail(medicine.getExplainBook());
		medicineDetailVo.setName(medicine.getGoodsName());
		medicineDetailVo.setImage(medicine.getLogo());
		return medicineDetailVo;
	}

}
