package com.huancheng.learngtool.ui;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.huancheng.learngtool.R;
import com.huancheng.learngtool.util.NullUtil;
import com.huancheng.learngtool.util.SharedPreferencesUtil;

import static com.huancheng.learngtool.util.SharedPreferencesUtil.USER_ID;

public class SplashActivity extends BasebussActivity {
    @BindView(R.id.splash_logo)
    ImageView splash_logo;
    @BindView(R.id.splash_skip_view)
    TextView splash_skip_view;
    private int count=3;
    private Handler mhandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0) {
                splash_skip_view.setText(getCount() + "s");
                mhandler.sendEmptyMessageDelayed(0, 1000);
            }
        }
    };

    @Override
    protected int setCustomLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected Activity initView() {
        splash_skip_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tonext();
                mhandler.removeMessages(0);
            }
        });
        mhandler.sendEmptyMessageDelayed(0, 1000);//开屏网络开屏
        return this;
    }

    private void tonext() {
        String userId = (String) SharedPreferencesUtil.getParam(_context,USER_ID,"");
        if (NullUtil.StringIsNull(userId)){
            startActivity(new Intent(this,LoginActivity.class));
        }else {
            startActivity(new Intent(this,MainActivity.class));
        }
        finish();
        mhandler.removeMessages(0);
    }
    private int getCount() {
        count--;
        if (count == 0) {
          tonext();
        }
        return count;
    }
}
