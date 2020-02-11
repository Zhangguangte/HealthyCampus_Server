package com.muyou.sso.form;

import java.io.Serializable;

import com.muyou.common.util.StringUtil;

public class FriendshipAddForm implements Serializable{
	private String friend_id;

    public boolean validate() {
        return !StringUtil.isEmpty(friend_id);
    }
}
