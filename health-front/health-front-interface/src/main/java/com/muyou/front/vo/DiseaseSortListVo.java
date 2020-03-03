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

	public DiseaseSortListVo(TbClassify classify) {
		this.title = classify.getTitle();
		subName = Arrays.asList(classify.getClassify().split(","));
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
