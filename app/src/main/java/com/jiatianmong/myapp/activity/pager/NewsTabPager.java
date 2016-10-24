package com.jiatianmong.myapp.activity.pager;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.jiatianmong.myapp.R;
import com.lidroid.xutils.ViewUtils;

public class NewsTabPager extends BasePager {
    private ListView mListView;
    private String mNewPagerTitle;
    private TextView mTextViewNewPagerTitle;

    public NewsTabPager(Activity activity, String mTabNewsName) {
        super(activity);
        mNewPagerTitle=mTabNewsName;
    }


    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.pager_newslist, null);

        mListView = (ListView) view.findViewById(R.id.lv_topnews);
        //注入布局



        ViewUtils.inject(view);
        return view;
    }
    @Override
    public void initData() {

    }
}