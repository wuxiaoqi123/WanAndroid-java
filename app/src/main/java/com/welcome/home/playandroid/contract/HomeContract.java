package com.welcome.home.playandroid.contract;

import com.welcome.home.playandroid.base.IPresenter;
import com.welcome.home.playandroid.base.IView;
import com.welcome.home.playandroid.bean.HomeList;

/**
 * Created by wuxiaoqi on 2018/4/26.
 * HomeFragment Contract
 */

public class HomeContract {

    public interface View extends IView {
        void setHomeList(HomeList homeList);

        void addHomeList(HomeList homeList);
    }

    public interface Presenter extends IPresenter {
        void loadHomeList(int page);
    }
}
