package com.welcome.home.playandroid.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

import com.welcome.home.playandroid.R;
import com.welcome.home.playandroid.activity.BrowserActivity;
import com.welcome.home.playandroid.bean.HomeList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wuxiaoqi on 2018/4/26.
 * HomeFragment Adapter
 */

public class HomeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<HomeList.DatasBean> mList;

    public HomeListAdapter() {
        mList = new ArrayList<>();
    }

    public void setHomeList(HomeList homeList) {
        mList.clear();
        mList.addAll(homeList.getDatas());
        notifyDataSetChanged();
    }

    public void addHomeList(HomeList homeList) {
        int lenght = mList.size();
        mList.addAll(homeList.getDatas());
//        notifyDataSetChanged();
        notifyItemChanged(lenght);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_list, parent, false);
        return new HomeListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HomeListItemViewHolder) {
            ((HomeListItemViewHolder) holder).bindHomeListItem(mList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if (mList == null) return 0;
        return mList.size();
    }

    static class HomeListItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_home_list_title_tv)
        TextView titleTv;

        @BindView(R.id.item_home_list_author_tv)
        TextView authorTv;

        @BindView(R.id.item_home_list_nicedate_tv)
        TextView nicedateTv;

        HomeListItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindHomeListItem(HomeList.DatasBean homeListDatasBean) {
            titleTv.setText(homeListDatasBean.getTitle());
            authorTv.setText(homeListDatasBean.getAuthor());
            nicedateTv.setText(homeListDatasBean.getNiceDate());
            itemView.animate().scaleX(0.9f).scaleY(0.7f).alpha(0.8f).setDuration(0).start();
            itemView.animate().scaleX(1).scaleY(1).alpha(1).setDuration(350).setInterpolator(new OvershootInterpolator(0.8f)).start();
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), BrowserActivity.class);
                intent.setData(Uri.parse(homeListDatasBean.getLink()));
                itemView.getContext().startActivity(intent);
            });
        }
    }
}
