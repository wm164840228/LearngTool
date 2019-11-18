package com.huancheng.learngtool.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.huancheng.learngtool.R;
import com.huancheng.learngtool.adapter.CourseAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

public class MainFragment extends BaseFragment {
    @BindView(R.id.main_course)
    RecyclerView main_course;
    @BindView(R.id.main_calculation)
    TextView main_calculation;
    @BindView(R.id.main_translate)
    TextView main_translate;
    private List<String> courseList;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_main;
    }
    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
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
    @OnClick({R.id.main_translate,R.id.main_calculation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_calculation:
                startActivity(new Intent(getActivity(),CalculationActivity.class));
                break;
            case R.id.main_translate:
                startActivity(new Intent(getActivity(),TranslateActivity.class));
                break;
        }
    }
}
