package com.welcome.home.playandroid.activity;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.welcome.home.playandroid.R;
import com.welcome.home.playandroid.base.BaseActivity;
import com.welcome.home.playandroid.bean.LoginBean;
import com.welcome.home.playandroid.contract.RegisterOrLoginContract;
import com.welcome.home.playandroid.presenter.RegisterOrLoginPresenterImp;
import com.welcome.home.playandroid.ui.TitleView;
import com.welcome.home.playandroid.util.SharedPreferenceUtils;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;


public class RegisterOrLoginActivity extends BaseActivity implements ViewTreeObserver.OnGlobalLayoutListener, RegisterOrLoginContract.View {

    @BindView(R.id.activity_register_or_login_titleview)
    TitleView titleView;

    @BindView(R.id.activity_register_or_login_head_iv)
    CircleImageView headCircleImageView;

    @BindView(R.id.username_login_et)
    EditText userNameEt;

    @BindView(R.id.pwd_login_et)
    EditText userPwdEt;

    @BindView(R.id.repwd_login_et)
    EditText userRePwdEt;

    @BindView(R.id.repwd_login_ll)
    LinearLayout repwdLoginLl;

    @BindView(R.id.pwd_forget_login_tv)
    TextView registerOrLoginTv;

    @BindView(R.id.submit_login_btn)
    Button submitBtn;

    private boolean loginState = true;

    private RegisterOrLoginPresenterImp presenterImp;

    public static void startActivity(Activity activity) {
        Intent i = new Intent(activity, RegisterOrLoginActivity.class);
        activity.startActivity(i);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register_or_login;
    }

    @Override
    protected void initWindow() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        titleView.setTitle(getString(R.string.login));
        presenterImp = new RegisterOrLoginPresenterImp(this);
    }

    @Override
    protected void initListener() {
        titleView.setListener(new TitleView.ITitleOnClicenListener() {
            @Override
            public void onClickLeft() {
                finish();
            }

            @Override
            public void onClickRight() {

            }
        });
        registerOrLoginTv.setOnClickListener(v -> {
            loginState = !loginState;
            if (loginState) {//登录状态
                loginState();
            } else {//注册状态
                registerState();
            }
        });
        submitBtn.setOnClickListener(v -> {
            if (loginState) {//登录
                if (!checkoutInput()) return;
                presenterImp.login(userNameEt.getText().toString().trim(), userPwdEt.getText().toString().trim());
            } else {//注册
                if (!checkoutInput()) return;
                presenterImp.register(userNameEt.getText().toString().trim(),
                        userPwdEt.getText().toString().trim(),
                        userRePwdEt.getText().toString().trim());
            }
        });
    }

    private boolean checkoutInput() {
        if (TextUtils.isEmpty(userNameEt.getText())) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(userPwdEt.getText())) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!loginState) {
            if (TextUtils.isEmpty(userRePwdEt.getText())) {
                Toast.makeText(this, "请输入重复密码", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    private void loginState() {
        registerOrLoginTv.setText("注册");
        submitBtn.setText("登录");
        repwdLoginLl.setVisibility(View.GONE);
        clearInput();
    }

    private void registerState() {
        registerOrLoginTv.setText("登录");
        submitBtn.setText("注册");
        repwdLoginLl.setVisibility(View.VISIBLE);
        clearInput();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getWindow().getDecorView().getViewTreeObserver().removeGlobalOnLayoutListener(this);
    }


    @Override
    public void onGlobalLayout() {
        int screenHeight = headCircleImageView.getRootView().getHeight();//获取根布局高
        Rect keyRect = new Rect();
        headCircleImageView.getWindowVisibleDisplayFrame(keyRect);//获取当前窗口可视区域大小
        int keyShowHeight = screenHeight - keyRect.bottom;
        if (keyShowHeight > 0) {
            animShowOrHideHeadImg(false);
        } else {
            animShowOrHideHeadImg(true);
        }
    }

    private int headImgHeight = 0;
    private int headImgWidth = 0;

    private void animShowOrHideHeadImg(boolean show) {
        if (show) {//显示
            if (headCircleImageView.getTag() == null) {
                return;
            }
            final int height = headImgHeight;
            final int width = headImgWidth;
            headCircleImageView.setTag(null);
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
            valueAnimator.setDuration(400).setInterpolator(new DecelerateInterpolator());
            valueAnimator.addUpdateListener(animation -> {
                float value = (float) animation.getAnimatedValue();
                ViewGroup.LayoutParams lp = headCircleImageView.getLayoutParams();
                lp.height = (int) (height * value);
                lp.width = (int) (width * value);
                headCircleImageView.requestLayout();
                headCircleImageView.setAlpha(value);
            });
            if (valueAnimator.isRunning()) {
                valueAnimator.cancel();
            }
            valueAnimator.start();
        } else {//隐藏
            if (headCircleImageView.getTag() != null) {
                return;
            }
            final int height = headCircleImageView.getHeight();
            final int width = headCircleImageView.getWidth();
            headImgHeight = height;
            headImgWidth = width;
            headCircleImageView.setTag(true);
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(1, 0);
            valueAnimator.setDuration(400).setInterpolator(new DecelerateInterpolator());
            valueAnimator.addUpdateListener(animation -> {
                float value = (float) animation.getAnimatedValue();
                ViewGroup.LayoutParams lp = headCircleImageView.getLayoutParams();
                lp.height = (int) (height * value);
                lp.width = (int) (width * value);
                headCircleImageView.requestLayout();
                headCircleImageView.setAlpha(value);
            });
            if (valueAnimator.isRunning()) {
                valueAnimator.cancel();
            }
            valueAnimator.start();
        }
    }

    private void clearInput() {
        userNameEt.setText("");
        userPwdEt.setText("");
        userRePwdEt.setText("");
        userNameEt.requestFocus();
    }

    @Override
    public void showErrMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void register(LoginBean loginBean) {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
        clearInput();
        loginState = true;
        loginState();
        userNameEt.setText(loginBean.getUsername());
        userPwdEt.requestFocus();
    }

    @Override
    public void login(LoginBean loginBean) {
        SharedPreferenceUtils.setStringData("username", loginBean.getUsername());
        SharedPreferenceUtils.setStringData("password", loginBean.getPassword());
        finish();
    }
}
