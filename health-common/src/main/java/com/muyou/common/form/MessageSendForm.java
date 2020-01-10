package com.muyou.common.form;

import java.io.Serializable;
import java.util.Map;

import com.muyou.common.util.StringUtil;

public class MessageSendForm implements Serializable{
	private String to;
	private String type;
	private Map<String, Object> content;

    public boolean validate() {
        return !StringUtil.isEmpty(to)
                && !StringUtil.isEmpty(type)
                && content != null
                && content.size() > 0;
    }
}
