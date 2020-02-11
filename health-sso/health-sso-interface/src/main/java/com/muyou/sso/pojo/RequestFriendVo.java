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

	public RequestFriendVo(TbRequest request) {
		this.id = request.getId()+"";
		this.user_id = request.getUserId();
		this.request_user_id = request.getFriend().getId();
		this.nickname = request.getFriend().getNickname();
		this.content = request.getContent();
		this.status = request.getStatus();
		// this.headImg = request.getDescription();
	}

}
