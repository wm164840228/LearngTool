package com.huancheng.learngtool.ui;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.huancheng.learngtool.R;
import com.huancheng.learngtool.bean.UserBean;
import com.huancheng.learngtool.bean.UsersBean;
import com.huancheng.learngtool.util.ChannelUtil;
import com.huancheng.learngtool.util.HttpUtil;
import com.huancheng.learngtool.util.LoadingUtil;
import com.huancheng.learngtool.util.NullUtil;
import com.huancheng.learngtool.util.SharedPreferencesUtil;
import com.huancheng.learngtool.util.StringUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.regex.Pattern;

import static com.huancheng.learngtool.util.SharedPreferencesUtil.USER_BEAN;
import static com.huancheng.learngtool.util.SharedPreferencesUtil.USER_ID;

public class LoginPhoneActivity extends BasebussActivity implements View.OnClickListener{
    private EditText et_login_phone,et_login_password,et_number_password;//手机号、密码
    private Button btn_login_register,btn_login_login;//注册按钮
    private TextView tv_back,tv_captcha;//微信登录、qq登录
    private ImageView iv_eye,iv_left_title_bar;//设置密码后面的小图
    private boolean flag = true;
    private RelativeLayout rl_number;

    @Override
    protected int setCustomLayout() {
        return R.layout.activity_login_phone;
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    if (msg.arg1 == 0) {
                        tv_captcha.setText("再次获取");
                        tv_captcha.setClickable(true);
                    } else {
                        tv_captcha.setText(msg.arg1 + " 秒");
                        tv_captcha.setClickable(false);
                    }
                    break;
            }
        }
    };
    @Override
    protected Activity initView() {
        iv_eye = findViewById(R.id.iv_eye);
        et_login_phone = findViewById(R.id.et_login_phone);
        et_login_password = findViewById(R.id.et_login_password);
        et_number_password = findViewById(R.id.et_number_password);
        btn_login_register = findViewById(R.id.btn_login_register);
        rl_number = findViewById(R.id.rl_number);
        tv_captcha = findViewById(R.id.tv_captcha);
        btn_login_login = findViewById(R.id.btn_login_login);
        tv_back = findViewById(R.id.tv_back);
        iv_left_title_bar = findViewById(R.id.iv_left_title_bar);
        et_login_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        tv_back.setText("手机登录");
        btn_login_register.setOnClickListener(this);
        btn_login_login.setOnClickListener(this);
        iv_eye.setOnClickListener(this);
        iv_left_title_bar.setOnClickListener(this);
        tv_captcha.setOnClickListener(this);
        return this;
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_left_title_bar:
                onBackPressed();
                break;
            case R.id.tv_captcha://注册按钮
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 59; i >= 0; i--) {
                            Message msg = handler.obtainMessage();
                            msg.arg1 = i;
                            handler.sendMessage(msg);
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
                if (StringUtil.isEmpty(et_login_phone.getText().toString())){
                    Toast.makeText(getApplicationContext(),"手机号码不能为空",Toast.LENGTH_LONG).show();
                    return;
                }
                if (!checkMobile(et_login_phone.getText().toString())){
                    Toast.makeText(getApplicationContext(),"请输入正确的手机号码",Toast.LENGTH_LONG).show();
                    return;
                }
                captcha();//短信验证
                break;
            case R.id.btn_login_register:
                rl_number.setVisibility(View.VISIBLE);
                if (et_number_password.getText().toString().length()!=5){
                    Toast.makeText(getApplicationContext(),"请输入完整验证码",Toast.LENGTH_LONG).show();
                    return;
                }
                    if (StringUtil.isEmpty(et_login_phone.getText().toString())){
                        Toast.makeText(getApplicationContext(),"手机号码不能为空",Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (StringUtil.isEmpty(et_login_password.getText().toString())){
                        Toast.makeText(getApplicationContext(),"密码不能为空",Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (!checkMobile(et_login_phone.getText().toString())){
                        Toast.makeText(getApplicationContext(),"请输入正确的手机号码",Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (!checkPwd(et_login_password.getText().toString())){
                        Toast.makeText(getApplicationContext(),"密码格式不正确",Toast.LENGTH_LONG).show();
                        return;
                    }
                    register();

                break;
            case R.id.btn_login_login://登录按钮
                if (StringUtil.isEmpty(et_login_phone.getText().toString())){
                    Toast.makeText(getApplicationContext(),"手机号码不能为空",Toast.LENGTH_LONG).show();
                    return;
                }
                if (StringUtil.isEmpty(et_login_password.getText().toString())){
                    Toast.makeText(getApplicationContext(),"密码不能为空",Toast.LENGTH_LONG).show();
                }
                if (!checkMobile(et_login_phone.getText().toString())){
                    Toast.makeText(getApplicationContext(),"请输入正确的手机号码",Toast.LENGTH_LONG).show();
                    return;
                }
                if (!checkPwd(et_login_password.getText().toString())){
                    Toast.makeText(getApplicationContext(),"密码格式不正确",Toast.LENGTH_LONG).show();
                    return;
                }
                login();

                //这里需要用到接口，登录方法
                break;
            case R.id.iv_eye:
                if (flag == true) {
                    // TODO Auto-generated method stub
                    iv_eye.setBackgroundResource(R.mipmap.login_code1);
                    et_login_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    flag = false;
                } else {
                    iv_eye.setBackgroundResource(R.mipmap.login_code2);
                    et_login_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    flag = true;
                }

                break;
        }
    }
    //登录
    private void login(){
        LoadingUtil loadingUtil = new LoadingUtil(this);
        loadingUtil.show();
        OkHttpUtils
                .get()
                .url(HttpUtil.SOCKET_HOST+ HttpUtil.LOGIN)
                .addParams("tel", et_login_phone.getText().toString())
                .addParams("pwd", et_login_password.getText().toString())
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getApplicationContext(),"登录失败",Toast.LENGTH_LONG).show();
                        loadingUtil.dismiss();
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        loadingUtil.dismiss();
                        UsersBean usersBean = new Gson().fromJson(response,UsersBean.class);
                        switch (Integer.parseInt(usersBean.getCode())){
                            case 0:
                                SharedPreferencesUtil.setParam(_context,USER_ID,usersBean.getUser().getId());
                                SharedPreferencesUtil.setObject(_context,USER_BEAN,usersBean.getUser());
                                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                                break;
                            default:
                                Toast.makeText(getApplicationContext(),usersBean.getMsg(),Toast.LENGTH_LONG).show();
                                break;
                        }
                    }
                });
    }
    private void captcha(){
        OkHttpUtils
                .get()
                .url(HttpUtil.SOCKET_HOST+ HttpUtil.CODE)
                .addParams("tel", et_login_phone.getText().toString())
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getApplicationContext(),"获取失败",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                    }
                });
    }
    private void register(){
        LoadingUtil loadingUtil = new LoadingUtil(this);
        loadingUtil.show();
        OkHttpUtils
                .get()
                .url(HttpUtil.SOCKET_HOST+ HttpUtil.REGISTER)
                .addParams("tel", et_login_phone.getText().toString())
                .addParams("pwd", et_login_password.getText().toString())
                .addParams("code",et_number_password.getText().toString())
                .addParams("registerChannel", ChannelUtil.getChannel(_context))
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getApplicationContext(),"注册失败",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        loadingUtil.dismiss();
                        UsersBean usersBean = new Gson().fromJson(response,UsersBean.class);
                        switch (Integer.parseInt(usersBean.getCode())){
                            case 0:
                                SharedPreferencesUtil.setParam(_context,USER_ID,usersBean.getUser().getId());
                                SharedPreferencesUtil.setObject(_context,USER_BEAN,usersBean.getUser());
                                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                                break;
                            default:
                                Toast.makeText(getApplicationContext(),usersBean.getMsg(),Toast.LENGTH_LONG).show();
                                break;
                        }
                    }
                });
    }
    //手机号码格式验证
    public static boolean checkMobile(String mobile) {
        String regex = "(\\+\\d+)?1[123456789]\\d{9}$";
        return Pattern.matches(regex, mobile);
    }
    //密码格式验证
    public static boolean checkPwd(String pwd){
        String regex = "[0-9a-zA-Z_]{6,18}";
        return Pattern.matches(regex,pwd);
    }
}
