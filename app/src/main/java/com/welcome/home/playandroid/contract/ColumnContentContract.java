package com.welcome.home.playandroid.contract;

import com.welcome.home.playandroid.base.IPresenter;
import com.welcome.home.playandroid.base.IView;
import com.welcome.home.playandroid.bean.ColumnContentList;

/**
 * Created by wuxiaoqi on 2018/5/12.
 */

public class ColumnContentContract {

    public interface View extends IView {
        void setColumnContent(ColumnContentList columnContent);

        void addColumnContent(ColumnContentList columnContentList);
    }

    public interface Presenter extends IPresenter {
        void getColumnContent(int page, int c_id);
    }
}
