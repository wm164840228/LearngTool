package com.huancheng.learngtool.ui;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.huancheng.learngtool.R;
import com.huancheng.learngtool.adapter.CourseotherAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

public class MainotherFragment extends BaseFragment {
    @BindView(R.id.recycler_other)
    RecyclerView recycler_other;
    private List<String> courseList;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_other;
    }
    static MainotherFragment newInstance() {
        MainotherFragment fragment = new MainotherFragment();
        return fragment;
    }
    @Override
    protected void initView() {
        recycler_other.setHasFixedSize(true);
        recycler_other.setNestedScrollingEnabled(false);
    }

    @Override
    protected void initData() {
        courseList = new ArrayList<>();
        courseList.add("选择题");
        courseList.add("填空题");
        courseList.add("问答题");
        courseList.add("判断题");
        courseList.add("其他");
        recycler_other.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        CourseotherAdapter courseAdapter=new CourseotherAdapter(getContext(),courseList);
        recycler_other.setAdapter(courseAdapter);
        courseAdapter.setOnItemClickListener(new CourseotherAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent=new Intent(getActivity(),SubjectotherActivity.class);
                intent.putExtra("tixing",courseList.get(position));
                startActivity(intent);
            }
        });
    }
    @OnClick({R.id.chengdu1,R.id.chengdu2,R.id.chengdu3,R.id.chengdu4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.chengdu1:
                Intent intent=new Intent(getActivity(),SubjectotherActivity.class);
                intent.putExtra("chengdu","不懂");
                startActivity(intent);
                break;
            case R.id.chengdu2:
                Intent intent2=new Intent(getActivity(),SubjectotherActivity.class);
                intent2.putExtra("chengdu","略懂");
                startActivity(intent2);
                break;
            case R.id.chengdu3:
                Intent intent3=new Intent(getActivity(),SubjectotherActivity.class);
                intent3.putExtra("chengdu","基本懂");
                startActivity(intent3);
                break;
            case R.id.chengdu4:
                Intent intent4=new Intent(getActivity(),SubjectotherActivity.class);
                intent4.putExtra("chengdu","完全懂");
                startActivity(intent4);
                break;
        }
    }

    @Override
    protected void BindComponentEvent() {
    }

    @Override
    protected void doActivityResult(int requestCode, Intent intent) {

    }
}
