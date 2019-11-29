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
import com.huantansheng.easyphotos.models.album.entity.Photo;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.OnClick;

public class PhotoActivity extends BasebussActivity {


    private ViewPager viewPager;
    private ArrayList<Photo> list; /*所有图片url*/
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
        list = bundle.getParcelableArrayList("list");
        picPosition=Integer.parseInt(bundle.getString("number"));
    }

    /*Adapter*/
    private  class PictureSlidePagerAdapter extends FragmentStatePagerAdapter {

        public PictureSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PictureSlideFragment.newInstance("file:/"+list.get(position).path);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
    @OnClick({R.id.photo_back, R.id.photo_delete,R.id.phtot_cut})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.photo_back:
                onBackPressed();
                break;
            case R.id.photo_delete:
                int currentItem = viewPager.getCurrentItem();
                list.remove(currentItem);
                Intent intent= new Intent();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("list",list);
                intent.putExtras(bundle);
                setResult(101,intent);
                onBackPressed();
                break;
            case R.id.phtot_cut:
                Intent intent1 = new Intent(this, CutActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putParcelableArrayList("list",list);
                intent1.putExtras(bundle1);
                intent1.putExtra("number",viewPager.getCurrentItem());
                startActivityForResult(intent1,200);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==200){
            if (resultCode==201){
                Bundle bundle1 = data.getExtras();
                list.clear();
                list.addAll(bundle1.getParcelableArrayList("list"));
                Intent intent= new Intent();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("list",list);
                intent.putExtras(bundle);
                setResult(101,intent);
                onBackPressed();
            }
        }
    }
}
