package com.welcome.home.playandroid.adapter;

import android.net.Uri;

import com.facebook.drawee.view.SimpleDraweeView;
import com.welcome.home.playandroid.bean.BannerList;

import cn.bingoogolapple.bgabanner.BGABanner;

public class HomeBannerAdapter implements BGABanner.Adapter<SimpleDraweeView, BannerList> {

    @Override
    public void fillBannerItem(BGABanner banner, SimpleDraweeView itemView, BannerList model, int position) {
        itemView.setImageURI(Uri.parse(model.getImagePath()));

    }
}
