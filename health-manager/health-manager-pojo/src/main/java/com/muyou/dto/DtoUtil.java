package com.muyou.dto;

import com.muyou.common.pojo.ZTreeNode;
import com.muyou.pojo.TbCate;
import com.muyou.pojo.TbDisease;
import com.muyou.pojo.TbMedicine;
import com.muyou.vo.DiseaseVo;
import com.muyou.vo.MedicineVo;

public class DtoUtil {

	public static ZTreeNode TbCate2ZTreeNode(TbCate cate) {
		ZTreeNode zTreeNode = new ZTreeNode();
		zTreeNode.setId(Math.toIntExact(cate.getId()));
		zTreeNode.setStatus(cate.getStatus());
		zTreeNode.setSortOrder(cate.getSortOrder());
		zTreeNode.setName(cate.getName());
		zTreeNode.setpId(Math.toIntExact(cate.getParentId()));
		zTreeNode.setIsParent(cate.getIsParent());
		zTreeNode.setRemark(cate.getRemark());
		return zTreeNode;
	}

	public static TbDisease DiseaseVo2TbDisease(DiseaseVo diseaseVo) {

		TbDisease disease = new TbDisease();
		disease.setName(diseaseVo.getName());
		disease.setAlias(diseaseVo.getAlias());
		disease.setIntroduce(diseaseVo.getIntroduce());
		disease.setContagious(diseaseVo.getContagious());
		disease.setInsurance(diseaseVo.getInsurance());
		disease.setCost(diseaseVo.getCost());
		disease.setDcase(diseaseVo.getDcase());
		disease.setDcheck(diseaseVo.getDcheck());

		disease.setDrug(diseaseVo.getDrug());
		disease.setComplication(diseaseVo.getComplication());
		disease.setPopulation(diseaseVo.getPopulation());
		disease.setPrevention(diseaseVo.getPrevention());
		disease.setRate(diseaseVo.getRate());
		disease.setSymptoms(diseaseVo.getSymptoms());
		disease.setTime(diseaseVo.getTime());
		disease.setUrl(diseaseVo.getUrl());
		disease.setWay(diseaseVo.getWay());

		return disease;
	}

	public static DiseaseVo TbDisease2DiseaseVo(TbDisease disease) {
		DiseaseVo diseaseVo = new DiseaseVo();
		diseaseVo.setName(disease.getName());
		diseaseVo.setAlias(disease.getAlias());
		diseaseVo.setIntroduce(disease.getIntroduce());
		diseaseVo.setContagious(disease.getContagious());
		diseaseVo.setInsurance(disease.getInsurance());
		diseaseVo.setCost(disease.getCost());
		diseaseVo.setDcase(disease.getDcase());
		diseaseVo.setDcheck(disease.getDcheck());

		diseaseVo.setDrug(disease.getDrug());
		diseaseVo.setComplication(disease.getComplication());
		diseaseVo.setPopulation(disease.getPopulation());
		diseaseVo.setPrevention(disease.getPrevention());
		diseaseVo.setRate(disease.getRate());
		diseaseVo.setSymptoms(disease.getSymptoms());
		diseaseVo.setTime(disease.getTime());
		diseaseVo.setUrl(disease.getUrl());
		diseaseVo.setWay(disease.getWay());
		return diseaseVo;
	}

	public static MedicineVo TbMedicine2MedicineVo(TbMedicine medicine) {
		MedicineVo medicineVo = new MedicineVo();
		medicineVo.setApprovalNumber(medicine.getApprovalNumber());
		medicineVo.setBarCode(medicine.getBarCode());
		medicineVo.setGoodsName(medicine.getGoodsName());
		medicineVo.setIndications(medicine.getIndications());
		medicineVo.setInstruction(medicine.getInstruction());
		medicineVo.setLogo(medicine.getLogo());
		medicineVo.setManufacturer(medicine.getManufacturer());
		medicineVo.setOtc(medicine.getOtc());
		medicineVo.setPrice(medicine.getPrice());
		medicineVo.setReplenish(medicine.getReplenish());
		medicineVo.setSpec(medicine.getSpec());
		medicineVo.setUnit(medicine.getUnit());
		return medicineVo;
	}

	public static TbMedicine MedicineVo2TbMedicine(MedicineVo medicineVo) {
		TbMedicine medicine = new TbMedicine();
		medicine.setApprovalNumber(medicineVo.getApprovalNumber());
		medicine.setBarCode(medicineVo.getBarCode());
		medicine.setGoodsName(medicineVo.getGoodsName());
		medicine.setIndications(medicineVo.getIndications());
		medicine.setInstruction(medicineVo.getInstruction());
		medicine.setLogo(medicineVo.getLogo());
		medicine.setManufacturer(medicineVo.getManufacturer());
		medicine.setOtc(medicineVo.getOtc());
		medicine.setPrice(medicineVo.getPrice());
		medicine.setReplenish(medicineVo.getReplenish());
		medicine.setSpec(medicineVo.getSpec());
		medicine.setUnit(medicineVo.getUnit());
		return medicine;
	}

}
