package com.yztc.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.fastjson.JSONObject;
import com.yztc.adapter.VideoAdapter;
import com.yztc.adapter.VideoAdapterRec;
import com.yztc.bean.Movie;
import com.yztc.call_back.RecyclerCallBack;
import com.yztc.http_utils.HttpUtils;
import com.yztc.json_help_utils.JsonHelper;
import com.yztc.json_help_utils.Json_param;
import com.yztc.json_help_utils.Type;
import com.yztc.myapplication.R;
import com.yztc.myapplication.ShowMovie;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/6/28.
 */
public class Video extends Fragment {

    @BindView(R.id.video_rec)
    RecyclerView videoRec;
    private List<Movie> datas;
    private VideoAdapterRec adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video, null);
        ButterKnife.bind(this, view);
        datas=new ArrayList<>();
        adapter = new VideoAdapterRec(getActivity(),datas);
        GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),2);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        videoRec.setLayoutManager(layoutManager);
        videoRec.setAdapter(adapter);
        adapter.setCallBack(new RecyclerCallBack<ImageView>() {
            @Override
            public void onclick(int position, ImageView view) {
                Intent intent=new Intent(getActivity(), ShowMovie.class);
                intent.putExtra("path",datas.get(position).getMp4_url());
                intent.putExtra("imgpath",datas.get(position).getCover());
                startActivity(intent);
            }
        });
        Log.e("tag",videoRec.toString());
        init();
        return view;
    }

    private void init() {
        HttpUtils.getConnection("http://c.3g.163.com/nc/video/Tlist/T1457069041911/0-10.html", new HttpUtils.MyCallBack() {
            @Override
            public void success(String result) {
               datas.addAll(JsonHelper.getList(result, Movie.class, new Json_param("T1457069041911", Type.JSON_ARRAY))) ;
                //Log.e("tag",datas.toString());
                adapter.notifyDataSetChanged();

            }

            @Override
            public void error(String erro) {

            }
        });


    }
}
