package com.muyou.common.exception;

/**
 * 用于Web端后台管理系统
 * @author 木友茶
 *
 */
public class GlobalException extends RuntimeException {
	private String msg;

	public GlobalException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
