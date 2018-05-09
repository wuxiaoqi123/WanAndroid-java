package com.welcome.home.playandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.welcome.home.playandroid.R;
import com.welcome.home.playandroid.bean.ColumnList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuxiaoqi on 2018/5/9.
 * ExpandableListView
 */

public class ColumnExpandableListViewAdapter extends BaseExpandableListAdapter {

    private List<ColumnList> mColumnLists;

    private Context mContext;

    public ColumnExpandableListViewAdapter(Context context) {
        this.mContext = context;
        mColumnLists = new ArrayList<>();
    }

    public void setColumnLists(List<ColumnList> columnLists) {
        mColumnLists.clear();
        mColumnLists.addAll(columnLists);
        notifyDataSetChanged();
    }

    public void addColumnLists(List<ColumnList> columnLists) {
        mColumnLists.addAll(columnLists);
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return mColumnLists.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mColumnLists.get(groupPosition).getChildren().size();
    }

    @Override
    public ColumnList getGroup(int groupPosition) {
        return mColumnLists.get(groupPosition);
    }

    @Override
    public ColumnList.ChildrenBean getChild(int groupPosition, int childPosition) {
        return mColumnLists.get(groupPosition).getChildren().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_column, parent, false);
        }
        TextView textView = convertView.findViewById(R.id.column_tv);
        textView.setText(getGroup(groupPosition).getName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_column_column, parent, false);
        }
        TextView textView = convertView.findViewById(R.id.column_tv);
        textView.setText(getGroup(groupPosition).getChildren().get(childPosition).getName());
        convertView.setOnClickListener(v -> Toast.makeText(mContext, "点击" + getGroup(groupPosition).getChildren().get(childPosition).getName(), Toast.LENGTH_SHORT).show());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
