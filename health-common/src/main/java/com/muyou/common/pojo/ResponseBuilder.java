package com.muyou.common.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ResponseBuilder implements Serializable{
    public static final int STATUS_OK = 200;
    public static final int STATUS_BAD_REQUEST = 400;
    public static final int STATUS_UNAUTHORIZED = 401;
    public static final int STATUS_FORBIDDEN = 403;
    public static final int STATUS_NOT_FOUND = 404;
    public static final int STATUS_INTERNAL_SERVER = 500;

    // error response
    public static final ResponseBuilder ERROR_DATA_LOSE = new ResponseBuilder(STATUS_BAD_REQUEST, 999, "Lose Data");
    public static final ResponseBuilder ERROR_BAD_SERVER = new ResponseBuilder(STATUS_INTERNAL_SERVER, 1000, "Bad Server");
    public static final ResponseBuilder ERROR_AUTHORIZATION_FAIL = new ResponseBuilder(STATUS_UNAUTHORIZED, 1001, "Authorization Fail");
    public static final ResponseBuilder ERROR_FORBIDDEN_FAIL = new ResponseBuilder(STATUS_FORBIDDEN, 1002, "Forbidden");
    public static final ResponseBuilder ERROR_INVALID_PARAMETER = new ResponseBuilder(STATUS_BAD_REQUEST, 1003, "Invalid Parameter");
    public static final ResponseBuilder ERROR_USER_EXIST = new ResponseBuilder(STATUS_BAD_REQUEST, 1004, "User Exist");
    public static final ResponseBuilder ERROR_USER_NOT_FOUND = new ResponseBuilder(STATUS_BAD_REQUEST, 1005, "No Such User");
    public static final ResponseBuilder ERROR_MESSAGE_NOT_FOUND = new ResponseBuilder(STATUS_BAD_REQUEST, 1006, "No Such Message");
    public static final ResponseBuilder ERROR_MEDICINE_NOT_FOUND = new ResponseBuilder(STATUS_BAD_REQUEST, 1007, "No Such Medicine");
    public static final ResponseBuilder ERROR_DISEASE_NOT_FOUND = new ResponseBuilder(STATUS_BAD_REQUEST, 1008, "No Such Disease");
    public static final ResponseBuilder ERROR_MEDICINE_CLASSIFY_NOT_FOUND = new ResponseBuilder(STATUS_BAD_REQUEST, 1009, "No Such Medicine Classify");
    public static final ResponseBuilder ERROR_ROOM_NOT_FOUND = new ResponseBuilder(STATUS_BAD_REQUEST, 1010, "No Such ROOM");
    public static final ResponseBuilder ERROR_MAX_ROOM = new ResponseBuilder(STATUS_BAD_REQUEST, 1011, "MAX ROOM");
    public static final ResponseBuilder ERROR_FRIEND_NOT_FOUND = new ResponseBuilder(STATUS_BAD_REQUEST, 1012, "No Such Friend");
    public static final ResponseBuilder ERROR_NEWS_NOT_FOUND = new ResponseBuilder(STATUS_BAD_REQUEST, 1013, "No Such News");
    public static final ResponseBuilder ERROR_RECIPES_NOT_FOUND = new ResponseBuilder(STATUS_BAD_REQUEST, 1014, "No Such Recipes");
    public static final ResponseBuilder ERROR_INGREDIENT_NOT_FOUND = new ResponseBuilder(STATUS_BAD_REQUEST, 1015, "No Such Ingredient");
    public static final ResponseBuilder ERROR_COLLECT_NOT_FOUND = new ResponseBuilder(STATUS_BAD_REQUEST, 1016, "No Such Collection");



    // success response
    public static final ResponseBuilder SUCCESS = new ResponseBuilder(STATUS_OK, 3000, "Success");


    private int status;
    private int code;
    private String message;

    public ResponseBuilder(int status, int code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	@Override
	public String toString() {
		return "ResponseBuilder [status=" + status + ", code=" + code + ", message=" + message
				+ "]";
	}
    
}
