package com.weijiadev.googlex.view.activity.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/4/7.
 * 方便统一管理Activity
 */

public abstract class BaseActivity extends AppCompatActivity {

    public Context mContext;
    protected abstract void initView();
    protected abstract void initListening();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutId());

        ViewGroup contentFrameLayout = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
        View parentView = contentFrameLayout.getChildAt(0);
        if (parentView != null && Build.VERSION.SDK_INT >= 14) {
            parentView.setFitsSystemWindows(true);
        }
        this.mContext = this;
        ButterKnife.bind(this);

        initView();
        initListening();

    }

    //setContent 布局文件
    protected abstract int getLayoutId();

}
