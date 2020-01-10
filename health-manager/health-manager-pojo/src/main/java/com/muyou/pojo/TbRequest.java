package com.muyou.pojo;

import java.io.Serializable;

public class TbRequest implements Serializable {
	private Integer id;

	private String userId;

	private String requestUserId;

	private String content;

	private String createTime;

	private String status;

	private String status1;
	private TbUser friend;

	public TbUser getFriend() {
		return friend;
	}

	public void setFriend(TbUser friend) {
		this.friend = friend;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public String getRequestUserId() {
		return requestUserId;
	}

	public void setRequestUserId(String requestUserId) {
		this.requestUserId = requestUserId == null ? null : requestUserId.trim();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime == null ? null : createTime.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getStatus1() {
		return status1;
	}

	public void setStatus1(String status1) {
		this.status1 = status1 == null ? null : status1.trim();
	}
}