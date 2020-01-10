package com.muyou.common.vo;

import java.io.Serializable;
import java.util.Map;

public class MessageVo implements Serializable{
    public String id;
    public String fromId;
    public String type;
    public Map<String, Object> content;
    public String create_time;

    public MessageVo() {
    }


//    public MessageVo(OfflineMessage msg) {
//        this.id = msg.getId();
//        Message _msg = msg.getMessage();
//        this.fromId = _msg.getFromId();
//        this.type = _msg.getType();
//        this.content = _msg.getContentMap();
//        this.create_time = _msg.getCreateTime();
//    }

}
