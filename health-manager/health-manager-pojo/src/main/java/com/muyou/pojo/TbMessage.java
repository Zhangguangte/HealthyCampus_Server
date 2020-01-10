package com.muyou.pojo;

import java.io.Serializable;

public class TbMessage implements Serializable {
	@Override
	public String toString() {
		return "TbMessage [id=" + id + ", fromId=" + fromId + ", type=" + type + ", createTime=" + createTime
				+ ", roomId=" + roomId + ", filePath=" + filePath + ", sentstatus=" + sentstatus + ", content="
				+ content + ", friend=" + friend + ", list=" + list + "]";
	}

	private Integer id;

	private String fromId;

	private String type;

	private String createTime;

	private String roomId;

	private String filePath;

	private String sentstatus;

	private String content;
	private TbUser friend;
	private TbMessageList list;

	public TbUser getFriend() {
		return friend;
	}

	public void setFriend(TbUser friend) {
		this.friend = friend;
	}

	public TbMessageList getList() {
		return list;
	}

	public void setList(TbMessageList list) {
		this.list = list;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFromId() {
		return fromId;
	}

	public void setFromId(String fromId) {
		this.fromId = fromId == null ? null : fromId.trim();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime == null ? null : createTime.trim();
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId == null ? null : roomId.trim();
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath == null ? null : filePath.trim();
	}

	public String getSentstatus() {
		return sentstatus;
	}

	public void setSentstatus(String sentstatus) {
		this.sentstatus = sentstatus == null ? null : sentstatus.trim();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}
}