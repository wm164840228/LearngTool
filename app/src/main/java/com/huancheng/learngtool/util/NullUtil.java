package com.huancheng.learngtool.util;

import java.util.List;

/**
 * Created by Administrator on 2017/10/20 0020.
 */
public class NullUtil {

    public static boolean StringIsNull(String str){
        if(str==null||str==""||str.equals("")||str.equals("null")){
            return true;
        }else{
            return false;
        }
    }

    public static boolean ObiectIsNull(Object o){
        if(o==null||o==""||o.equals("")||o.equals("null")){
            return true;
        }else{
            return false;
        }
    }

    public static boolean listIsNull(List list){
        if(list==null||list.size()<=0){
            return true;
        }else{
            return false;
        }
    }
}
