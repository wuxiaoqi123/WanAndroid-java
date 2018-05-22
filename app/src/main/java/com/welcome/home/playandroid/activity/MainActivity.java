package com.welcome.home.playandroid.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.welcome.home.playandroid.R;
import com.welcome.home.playandroid.base.BaseActivity;
import com.welcome.home.playandroid.bean.LoginBean;
import com.welcome.home.playandroid.contract.RegisterOrLoginContract;
import com.welcome.home.playandroid.fragment.ColumnFragment;
import com.welcome.home.playandroid.fragment.HomeFragment;
import com.welcome.home.playandroid.fragment.MyFragment;
import com.welcome.home.playandroid.presenter.RegisterOrLoginPresenterImp;
import com.welcome.home.playandroid.util.SharedPreferenceUtils;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements RegisterOrLoginContract.View {

    public static final int HOME_PAGE = 0;
    public static final int COLUMN_PAGE = 1;
    public static final int ME_PAGE = 2;
    @BindView(R.id.activity_main_toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_main_root_fl)
    FrameLayout contentFl;
    @BindView(R.id.activity_main_bottomBar)
    BottomBar bottomBar;
    private String mCurrentTag;
    private Fragment mCurrentFragment;
    private RegisterOrLoginPresenterImp presenterImp;
    private long lastClickTime = 0;
    private DrawerLayout drawer;

    public static void startActivity(Context activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWindow() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar == null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("");
        }
        drawer = findViewById(R.id.drawer_layout);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        setFragment(HOME_PAGE);
        if (!TextUtils.isEmpty(SharedPreferenceUtils.getStringData("username", ""))) {
            String userName = SharedPreferenceUtils.getStringData("username", "");
            String pwd = SharedPreferenceUtils.getStringData("password", "");
            presenterImp = new RegisterOrLoginPresenterImp(this);
            presenterImp.login(userName, pwd);
        }
    }

    @Override
    protected void initListener() {
        bottomBar.setOnTabSelectListener(tabId -> {
            switch (tabId) {
                case R.id.tab_recommend:
                    setFragment(HOME_PAGE);
                    break;
                case R.id.tab_column:
                    setFragment(COLUMN_PAGE);
                    break;
                case R.id.tab_my:
                    setFragment(ME_PAGE);
                    break;
                default:
                    break;
            }
        });
    }

    private void setFragment(int index) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        String tag = index + "";
        if (mCurrentTag != null && !tag.equals(mCurrentTag)) {
            Fragment fragmentByTag = fm.findFragmentByTag(tag);
            if (fragmentByTag != null) {//fragment栈中已经有该fragment实例
                ft.hide(mCurrentFragment)
                        .show(fragmentByTag)
                        .commitAllowingStateLoss();
                mCurrentFragment = fragmentByTag;
                mCurrentTag = tag;
            } else {
                Fragment chilFragment = findFragmentByIndex(index);
                if (chilFragment == null) return;
                ft.hide(mCurrentFragment)
                        .add(contentFl.getId(), chilFragment, tag)
                        .commitAllowingStateLoss();
                mCurrentFragment = chilFragment;
                mCurrentTag = tag;
            }
        } else {
            if (mCurrentFragment == null) {
                Fragment childFragment = findFragmentByIndex(index);
                if (childFragment == null) return;
                ft.replace(contentFl.getId(), childFragment, tag)
                        .addToBackStack(tag)
                        .commitAllowingStateLoss();
                mCurrentFragment = childFragment;
                mCurrentTag = tag;
            }
        }
    }

    protected Fragment findFragmentByIndex(int index) {
        Fragment fragment = null;
        switch (index) {
            case HOME_PAGE:
                fragment = HomeFragment.getInstance();
                break;
            case COLUMN_PAGE:
                fragment = ColumnFragment.getInstance();
                break;
            case ME_PAGE:
                fragment = MyFragment.getInstance();
                break;
            default:
        }
        return fragment;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (System.currentTimeMillis() - lastClickTime > 2000) {
                lastClickTime = System.currentTimeMillis();
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            } else {
                finish();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        }
    }

    @Override
    public void register(LoginBean loginBean) {

    }

    @Override
    public void login(LoginBean loginBean) {
    }

    @Override
    public void showErrMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
