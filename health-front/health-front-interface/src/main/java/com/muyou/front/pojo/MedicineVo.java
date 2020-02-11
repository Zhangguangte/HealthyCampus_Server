package com.muyou.front.pojo;

import java.io.Serializable;

import com.muyou.pojo.TbMedicineClassify;

public class MedicineVo implements Serializable {
	private String typeName;
	private String classifyName;

	public MedicineVo() {}
	
	public MedicineVo(TbMedicineClassify mClassify) {
		this.typeName = mClassify.getType();
		this.classifyName = mClassify.getClassify();
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
