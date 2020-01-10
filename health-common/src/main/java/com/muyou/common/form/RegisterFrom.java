package com.muyou.common.form;

import java.io.Serializable;

import com.muyou.common.util.StringUtil;

public class RegisterFrom implements Serializable {
	private String password;
	private String phone;

	public boolean validate() {
		return StringUtil.isEmpty(phone) || StringUtil.isEmpty(password);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
