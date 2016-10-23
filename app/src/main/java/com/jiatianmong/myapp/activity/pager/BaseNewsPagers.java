package com.jiatianmong.myapp.activity.pager;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;


/**
 * Created by jiatianmong on 2016-10-14 19:56
 */
public abstract class BaseNewsPagers {
    public Activity mActivity;
    public View mRootView;// 当前页面的布局对象
    public TextView mTextview;

    public BaseNewsPagers(Activity activity) {
        mActivity = activity;
        mRootView = initView();
    }

    // 初始化布局,必须有子类继承
    public abstract View initView();

    //初始化数据
    public abstract void initData();



}
