package com.huancheng.learngtool.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.OnClick;
import me.pqpo.smartcropperlib.view.CropImageView;

import android.app.Activity;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.huancheng.learngtool.R;
import com.huantansheng.easyphotos.models.album.entity.Photo;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class CutActivity extends BasebussActivity{
    @BindView(R.id.iv_crop)
    CropImageView ivCrop;
    private ArrayList<Photo> list; /*所有图片url*/
    private int number;

    @Override
    protected int setCustomLayout() {
        return R.layout.activity_cut;
    }

    @Override
    protected Activity initView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        list = bundle.getParcelableArrayList("list");
        number = intent.getIntExtra("number", 99);
        try {
            FileInputStream fis = new FileInputStream(list.get(number).path);
            Bitmap resource= BitmapFactory.decodeStream(fis);
            ivCrop.setImageToCrop(resource);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return this;
    }
    @OnClick({R.id.cut_back,R.id.cut_cut})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cut_back:
                onBackPressed();
                break;
            case R.id.cut_cut:
                Bitmap crop = ivCrop.crop();
                //自己创建想要保存的文件的文件对象
                ContextWrapper contextWrapper = new ContextWrapper(this);
                File filesDir = contextWrapper.getFilesDir();
                long time = new Date().getTime();
                String path=filesDir+"/"+String.valueOf(time)+".png";
                Uri uri = saveBitmap(crop, path);
                list.get(number).path=path;
                list.get(number).uri=uri;
                Intent intent= new Intent();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("list",list);
                intent.putExtras(bundle);
                setResult(201,intent);
                onBackPressed();
                break;
        }
    }
    /**
     * 保存bitmap到本地
     *
     * @param bitmap Bitmap
     */
    public static Uri saveBitmap(Bitmap bitmap,String path) {
        String savePath;
        File filePic;
        Uri uri = null;
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            savePath = path;
        } else {
            Log.e("tag", "saveBitmap failure : sdcard not mounted");
            return uri;
        }
        try {
            filePic = new File(savePath);
            if (!filePic.exists()) {
                filePic.getParentFile().mkdirs();
                filePic.createNewFile();
                uri = Uri.fromFile(filePic);
            }
            FileOutputStream fos = new FileOutputStream(filePic);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            Log.e("tag", "saveBitmap: " + e.getMessage());
            return uri;
        }
        Log.i("tag", "saveBitmap success: " + filePic.getAbsolutePath());
        return uri;
    }
}
