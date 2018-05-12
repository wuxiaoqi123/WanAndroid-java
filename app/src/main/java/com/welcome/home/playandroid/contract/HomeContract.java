package com.welcome.home.playandroid.contract;

import com.welcome.home.playandroid.base.IPresenter;
import com.welcome.home.playandroid.base.IView;
import com.welcome.home.playandroid.bean.BannerList;
import com.welcome.home.playandroid.bean.HomeList;

import java.util.List;

/**
 * Created by wuxiaoqi on 2018/4/26.
 * HomeFragment Contract
 */

public class HomeContract {

    public interface View extends IView {
        void setBannnerList(List<BannerList> bannnerList);

        void setHomeList(HomeList homeList);

        void addHomeList(HomeList homeList);
    }

    public interface Presenter extends IPresenter {
        void loadBannerList();

        void loadHomeList(int page);
    }
}
