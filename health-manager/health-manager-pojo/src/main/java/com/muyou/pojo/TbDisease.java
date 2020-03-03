package com.muyou.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbDisease implements Serializable {
	private Integer id;

	private String name;

	private String symbol;

	private String alias;

	private String introduce;

	private String contagious;

	private String rate;

	private String depart;

	private String cost;

	private String way;

	private String drug;

	private String part;

	private String insurance;

	private String time;

	private String complication;

	private String population;

	private String symptoms;

	private String prevention;

	private String dcase;

	private String dcheck;

	private String url;

	private Boolean status;

	private Date created;

	private Date updated;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol == null ? null : symbol.trim();
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias == null ? null : alias.trim();
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce == null ? null : introduce.trim();
	}

	public String getContagious() {
		return contagious;
	}

	public void setContagious(String contagious) {
		this.contagious = contagious == null ? null : contagious.trim();
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate == null ? null : rate.trim();
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost == null ? null : cost.trim();
	}

	public String getWay() {
		return way;
	}

	public void setWay(String way) {
		this.way = way == null ? null : way.trim();
	}

	public String getDrug() {
		return drug;
	}

	public void setDrug(String drug) {
		this.drug = drug == null ? null : drug.trim();
	}

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
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
		this.insurance = insurance == null ? null : insurance.trim();
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time == null ? null : time.trim();
	}

	public String getComplication() {
		return complication;
	}

	public void setComplication(String complication) {
		this.complication = complication == null ? null : complication.trim();
	}

	public String getPopulation() {
		return population;
	}

	public void setPopulation(String population) {
		this.population = population == null ? null : population.trim();
	}

	public String getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms == null ? null : symptoms.trim();
	}

	public String getPrevention() {
		return prevention;
	}

	public void setPrevention(String prevention) {
		this.prevention = prevention == null ? null : prevention.trim();
	}

	public String getDcase() {
		return dcase;
	}

	public void setDcase(String dcase) {
		this.dcase = dcase == null ? null : dcase.trim();
	}

	public String getDcheck() {
		return dcheck;
	}

	public void setDcheck(String dcheck) {
		this.dcheck = dcheck == null ? null : dcheck.trim();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}
}