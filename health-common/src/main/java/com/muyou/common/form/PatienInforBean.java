package com.muyou.common.form;

import java.io.Serializable;

public class PatienInforBean implements Serializable{
	private Long id;
	private String name;
	private String card_id;
	private String sex;
	private String birthday;
	private String weight;
	private String allergy;
	private String history;
	private String liver;
	private String kidney;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCard_id() {
		return card_id;
	}
	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getAllergy() {
		return allergy;
	}
	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}
	public String getHistory() {
		return history;
	}
	public void setHistory(String history) {
		this.history = history;
	}
	public String getLiver() {
		return liver;
	}
	public void setLiver(String liver) {
		this.liver = liver;
	}
	public String getKidney() {
		return kidney;
	}
	public void setKidney(String kidney) {
		this.kidney = kidney;
	}
	@Override
	public String toString() {
		return "PatienInforBean [id=" + id + ", name=" + name + ", card_id=" + card_id + ", sex=" + sex + ", birthday="
				+ birthday + ", weight=" + weight + ", allergy=" + allergy + ", history=" + history + ", liver=" + liver
				+ ", kidney=" + kidney + "]";
	}

	
}
