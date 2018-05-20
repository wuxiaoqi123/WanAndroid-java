package com.welcome.home.playandroid.contract;

import com.welcome.home.playandroid.base.IPresenter;
import com.welcome.home.playandroid.base.IView;
import com.welcome.home.playandroid.bean.CollectArticleList;

public class CollectContract {

    public interface View extends IView {
        void collectSuccess(CollectArticleList collectArticleList);
    }

    public interface Presenter extends IPresenter {

        void collectArticle(String title, String author, String link);
    }
}
