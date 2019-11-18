package com.huancheng.learngtool.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;

public abstract class BasebussActivity extends AppCompatActivity {


    protected Activity _context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setCustomLayout());
        ButterKnife.bind(this);
        _context=this;
        initView();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);
    }
    /**
     * 初始化布局
     * */
    protected abstract int setCustomLayout();

    /**
     * 初始化控件
     * */
    protected abstract Activity initView();



}
