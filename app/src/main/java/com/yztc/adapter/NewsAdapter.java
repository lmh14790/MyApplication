package com.yztc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.yztc.bean.News;
import com.yztc.myapplication.R;

import java.util.List;

/**
 * Created by Administrator on 2016/6/28.
 */
public class NewsAdapter extends BaseAdapter{
    private List<News> datas;
    private Context context;

    public NewsAdapter(List<News> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    public NewsAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<News> datas) {
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
            convertView= LayoutInflater.from(context).inflate(R.layout.news_item,null);
            vh=new ViewHold();
            vh.iv= (ImageView) convertView.findViewById(R.id.news_iv);
            vh.tv= (TextView) convertView.findViewById(R.id.news_tv);
            convertView.setTag(vh);
        }else {
            vh= (ViewHold) convertView.getTag();
        }
        News news =datas.get(position);
        vh.tv.setText(news.getTitle());
        Glide.with(context).load(news.getImgsrc()).centerCrop().placeholder(R.mipmap.ic_launcher).into(vh.iv);
        return convertView;
    }
    static class ViewHold{
        ImageView iv;
        TextView tv;
    }
}
