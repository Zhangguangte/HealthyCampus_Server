package com.muyou.common.vo;

import java.io.Serializable;

public class MedicineVo implements Serializable{
	private String typeName;
	private String classifyName;

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

//	public MedicineVo(MedicineClassify mClassify ) {
//		this.typeName=mClassify.getType();
//		this.classifyName=mClassify.getClassify();
//	}

	

	

}
