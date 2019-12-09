package com.huancheng.learngtool.ui;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.huancheng.learngtool.R;
import com.huancheng.learngtool.adapter.CourseAdapter;
import com.huancheng.learngtool.util.NullUtil;
import com.huancheng.learngtool.util.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

public class MainsubjectFragment extends BaseFragment {
    @BindView(R.id.main_course)
    RecyclerView main_course;
    private List<String> courseList;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_subject;
    }
    static MainsubjectFragment newInstance() {
        MainsubjectFragment fragment = new MainsubjectFragment();
        return fragment;
    }
    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        courseList = new ArrayList<>();
        courseList.add("语文");
        courseList.add("数学");
        courseList.add("英语");
        courseList.add("历史");
        courseList.add("地理");
        courseList.add("政治");
        courseList.add("生物");
        courseList.add("物理");
        courseList.add("化学");
        courseList.add("科学");
        courseList.add("其他");
        main_course.setLayoutManager(new GridLayoutManager(getContext(), 3, GridLayoutManager.VERTICAL, false));
        CourseAdapter courseAdapter=new CourseAdapter(getContext(),courseList);
        main_course.setAdapter(courseAdapter);
        courseAdapter.setOnItemClickListener(new CourseAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent=new Intent(getActivity(),SubjectActivity.class);
                intent.putExtra("sub",courseList.get(position));
                if (!NullUtil.StringIsNull((String) SharedPreferencesUtil.getParam(_context, "nianji",""))){
                    intent.putExtra("nianji",(String) SharedPreferencesUtil.getParam(_context, "nianji",""));
                }
                startActivity(intent);
            }
        });
    }

    @Override
    protected void BindComponentEvent() {
    }

    @Override
    protected void doActivityResult(int requestCode, Intent intent) {

    }
}
