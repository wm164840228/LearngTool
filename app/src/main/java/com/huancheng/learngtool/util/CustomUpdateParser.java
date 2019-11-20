package com.huancheng.learngtool.util;


import com.google.gson.Gson;
import com.huancheng.learngtool.bean.UpdateBean;
import com.xuexiang.xupdate.entity.UpdateEntity;
import com.xuexiang.xupdate.proxy.IUpdateParser;

public class CustomUpdateParser implements IUpdateParser {
    CheckListener listener;

    public CustomUpdateParser(CheckListener listener){
        this.listener=listener;
    }
    @Override
    public UpdateEntity parseJson(String json) {
        boolean updata = false;
        UpdateBean updateBean;
        long size;
        if (NullUtil.StringIsNull(json)||json.equals("0")){
            listener.check();
            return null;
        }else {
            updateBean = new Gson().fromJson(json, UpdateBean.class);
            updata=true;
        }
        if (NullUtil.StringIsNull(updateBean.getSize())){
            size=0;
        }else {
            String substring = updateBean.getSize().substring(0, 2);
            size= Integer.parseInt(substring)*1024;
        }
            return new UpdateEntity().setHasUpdate(updata)
                    .setIsIgnorable(false)
                    .setVersionName(updateBean.getVersionid())
                    .setUpdateContent("重要更新,请更新后使用。\n"+updateBean.getUpdatetext())
                    .setDownloadUrl(updateBean.getEditionurl())
                    .setIsAutoInstall(true)
                    .setSize(size);

    }
    interface CheckListener {
        void check();
    }

}