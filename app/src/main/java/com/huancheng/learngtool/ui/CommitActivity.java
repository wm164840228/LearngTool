package com.huancheng.learngtool.ui;

import butterknife.BindView;
import butterknife.OnClick;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.huancheng.greendao.ClassifyDao;
import com.huancheng.learngtool.R;
import com.huancheng.learngtool.bean.Classify;
import com.huancheng.learngtool.bean.UriDeserializer;
import com.huancheng.learngtool.bean.UriSerializer;
import com.huancheng.learngtool.util.GlideEngine;
import com.huancheng.learngtool.util.NullUtil;
import com.huancheng.learngtool.util.TimeUtils;
import com.huantansheng.easyphotos.models.album.entity.Photo;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class CommitActivity extends BasebussActivity {
    @BindView(R.id.commit_img1)
    ImageView commit_img1;
    @BindView(R.id.commit_img2)
    ImageView commit_img2;
    @BindView(R.id.commit_img3)
    ImageView commit_img3;
    @BindView(R.id.commit_img4)
    ImageView commit_img4;
    @BindView(R.id.kemu_flowlayout)
    TagFlowLayout kemu_flowlayout;
    @BindView(R.id.laiyuan_flowlayout)
    TagFlowLayout laiyuan_flowlayout;
    @BindView(R.id.tixing_flowlayout)
    TagFlowLayout tixing_flowlayout;
    @BindView(R.id.yuanyin_flowlayout)
    TagFlowLayout yuanyin_flowlayout;
    @BindView(R.id.zhangwo_flowlayout)
    TagFlowLayout zhangwo_flowlayout;
    @BindView(R.id.commit_text)
    EditText commit_text;
    @BindView(R.id.commit)
    TextView commit;
    @BindView(R.id.delete)
    TextView delete;
    private String[] kemu = new String[]{"语文", "数学", "英语", "历史", "地理", "政治", "生物", "物理", "化学","科学", "其他"};
    private String[] laiyuan = new String[]{"默认", "作业", "单元测试", "考试", "课本", "老师"};
    private String[] tixing = new String[]{"选择题", "填空题", "问答题", "判断题"};
    private String[] yuanyin = new String[]{"概念", "审题", "粗心"};
    private String[] zhangwo = new String[]{"不懂", "略懂", "基本懂", "完全懂"};
    private ArrayList<Photo> list;
    private Bundle getBundle;
    private ArrayList<String> stringlist;
    private long id;

    @Override
    protected int setCustomLayout() {
        return R.layout.activity_commit;
    }

    @Override
    protected Activity initView() {
        getBundle = this.getIntent().getExtras();
        list = getBundle.getParcelableArrayList("list");
        stringlist = new ArrayList<>();
        id = getBundle.getLong("id");
        if (id ==0){
            delete.setVisibility(View.GONE);
        }
        for (int i = 0; i< list.size(); i++){
            if (i==0){
                GlideEngine.getInstance().loadPhoto(this, list.get(i).uri,commit_img1);
            }else if (i==1){
                GlideEngine.getInstance().loadPhoto(this, list.get(i).uri,commit_img2);
            }else if (i==2){
                GlideEngine.getInstance().loadPhoto(this, list.get(i).uri,commit_img3);
            }else if (i==3){
                GlideEngine.getInstance().loadPhoto(this, list.get(i).uri,commit_img4);
            }
            stringlist.add("file:/"+list.get(i).path);
        }
        TagAdapter<Object> kemuAdapter = new TagAdapter<Object>(CommitActivity.this.kemu) {
            @Override
            public View getView(FlowLayout flowLayout, int i, Object s) {
                TextView tv = (TextView) LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_view_textview, kemu_flowlayout, false);
                tv.setText(String.valueOf(s));
                return tv;
            }
        };
        if (getIndex(kemu, getBundle.getString("kemu"))!=-1){
            kemuAdapter.setSelectedList(getIndex(kemu, getBundle.getString("kemu")));
        }
        kemu_flowlayout.setAdapter(kemuAdapter);

        TagAdapter<Object> laiyuanAdapter = new TagAdapter<Object>(laiyuan) {
            @Override
            public View getView(FlowLayout flowLayout, int i, Object s) {
                TextView tv = (TextView) LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_view_textview, laiyuan_flowlayout, false);
                tv.setText(String.valueOf(s));
                return tv;
            }
        };
        if (getIndex(laiyuan, getBundle.getString("laiyuan"))!=-1){
            laiyuanAdapter.setSelectedList(getIndex(laiyuan, getBundle.getString("laiyuan")));
        }
        laiyuan_flowlayout.setAdapter(laiyuanAdapter);

        TagAdapter<Object> yuanyinAdapter = new TagAdapter<Object>(yuanyin) {
            @Override
            public View getView(FlowLayout flowLayout, int i, Object s) {
                TextView tv = (TextView) LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_view_textview, yuanyin_flowlayout, false);
                tv.setText(String.valueOf(s));
                return tv;
            }
        };
        if (getIndex(yuanyin, getBundle.getString("yuanyin"))!=-1){
            yuanyinAdapter.setSelectedList(getIndex(yuanyin, getBundle.getString("yuanyin")));
        }
        yuanyin_flowlayout.setAdapter(yuanyinAdapter);

        TagAdapter<Object> tixingAdapter = new TagAdapter<Object>(tixing) {
            @Override
            public View getView(FlowLayout flowLayout, int i, Object s) {
                TextView tv = (TextView) LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_view_textview, tixing_flowlayout, false);
                tv.setText(String.valueOf(s));
                return tv;
            }
        };
        if (getIndex(tixing, getBundle.getString("tixing"))!=-1){
            tixingAdapter.setSelectedList(getIndex(tixing, getBundle.getString("tixing")));
        }
        tixing_flowlayout.setAdapter(tixingAdapter);

        TagAdapter<Object> zhangwoAdapter = new TagAdapter<Object>(zhangwo) {
            @Override
            public View getView(FlowLayout flowLayout, int i, Object s) {
                TextView tv = (TextView) LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_view_textview, zhangwo_flowlayout, false);
                tv.setText(String.valueOf(s));
                return tv;
            }
        };
        if (getIndex(zhangwo, getBundle.getString("chengdu"))!=-1){
            zhangwoAdapter.setSelectedList(getIndex(zhangwo, getBundle.getString("chengdu")));
        }
        zhangwo_flowlayout.setAdapter(zhangwoAdapter);
        commit_text.setText(getBundle.getString("yuanyin"));
        return this;
    }
        @OnClick({R.id.commit,R.id.commit_img,R.id.delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.commit:
                commit();
                break;
            case R.id.commit_img:
                Intent intent = new Intent(getApplicationContext(), PhotoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("list",stringlist);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.delete:
                MainApplication.getmDaoSession().getClassifyDao().queryBuilder().where(ClassifyDao.Properties.Id.eq(id)).buildDelete().executeDeleteWithoutDetachingEntities();
                setResult(200);
                finish();
                break;
        }
    }

    private void commit() {
        Iterator<Integer> iteratorkemu = kemu_flowlayout.getSelectedList().iterator();
        Iterator<Integer> iteratorlaiyuan = laiyuan_flowlayout.getSelectedList().iterator();
        Iterator<Integer> iteratortixing = tixing_flowlayout.getSelectedList().iterator();
        Iterator<Integer> iteratoryuanyin = yuanyin_flowlayout.getSelectedList().iterator();
        Iterator<Integer> iteratorzhangwo = zhangwo_flowlayout.getSelectedList().iterator();
        boolean hasNext = iteratorkemu.hasNext();
        if (!NullUtil.listIsNull(list)&&hasNext){
            Classify classifyBean = new Classify();
            classifyBean.setKemu(kemu[iteratorkemu.next()]);
            if (iteratorlaiyuan.hasNext())
                classifyBean.setLaiyuan(laiyuan[iteratorlaiyuan.next()]);
            if (iteratortixing.hasNext())
                classifyBean.setTixing(tixing[iteratortixing.next()]);
            if (iteratoryuanyin.hasNext())
                classifyBean.setYuanyin(yuanyin[iteratoryuanyin.next()]);
            if (iteratorzhangwo.hasNext())
                classifyBean.setChengdu(zhangwo[iteratorzhangwo.next()]);
            classifyBean.setTime(TimeUtils.currentTimeMillis());
            classifyBean.setUri(new GsonBuilder()
                    .registerTypeAdapter(Uri.class, new UriSerializer())
                    .create().toJson(list));
            classifyBean.setBeizhu(commit_text.getText().toString());
            long id = getBundle.getLong("id");
            if (getBundle.getLong("id")!=0){
                classifyBean.setId(id);
                MainApplication.getmDaoSession().getClassifyDao().insertOrReplace(classifyBean);
            }else {
                MainApplication.getmDaoSession().getClassifyDao().insert(classifyBean);
            }
            finish();
        }else {
            Toast.makeText(getApplicationContext(),"请选择科目后才可以提交",Toast.LENGTH_LONG).show();
        }
    }
    public static int getIndex(String[] arr, String value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(value)) {
                return i;
            }
        }
        return -1;//如果未找到返回-1
    }
}