package com.muyou.vo;

import java.io.Serializable;

public class TimeTableVo implements Serializable {

	String ids[][];

	String descs[][];

	public String[][] getIds() {
		return ids;
	}

	public void setIds(String[][] ids) {
		this.ids = ids;
	}

	public String[][] getDescs() {
		return descs;
	}

	public void setDescs(String[][] descs) {
		this.descs = descs;
	}
	
}
