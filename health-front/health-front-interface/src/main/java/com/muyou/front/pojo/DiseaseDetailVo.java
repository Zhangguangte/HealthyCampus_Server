package com.muyou.front.pojo;

import java.io.Serializable;

import com.muyou.pojo.TbDisease;

public class DiseaseDetailVo implements Serializable {

	public String id;
	public String diseaseName;
	public String symbol;
	public String alias;
	public String introduce;
	public String contagious;
	public String cureRate;
	public String cureDepart;
	public String cost;
	public String cureWay;
	public String recommendDrug;
	public String part;
	public String insurance;
	public String complication;
	public String cureTime;
	public String population;
	public String typicalSymptoms;
	public String prevention;
	public String diseaseCase;
	public String check;
	public String url;
	public boolean collect;

	public DiseaseDetailVo() {}
	
	public DiseaseDetailVo(TbDisease disease) {
		this.id = disease.getId() + "";
		this.diseaseName = disease.getDiseaseName();
		this.symbol = disease.getDiseaseSymbol();
		this.alias = disease.getDiseaseAlias();
		this.introduce = disease.getDiseaseIntroduce();
		this.contagious = disease.getDiseaseContagious();
		this.cureRate = disease.getCureRate();
		this.cureDepart = disease.getCureDepart();
		this.cost = disease.getCureCost();
		this.cureWay = disease.getCureWay();
		this.recommendDrug = disease.getCureRecommendDrug();
		this.part = disease.getDiseasePart();
		this.insurance = disease.getMedicalInsurance();
		this.complication = disease.getDiseaseComplication();
		this.cureTime = disease.getCureTime();
		this.population = disease.getDiseasePopulation();
		this.typicalSymptoms = disease.getDiseaseTypicalSymptoms();
		this.prevention = disease.getDiseasePrevention();
		this.diseaseCase = disease.getDiseaseCase();
		this.check = disease.getDiseaseCheck();
		this.url = disease.getDiseaseUrl();
	}

	public String getDiseaseName() {
		return diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getContagious() {
		return contagious;
	}

	public void setContagious(String contagious) {
		this.contagious = contagious;
	}

	public String getCureRate() {
		return cureRate;
	}

	public void setCureRate(String cureRate) {
		this.cureRate = cureRate;
	}

	public String getCureDepart() {
		return cureDepart;
	}

	public void setCureDepart(String cureDepart) {
		this.cureDepart = cureDepart;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getCureWay() {
		return cureWay;
	}

	public void setCureWay(String cureWay) {
		this.cureWay = cureWay;
	}

	public String getRecommendDrug() {
		return recommendDrug;
	}

	public void setRecommendDrug(String recommendDrug) {
		this.recommendDrug = recommendDrug;
	}

	public String getPart() {
		return part;
	}

	public void setPart(String part) {
		this.part = part;
	}

	public String getInsurance() {
		return insurance;
	}

	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

	public String getComplication() {
		return complication;
	}

	public void setComplication(String complication) {
		this.complication = complication;
	}

	public String getCureTime() {
		return cureTime;
	}

	public void setCureTime(String cureTime) {
		this.cureTime = cureTime;
	}

	public String getPopulation() {
		return population;
	}

	public void setPopulation(String population) {
		this.population = population;
	}

	public String getTypicalSymptoms() {
		return typicalSymptoms;
	}

	public void setTypicalSymptoms(String typicalSymptoms) {
		this.typicalSymptoms = typicalSymptoms;
	}

	public String getPrevention() {
		return prevention;
	}

	public void setPrevention(String prevention) {
		this.prevention = prevention;
	}

	public String getDiseaseCase() {
		return diseaseCase;
	}

	public void setDiseaseCase(String diseaseCase) {
		this.diseaseCase = diseaseCase;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isCollect() {
		return collect;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
	public void setCollect(boolean collect) {
		this.collect = collect;
	}
}