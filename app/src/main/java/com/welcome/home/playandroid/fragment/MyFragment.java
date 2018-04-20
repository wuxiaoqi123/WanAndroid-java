package com.welcome.home.playandroid.fragment;

import android.os.Bundle;

import com.welcome.home.playandroid.R;
import com.welcome.home.playandroid.base.BaseFragment;

/**
 * Created by wuxiaoqi on 2018/4/20.
 * 我的 Fragment
 */

public class MyFragment extends BaseFragment {

    public static MyFragment getInstance() {
        return new MyFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView(Bundle bundle) {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void lazyFetchData() {

    }
}
