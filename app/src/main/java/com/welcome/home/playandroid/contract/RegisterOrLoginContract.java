package com.welcome.home.playandroid.contract;


import com.welcome.home.playandroid.base.IPresenter;
import com.welcome.home.playandroid.base.IView;
import com.welcome.home.playandroid.bean.LoginBean;

public class RegisterOrLoginContract {

    public interface View extends IView {
        void register(LoginBean loginBean);

        void login(LoginBean loginBean);
    }

    public interface Presenter extends IPresenter {
        void register(String username, String pwd, String repwd);

        void login(String username, String pwd);
    }
}
