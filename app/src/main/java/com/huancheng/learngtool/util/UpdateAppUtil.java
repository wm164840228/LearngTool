package com.huancheng.learngtool.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.Toast;


import com.huancheng.learngtool.R;
import com.xuexiang.xupdate.XUpdate;
import com.xuexiang.xupdate.entity.UpdateError;
import com.xuexiang.xupdate.listener.OnUpdateFailureListener;

public class UpdateAppUtil {
    public static void initUptate(Application context){
        XUpdate.get()
                .isWifiOnly(false)     //默认设置只在wifi下检查版本更新
                .isGet(true)          //默认设置使用get请求检查版本
                .isAutoMode(false)    //默认设置非自动模式，可根据具体使用配置
                .setOnUpdateFailureListener(new OnUpdateFailureListener() { //设置版本更新出错的监听
                    @Override
                    public void onFailure(UpdateError error) {
                    }
                })
                .setIUpdateHttpService(new OKHttpUpdateHttpService()) //这个必须设置！实现网络请求功能。
                .init(context);   //这个必须初始化
    }
    public static void mainUpdate(Activity context){
        XUpdate.newBuild(context)
                .updateUrl(HttpUtil.SOCKET_HOST+HttpUtil.CheckUpdate)
                .themeColor(context.getResources().getColor(R.color.updateblue))
                .topResId(R.mipmap.updatebg)
                .param("versionID",getVersionName(context))
                .param("registerChannel",ChannelUtil.getChannel(context))
                .updateParser(new CustomUpdateParser(new CustomUpdateParser.CheckListener() {
                    @Override
                    public void check() {

                    }
                })) //设置自定义的版本更新解析器
                .update();
    }
    public static void checkUpdate(Activity activity){
        XUpdate.newBuild(activity)
                .updateUrl(HttpUtil.SOCKET_HOST+HttpUtil.CheckUpdate)
                .themeColor(activity.getResources().getColor(R.color.updateblue))
                .topResId(R.mipmap.updatebg)
                .param("versionID",getVersionName(activity))
                .param("registerChannel",ChannelUtil.getChannel(activity))
                .updateParser(new CustomUpdateParser(new CustomUpdateParser.CheckListener() {
                    @Override
                    public void check() {
                        Toast.makeText(activity.getApplicationContext(),"已是最新版本",Toast.LENGTH_LONG).show();
                    }
                })) //设置自定义的版本更新解析器
                .update();
    }
    /**
     * 获取当前程序的版本号
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        //获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
        return packInfo.versionName;
    }

}
