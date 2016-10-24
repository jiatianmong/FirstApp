package com.jiatianmong.myapp.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by jiatianmong on 2016-10-11 19:52
 */
public class NoScrollViewPager extends ViewPager {
    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScrollViewPager(Context context) {
        super(context);
    }
    //事件拦截，两个ViewPager点击事件重合时调用
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    //重写此方法，触摸时什么都不做，从而实现对滑动的禁用
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }
}
