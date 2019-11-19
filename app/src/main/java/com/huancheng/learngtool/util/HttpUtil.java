package com.huancheng.learngtool.util;

/**
 * Created by admin on 2018/12/5.
 */

public class HttpUtil {
//public static final String SOCKET_HOST = "http://192.168.1.5:8083";//四启
//public static final String SOCKET_HOST = "http://www.hzhuanqu.com:8086";
public static final String SOCKET_HOST = "http://192.168.1.8:8080";//小袁
    //手机登录
    public static final String LOGIN = "/errorbook/user/loginTel";
    //手机登录
    public static final String QQLOGIN = "/errorbook/user/qqlogin";
    //手机登录
    public static final String WELOGIN = "/errorbook/user/wechatlogin";
    //自动登录从新获取userbean最新数据
    public static final String UPDATE_USERBEAN= "/novel/user/boxuserlist";
    //注册
    public static final String REGISTER = "/errorbook/user/registerTel";
    //短信验证
    public static final String CODE = "/errorbook/user/sendMessage";
}
