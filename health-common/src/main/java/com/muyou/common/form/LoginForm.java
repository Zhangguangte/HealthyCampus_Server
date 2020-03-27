package com.muyou.common.form;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.muyou.common.util.StringUtil;

public class LoginForm implements Serializable {
	private String device_id;
	private String account;
	private String password;

	public LoginForm() {
	}
	
	public LoginForm(String account, String password) {
		super();
		this.account = account;
		this.password = password;
	}

	public boolean validate() {
		return StringUtils.isBlank(account) || StringUtils.isBlank(password);
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
