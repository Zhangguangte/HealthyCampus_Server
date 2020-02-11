package com.muyou.sso.pojo;

import java.io.Serializable;

import com.muyou.pojo.TbUser;


public class UserVo implements Serializable{
	public String id;
	public String account;
	public String nickname;
	public String avatar;
	public String description;
	public String sex;
	public String location;
	public String phone;
	public String createTime;
	public String lastmodifyTime;
	public String initials;
	public String token;
	public boolean isfriends;

	public UserVo() {
	}

	public UserVo(TbUser user, boolean isfriends) {
		this.id = user.getId();
		this.account = user.getAccount();
		this.nickname = user.getNickname();
		this.avatar = user.getAvatar();
		this.description = user.getDescription();
		this.location = user.getLocation();
		this.sex = user.getSex();
		this.phone = user.getPhone();
		this.initials = user.getInitials();
		this.createTime = user.getCreateTime();
		this.lastmodifyTime = user.getLastModifyTime();
		this.isfriends = isfriends;
	}

	public UserVo(TbUser user) {
		this.id = user.getId();
		this.account = user.getAccount();
		this.nickname = user.getNickname();
		this.avatar = user.getAvatar();
		this.description = user.getDescription();
		this.location = user.getLocation();
		this.sex = user.getSex();
		this.phone = user.getPhone();
		this.initials = user.getInitials();
		this.createTime = user.getCreateTime();
		this.lastmodifyTime = user.getLastModifyTime();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setToken(String token) {
		this.token = token;
	}

	

}
