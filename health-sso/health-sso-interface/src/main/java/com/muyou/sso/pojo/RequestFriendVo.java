package com.muyou.sso.pojo;

import java.io.Serializable;

import com.muyou.pojo.TbRequest;

public class RequestFriendVo implements Serializable {

	public String id;
	public String user_id;
	public String request_user_id;
	public String nickname;
	public String content;
	public String status;
	public String headImg;

	public RequestFriendVo() {}
	public RequestFriendVo(TbRequest request) {
		this.id = request.getId()+"";
		this.user_id = request.getUserId();
		this.request_user_id = request.getFriend().getId();
		this.nickname = request.getFriend().getNickname();
		this.content = request.getContent();
		this.status = request.getStatus();
		// this.headImg = request.getDescription();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getRequest_user_id() {
		return request_user_id;
	}
	public void setRequest_user_id(String request_user_id) {
		this.request_user_id = request_user_id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

}
