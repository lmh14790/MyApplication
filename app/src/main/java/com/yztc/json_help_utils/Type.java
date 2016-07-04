package com.yztc.json_help_utils;

/**
 *枚举类 表示是json_object还是json_array
 * Created by Administrator on 2016/6/2.
 */
public enum Type {
    JSON_OBJT(1),JSON_ARRAY(2);
     private Type(int type){
         this.type=type;
     }
      public int type;
}
