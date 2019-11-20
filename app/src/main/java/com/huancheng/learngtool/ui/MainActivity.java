package com.huancheng.learngtool.ui;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.huancheng.learngtool.R;
import com.huancheng.learngtool.util.GlideEngine;
import com.huancheng.learngtool.util.UpdateAppUtil;
import com.huantansheng.easyphotos.EasyPhotos;
import com.huantansheng.easyphotos.callback.SelectCallback;
import com.huantansheng.easyphotos.models.album.entity.Photo;
import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BasebussActivity implements BaseFragment.FragmentCallBack{
    @BindView(R.id.btn_homepage)
    RadioButton btn_homepage;
    @BindView(R.id.btn_center)
    ImageView btn_center;
    @BindView(R.id.btn_my)
    RadioButton btn_my;
    private List<Fragment> fragmentList;
    private Fragment currentFragment;
    private FragmentManager fragmentManager;

    @Override
    protected int setCustomLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected Activity initView() {
        currentFragment = new Fragment();
        fragmentManager = getSupportFragmentManager();
        addFragment();
        showFragment(fragmentList.get(0));
        //自动检查更新
        UpdateAppUtil.initUptate(getApplication());
        UpdateAppUtil.mainUpdate(MainActivity.this);
        return this;
    }

    /*
     * 添加首页fragment
     * */
    private void addFragment() {
        fragmentList = new ArrayList<>();
        MainFragment mainFragment = new MainFragment();
        fragmentList.add(mainFragment);
        MyFragment myFragment = new MyFragment();
        fragmentList.add(myFragment);
}
    /*
     * 显示fragment
     * */
    private void showFragment(Fragment fragment) {
        if (currentFragment != fragment) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.hide(currentFragment);
            currentFragment = fragment;
            if (!fragment.isAdded()) {// 先判断是否被add过
                transaction.add(R.id.content, fragment).show(fragment).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.show(fragment).commit();// 隐藏当前的fragment，显示下一个
            }
        }
    }

    @Override
    public void setResult(Object... param) {

    }
    @OnClick({R.id.btn_homepage,R.id.btn_my,R.id.btn_center})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_my:
                showFragment(fragmentList.get(1));
                break;
            case R.id.btn_homepage:
                showFragment(fragmentList.get(0));
                break;
            case R.id.btn_center:
                EasyPhotos.createAlbum(this, true, GlideEngine.getInstance())
                        .setFileProviderAuthority("com.huantansheng.easyphotos.fileprovider")
                        .setCount(4)
                        .start(new SelectCallback() {
                            @Override
                            public void onResult(ArrayList<Photo> photos, boolean isOriginal) {
                                Intent intent = new Intent(getApplicationContext(), CommitActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putParcelableArrayList("list",photos);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        });
                break;
        }
    }
}
