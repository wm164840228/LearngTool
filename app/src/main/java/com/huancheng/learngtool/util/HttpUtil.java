package com.huancheng.learngtool.util;

/**
 * Created by admin on 2018/12/5.
 */

public class HttpUtil {
public static final String SOCKET_HOST = "http://www.hzhuanqu.com:8086";
//public static final String SOCKET_HOST = "http://192.168.1.8:8080";//小袁
    //手机登录
    public static final String LOGIN = "/errorbook/user/loginTel";
    //手机登录
    public static final String QQLOGIN = "/errorbook/user/qqlogin";
    //手机登录
    public static final String WELOGIN = "/errorbook/user/wechatlogin";
    //注册
    public static final String REGISTER = "/errorbook/user/registerTel";
    //短信验证
    public static final String CODE = "/errorbook/user/sendMessage";
    //检查软件更新
    public static final String CheckUpdate="/errorbook/version/check";
}
