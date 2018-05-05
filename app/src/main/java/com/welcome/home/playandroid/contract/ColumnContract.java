package com.welcome.home.playandroid.contract;

import com.welcome.home.playandroid.base.IPresenter;
import com.welcome.home.playandroid.base.IView;
import com.welcome.home.playandroid.bean.ColumnList;

/**
 * Created by wuxiaoqi on 2018/5/4.
 * ColumnFragment->contract
 */

public class ColumnContract {

    public interface View extends IView {
        void setColumnList(ColumnList list);
    }

    public interface Presenter extends IPresenter {
        void getColumnList();
    }
}
