package com.huancheng.learngtool.util;

/**
 * Created by admin on 2018/12/5.
 */

public class HttpUtil {
//public static final String SOCKET_HOST = "http://192.168.1.5:8083";//四启
//public static final String SOCKET_HOST = "http://www.hzhuanqu.com:8086";
public static final String SOCKET_HOST = "http://192.168.1.8:8080";//小袁
//测试7
    public static final String HOST = "http://www.hzhuanqu.com:8086";
    //手机登录
    public static final String LOGIN = "/novel/user/telLogin";
    //自动登录从新获取userbean最新数据
    public static final String UPDATE_USERBEAN= "/novel/user/boxuserlist";
    //注册
    public static final String REGISTER = "/novel/user/telRegister";
    //短信验证
    public static final String CODE = "/novel/sms/getMessage";
    //忘记密码
    public static final String FORGET_PASSWORD = "/novel/user/forgetPwd";
    //邀请码
    public static final String INVITATION_CODE = "/novel/user/checkInviteCode";
}
