package com.huancheng.learngtool.ui;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;


import com.huancheng.greendao.DaoMaster;
import com.huancheng.greendao.DaoSession;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.socialize.PlatformConfig;

import java.util.ArrayList;
import java.util.Map;


public class MainApplication extends Application {

    private static ArrayList<Activity> list = new ArrayList<Activity>();
    private static Context sInstance;
    private static Context context;
    private static DaoSession mDaoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        //greendao
        initGreenDao();
        PlatformConfig.setWeixin("wx18a192099a9f0627","aab4a984207b366068599b15bc1bb2fd");
        PlatformConfig.setQQZone("101828804","dc57b43cdb94741728fcacb2c80bedda");
        sInstance = this;
        //初始化友盟
        UMConfigure.init(this,UMConfigure.DEVICE_TYPE_PHONE,"b3c00096102634021da772d81f2483cb");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0
        UMConfigure.setLogEnabled(true);
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);
        //获取消息推送代理示例
        PushAgent mPushAgent = PushAgent.getInstance(this);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回deviceToken deviceToken是推送消息的唯一标志
                Log.i("注册成功","注册成功：deviceToken：-------->  " + deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                Log.e("注册成功","注册失败：-------->  " + "s:" + s + ",s1:" + s1);
            }
        });
        String registrationId = mPushAgent.getRegistrationId();
        String registrationId1 = mPushAgent.getRegistrationId();
        //OPPO通道，参数1为app key，参数2为app secret
       // OppoRegister.register(this, "94025bc1c18640a4b6a1fbf8212f5d50", "2b41d32ba46a49beafa601b8832ba4d4");
    }
    /**
     * 添加Activity到集合中
     */
    public void addActivity(Activity activity) {
        list.add(activity);
    }

    public Context getContext() {
        return context;
    }
    public static Context getReaderContext(){
        return sInstance;
    }
    // 用于存放倒计时时间
    public static Map<String, Long> map;
    /**
     * 从集合中移除Activity
     */
    public void removeActivity(Activity activity) {
        list.remove(activity);
    }

//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(base);
//        MultiDex.install(base);
//    }

    /**
     * 关闭所有的Activity
     */
    public static void closeActivity() {
        for (Activity activity : list) {
            if (null != activity) {
                activity.finish();
            }
        }
    }
    private static MainApplication mInstance;
    /**
     * 获取context
     * @return
     */
    public static Context getInstance() {
        if (mInstance == null) {
            mInstance = new MainApplication();
        }
        return mInstance;
    }
    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "app.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();
    }

    public static DaoSession getmDaoSession() {
        return mDaoSession;
    }
}
