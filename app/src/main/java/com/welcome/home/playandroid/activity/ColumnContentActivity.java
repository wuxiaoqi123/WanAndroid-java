package com.welcome.home.playandroid.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.welcome.home.playandroid.R;
import com.welcome.home.playandroid.base.BaseActivity;

public class ColumnContentActivity extends BaseActivity {

    private static final String KEY_CID = "key_cid";

    public static void startActivity(Context activity, int cid) {
        Intent intent = new Intent(activity, ColumnContentActivity.class);
        intent.putExtra(KEY_CID, cid);
        activity.startActivity(intent);
    }

    private int c_id = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_column_content;
    }

    @Override
    protected void initWindow() {

    }

    @Override
    protected void initData() {
        c_id = getIntent().getIntExtra(KEY_CID, 0);
        Toast.makeText(this, c_id + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListener() {

    }
}
