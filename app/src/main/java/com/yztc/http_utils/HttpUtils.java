package com.yztc.http_utils;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/** 数据连接okhttp
 * Created by Administrator on 2016/6/22.
 */
public class HttpUtils {
    private static OkHttpClient okHttpClient=new OkHttpClient();
    public static interface MyCallBack{
        public void success(String result);
        public void error(String erro);
    };
    public static interface MyCallBackArray{
        public void success(byte[] data);
        public void error(String erro);
    };
    //返回json
     public static void getConnection(String url, final MyCallBack myCallBack){
          Request request=new Request.Builder().url(url).build();
          okHttpClient.newCall(request).enqueue(new Callback() {
             @Override
             public void onFailure(Call call, IOException e) {

             }

             @Override
             public void onResponse(Call call, Response response) throws IOException {
                 if(response.code()==200){
                     Handler handler=new Handler(Looper.getMainLooper());
                     final  String result=response.body().string();
                     handler.post(new Runnable() {
                         @Override
                         public void run() {
                            myCallBack.success(result);
                         }
                     });


                 }else{

                     myCallBack.error("连接错误，连接码为"+response.code());
                 }

             }
         });

     }
   //返回byte[] 数组
     public static void getArray(String url,final MyCallBackArray myCallBack){
         Request request=new Request.Builder().url(url).build();
         okHttpClient.newCall(request).enqueue(new Callback() {
             @Override
             public void onFailure(Call call, IOException e) {

             }

             @Override
             public void onResponse(Call call, Response response) throws IOException {
                 if(response.code()==200){
                     Handler handler=new Handler(Looper.getMainLooper());
                     final  byte[] data=response.body().bytes();
                     handler.post(new Runnable() {
                         @Override
                         public void run() {
                             myCallBack.success(data);
                         }
                     });


                 }else{

                     myCallBack.error("连接错误，连接码为"+response.code());
                 }

             }
         });

     }

}
