package com.muyou.vo;

import java.io.Serializable;
import java.util.List;

public class DiseaseVo implements Serializable {

	private String name;

	private String alias;

	private String introduce;

	private String contagious;

	private String rate;

	private List<String> depart;

	private String cost;

	private String way;

	private String drug;

	private List<String> part;

	private String insurance;

	private String time;

	private String complication;

	private String population;

	private String symptoms;

	private String prevention;

	private String dcase;

	private String dcheck;

	private String url;

	private List<String> did;
	
	private List<String> pid;

	public List<String> getDepart() {
		return depart;
	}

	public void setDepart(List<String> depart) {
		this.depart = depart;
	}

	public List<String> getPart() {
		return part;
	}

	public void setPart(List<String> part) {
		this.part = part;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getWay() {
		return way;
	}

	public void setWay(String way) {
		this.way = way;
	}

	public String getDrug() {
		return drug;
	}

	public void setDrug(String drug) {
		this.drug = drug;
	}

	public String getInsurance() {
		return insurance;
	}

	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getComplication() {
		return complication;
	}

	public void setComplication(String complication) {
		this.complication = complication;
	}

	public String getPopulation() {
		return population;
	}

	public void setPopulation(String population) {
		this.population = population;
	}

	public String getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}

	public String getPrevention() {
		return prevention;
	}

	public void setPrevention(String prevention) {
		this.prevention = prevention;
	}

	public String getDcase() {
		return dcase;
	}

	public void setDcase(String dcase) {
		this.dcase = dcase;
	}

	public String getDcheck() {
		return dcheck;
	}

	public void setDcheck(String dcheck) {
		this.dcheck = dcheck;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<String> getDid() {
		return did;
	}

	public void setDid(List<String> did) {
		this.did = did;
	}

	public List<String> getPid() {
		return pid;
	}

	public void setPid(List<String> pid) {
		this.pid = pid;
	}

	@Override
	public String toString() {
		return "DiseaseVo [name=" + name + ", alias=" + alias + ", introduce=" + introduce + ", contagious="
				+ contagious + ", rate=" + rate + ", depart=" + depart + ", cost=" + cost + ", way=" + way + ", drug="
				+ drug + ", part=" + part + ", insurance=" + insurance + ", time=" + time + ", complication="
				+ complication + ", population=" + population + ", symptoms=" + symptoms + ", prevention=" + prevention
				+ ", dcase=" + dcase + ", dcheck=" + dcheck + ", url=" + url + ", did=" + did + ", pid=" + pid + "]";
	}

}
