package com.huancheng.learngtool.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;
@Entity
public class Classify {
    @Id(autoincrement = true)
    Long id;
    String uri;//uri
    String kemu; //科目
    String laiyuan;//来源
    String chengdu; //程度
    String tixing;//题型
    String yuanyin;//原因
    String beizhu;//备注
    String daan;//答案
    String nianji;//年级
    long time;//时间
    @Generated(hash = 1807565117)
    public Classify(Long id, String uri, String kemu, String laiyuan,
            String chengdu, String tixing, String yuanyin, String beizhu,
            String daan, String nianji, long time) {
        this.id = id;
        this.uri = uri;
        this.kemu = kemu;
        this.laiyuan = laiyuan;
        this.chengdu = chengdu;
        this.tixing = tixing;
        this.yuanyin = yuanyin;
        this.beizhu = beizhu;
        this.daan = daan;
        this.nianji = nianji;
        this.time = time;
    }
    @Generated(hash = 767880343)
    public Classify() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUri() {
        return this.uri;
    }
    public void setUri(String uri) {
        this.uri = uri;
    }
    public String getKemu() {
        return this.kemu;
    }
    public void setKemu(String kemu) {
        this.kemu = kemu;
    }
    public String getLaiyuan() {
        return this.laiyuan;
    }
    public void setLaiyuan(String laiyuan) {
        this.laiyuan = laiyuan;
    }
    public String getChengdu() {
        return this.chengdu;
    }
    public void setChengdu(String chengdu) {
        this.chengdu = chengdu;
    }
    public String getTixing() {
        return this.tixing;
    }
    public void setTixing(String tixing) {
        this.tixing = tixing;
    }
    public String getYuanyin() {
        return this.yuanyin;
    }
    public void setYuanyin(String yuanyin) {
        this.yuanyin = yuanyin;
    }
    public String getBeizhu() {
        return this.beizhu;
    }
    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }
    public String getDaan() {
        return this.daan;
    }
    public void setDaan(String daan) {
        this.daan = daan;
    }
    public long getTime() {
        return this.time;
    }
    public void setTime(long time) {
        this.time = time;
    }
    public String getNianji() {
        return this.nianji;
    }
    public void setNianji(String nianji) {
        this.nianji = nianji;
    }
}
