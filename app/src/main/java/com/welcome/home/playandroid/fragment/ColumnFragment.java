package com.welcome.home.playandroid.fragment;

import android.os.Bundle;

import com.welcome.home.playandroid.R;
import com.welcome.home.playandroid.base.BaseFragment;

/**
 * Created by wuxiaoqi on 2018/4/20.
 * 栏目 Fragment
 */

public class ColumnFragment extends BaseFragment {

    public static ColumnFragment getInstance() {
        return new ColumnFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_column;
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
