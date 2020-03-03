package com.muyou.front.vo;

import java.io.Serializable;

import com.muyou.pojo.TbClassify;

public class MedicineVo implements Serializable {
	private String typeName;
	private String classifyName;

	public MedicineVo() {}
	
	public MedicineVo(TbClassify classify) {
		this.typeName = classify.getTitle();
		this.classifyName = classify.getClassify();
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
