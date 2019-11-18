package com.huancheng.learngtool.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.huancheng.learngtool.R;
import com.huancheng.learngtool.bean.Classify;
import com.huancheng.learngtool.bean.UriDeserializer;
import com.huancheng.learngtool.util.GlideEngine;
import com.huancheng.learngtool.util.NullUtil;
import com.huancheng.learngtool.util.TimeUtils;
import com.huantansheng.easyphotos.models.album.entity.Photo;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by admin on 2018/12/20.
 */

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.ViewHolder> {
    private List<Classify> list;
    private Context context;
    public SubjectAdapter(Context context, List<Classify> list) {
        this.list = list;
        this.context = context;
    }
    public SubjectAdapter(){}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_subject,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Uri.class, new UriDeserializer())
                .create();
        List<Photo> photoList = gson.fromJson(list.get(position).getUri(),new TypeToken<List<Photo>>(){}.getType());
        GlideEngine.getInstance().loadPhoto(context, photoList.get(0).uri,holder.subject_item_img);
        String title="";
        if (!NullUtil.StringIsNull(list.get(position).getLaiyuan())){
            title=title+list.get(position).getLaiyuan()+"/";
        }
        if (!NullUtil.StringIsNull(list.get(position).getTixing())){
            title=title+list.get(position).getTixing()+"/";
        }
        if (!NullUtil.StringIsNull(list.get(position).getChengdu())){
            title=title+list.get(position).getChengdu()+"/";
        }
        holder.subject_item_title.setText(title);
        holder.subject_item_time.setText(TimeUtils.long2String(list.get(position).getTime(),"yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView subject_item_title;
        private TextView subject_item_time;
        private ImageView subject_item_img;


        public ViewHolder(View itemView) {
            super(itemView);
            subject_item_title = itemView.findViewById(R.id.subject_item_title);
            subject_item_time = itemView.findViewById(R.id.subject_item_time);
            subject_item_img = itemView.findViewById(R.id.subject_item_img);
        }
    }
    //第一步 定义接口
    public interface OnItemClickListener {
        void onClick(int position);
    }

    private OnItemClickListener listener;

    //第二步， 写一个公共的方法
    public void setOnItemClickListener(SubjectAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
}
