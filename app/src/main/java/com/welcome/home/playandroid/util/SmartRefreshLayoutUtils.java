package com.welcome.home.playandroid.util;

import android.content.Context;

import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.welcome.home.playandroid.R;

/**
 * Created by wuxiaoqi on 2018/3/26.
 * SmartRefreshLayoutUtils
 */

public final class SmartRefreshLayoutUtils {

    /**
     * MD 风格
     *
     * @param context
     * @param smartRefreshLayout
     */
    public static void initRefreshLayoutMD(Context context, SmartRefreshLayout smartRefreshLayout) {
        //设置 Header 为 Material风格
        MaterialHeader materialHeader = new MaterialHeader(context);
        materialHeader.setColorSchemeColors(context.getResources().getColor(R.color.colorPrimary));
        smartRefreshLayout.setRefreshHeader(materialHeader.setShowBezierWave(true));
        smartRefreshLayout.setPrimaryColors(context.getResources().getColor(R.color.colorPrimary));
        //设置 Footer 为 球脉冲
        smartRefreshLayout.setRefreshFooter(new BallPulseFooter(context)
                .setSpinnerStyle(SpinnerStyle.Scale)
                .setAnimatingColor(context.getResources().getColor(R.color.colorPrimary)));

    }

    /**
     * 贝塞尔雷达
     *
     * @param context
     * @param smartRefreshLayout
     */
    public static void initRefreshLayoutBz(Context context, SmartRefreshLayout smartRefreshLayout) {
        //设置 Header 为 Material风格
        BezierRadarHeader bezierRadarHeader = new BezierRadarHeader(context);
        smartRefreshLayout.setRefreshHeader(bezierRadarHeader);
        smartRefreshLayout.setPrimaryColors(context.getResources().getColor(R.color.colorPrimary));
        //设置 Footer 为 球脉冲
        smartRefreshLayout.setRefreshFooter(new BallPulseFooter(context)
                .setSpinnerStyle(SpinnerStyle.Scale)
                .setAnimatingColor(context.getResources().getColor(R.color.colorPrimary)));

    }
}
