package com.muyou.dto;

import java.util.Arrays;

import com.muyou.common.pojo.ZTreeNode;
import com.muyou.pojo.TbCate;
import com.muyou.pojo.TbDisease;
import com.muyou.pojo.TbLecture;
import com.muyou.pojo.TbLibrary;
import com.muyou.pojo.TbMedicine;
import com.muyou.pojo.TbPanel;
import com.muyou.pojo.TbRecipes;
import com.muyou.vo.DiseaseVo;
import com.muyou.vo.LectureVo;
import com.muyou.vo.LibraryVo;
import com.muyou.vo.MedicineVo;
import com.muyou.vo.RecipesVo;

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

	public static RecipesVo TbRecipes2RecipesVo(TbRecipes recipes) {
		RecipesVo recipesVo = new RecipesVo();
		recipesVo.setCalorie("" + recipes.getCalorie());
		recipesVo.setFlavor(recipes.getFlavor());
		recipesVo.setFunctional(recipes.getFunctional());
		recipesVo.setName(recipes.getName());
		recipesVo.setPhysique(recipes.getPhysique());
		recipesVo.setProcess(recipes.getProcess());
		recipesVo.setTime(recipes.getTime());
		recipesVo.setComponents(Arrays.asList(recipes.getComponents().split(",")));
		recipesVo.setType(recipes.getType());
		recipesVo.setUrl(recipes.getUrl());
		recipesVo.setIngredients(recipes.getIngredients());
		recipesVo.setPractice(recipes.getPractice());
		return recipesVo;
	}

	public static TbRecipes RecipesVo2TbRecipes(RecipesVo recipesVo) {
		TbRecipes recipes = new TbRecipes();
		recipes.setCalorie(Long.valueOf(recipesVo.getCalorie()));
		recipes.setFlavor(recipesVo.getFlavor());
		recipes.setFunctional(recipesVo.getFunctional());
		recipes.setName(recipesVo.getName());
		recipes.setPhysique(recipesVo.getPhysique());
		recipes.setProcess(recipesVo.getProcess());
		recipes.setComponents(String.join(",", recipesVo.getComponents()));
		recipes.setType(recipesVo.getType());
		recipes.setTime(recipesVo.getTime());
		recipes.setUrl(recipesVo.getUrl());
		recipes.setIngredients(recipesVo.getIngredients());
		recipes.setPractice(recipesVo.getPractice());
		return recipes;
	}

	public static ZTreeNode TbPanel2ZTreeNode(TbPanel panel) {
		ZTreeNode zTreeNode = new ZTreeNode();
		zTreeNode.setId(panel.getId());
		zTreeNode.setIsParent(false);
		zTreeNode.setpId(0);
		zTreeNode.setName(panel.getName());
		zTreeNode.setSortOrder(panel.getSortOrder());
		zTreeNode.setStatus(panel.getStatus());
		zTreeNode.setRemark(panel.getRemark());
		zTreeNode.setLimitNum(panel.getLimitNum());
		zTreeNode.setType(panel.getType());
		return zTreeNode;
	}

	public static LibraryVo TbLibrary2LibraryVo(TbLibrary library) {
		LibraryVo libraryVo = new LibraryVo();
		libraryVo.setAuthor(library.getAuthor());
		libraryVo.setbIndex(library.getbIndex());
		libraryVo.setCreated(library.getCreated());
		libraryVo.setUpdated(library.getUpdated());
		libraryVo.setSum(library.getSum());
		libraryVo.setRest(library.getRest());
		libraryVo.setPlace(library.getPlace());
		libraryVo.setStatus(library.getStatus());
		libraryVo.setIntroduced(library.getIntroduced());
		libraryVo.setName(library.getName());
		libraryVo.setPublish(library.getPublish());
		libraryVo.setPrice(library.getPrice());
		libraryVo.setUrl(library.getUrl());
		return libraryVo;
	}

	public static TbLibrary LibraryVo2TbLibrary(LibraryVo libraryVo) {
		TbLibrary library = new TbLibrary();
		library.setAuthor(libraryVo.getAuthor());
		library.setbIndex(libraryVo.getbIndex());
		library.setCreated(libraryVo.getCreated());
		library.setUpdated(libraryVo.getUpdated());
		library.setSum(libraryVo.getSum());
		library.setRest(libraryVo.getRest());
		library.setPlace(libraryVo.getPlace());
		library.setStatus(libraryVo.getStatus());
		library.setIntroduced(libraryVo.getIntroduced());
		library.setName(libraryVo.getName());
		library.setPublish(libraryVo.getPublish());
		library.setPrice(libraryVo.getPrice());
		library.setUrl(libraryVo.getUrl());
		return library;
	}

	public static TbLecture LectureVo2TbLecture(LectureVo lectureVo) {
		TbLecture lecture = new TbLecture();
		lecture.setAuthor(lectureVo.getAuthor());
		lecture.setContent(lectureVo.getContent());
		lecture.setCreated(lectureVo.getCreated());
		lecture.setUpdated(lectureVo.getUpdated());
		lecture.setStatus(lectureVo.getStatus());
		lecture.setTitle(lectureVo.getTitle());
		return lecture;
	}

	public static LectureVo TbLecture2LectureVo(TbLecture lecture) {
		LectureVo lectureVo = new LectureVo();
		lectureVo.setAuthor(lecture.getAuthor());
		lectureVo.setContent(lecture.getContent());
		lectureVo.setCreated(lecture.getCreated());
		lectureVo.setUpdated(lecture.getUpdated());
		lectureVo.setStatus(lecture.getStatus());
		lectureVo.setTitle(lecture.getTitle());
		return lectureVo;
	}
}
