package com.huancheng.learngtool.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.chrisbanes.photoview.PhotoView;
import com.huancheng.learngtool.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.OnClick;

public class PictureSlideFragment extends Fragment {
    private String url;
    private PhotoView imageView;
    private ImageLoader loader;

    // 获取URL
    public static PictureSlideFragment newInstance(String url) {
        PictureSlideFragment f = new PictureSlideFragment();

        Bundle args = new Bundle();
        args.putString("url", url);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        url = getArguments().getString("url");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*加载视图*/
        View v=inflater.inflate(R.layout.fragment_picture_slide,container,false);

        imageView= (PhotoView) v.findViewById(R.id.photo_view_pic);

        /*加载图片*/
        loader = ImageLoader.getInstance();
        loader.displayImage(url, imageView);


        return v;
    }

}
