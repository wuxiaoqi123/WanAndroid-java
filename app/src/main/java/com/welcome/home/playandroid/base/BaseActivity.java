package com.welcome.home.playandroid.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wuxiaoqi on 2018/2/27.
 * Activity 基类
 */

public abstract class BaseActivity extends RxAppCompatActivity {

    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindow();
        setContentView(getLayoutId());
        bind = ButterKnife.bind(this);
        initData();
        initViews();
        initListener();
    }

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void initWindow();

    protected abstract void initData();

    protected abstract void initViews();

    protected abstract void initListener();

    /**
     * 绑定生命周期
     */
    public <T> LifecycleTransformer<T> bindToLife() {
        return bindUntilEvent(ActivityEvent.DESTROY);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind != null) {
            bind.unbind();
            bind = null;
        }
    }
}
