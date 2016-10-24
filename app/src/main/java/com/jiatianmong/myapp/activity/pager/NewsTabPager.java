package com.jiatianmong.myapp.activity.pager;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;

public class NewsTabPager extends BasePager {
    private ListView mListView;
    private TextView mTabNewsName;

    public NewsTabPager(Activity activity, String s) {
        super(activity);
    }


    @Override
    public View initView() {
//        View view = View.inflate(mActivity, R.layout.pager_newslist, null);
//        mTabNewsName = (TextView) view.findViewById(R.id.tv_newsTabName);
//        mTabNewsName.setText("新闻");
//        mListView = (ListView) view.findViewById(R.id.lv_topnews);
//        //注入布局
        TextView view = new TextView(mActivity);
        view.setText("lala");

        ViewUtils.inject(view);
        return view;
    }
    @Override
    public void initData() {

    }
}