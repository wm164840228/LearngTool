package com.huancheng.learngtool.ui;

import androidx.annotation.Nullable;
import androidx.print.PrintHelper;

import butterknife.BindView;
import butterknife.OnClick;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.huancheng.greendao.ClassifyDao;
import com.huancheng.learngtool.R;
import com.huancheng.learngtool.bean.Classify;
import com.huancheng.learngtool.bean.UriSerializer;
import com.huancheng.learngtool.util.GlideEngine;
import com.huancheng.learngtool.util.NullUtil;
import com.huancheng.learngtool.util.TimeUtils;
import com.huantansheng.easyphotos.models.album.entity.Photo;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Iterator;


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
    @BindView(R.id.nianji_flowlayout)
    TagFlowLayout nianji_flowlayout;
    @BindView(R.id.commit_text)
    EditText commit_text;
    @BindView(R.id.commit)
    TextView commit;
    @BindView(R.id.delete)
    TextView delete;
    @BindView(R.id.export)
    TextView export;
    private String[] kemu = new String[]{"语文", "数学", "英语", "历史", "地理", "政治", "生物", "物理", "化学","科学", "其他"};
    private String[] laiyuan = new String[]{"默认", "作业", "单元测试", "考试", "课本", "老师"};
    private String[] tixing = new String[]{"选择题", "填空题", "问答题", "判断题","其他"};
    private String[] yuanyin = new String[]{"概念", "审题", "粗心"};
    private String[] zhangwo = new String[]{"不懂", "略懂", "基本懂", "完全懂"};
    private String[] nianji = new String[]{"低年级","五年级", "六年级", "七年级", "八年级","九年级","高一", "高二", "高三"};
    private ArrayList<Photo> list;
    private Bundle getBundle;
    private long id;

    @Override
    protected int setCustomLayout() {
        return R.layout.activity_commit;
    }

    @Override
    protected Activity initView() {
        getBundle = this.getIntent().getExtras();
        list = getBundle.getParcelableArrayList("list");
        id = getBundle.getLong("id");
        if (id ==0){
            delete.setVisibility(View.GONE);
            export.setVisibility(View.GONE);
        }
        initTop();
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

        TagAdapter<Object> nianjiAdapter = new TagAdapter<Object>(nianji) {
            @Override
            public View getView(FlowLayout flowLayout, int i, Object s) {
                TextView tv = (TextView) LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_view_textview, nianji_flowlayout, false);
                tv.setText(String.valueOf(s));
                return tv;
            }
        };
        if (getIndex(nianji, getBundle.getString("nianji"))!=-1){
            nianjiAdapter.setSelectedList(getIndex(nianji, getBundle.getString("nianji")));
        }
        nianji_flowlayout.setAdapter(nianjiAdapter);
        commit_text.setText(getBundle.getString("beizhu"));
        return this;
    }

    private void initTop() {
        if (list.size()>=1){
            GlideEngine.getInstance().loadPhoto(this, list.get(0).uri,commit_img1);
        }else {
            commit_img1.setVisibility(View.INVISIBLE);
        }
        if (list.size()>=2){
            GlideEngine.getInstance().loadPhoto(this, list.get(1).uri,commit_img2);
        }else {
            commit_img2.setVisibility(View.INVISIBLE);
        }
        if (list.size()>=3){
            GlideEngine.getInstance().loadPhoto(this, list.get(2).uri,commit_img3);
        }else {
            commit_img3.setVisibility(View.INVISIBLE);
        }
        if (list.size()>=4){
            GlideEngine.getInstance().loadPhoto(this, list.get(3).uri,commit_img4);
        }else {
            commit_img4.setVisibility(View.INVISIBLE);
        }
    }

    @OnClick({R.id.commit,R.id.commit_img1,R.id.commit_img2,R.id.commit_img3,R.id.commit_img4,R.id.delete,R.id.export})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.commit:
                commit();
                break;
            case R.id.commit_img1:
                Intent intent1 = new Intent(getApplicationContext(), PhotoActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putParcelableArrayList("list",list);
                bundle1.putString("number","0");
                intent1.putExtras(bundle1);
                startActivityForResult(intent1,100);
                break;
            case R.id.commit_img2:
                Intent intent2 = new Intent(getApplicationContext(), PhotoActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putString("number","1");
                bundle2.putParcelableArrayList("list",list);
                intent2.putExtras(bundle2);
                startActivityForResult(intent2,100);
                break;
            case R.id.commit_img3:
                Intent intent3 = new Intent(getApplicationContext(), PhotoActivity.class);
                Bundle bundle3 = new Bundle();
                bundle3.putString("number","2");
                bundle3.putParcelableArrayList("list",list);
                intent3.putExtras(bundle3);
                startActivityForResult(intent3,100);
                break;
            case R.id.commit_img4:
                Intent intent4 = new Intent(getApplicationContext(), PhotoActivity.class);
                Bundle bundle4 = new Bundle();
                bundle4.putParcelableArrayList("list",list);
                bundle4.putString("number","3");
                intent4.putExtras(bundle4);
                startActivityForResult(intent4,100);
                break;
            case R.id.delete:
                MainApplication.getmDaoSession().getClassifyDao().queryBuilder().where(ClassifyDao.Properties.Id.eq(id)).buildDelete().executeDeleteWithoutDetachingEntities();
                setResult(200);
                finish();
                break;
            case R.id.export:
                exPorts();
                break;
        }
    }

    private void exPorts() {
        PrintHelper photoPrinter = new PrintHelper(this);
        photoPrinter.setScaleMode(PrintHelper.SCALE_MODE_FIT);
        Bitmap bitmap = BitmapFactory.decodeFile(list.get(0).path);
        photoPrinter.printBitmap("droids.jpg - test print", bitmap);
    }

    private void commit() {
        Iterator<Integer> iteratorkemu = kemu_flowlayout.getSelectedList().iterator();
        Iterator<Integer> iteratorlaiyuan = laiyuan_flowlayout.getSelectedList().iterator();
        Iterator<Integer> iteratortixing = tixing_flowlayout.getSelectedList().iterator();
        Iterator<Integer> iteratoryuanyin = yuanyin_flowlayout.getSelectedList().iterator();
        Iterator<Integer> iteratorzhangwo = zhangwo_flowlayout.getSelectedList().iterator();
        Iterator<Integer> iteratornianji = nianji_flowlayout.getSelectedList().iterator();
        boolean hasNext = iteratorkemu.hasNext();
        boolean hasNext1 = iteratornianji.hasNext();
        if (!NullUtil.listIsNull(list)&&hasNext&&hasNext1){
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
            if (iteratornianji.hasNext())
                classifyBean.setNianji(nianji[iteratornianji.next()]);
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
        }else if (!hasNext){
            Toast.makeText(getApplicationContext(),"请选择科目后才可以提交",Toast.LENGTH_LONG).show();
        }else if (!hasNext1){
            Toast.makeText(getApplicationContext(),"请选择年级后才可以提交",Toast.LENGTH_LONG).show();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100){
            if (resultCode==101){
                Bundle bundle = data.getExtras();
                list.clear();
                list.addAll(bundle.getParcelableArrayList("list"));
                initTop();
                setResult(200);
            }
        }
    }


}
