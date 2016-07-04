package com.yztc.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.yztc.adapter.NewsAdapter;
import com.yztc.http_utils.HttpUtils;
import com.yztc.json_help_utils.JsonHelper;
import com.yztc.json_help_utils.Json_param;
import com.yztc.json_help_utils.Type;
import com.yztc.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/28.
 */
public class News extends Fragment {
    private ListView listView;
    private List<com.yztc.bean.News> datas;
    private NewsAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.news,null);
        listView= (ListView) view.findViewById(R.id.news);
        adapter=new NewsAdapter(getActivity());
        datas=new ArrayList<>();
        init();
        return  view;
    }
    private void init(){
        HttpUtils.getConnection("http://c.3g.163.com/nc/article/list/T1348649776727/0-20.html", new HttpUtils.MyCallBack() {
            @Override
            public void success(String result) {
                datas= JsonHelper.getList(result,com.yztc.bean.News.class,new Json_param("T1348649776727", Type.JSON_ARRAY));
                adapter.setDatas(datas);
                listView.setAdapter(adapter);
            }

            @Override
            public void error(String erro) {

            }
        });
    }
}
