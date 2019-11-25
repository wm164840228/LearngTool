package com.huancheng.learngtool.ui;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.PointF;

import android.os.Bundle;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.huancheng.learngtool.R;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class PhotoActivity extends BasebussActivity {


    private ViewPager viewPager;
    private ArrayList<String> urlList; /*所有图片url*/
    private int picPosition; /*图片位置*/


    @Override
    protected int setCustomLayout() {
        return R.layout.activity_photo;
    }

    @Override
    protected Activity initView() {
        initData();
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new PictureSlidePagerAdapter(getSupportFragmentManager()));
        /*position*/
        //使用ViewPager的setCurrentItem (int item) 方法设置其初始显示的页面
        viewPager.setCurrentItem(picPosition);
        return this;
    }
    private void initData(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        urlList = bundle.getStringArrayList("list");
        picPosition=Integer.parseInt(bundle.getString("number"));
    }

    /*Adapter*/
    private  class PictureSlidePagerAdapter extends FragmentStatePagerAdapter {

        public PictureSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PictureSlideFragment.newInstance(urlList.get(position));
        }

        @Override
        public int getCount() {
            return urlList.size();
        }
    }
}
