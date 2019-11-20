package com.huancheng.learngtool.ui;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.huancheng.learngtool.R;
import com.huancheng.learngtool.bean.UserBean;
import com.huancheng.learngtool.util.SharedPreferencesUtil;
import com.huancheng.learngtool.util.UpdateAppUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;



import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

import static com.huancheng.learngtool.util.SharedPreferencesUtil.USER_BEAN;

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
        UserBean userBean = SharedPreferencesUtil.getObject(_context, USER_BEAN, UserBean.class);
        if (userBean==null){
            startActivity(new Intent(getActivity(),LoginActivity.class));
        }else {
            my_name.setText(userBean.getName());
            Glide.with(this).load(userBean.getIconurl()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(my_img);
        }

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
    @OnClick({R.id.my_vip,R.id.my_talk,R.id.my_about,R.id.my_chack,R.id.my_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_vip:
                getActivity().startActivity(new Intent(getActivity(),VipActivity.class));
                break;
            case R.id.my_talk:
                getActivity().startActivity(new Intent(getActivity(),SuggestActivity.class));
                break;
            case R.id.my_about:
                getActivity().startActivity(new Intent(getActivity(),AboutActivity.class));
                break;
            case R.id.my_chack:
                UpdateAppUtil.initUptate(getActivity().getApplication());
                UpdateAppUtil.checkUpdate(getActivity());
                break;
            case R.id.my_exit:
                getActivity().startActivity(new Intent(getActivity(),LoginActivity.class));
                SharedPreferencesUtil.clear(getActivity());
                getActivity().finish();
        }
    }
}
