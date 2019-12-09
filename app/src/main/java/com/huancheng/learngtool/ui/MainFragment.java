package com.huancheng.learngtool.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.huancheng.learngtool.R;
import com.huancheng.learngtool.adapter.CourseAdapter;
import com.huancheng.learngtool.util.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.OnClick;

import static com.huancheng.learngtool.util.SharedPreferencesUtil.USER_ID;

public class MainFragment extends BaseFragment {
    private List<Fragment> fragmentList;
    @BindView(R.id.main_viewpager)
    ViewPager main_viewpager;
    @BindView(R.id.other)
    TextView other;
    @BindView(R.id.other_line)
    ImageView other_line;
    @BindView(R.id.cuotiben)
    TextView cuotiben;
    @BindView(R.id.cuotiben_line)
    ImageView cuotiben_line;
    @BindView(R.id.main_spinner)
    Spinner main_spinner;
    private String[] ctype;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_main;
    }
    static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }
    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        fragmentList = new ArrayList<>();
        fragmentList.add(MainsubjectFragment.newInstance());
        fragmentList.add(MainotherFragment.newInstance());
        main_viewpager.setAdapter(new MainAdapter(getChildFragmentManager()));
        main_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                initTop(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        ctype = new String[]{"一年级", "二年级", "三年级", "四年级","五年级", "六年级", "七年级", "八年级","九年级", "初一", "初二", "初三","高一", "高二", "高三"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),R.layout.item_spinner, ctype);  //创建一个数组适配器
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);     //设置下拉列表框的下拉选项样式
        main_spinner.setAdapter(adapter);
        main_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SharedPreferencesUtil.setParam(_context,"nianji",ctype[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        String nianji = (String) SharedPreferencesUtil.getParam(_context, "nianji","");
        int index = getIndex(ctype, nianji);
        if (index!=-1){
            main_spinner.setSelection(index);
        }else {
            SharedPreferencesUtil.setParam(_context,"nianji","一年级");
        }
    }

    private void initTop(int position) {
        if (position==0){
            cuotiben.setTextColor(getResources().getColor(R.color.maintop1));
            cuotiben_line.setVisibility(View.VISIBLE);
            other.setTextColor(getResources().getColor(R.color.maintop2));
            other_line.setVisibility(View.INVISIBLE);
        }else {
            cuotiben.setTextColor(getResources().getColor(R.color.maintop2));
            cuotiben_line.setVisibility(View.INVISIBLE);
            other.setTextColor(getResources().getColor(R.color.maintop1));
            other_line.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void BindComponentEvent() {
    }

    @Override
    protected void doActivityResult(int requestCode, Intent intent) {

    }
    @OnClick({R.id.main_translate,R.id.main_calculation,R.id.main_top1,R.id.main_top2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_calculation:
                startActivity(new Intent(getActivity(),CalculationActivity.class));
                break;
            case R.id.main_translate:
                startActivity(new Intent(getActivity(),TranslateActivity.class));
                break;
            case R.id.main_top1:
                main_viewpager.setCurrentItem(0);
                initTop(0);
                break;
            case R.id.main_top2:
                main_viewpager.setCurrentItem(1);
                initTop(1);
                break;
        }
    }
    private class MainAdapter extends FragmentPagerAdapter{
        MainAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
    public static int getIndex(String[] arr, String value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(value)) {
                return i;
            }
        }
        return -1;//如果未找到返回-1
    }
}
