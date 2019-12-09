package com.huancheng.learngtool.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
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
import com.huancheng.learngtool.util.SharedPreferencesUtil;
import com.huantansheng.easyphotos.models.album.entity.Photo;

import org.greenrobot.greendao.query.WhereCondition;

import java.util.ArrayList;
import java.util.List;

public class SubjectotherActivity extends BasebussActivity {
    @BindView(R.id.subject_recycler)
    RecyclerView subject_recycler;
    @BindView(R.id.subject_title)
    TextView subject_title;
    @BindView(R.id.subject_spinner)
    Spinner subject_spinner;

    private WhereCondition whereCondition;
    private String tixing;
    private String chengdu;
    private String[] ctype;
    private List<Classify> list;

    @Override
    protected int setCustomLayout() {
        return R.layout.activity_subjectother;
    }

    @Override
    protected Activity initView() {
        tixing = getIntent().getStringExtra("tixing");
        chengdu = getIntent().getStringExtra("chengdu");
        if (tixing!=null){
            subject_title.setText(tixing);
            whereCondition = ClassifyDao.Properties.Tixing.eq(tixing);
        }else if (chengdu!=null){
            subject_title.setText(chengdu);
            whereCondition = ClassifyDao.Properties.Chengdu.eq(chengdu);
        }
        ctype = new String[]{"全学科","语文", "数学", "英语", "历史", "地理", "政治", "生物", "物理", "化学","科学", "其他"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, ctype);  //创建一个数组适配器
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);     //设置下拉列表框的下拉选项样式
        subject_spinner.setAdapter(adapter);
        subject_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                init();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        init();
        return this;
    }
    private void init() {
        if (subject_spinner.getSelectedItem().toString().equals("全学科")){
            list = MainApplication.getmDaoSession().getClassifyDao().queryBuilder().where(whereCondition).orderDesc(ClassifyDao.Properties.Id).list();
        }else {
            list = MainApplication.getmDaoSession().getClassifyDao().queryBuilder().where(whereCondition,ClassifyDao.Properties.Kemu.eq(subject_spinner.getSelectedItem().toString())).orderDesc(ClassifyDao.Properties.Id).list();
        }
        if (list.size()==0){
            Toast.makeText(this,"暂无错题，快去首页添加吧",Toast.LENGTH_LONG).show();
        }
        subject_recycler.setLayoutManager(new LinearLayoutManager(this));
        SubjectAdapter subjectAdapter = new SubjectAdapter(SubjectotherActivity.this, list);
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


    @OnClick({R.id.subject_title,R.id.iv_left_title_bar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left_title_bar:
                finish();
                break;
            case R.id.subject_title:
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==100){
            init();
        }else if (resultCode==200){
            init();
        }
    }
}
