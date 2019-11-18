package com.huancheng.learngtool.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huancheng.learngtool.R;


import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by admin on 2018/12/20.
 */

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {
    private List<String> list;
    private Context context;
    public CourseAdapter(Context context, List<String> list) {
        this.list = list;
        this.context = context;
    }
    public CourseAdapter(){}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_courseclass,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.course_text.setText(list.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });
        switch (list.get(position)){
            case "语文":
                Glide.with(context).load(R.mipmap.frist_chinese).into(holder.course_img);
                break;
            case "数学":
                Glide.with(context).load(R.mipmap.first_mathematics).into(holder.course_img);
                break;
            case "英语":
                Glide.with(context).load(R.mipmap.first_english).into(holder.course_img);
                break;
            case "历史":
                Glide.with(context).load(R.mipmap.frist_histroy).into(holder.course_img);
                break;
            case "地理":
                Glide.with(context).load(R.mipmap.frist_eography).into(holder.course_img);
                break;
            case "政治":
                Glide.with(context).load(R.mipmap.frist_politics).into(holder.course_img);
                break;
            case "生物":
                Glide.with(context).load(R.mipmap.frist_biology).into(holder.course_img);
                break;
            case "物理":
                Glide.with(context).load(R.mipmap.first_physics).into(holder.course_img);
                break;
            case "化学":
                Glide.with(context).load(R.mipmap.frist_chemistry).into(holder.course_img);
                break;
            case "科学":
                Glide.with(context).load(R.mipmap.frist_science).into(holder.course_img);
                break;
            case "其他":
                Glide.with(context).load(R.mipmap.frist_other).into(holder.course_img);
                break;

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView course_text;
        private ImageView course_img;


        public ViewHolder(View itemView) {
            super(itemView);
            course_text = itemView.findViewById(R.id.course_text);
            course_img = itemView.findViewById(R.id.course_img);
        }
    }
    //第一步 定义接口
    public interface OnItemClickListener {
        void onClick(int position);
    }

    private OnItemClickListener listener;

    //第二步， 写一个公共的方法
    public void setOnItemClickListener(CourseAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
}
