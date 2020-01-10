package com.muyou.common.form;

import java.io.Serializable;

import com.muyou.common.util.StringUtil;

public class ChatForm implements Serializable{
	private String user_id;
	private String type;
	private String create_time;
	private String content;
	private String file_path;
	private String room_id;

	public boolean validate() {
		return !StringUtil.isEmpty(room_id) && !StringUtil.isEmpty(type);
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public String getRoom_id() {
		return room_id;
	}

	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}
	
	
}
