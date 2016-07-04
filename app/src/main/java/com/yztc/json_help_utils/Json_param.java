package com.yztc.json_help_utils;

/**
 * 解析json剥离原串的帮助类
 * Created by Administrator on 2016/6/2.
 */
public class Json_param {
    private String key;
    private Type   type;

    public Json_param(String key, Type type) {
        this.key = key;
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
