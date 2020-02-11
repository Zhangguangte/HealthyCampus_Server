package com.muyou.front.pojo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import com.muyou.pojo.TbDepartment;
import com.muyou.pojo.TbPart;

public class DiseaseSortListVo implements Serializable{
	
    public String title;
	public List<String> subName;
	
	public DiseaseSortListVo()
	{}

	public DiseaseSortListVo(TbPart part)
	{
		this.title = part.getPartName();
		subName = Arrays.asList(part.getRelatedOrgans().split(","));
	}
	
	public DiseaseSortListVo(TbDepartment department)
	{
		this.title = department.getDepartName();
		subName = Arrays.asList(department.getSubName().split(","));
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
