package com.muyou.front.vo;

import java.io.Serializable;

public class MedicineVo implements Serializable {
	private String typeName;
	private String classifyName;

	public MedicineVo() {
	}

	public MedicineVo(String type, String classify) {
		this.typeName = type;
		this.classifyName = classify;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getClassifyName() {
		return classifyName;
	}

	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}

}
