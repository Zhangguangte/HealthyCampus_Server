package com.muyou.pojo;

import java.io.Serializable;

public class TbFeedback implements Serializable{
    private Integer id;

    private String advice;

    private String contract;

    public TbFeedback(String advice,String contract)
    {
    	this.advice = advice;
    	this.contract = contract;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice == null ? null : advice.trim();
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract == null ? null : contract.trim();
    }
}