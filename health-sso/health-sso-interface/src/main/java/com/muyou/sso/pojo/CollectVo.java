package com.muyou.sso.pojo;

import java.io.Serializable;

import com.muyou.pojo.TbCollect;

public class CollectVo implements Serializable {
	private int id;
	private String tid;
	private String type;
	private String url;
	private String name;

	public CollectVo() {}
	
	public CollectVo(TbCollect collect) {
		this.id = collect.getId();
		this.tid = collect.getTypeId();
		this.type = collect.getType();
		this.url = collect.getUrl();
		this.name = collect.getName();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
