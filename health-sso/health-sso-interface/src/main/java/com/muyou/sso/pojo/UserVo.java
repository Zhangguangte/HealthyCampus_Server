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

	 //学生信息
    public boolean auth;
    public String no;
    public String sid;
    public String sname;
    public String major;
    public String year;
    
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
		this.createTime = user.getBirthday();
		this.lastmodifyTime = user.getLastModifyTime();
	}

	public boolean isAuth() {
		return auth;
	}

	public void setAuth(boolean auth) {
		this.auth = auth;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastmodifyTime() {
		return lastmodifyTime;
	}

	public void setLastmodifyTime(String lastmodifyTime) {
		this.lastmodifyTime = lastmodifyTime;
	}

	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public boolean isIsfriends() {
		return isfriends;
	}

	public void setIsfriends(boolean isfriends) {
		this.isfriends = isfriends;
	}

	public String getToken() {
		return token;
	}

	

}
