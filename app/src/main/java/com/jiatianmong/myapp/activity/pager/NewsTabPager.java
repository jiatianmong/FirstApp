package com.jiatianmong.myapp.activity.pager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;


/**
 * Creat    public Activity mActivity;
 * 09:39
 */
public class NewsTabPager extends BaseNewsPagers {


    public NewsTabPager(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        // 要给帧布局填充布局对象
        TextView view = new TextView(mActivity);
        view.setText("首页");
        view.setTextColor(Color.RED);
        view.setTextSize(22);
        view.setGravity(Gravity.CENTER);
        return view;
    }

    @Override
    public void initData() {

    }
}