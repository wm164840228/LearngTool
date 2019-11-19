package com.huancheng.learngtool.ui;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.huancheng.learngtool.R;

public class SuggestActivity extends BasebussActivity {
    @BindView(R.id.suggest_button)
    Button suggest_button;
    @BindView(R.id.suggest_text)
    Button suggest_text;

    @Override
    protected int setCustomLayout() {
        return R.layout.activity_suggest;
    }

    @Override
    protected Activity initView() {
        suggest_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"你的建议已提交，感谢您的支持！",Toast.LENGTH_LONG).show();
            }
        });
        return this;
    }
}
