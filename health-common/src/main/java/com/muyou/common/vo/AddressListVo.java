package com.muyou.common.vo;

import java.io.Serializable;

public class AddressListVo implements Serializable{
	private String nickname;
	private String account;
	private String image;
	private String sortLetters; // 显示数据拼音的首字母

	public AddressListVo() {
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSortLetters() {
		return sortLetters;
	}

	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}

	 public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

}
