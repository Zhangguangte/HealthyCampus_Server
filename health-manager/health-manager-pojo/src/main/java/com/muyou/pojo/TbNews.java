package com.muyou.pojo;

import java.io.Serializable;

public class TbNews implements Serializable{
    private Integer id;

    private String createTime;

    private String userType;

    private String content;
    private TbNewsUser newsUser;
    public Integer getId() {
        return id;
    }

    public TbNewsUser getNewsUser() {
		return newsUser;
	}

	public void setNewsUser(TbNewsUser newsUser) {
		this.newsUser = newsUser;
	}

	public void setId(Integer id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}