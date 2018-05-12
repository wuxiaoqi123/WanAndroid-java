package com.welcome.home.playandroid.presenter;


import com.welcome.home.playandroid.bean.LoginBean;
import com.welcome.home.playandroid.contract.RegisterOrLoginContract;
import com.welcome.home.playandroid.net.api.WanAndroidApi;
import com.welcome.home.playandroid.net.callback.RxObserver;
import com.welcome.home.playandroid.net.exception.ResponeThrowable;
import com.welcome.home.playandroid.net.http.HttpUtils;
import com.welcome.home.playandroid.net.transformer.DefaultTransformer;

public class RegisterOrLoginPresenterImp implements RegisterOrLoginContract.Presenter {

    private RegisterOrLoginContract.View mView;

    public RegisterOrLoginPresenterImp(RegisterOrLoginContract.View view) {
        this.mView = view;
    }


    @Override
    public void register(String username, String pwd, String repwd) {
        HttpUtils.getInstance().getRetrofitClient().build(WanAndroidApi.class)
                .register(username, pwd, repwd)
                .compose(new DefaultTransformer<>())
                .compose(mView.bindToLife())
                .subscribe(new RxObserver<LoginBean>() {
                    @Override
                    public void onFail(ResponeThrowable ex) {
                        mView.showErrMsg(ex.message);
                    }

                    @Override
                    public void onSuccess(LoginBean loginBean) {
                        mView.register(loginBean);
                    }
                });
    }

    @Override
    public void login(String username, String pwd) {
        HttpUtils.getInstance().getRetrofitClient().build(WanAndroidApi.class)
                .login(username, pwd)
                .compose(new DefaultTransformer<>())
                .compose(mView.bindToLife())
                .subscribe(new RxObserver<LoginBean>() {
                    @Override
                    public void onFail(ResponeThrowable ex) {
                        mView.showErrMsg(ex.message);
                    }

                    @Override
                    public void onSuccess(LoginBean loginBean) {
                        mView.login(loginBean);
                    }
                });
    }
}
