package com.muyou.common.vo;

import java.io.Serializable;

public class MessageListVo implements Serializable{
	public int unread;
	public String room_id;
	public String type;
	public String content;
	public String create_time;
	public String another_name;
	public String belongId;
	public String file_path;
	public String sentStatus;

	public MessageListVo() {
	}

	
	public MessageListVo(String roomId) {
		this.room_id =roomId;
	}
	
	
	public String getSentStatus() {
		return sentStatus;
	}

	public void setSentStatus(String sentStatus) {
		this.sentStatus = sentStatus;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public int getUnread() {
		return unread;
	}

	public void setUnread(int unread) {
		this.unread = unread;
	}

	public String getBelongId() {
		return belongId;
	}

	public void setBelongId(String belongId) {
		this.belongId = belongId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getRoom_id() {
		return room_id;
	}

	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}

	public String getAnother_name() {
		return another_name;
	}

	public void setAnother_name(String another_name) {
		this.another_name = another_name;
	}


}
