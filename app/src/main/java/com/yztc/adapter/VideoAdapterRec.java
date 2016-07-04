package com.yztc.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yztc.bean.Movie;
import com.yztc.call_back.RecyclerCallBack;
import com.yztc.myapplication.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/6/30.
 */
public class VideoAdapterRec extends RecyclerView.Adapter<VideoAdapterRec.MyViewHold> {
   private RecyclerCallBack<ImageView> callBack;
    private Context context;
    private List<Movie> datas;

    public void setCallBack(RecyclerCallBack<ImageView> callBack) {
        this.callBack = callBack;
    }

    public VideoAdapterRec(Context context, List<Movie> datas) {
        this.context = context;
        this.datas = datas;
        Log.e("tag", "==================");
    }

    public void setDatas(List<Movie> datas) {
        this.datas = datas;
    }

    @Override
    public MyViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("tag", "---------------------------------------");
        View convertView = LayoutInflater.from(context).inflate(R.layout.video_card, null);
        Log.e("tag", "----------------");
        return new MyViewHold(convertView);
    }

    @Override
    public void onBindViewHolder(MyViewHold holder, int position) {
        Movie movie = datas.get(position);
        holder.videoDes.setText(movie.getTopicDesc());
        Glide.with(context).load(movie.getTopicImg()).into(holder.videoUserImg);
        Glide.with(context).load(movie.getCover()).centerCrop().into(holder.cardImag);
        holder.videoUserName.setText(movie.getVideoTopic().getTname());

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    class MyViewHold extends RecyclerView.ViewHolder {
        @BindView(R.id.video_des)
        TextView videoDes;
        @BindView(R.id.video_userImg)
        ImageView videoUserImg;
        @BindView(R.id.video_userName)
        TextView videoUserName;
        @BindView(R.id.card_imag)
        ImageView cardImag;
        public MyViewHold(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            Log.e("tag", videoDes.toString());
            cardImag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  callBack.onclick(getLayoutPosition(),(ImageView)v);
                }
            });

        }

    }
}
