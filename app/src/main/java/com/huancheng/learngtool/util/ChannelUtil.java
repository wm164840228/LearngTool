package com.huancheng.learngtool.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

public class ChannelUtil {
    /**
     * 获取当前apk包的渠道值
     * -- 无渠道-0  华为-1 联想-2 魅族-3 小米-4 360-5 腾讯（应用宝）-6 百度-7 豌豆荚-8 OPPO-9 VIVO-10 金立-11
     * //    瑞狮-12  券妈妈-13 东方头条web-14，东方头条apk-15，米赚web-16，米赚apk-17，赚客-18，朗淘1-19，朗淘2-20 星空 21 齐乐网络22 小啄赚钱23 乐瓜1-24
     * 乐瓜2-25， 乐瓜3-26
     * @return
     */
    public static String getChannel(Context context){
        ApplicationInfo applicationInfo=null;
        try {
            applicationInfo=context.getApplicationContext()
                    .getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            if(applicationInfo!=null&&applicationInfo.metaData!=null){
                String channel=applicationInfo.metaData.getString("UMENG_CHANNEL");
                if (channel.equals("ceshi")){
                    return "0";
                }else if (channel.equals("none")){
                    return "0";
                }else if (channel.equals("huawei")){
                    return "1";
                }else if (channel.equals("lianxiang")){
                    return "2";

                }else if (channel.equals("meizu")){
                    return "3";
                }else if (channel.equals("xiaomi")){
                    return "4";
                }else if (channel.equals("360")){
                    return "5";
                }else if (channel.equals("yyb")){
                    return "6";
                }else if (channel.equals("baidu")){
                    return "7";
                }else if (channel.equals("wandoujia")){
                    return "8";
                }else if (channel.equals("oppo")){
                    return "9";
                }else if (channel.equals("vivo")){
                    return "10";
                }else{
                    return "0";
                }
            }else {
                return "0";
            }
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }
        return "0";
    }
}
