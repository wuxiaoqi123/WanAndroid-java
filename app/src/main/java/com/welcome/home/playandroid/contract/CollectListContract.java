package com.welcome.home.playandroid.contract;

import com.welcome.home.playandroid.base.IPresenter;
import com.welcome.home.playandroid.base.IView;
import com.welcome.home.playandroid.bean.CollectList;

public class CollectListContract {
    public interface View extends IView {
        void setCollectList(CollectList collectList);
    }

    public interface Presenter extends IPresenter {
        void getCollectList();
    }
}
