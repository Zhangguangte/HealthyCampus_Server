package com.muyou.vo;

import java.io.Serializable;
import java.util.List;

public class RecipesVo implements Serializable {

	private Integer id;

	private String name;

	private String functional;

	private String flavor;

	private String process;

	private String time;

	private String url;

	private Integer type;

	private String calorie;

	private String physique;

	private String ingredients;
	
	private String practice;
	
	private List<String> components;

	private List<String> cid;

	private List<String> cname;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public List<String> getCid() {
		return cid;
	}

	public void setCid(List<String> cid) {
		this.cid = cid;
	}

	public List<String> getCname() {
		return cname;
	}

	public void setCname(List<String> cname) {
		this.cname = cname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFunctional() {
		return functional;
	}

	public void setFunctional(String functional) {
		this.functional = functional;
	}

	public String getFlavor() {
		return flavor;
	}

	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCalorie() {
		return calorie;
	}

	public void setCalorie(String calorie) {
		this.calorie = calorie;
	}

	public String getPhysique() {
		return physique;
	}

	public void setPhysique(String physique) {
		this.physique = physique;
	}

	public List<String> getComponents() {
		return components;
	}

	public void setComponents(List<String> components) {
		this.components = components;
	}

	public String getPractice() {
		return practice;
	}

	public void setPractice(String practice) {
		this.practice = practice;
	}

	@Override
	public String toString() {
		return "RecipesVo [id=" + id + ", name=" + name + ", functional=" + functional + ", flavor=" + flavor
				+ ", process=" + process + ", time=" + time + ", url=" + url + ", type=" + type + ", calorie=" + calorie
				+ ", physique=" + physique + ", ingredients=" + ingredients + ", practice=" + practice + ", components="
				+ components + ", cid=" + cid + ", cname=" + cname + "]";
	}

}
