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

public class CourseotherAdapter extends RecyclerView.Adapter<CourseotherAdapter.ViewHolder> {
    private List<String> list;
    private Context context;
    public CourseotherAdapter(Context context, List<String> list) {
        this.list = list;
        this.context = context;
    }
    public CourseotherAdapter(){}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_othercourseclass,parent,false);
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
        switch (list.get(position)){
            case "选择题":
                Glide.with(context).load(R.mipmap.other_tixing1).into(holder.course_img);
                break;
            case "填空题":
                Glide.with(context).load(R.mipmap.other_tixing2).into(holder.course_img);
                break;
            case "问答题":
                Glide.with(context).load(R.mipmap.other_tixing3).into(holder.course_img);
                break;
            case "判断题":
                Glide.with(context).load(R.mipmap.other_tixing4).into(holder.course_img);
                break;
            case "其他":
                Glide.with(context).load(R.mipmap.other_tixing5).into(holder.course_img);
                break;

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView course_img;


        public ViewHolder(View itemView) {
            super(itemView);
            course_img = itemView.findViewById(R.id.course_img);
        }
    }
    //第一步 定义接口
    public interface OnItemClickListener {
        void onClick(int position);
    }

    private OnItemClickListener listener;

    //第二步， 写一个公共的方法
    public void setOnItemClickListener(CourseotherAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
}
