package com.muyou.common.form;

import java.io.Serializable;
import java.util.Map;

public class RequestForm implements Serializable {
	private String quest_id;
	private String content;
	private Integer row;
	private int type;
	public int id;
	private Map<String, String> map;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuest_id() {
		return quest_id;
	}

	public void setQuest_id(String quest_id) {
		this.quest_id = quest_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	@Override
	public String toString() {
		return "RequestForm [quest_id=" + quest_id + ", content=" + content + ", row=" + row + ", type=" + type
				+ ", map=" + map + "]";
	}

}
