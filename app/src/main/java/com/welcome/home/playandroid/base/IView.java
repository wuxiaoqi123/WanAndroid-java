package com.welcome.home.playandroid.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * Created by wuxiaoqi on 2018/2/28.
 * 基类 iview
 */

public interface IView {
    /**
     * 显示错误信息
     *
     * @param msg String
     */
    void showErrMsg(String msg);


    /**
     * 绑定生命周期
     *
     * @param <T>
     * @return
     */
    <T> LifecycleTransformer<T> bindToLife();
}
