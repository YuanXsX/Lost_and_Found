package com.laf.utils;

import com.alibaba.fastjson.JSON;
import com.laf.entity.ResultMessage;

public class MessageUtils {
    public static String getMessage(boolean isSystem, String formName, Object message){
        ResultMessage msg = new ResultMessage();
        msg.setSystem(isSystem);//？？
        msg.setMessage(message);
        if(formName != null){
            msg.setFromName(formName);
        }
        return JSON.toJSONString(msg);
    }
}
