package com.muyou.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbTimeAttend implements Serializable{
    private Integer id;

    private Integer tId;

    private String aNo;

    private Date created;

    private Date endTime;

    private Date updated;

    private Boolean status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }

    public String getaNo() {
        return aNo;
    }

    public void setaNo(String aNo) {
        this.aNo = aNo == null ? null : aNo.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}