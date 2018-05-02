package com.welcome.home.playandroid.activity;

import android.os.Handler;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;

import com.welcome.home.playandroid.R;
import com.welcome.home.playandroid.base.BaseActivity;

import butterknife.BindView;

public class SplashActivity extends BaseActivity {

    @BindView(R.id.splash_activity_tv)
    TextView splashTv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initWindow() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListener() {
        splashTv.animate()
                .alpha(1)
                .setDuration(1000)
                .setInterpolator(new AccelerateInterpolator())
                .start();
        new Handler().postDelayed(() -> {
            if (isFinishing()) return;
            MainActivity.startActivity(SplashActivity.this);
            overridePendingTransition(0, 0);
            SplashActivity.this.finish();
        }, 2300);
    }
}
