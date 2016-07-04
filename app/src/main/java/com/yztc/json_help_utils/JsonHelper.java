package com.yztc.json_help_utils;

import com.alibaba.fastjson.JSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2016/6/2.
 */
public class JsonHelper {
    /**
     * 解析json到javabean
     * @param jsonString 原串
     * @param clazz     javabean.class
     * @param params    剥离原串需要的参数
     * @param <T>       泛型
     * @return          解析出来的json串
     */
    public static<T> List<T> getList(String jsonString,Class<T> clazz,Json_param... params){
        String resultJsonString=getRealJsonString(jsonString, params);

       return  JSON.parseArray(resultJsonString,clazz);
    }

    /**
     * 剥离原串得到要使用的串
     * @param jsonString 原串
     * @param params   剥离原串使用的参数
     * @return  真正要使用的串
     */
    private static String getRealJsonString(String jsonString,Json_param... params){

        JSONObject object= null;
        JSONArray array=null;
        if(params==null){
            return  jsonString;
        }
        try {
            object = new JSONObject(jsonString);
            for (int i = 0; i <params.length ; i++) {
                Json_param param=params[i];
                if(param.getType().type==Type.JSON_OBJT.type){
                    object=object.getJSONObject(param.getKey());
                }else{
                    array=object.getJSONArray(param.getKey());
                }
            }
            if(array!=null){
                return array.toString();
            }else{
                return object.toString();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


     return null;
    }
}
