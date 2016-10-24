package com.jiatianmong.myapp.activity.Fragment;

import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jiatianmong.myapp.R;
import com.jiatianmong.myapp.activity.MainActivity;
import com.jiatianmong.myapp.activity.pager.NewsTabPager;
import com.jiatianmong.myapp.bean.NewsMenu;
import com.jiatianmong.myapp.global.GlobalContents;
import com.jiatianmong.myapp.utils.CacheUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.viewpagerindicator.TabPageIndicator;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by jiatianmong on 2016-10-10 15:59
 */
public class NewsFragment extends BaseFragment {
    private HttpURLConnection conne;
    private ViewPager mViewPager;
    private List mHtnlData;
    private NewsMenu mNewsMenu;
    private TextView mTextView;
    public static ArrayList<String> mNewPagerTitle =new ArrayList<>() ;
    private ArrayList<NewsTabPager> mNewsTabPager;
    private ImageButton mImageButton;
    private TabPageIndicator mIndicator;



    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_news, null);
        mViewPager = (ViewPager) view.findViewById(R.id.vp_newstabpager);
        mTextView = (TextView) view.findViewById(R.id.tv_title);
        mIndicator = (TabPageIndicator) view.findViewById(R.id.indicator);
        mImageButton = (ImageButton) view.findViewById(R.id.btn_imagemenu);
        mTextView.setText("News");
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle();
            }
        });
        mTextView.setTextColor(Color.WHITE);
        com.lidroid.xutils.ViewUtils.inject(this, view);

        init_listtitle();

        return view;
    }

    private void init_listtitle() {
        mNewPagerTitle.add("头条");
        mNewPagerTitle.add("社会");
        mNewPagerTitle.add("国内");
        mNewPagerTitle.add("国际");
        mNewPagerTitle.add("娱乐");
        mNewPagerTitle.add("体育");
        mNewPagerTitle.add("军事");
        mNewPagerTitle.add("科技");
        mNewPagerTitle.add("财经");
        mNewPagerTitle.add("时尚");
    }

    @Override
    public void initData() {
        System.out.println("链接网路");

        getDataFromServer();

        init_PagerAdapte();


}


    private void init_PagerAdapte() {
        mNewsTabPager = new ArrayList<>();
        for (int i = 0; i < mNewPagerTitle.size(); i++) {
            NewsTabPager mBaseNewTab = new NewsTabPager(mActivity, mNewPagerTitle.get(i));
            mNewsTabPager.add(mBaseNewTab);
        }
        mViewPager.setAdapter(new NewsTabadapter());
        //在页签内容加载数据完再绑定，将ViewPager和指示器绑定
        mIndicator.setViewPager(mViewPager);
    }
    /*
        从服务器获取json数据
         */
    /*
        从服务器获取json数据
         */
    public void getDataFromServer() {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("key", GlobalContents.APIKEY);
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, GlobalContents.SERVER_URL, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                //返回数据
                String result = responseInfo.result;
                //System.out.println("result"+result);
                //解析数据
                processJsonData(result);
                System.out.println("解析完json数据");
                //设置缓存
                CacheUtils.setCache(GlobalContents.CATEGORY_URL, result, mActivity);
                System.out.println("设置缓存");
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });

    }
       class NewsTabadapter extends PagerAdapter {

        //4制定指示器对应标签
        @Override
        public CharSequence getPageTitle(int position) {
            String Title = mNewPagerTitle.get(position);
            return Title;
        }

        @Override
        public int getCount() {
            return mNewsTabPager.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            NewsTabPager newsTabPager = mNewsTabPager.get(position);
            View view = newsTabPager.mRootView;
            container.addView(view);
            newsTabPager.initData();
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    private void processJsonData(String json) {

        Gson gson = new Gson();
        mNewsMenu = gson.fromJson(json, NewsMenu.class);
        System.out.println("解析结果" + mNewsMenu.result.data);

    }
    public  void toggle() {
        MainActivity mainUI = (MainActivity) mActivity;
        SlidingMenu slidingMenu = mainUI.getSlidingMenu();
        slidingMenu.toggle();// 如果当前状态是开, 调用后就关; 反之亦然
    }


}
