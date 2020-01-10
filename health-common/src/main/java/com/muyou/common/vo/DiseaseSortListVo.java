package com.muyou.common.vo;

import java.io.Serializable;
import java.util.List;

public class DiseaseSortListVo implements Serializable{
	
    public String title;
	public List<String> subName;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getSubName() {
		return subName;
	}
	public void setSubName(List<String> subName) {
		this.subName = subName;
	}

	

	

}
