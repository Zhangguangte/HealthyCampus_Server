package com.muyou.sso.pojo;

import java.io.Serializable;

import com.muyou.pojo.TbMessage;
import com.muyou.pojo.TbMessageList;

public class MessageListVo implements Serializable {
	public int unread;
	public String room_id;
	public String type;
	public String content;
	public String create_time;
	public String another_name;
	public String belongId;
	public String file_path;
	public String sentStatus;

	public MessageListVo(TbMessage message) {
		this.type = message.getType();
		this.content = message.getContent();
		this.room_id = message.getRoomId();
		this.belongId = message.getFromId();
		this.file_path = message.getFilePath();
		this.create_time = message.getCreateTime();
		this.sentStatus = message.getSentstatus();
		if (null != message.getList()) {
			this.unread = message.getList().getUnread();
			this.another_name = message.getList().getAnotherName();
		}

	}

	public MessageListVo(TbMessageList message) {
		this.room_id = message.getRoomId();
		this.another_name = message.getAnotherName();
	}

	public MessageListVo(String roomId) {
		this.room_id = roomId;
	}

}
