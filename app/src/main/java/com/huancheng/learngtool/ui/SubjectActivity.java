package com.huancheng.learngtool.ui;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.huancheng.greendao.ClassifyDao;
import com.huancheng.learngtool.R;
import com.huancheng.learngtool.adapter.SubjectAdapter;
import com.huancheng.learngtool.bean.Classify;
import com.huancheng.learngtool.bean.UriDeserializer;
import com.huancheng.learngtool.util.NullUtil;
import com.huantansheng.easyphotos.models.album.entity.Photo;

import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.ArrayList;
import java.util.List;

public class SubjectActivity extends BasebussActivity {
    @BindView(R.id.subject_recycler)
    RecyclerView subject_recycler;
    @BindView(R.id.subject_select)
    TextView subject_select;
    @BindView(R.id.subject_title)
    TextView subject_title;
    private WhereCondition whereCondition1;
    private WhereCondition whereCondition2;
    private WhereCondition whereCondition3;
    private String sub;

    @Override
    protected int setCustomLayout() {
        return R.layout.activity_subject;
    }

    @Override
    protected Activity initView() {
        sub = getIntent().getStringExtra("sub");
        subject_title.setText(sub);
        whereCondition1 = ClassifyDao.Properties.Kemu.eq(sub);
        whereCondition2 = ClassifyDao.Properties.Kemu.eq(sub);
        whereCondition3 = ClassifyDao.Properties.Kemu.eq(sub);
        init();
        return this;
    }

    private void init() {
        List<Classify> list = MainApplication.getmDaoSession().getClassifyDao().queryBuilder().where(ClassifyDao.Properties.Kemu.eq(sub), whereCondition1, whereCondition2, whereCondition3) .orderDesc(ClassifyDao.Properties.Id).list();
        if (list.size()==0){
            Toast.makeText(this,"暂无错题，快去首页添加吧",Toast.LENGTH_LONG).show();
        }
        subject_recycler.setLayoutManager(new LinearLayoutManager(this));
        SubjectAdapter subjectAdapter = new SubjectAdapter(SubjectActivity.this, list);
        subject_recycler.setAdapter(subjectAdapter);
        subjectAdapter.setOnItemClickListener(new SubjectAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(Uri.class, new UriDeserializer())
                        .create();
                ArrayList<Photo> photoList=gson.fromJson(list.get(position).getUri(),new TypeToken<List<Photo>>(){}.getType());
                Intent intent = new Intent(getApplicationContext(), CommitActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("list",photoList);
                bundle.putString("kemu",list.get(position).getKemu());
                bundle.putString("beizhu",list.get(position).getBeizhu());
                bundle.putString("chengdu",list.get(position).getChengdu());
                bundle.putString("daan",list.get(position).getDaan());
                bundle.putString("laiyuan",list.get(position).getLaiyuan());
                bundle.putString("tixing",list.get(position).getTixing());
                bundle.putString("yuanyin",list.get(position).getYuanyin());
                bundle.putLong("id",list.get(position).getId());
                intent.putExtras(bundle);
                startActivityForResult(intent,200);
            }
        });
    }

    @OnClick({R.id.subject_select,R.id.subject_title,R.id.iv_left_title_bar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left_title_bar:
                finish();
                break;
            case R.id.subject_select:
                startActivityForResult(new Intent(this,SelectActivity.class),100);
                break;
            case R.id.subject_title:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==100){
            String laiyuan = data.getStringExtra("laiyuan");
            String tixing = data.getStringExtra("tixing");
            String zhangwo = data.getStringExtra("zhangwo");
            if (NullUtil.StringIsNull(laiyuan)){
                whereCondition1 = ClassifyDao.Properties.Kemu.eq(sub);
            }else {
                whereCondition1 = ClassifyDao.Properties.Laiyuan.eq(laiyuan);
            }
            if (NullUtil.StringIsNull(tixing)){
                whereCondition2 = ClassifyDao.Properties.Kemu.eq(sub);
            }else {
                whereCondition2 = ClassifyDao.Properties.Tixing.eq(tixing);
            }
            if (NullUtil.StringIsNull(zhangwo)){
                whereCondition3 = ClassifyDao.Properties.Kemu.eq(sub);
            }else {
                whereCondition3 = ClassifyDao.Properties.Chengdu.eq(zhangwo);
            }
            init();
        }else if (resultCode==200){
            init();
        }
    }
}
