package com.huancheng.learngtool.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.huancheng.learngtool.R;
import com.huancheng.learngtool.util.TransApi;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLDecoder;

import okhttp3.Call;

public class TranslateActivity extends BaseActivity {
    private boolean is=true;
    private EditText translate_one;
    private EditText translate_two;
    private TextView translate_topleft;
    private TextView translate_topRight;
    private TransApi t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
        ImageView translate_bt = findViewById(R.id.translate_bt);
        translate_one = findViewById(R.id.translate_one);
        translate_two = findViewById(R.id.translate_two);
        translate_topleft = findViewById(R.id.translate_topleft);
        translate_topRight = findViewById(R.id.translate_topRight);
        ImageView translate_fnayi = findViewById(R.id.translate_fnayi);
        t = new TransApi("20191108000354579", "tX0DZ4yM39oWFwow325U");
        translate_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (is){
                    translate_topleft.setText("英语");
                    translate_topRight.setText("中文");
                    is=false;
                }else {
                    translate_topleft.setText("中文");
                    translate_topRight.setText("英语");
                    is=true;
                }
            }
        });
        translate_fnayi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                translate();
            }
        });
    }

    private void translate() {
        StringCallback stringCallback = new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                JSONObject resultJson = null;
                try {
                    resultJson = new JSONObject(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // {"from":"zh","to":"en","trans_result":[{"src":"hello，你好","dst":"Hello, hello."}]}
                try {
                    String error_code = resultJson.getString("error_code");
                    if (error_code != null) {
                        System.out.println("出错代码:" + error_code);
                        System.out.println("出错信息:" + resultJson.getString("error_msg"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    JSONArray jsonArray = (JSONArray) resultJson.get("trans_result");
                    JSONObject dstJson = (JSONObject) jsonArray.get(0);
                    String text = dstJson.getString("dst");
                    translate_two.setText(text);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        if (is){
            t.getTransResult(translate_one.getText().toString(), "zh", "en",stringCallback);
        }else {
            t.getTransResult(translate_one.getText().toString(), "en", "zh",stringCallback);
        }
    }
}
