package com.muyou.common.exception;

import com.muyou.common.pojo.ResponseBuilder;

/**
 * 用于Android前台
 * @author 木友茶
 *
 */
public class ServiceException extends Exception {
    private boolean logException;
    
    private ResponseBuilder responseBuilder;

    public ServiceException() {}
    
    public ServiceException(ResponseBuilder responseBuilder) {
        super(responseBuilder.getMessage());
        this.logException = false;
        this.responseBuilder = responseBuilder;
    }

    public ServiceException(ResponseBuilder responseBuilder, boolean logException) {
        super(responseBuilder.getMessage());
        this.logException = logException;
        this.responseBuilder = responseBuilder;
    }

    public boolean isLogException() {
        return logException;
    }

    public void setLogException(boolean logException) {
        this.logException = logException;
    }

    public ResponseBuilder getResponseBuilder() {
        return responseBuilder;
    }

    public void setResponseBuilder(ResponseBuilder responseBuilder) {
        this.responseBuilder = responseBuilder;
    }
}
