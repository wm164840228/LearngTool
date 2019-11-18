package com.huancheng.learngtool.ui;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huancheng.learngtool.R;

public class AboutActivity extends BasebussActivity {

    @BindView(R.id.iv_left_title_bar)
    ImageView ivLeftTitleBar;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.rl_title_bar)
    RelativeLayout rlTitleBar;
    @BindView(R.id.about_logo)
    ImageView about_logo;
    @BindView(R.id.about_code)
    TextView about_code;



    @Override
    protected int setCustomLayout() {
        return R.layout.activity_about;
    }

    @Override
    protected Activity initView() {
        tvBack.setText("关于");
        String versionName = "";
        try {
            versionName = getVersionName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        about_code.setText("当前版本  V"+versionName);
        about_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return null;
    }

    @OnClick({R.id.iv_left_title_bar, R.id.rl_title_bar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left_title_bar:
                onBackPressed();
                break;
            case R.id.rl_title_bar:
                break;
        }
    }
    private String getVersionName() throws Exception
    {
        // 获取packagemanager的实例
        PackageManager packageManager = getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(),0);
        String version = packInfo.versionName;
        return version;
    }
}
