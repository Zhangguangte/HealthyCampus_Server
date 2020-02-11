package com.muyou.sso.form;

import java.io.Serializable;

import com.muyou.common.util.StringUtil;

public class LoginForm implements Serializable{
	private String device_id;
	private String account;
	private String password;

    public boolean validate() {
        return StringUtil.isEmpty(account)
                || StringUtil.isEmpty(password);
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