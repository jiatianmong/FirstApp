package com.jiatianmong.myapp.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by jiatianmong on 2016-10-11 19:52
 */
public class ViewPagerToTab extends ViewPager {
    public ViewPagerToTab(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewPagerToTab(Context context) {
        super(context);
    }

    //请求父窗体不要拦截触摸事件
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        getParent().requestDisallowInterceptTouchEvent(true);
//        return super.dispatchTouchEvent(ev);
//    }
}
