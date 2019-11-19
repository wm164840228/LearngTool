package com.huancheng.learngtool.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import okhttp3.Call;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.huancheng.learngtool.R;
import com.huancheng.learngtool.bean.UserBean;
import com.huancheng.learngtool.bean.UsersBean;
import com.huancheng.learngtool.util.ChannelUtil;
import com.huancheng.learngtool.util.HttpUtil;
import com.huancheng.learngtool.util.LoadingUtil;
import com.huancheng.learngtool.util.SharedPreferencesUtil;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.huancheng.learngtool.util.SharedPreferencesUtil.USER_BEAN;
import static com.huancheng.learngtool.util.SharedPreferencesUtil.USER_ID;

public class LoginActivity extends BasebussActivity implements View.OnClickListener {


    private ImageView iv_line,iv_qq,iv_phone;
    private LinearLayout ll_login_phone;
    private ImageView iv_logo;
    private ImageView iv_back;
    private LoadingUtil loadingUtil;
    private String user_id;
    private boolean state;
    @Override
    protected int setCustomLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected Activity initView() {
        iv_line = findViewById(R.id.iv_line);
        iv_back = findViewById(R.id.iv_back);
        iv_qq = findViewById(R.id.iv_qq);
        iv_phone = findViewById(R.id.iv_phone);
        ll_login_phone = findViewById(R.id.ll_login_phone);
        iv_logo = findViewById(R.id.iv_logo);
        loadingUtil = new LoadingUtil(this);
        user_id = (String) SharedPreferencesUtil.getParam(_context, USER_ID,"");
       /* if (UserBean.touriststate){
            state = true;
        }else {
            state = false;
        }*/
        iv_phone.setOnClickListener(this);
        iv_qq.setOnClickListener(this);
        iv_line.setOnClickListener(this);
        iv_logo.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        return this;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_line://微信登录
                loadingUtil.show();
                UMShareAPI.get(this).getPlatformInfo(_context, SHARE_MEDIA.WEIXIN, umAuthListener);
                break;
            case R.id.iv_qq:
                loadingUtil.show();
                UMShareAPI.get(this).getPlatformInfo(_context, SHARE_MEDIA.QQ, umAuthListener);
                break;
            case R.id.iv_phone:
                startActivity(new Intent(this,LoginPhoneActivity.class));
                break;
            case R.id.iv_logo:
                break;
            case R.id.iv_back:
                finish();
                break;

        }
    }
    //等三方登录回调
    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            try {
                loadingUtil.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
            String uid = "";//用户唯一标识
            String name = "";//用户昵称
            String gender = "";//用户性别
            String iconurl = "";//用户头像

            if (SHARE_MEDIA.WEIXIN == share_media) {
                uid=map.get("openid");
                name = map.get("screen_name");
                gender = map.get("gender");
                iconurl = map.get("profile_image_url");
                loginShare(name,uid,iconurl,gender,state);
            } else {
                uid = map.get("uid");
                name = map.get("name");
                gender = map.get("gender");
                iconurl = map.get("iconurl");
                loginShareQQ(name,uid,iconurl,gender,state);
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, int i, Throwable throwable) {
            try {
                loadingUtil.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Toast.makeText(getApplicationContext(),"登录失败",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int i) {
            try {
                loadingUtil.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Toast.makeText(getApplicationContext(),"授权取消",Toast.LENGTH_LONG).show();
        }
    };

    /**
     * 微信登录
     * */
    private void loginShare(String wechatName,String openId,String iconUrl,String male,boolean state){
        OkHttpUtils
                .post()
                .url(HttpUtil.SOCKET_HOST + HttpUtil.WELOGIN)
                .addParams("wechatName", wechatName)
                .addParams("wechatopenId", openId)
                .addParams("iconUrl", iconUrl)
                .addParams("registerChannelId", ChannelUtil.getChannel(_context))
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getApplicationContext(),"登录失败",Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        UsersBean usersBean = new Gson().fromJson(response,UsersBean.class);
                        switch (Integer.parseInt(usersBean.getCode())) {
                            case 0:
                                SharedPreferencesUtil.setParam(_context, USER_ID, usersBean.getUser().getId());
                                SharedPreferencesUtil.setObject(_context, USER_BEAN, usersBean.getUser());
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                                break;
                            default:
                                Toast.makeText(getApplicationContext(), usersBean.getMsg(), Toast.LENGTH_LONG).show();
                                break;
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
    /**
     * QQ登录 state区别登录和绑定
     * */
    private void loginShareQQ(String wechatName,String openId,String iconUrl,String male,boolean state){
        OkHttpUtils
                .post()
                .url(HttpUtil.SOCKET_HOST + HttpUtil.QQLOGIN)
                .addParams("qqName", wechatName)
                .addParams("qq", openId)
                .addParams("iconUrl", iconUrl)
                .addParams("registerChannelId", ChannelUtil.getChannel(_context))
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getApplicationContext(),"登录失败",Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        UsersBean usersBean = new Gson().fromJson(response,UsersBean.class);
                        switch (Integer.parseInt(usersBean.getCode())) {
                            case 0:
                                SharedPreferencesUtil.setParam(_context, USER_ID, usersBean.getUser().getId());
                                SharedPreferencesUtil.setObject(_context, USER_BEAN, usersBean.getUser());
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                                break;
                            default:
                                Toast.makeText(getApplicationContext(), usersBean.getMsg(), Toast.LENGTH_LONG).show();
                                break;
                        }
                    }
                });
    }
}
