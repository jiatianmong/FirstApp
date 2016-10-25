package com.jiatianmong.myapp.activity.pager;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.jiatianmong.myapp.R;
import com.jiatianmong.myapp.global.ConnectServer;
import com.lidroid.xutils.ViewUtils;

import java.util.ArrayList;

public class NewsTabPager extends BasePager {
    private ListView mListView;
    private String mNewPagerTitle;
    private TextView mTextViewNewPagerTitle;
    public static ArrayList<String> MNEWPAGERTITLENAME =new ArrayList<>() ;
    public NewsTabPager(Activity activity, String mTabNewsName) {
        super(activity);
        mNewPagerTitle=mTabNewsName;
        init_listtitle();
    }
    private void init_listtitle() {
        MNEWPAGERTITLENAME.add("top");
        MNEWPAGERTITLENAME.add("shehui");
        MNEWPAGERTITLENAME.add("guonei");
        MNEWPAGERTITLENAME.add("guoji");
        MNEWPAGERTITLENAME.add("yule");
        MNEWPAGERTITLENAME.add("tiyu");
        MNEWPAGERTITLENAME.add("junshi");
        MNEWPAGERTITLENAME.add("keji");
        MNEWPAGERTITLENAME.add("caijing");
        MNEWPAGERTITLENAME.add("shishang");
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
    public void initData(int position) {

        //System.out.println("当前所在页面-->"+MNEWPAGERTITLENAME.get(position));
        ConnectServer connectServer = new ConnectServer();
        connectServer.getDataFromServer(MNEWPAGERTITLENAME.get(position));

    }
}