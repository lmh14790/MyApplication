package com.yztc.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yztc.bean.Movie;
import com.yztc.myapplication.R;

import java.util.List;

/**
 * Created by Administrator on 2016/6/28.
 */
public class VideoAdapter extends BaseAdapter{
    private List<Movie> datas;
    private Context context;

    public VideoAdapter(List<Movie> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    public VideoAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<Movie> datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHold vh=null;
         if(convertView==null){
         convertView= LayoutInflater.from(context).inflate(R.layout.video_card,null);
             vh=new ViewHold();
             vh.iv= (ImageView) convertView.findViewById(R.id.video_userImg);
             vh.tv= (TextView) convertView.findViewById(R.id.video_userName);
             convertView.setTag(vh);
         }else {
             vh= (ViewHold) convertView.getTag();
         }
          Movie m=datas.get(position);
           vh.tv.setText(m.getTopicDesc());
           Glide.with(context).load(m.getCover()).centerCrop().placeholder(R.mipmap.ic_launcher).into(vh.iv);
           return convertView;
    }
    static class ViewHold{
        ImageView iv;
        TextView tv;
    }
}
