package com.jiatianmong.myapp.activity.pager;

import android.app.Activity;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jiatianmong.myapp.R;
import com.jiatianmong.myapp.bean.FileService;
import com.jiatianmong.myapp.bean.NewsMenu;
import com.jiatianmong.myapp.global.GlobalContents;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.viewpagerindicator.LinePageIndicator;

import java.util.ArrayList;

public class NewsTabPager extends BasePager implements SwipeRefreshLayout.OnRefreshListener   {
    private String mNewPagerTitle;
    public static ArrayList<String> MNEWPAGERTITLENAME = new ArrayList<>();
    private NewsMenu mNewsMenu;
    private ViewPager mViewPagerToPic;
    private TextView mTopPicTie;
    private LinePageIndicator mIndicator;
    private ListView mListNews;
    private static final int REFRESH_COMPLETE = 0X110;
    private SwipeRefreshLayout mSwipeLayout;
    private int mPosition;

    public NewsTabPager(Activity activity, String mTabNewsName) {
        super(activity);
        mNewPagerTitle = mTabNewsName;
        init_listtitle();

        //init_new_urlpic();
    }

//
//    private void init_new_urlpic() {
//        TOPNEWPIC.add(mNewsMenu.result.data.get(0).thumbnail_pic_s);
//        TOPNEWPIC.add(mNewsMenu.result.data.get(0).thumbnail_pic_s02);
//        TOPNEWPIC.add(mNewsMenu.result.data.get(0).thumbnail_pic_s03);
//    }

    //下拉刷新
    private Handler mHandler = new Handler()
    {
        public void handleMessage(android.os.Message msg)
        {
            switch (msg.what)
            {
                case REFRESH_COMPLETE:
                    getDataFromServer(MNEWPAGERTITLENAME.get(mPosition));
                    mSwipeLayout.setRefreshing(false);//设置不刷新
                    break;

            }
        }
    };
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
       mListNews = (ListView) view.findViewById(R.id.lv_newsList);
        //下拉刷新控件初始化
        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.id_swipe_ly);
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        ViewUtils.inject(this,view);

        View headview = View.inflate(mActivity, R.layout.pager_newstoppic, null);
        mViewPagerToPic = (ViewPager) headview.findViewById(R.id.vp_newspicPager);
        mTopPicTie = (TextView) headview.findViewById(R.id.tv_newsTile);
        mIndicator = (LinePageIndicator) headview.findViewById(R.id.lp_indicator);


        mListNews.addHeaderView(headview);

        //注入布局
        ViewUtils.inject(this,headview);

        return view;
    }

    @Override
    public void initData(int position) {

/*      //使用SharedPreferences缓存
        String cache = CacheUtils.getCache(MNEWPAGERTITLENAME.get(position), mActivity);
        if (!TextUtils.isEmpty(cache)) {
            processJsonData(cache);
        }*/

        String cache = FileService.getFileFromSd(MNEWPAGERTITLENAME.get(position));
        if (!TextUtils.isEmpty(cache)) {
            mSwipeLayout.setRefreshing(true);
            //进入页面先自动刷新，显示转圈
            mSwipeLayout.post(new Runnable(){
                @Override
                public void run() {
                    mSwipeLayout.setRefreshing(true);
                    mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 1000);
                }
            });

            //有缓存先加载缓存数据
            processJsonData(cache);
        }
        //getDataFromServer(MNEWPAGERTITLENAME.get(position));

        mPosition = position;
    }


    private void processJsonData(String json) {

        Gson gson = new Gson();
        mNewsMenu = gson.fromJson(json, NewsMenu.class);
        System.out.println("解析结果" + mNewsMenu.result.data);
        //填充完数据就绑定Indicater
        mViewPagerToPic.setAdapter(new NewsPagerToPicAdapter());
        mIndicator.setViewPager(mViewPagerToPic);

        mViewPagerToPicListener();
        mListNews.setAdapter(new NewsListAdapter());

    }

    @Override
    public void onRefresh() {

        System.out.println("onRefresh");
        mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 1000);

    }

    class NewsListAdapter extends BaseAdapter {

        private BitmapUtils mBitmapUtils;

        public NewsListAdapter() {
            mBitmapUtils = new BitmapUtils(mActivity);
            mBitmapUtils.configDefaultLoadingImage(R.mipmap.jiazai);
        }
        @Override
        public int getCount() {
            return mNewsMenu.result.data.size();
        }

        @Override
        public Object getItem(int position) {
            return mNewsMenu.result.data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ItemAdapter mItemAdapter;
            if (convertView == null) {
                convertView = View.inflate(mActivity, R.layout.news_item, null);
                mItemAdapter = new ItemAdapter();
                mItemAdapter.iv_image = (ImageView) convertView.findViewById(R.id.iv_icon);
                mItemAdapter.tv_title = (TextView) convertView.findViewById(R.id.tv_listtitle);
                mItemAdapter.tv_author = (TextView) convertView.findViewById(R.id.tv_author_name);
                mItemAdapter.tv_Data = (TextView) convertView.findViewById(R.id.tv_date);
                convertView.setTag(mItemAdapter);
            } else {
                mItemAdapter = (ItemAdapter) convertView.getTag();
            }
            NewsMenu.NewsTabData newsData = (NewsMenu.NewsTabData) getItem(position);
            mBitmapUtils.display(mItemAdapter.iv_image, newsData.thumbnail_pic_s);
            mItemAdapter.tv_title.setText(newsData.title);
            mItemAdapter.tv_Data.setText(newsData.date);
            mItemAdapter.tv_author.setText(newsData.author_name);
            return convertView;

        }
    }
    static class ItemAdapter {
        private ImageView iv_image;
        private TextView tv_title;
        private TextView tv_Data;
        private TextView tv_author;
    }

    public void getDataFromServer(final String type) {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("key", GlobalContents.APIKEY);
        params.addQueryStringParameter("type", type);
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, GlobalContents.SERVER_URL, params,
                new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                //返回数据
                String result = responseInfo.result;
                //解析数据
                processJsonData(result);
                System.out.println("解析完json数据");

/*                //设置缓存,使用SharedPreferences缓存
                CacheUtils.setCache(type, result, mActivity);*/
                FileService.saveContentToSdcard(result, type);

                System.out.println("设置缓存");
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(mActivity, "获取数据失败", Toast.LENGTH_SHORT).show();
            }

        });
    }


    class NewsPagerToPicAdapter extends PagerAdapter {

        private BitmapUtils mBitmapUtils;

        public NewsPagerToPicAdapter() {
            mBitmapUtils = new BitmapUtils(mActivity);
            mBitmapUtils.configDefaultLoadingImage(R.mipmap.jiazai);
        }

        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView view = new ImageView(mActivity);
            String imageUrl = mNewsMenu.result.data.get(mNewsMenu.result.data.size() - position-1).thumbnail_pic_s03;

            mBitmapUtils.display(view, imageUrl);
            container.addView(view);
            view.setScaleType(ImageView.ScaleType.FIT_XY);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


    private void mViewPagerToPicListener() {
        mViewPagerToPic.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                NewsMenu.NewsTabData newsTabData = mNewsMenu.result.data.get(mNewsMenu.result.data.size() - position-1);
                mTopPicTie.setText(newsTabData.title);

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
