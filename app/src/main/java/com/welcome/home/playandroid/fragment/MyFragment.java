package com.welcome.home.playandroid.fragment;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.welcome.home.playandroid.R;
import com.welcome.home.playandroid.activity.RegisterOrLoginActivity;
import com.welcome.home.playandroid.base.BaseFragment;
import com.welcome.home.playandroid.util.SharedPreferenceUtils;

import butterknife.BindView;

/**
 * Created by wuxiaoqi on 2018/4/20.
 * 我的 Fragment
 */

public class MyFragment extends BaseFragment {

    public static MyFragment getInstance() {
        return new MyFragment();
    }

    @BindView(R.id.fragment_my_regiter_or_login_tv)
    TextView loginNameTv;

    @BindView(R.id.fragment_my_exit_tv)
    TextView exitTv;

    @BindView(R.id.fragment_my_version)
    TextView versionTv;

    @BindView(R.id.fragment_my_version_tv)
    TextView showVersionTv;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView(Bundle bundle) {
        showVersionTv.setText("v " + getVersionName());
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(SharedPreferenceUtils.getStringData("username", ""))) {
            loginNameTv.setText(SharedPreferenceUtils.getStringData("username", ""));
            exitTv.setVisibility(View.VISIBLE);
        } else {
            loginNameTv.setText(getString(R.string.register_or_login));
            exitTv.setVisibility(View.GONE);
        }
    }

    private String getVersionName() {
        String versionName = "";
        PackageManager packageManager = getActivity().getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getActivity().getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    @Override
    protected void initListener() {
        loginNameTv.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(SharedPreferenceUtils.getStringData("username", ""))) {
                return;
            }
            RegisterOrLoginActivity.startActivity(getActivity());
        });
        exitTv.setOnClickListener(v -> showDialog());
        versionTv.setOnClickListener(v -> Toast.makeText(mContext, "v" + getVersionName(), Toast.LENGTH_SHORT).show());
    }

    private void showDialog() {
        new AlertDialog.Builder(getContext())
                .setTitle("提示")
                .setMessage("确定退出登录")
                .setNegativeButton("确定", (dialog, which) -> {
                    SharedPreferenceUtils.clear();
                    exitTv.setVisibility(View.GONE);
                    loginNameTv.setText(getString(R.string.register_or_login));
                })
                .setPositiveButton("取消", null)
                .setCancelable(true)
                .show();
    }

    @Override
    protected void lazyFetchData() {

    }
}
