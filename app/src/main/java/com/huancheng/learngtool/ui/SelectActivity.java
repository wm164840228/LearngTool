package com.huancheng.learngtool.ui;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.OnClick;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.huancheng.learngtool.R;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.Iterator;

public class SelectActivity extends BasebussActivity {
    @BindView(R.id.laiyuan_flowlayout)
    TagFlowLayout laiyuan_flowlayout;
    @BindView(R.id.tixing_flowlayout)
    TagFlowLayout tixing_flowlayout;
    @BindView(R.id.zhangwo_flowlayout)
    TagFlowLayout zhangwo_flowlayout;
    @BindView(R.id.commit)
    TextView commit;
    private String[] laiyuan = new String[]{"默认", "作业", "单元测试", "考试", "课本", "老师"};
    private String[] tixing = new String[]{"选择题", "填空题", "问答题", "判断题","其他"};
    private String[] zhangwo = new String[]{"不懂", "略懂", "基本懂", "完全懂"};
    @Override
    protected int setCustomLayout() {
        return R.layout.activity_select;
    }

    @Override
    protected Activity initView() {

        TagAdapter<Object> laiyuanAdapter = new TagAdapter<Object>(laiyuan) {
            @Override
            public View getView(FlowLayout flowLayout, int i, Object s) {
                TextView tv = (TextView) LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_view_textview, laiyuan_flowlayout, false);
                tv.setText(String.valueOf(s));
                return tv;
            }
        };
        laiyuan_flowlayout.setAdapter(laiyuanAdapter);


        TagAdapter<Object> tixingAdapter = new TagAdapter<Object>(tixing) {
            @Override
            public View getView(FlowLayout flowLayout, int i, Object s) {
                TextView tv = (TextView) LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_view_textview, tixing_flowlayout, false);
                tv.setText(String.valueOf(s));
                return tv;
            }
        };
        tixing_flowlayout.setAdapter(tixingAdapter);

        TagAdapter<Object> zhangwoAdapter = new TagAdapter<Object>(zhangwo) {
            @Override
            public View getView(FlowLayout flowLayout, int i, Object s) {
                TextView tv = (TextView) LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_view_textview, zhangwo_flowlayout, false);
                tv.setText(String.valueOf(s));
                return tv;
            }
        };
        zhangwo_flowlayout.setAdapter(zhangwoAdapter);
        return this;
    }
    @OnClick({R.id.commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.commit:
                Iterator<Integer> iteratorlaiyuan = laiyuan_flowlayout.getSelectedList().iterator();
                Iterator<Integer> iteratortixing = tixing_flowlayout.getSelectedList().iterator();
                Iterator<Integer> iteratorzhangwo = zhangwo_flowlayout.getSelectedList().iterator();
                Intent intent=new Intent();
                if (iteratorlaiyuan.hasNext())
                    intent.putExtra("laiyuan",laiyuan[iteratorlaiyuan.next()]);
                if (iteratortixing.hasNext())
                    intent.putExtra("tixing",tixing[iteratortixing.next()]);
                if (iteratorzhangwo.hasNext())
                    intent.putExtra("zhangwo",zhangwo[iteratorzhangwo.next()]);
                setResult(100,intent);
                finish();
                break;
        }
    }
}
