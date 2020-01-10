package com.muyou.pojo;

import java.io.Serializable;

public class TbFriendship implements Serializable{
    private String id;

    private String userId;

    private String userId2;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserId2() {
        return userId2;
    }

    public void setUserId2(String userId2) {
        this.userId2 = userId2 == null ? null : userId2.trim();
    }
}