package com.huancheng.learngtool.ui;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.OnClick;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.huancheng.learngtool.R;

public class VipActivity extends BasebussActivity {
    @BindView(R.id.vip_number)
    TextView vip_number;
    @BindView(R.id.vip_number1)
    TextView vip_number1;
    @BindView(R.id.vip_number2)
    TextView vip_number2;
    private String id="1";


    @Override
    protected int setCustomLayout() {
        return R.layout.activity_vip;
    }

    @Override
    protected Activity initView() {
        return this;
    }
    @OnClick({R.id.iv_left_title_bar,R.id.vip_sub,R.id.vip_yueka,R.id.vip_nianka})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left_title_bar:
                finish();
                break;
            case R.id.vip_sub:
                pay();
                break;
            case R.id.vip_nianka:
                id="2";
                vip_number1.setText("支付金额：150元");
                vip_number2.setText("（原价360元）");
                break;
            case R.id.vip_yueka:
                id="1";
                vip_number1.setText("支付金额：15元");
                vip_number2.setText("（原价30元）");
                break;
        }
    }

    private void pay() {

    }
}
