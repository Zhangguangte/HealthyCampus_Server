package com.muyou.sso.pojo;

import java.io.Serializable;

import com.muyou.pojo.TbCollect;

public class CollectVo implements Serializable {
	private int id;
	private String tid;
	private String type;
	private String url;
	private String name;

	public CollectVo(TbCollect collect) {
		this.id = collect.getId();
		this.tid = collect.getTypeId();
		this.type = collect.getType();
		this.url = collect.getUrl();
		this.name = collect.getName();
	}

}
