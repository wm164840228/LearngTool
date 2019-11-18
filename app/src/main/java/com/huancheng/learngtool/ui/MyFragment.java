package com.huancheng.learngtool.ui;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.huancheng.learngtool.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;



import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

public class MyFragment extends BaseFragment {
    @BindView(R.id.my_img)
    ImageView my_img;
    @BindView(R.id.my_name)
    TextView my_name;
    @BindView(R.id.my_vip)
    LinearLayout my_vip;
    @BindView(R.id.my_about)
    LinearLayout my_about;
    @BindView(R.id.my_talk)
    LinearLayout my_talk;
    public static MyFragment newInstance() {
        MyFragment fragment = new MyFragment();
        return fragment;
    }
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void BindComponentEvent() {

    }

    @Override
    protected void doActivityResult(int requestCode, Intent intent) {

    }
    @OnClick({R.id.my_vip,R.id.my_talk,R.id.my_about,R.id.my_chack})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_vip:
                break;
            case R.id.my_talk:
                break;
            case R.id.my_about:
                getActivity().startActivity(new Intent(getActivity(),AboutActivity.class));
                break;
            case R.id.my_chack:
                Toast.makeText(getActivity(),"已是最新版本",Toast.LENGTH_LONG).show();
                break;
        }
    }
}
