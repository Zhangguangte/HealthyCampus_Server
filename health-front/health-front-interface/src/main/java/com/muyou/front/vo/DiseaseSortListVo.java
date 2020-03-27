package com.muyou.front.vo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import com.muyou.pojo.TbClassify;

public class DiseaseSortListVo implements Serializable {

	public String title;
	public List<String> subName;

	public DiseaseSortListVo() {
	}

	public DiseaseSortListVo(String title,List<String> classify) {
		this.title = title;
		this.subName = classify;
	}
	

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
