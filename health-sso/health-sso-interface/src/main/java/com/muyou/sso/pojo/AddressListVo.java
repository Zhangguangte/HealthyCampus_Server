package com.muyou.sso.pojo;

import java.io.Serializable;

import com.muyou.pojo.TbUser;


public class AddressListVo implements Serializable {
	private String nickname;
	private String account;
	private String image;
	private String sortLetters; // 显示数据拼音的首字母

	public AddressListVo(TbUser user) {
		this.nickname = user.getNickname();
		this.account = user.getAccount();
		this.sortLetters = user.getInitials();
	}
}
