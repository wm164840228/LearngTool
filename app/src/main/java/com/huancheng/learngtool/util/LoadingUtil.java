package com.huancheng.learngtool.util;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;

import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


import com.huancheng.learngtool.R;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 获取广告
 */
public class LoadingUtil extends Dialog {
    private Activity mActivity;
    private String mtxt;
    @BindView(R.id.loading_TextView)
    TextView loading_TextView;
    @BindView(R.id.loading_img)
    ImageView loading_img;
    public LoadingUtil(@NonNull Activity activity) {
        super(activity, R.style.ReadSettingDialog);
        mActivity = activity;
        mtxt="正在加载中...";
    }
    public LoadingUtil(@NonNull Activity activity, String text) {
        super(activity, R.style.ReadSettingDialog);
        mActivity = activity;
        mtxt=text;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loadings);
        ButterKnife.bind(this);
        setUpWindow();
        initData();
        initWidget();
    }

    //设置Dialog显示的位置
    private void setUpWindow() {
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        window.setAttributes(lp);
    }

    private void initData() {
        loading_TextView.setText(mtxt);
    }

    private void initWidget() {
        // 加载动画，动画用户使img图片不停的旋转
        Animation animation = AnimationUtils.loadAnimation(mActivity,
                R.anim.rotate_anim3);
        // 显示动画
        loading_img.startAnimation(animation);
    }

}
