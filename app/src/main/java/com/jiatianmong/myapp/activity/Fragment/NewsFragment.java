package com.jiatianmong.myapp.activity.Fragment;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.google.gson.Gson;
import com.jiatianmong.myapp.R;
import com.jiatianmong.myapp.bean.NewsMenu;
import com.jiatianmong.myapp.global.GlobalContents;
import com.jiatianmong.myapp.utils.CacheUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.net.HttpURLConnection;
import java.util.List;


/**
 * Created by jiatianmong on 2016-10-10 15:59
 */
public class NewsFragment extends BaseFragment{
    private HttpURLConnection conne;
    private ViewPager mViewPager;
    private List mHtnlData;
    private NewsMenu mNewsMenu;



    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_news, null);
        mViewPager = (ViewPager) view.findViewById(R.id.vp_newstabpager);
        com.lidroid.xutils.ViewUtils.inject(this, view);
        System.out.println("lala");



        return view;
    }

    @Override
    public void initData() {
        System.out.println("链接网路");
        getDataFromServer();

    }


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
                CacheUtils.setCache(GlobalContents.CATEGORY_URL,result,mActivity);
                System.out.println("设置缓存");
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });

    }



//    class NewsTabadapter extends PagerAdapter {
//
//        //4制定指示器对应标签
//        @Override
//        public CharSequence getPageTitle(int position) {
//            NewsMenu.NewsTabData newsTabData = mTabdatas.get(position);
//            return newsTabData.title;
//        }
//
//        @Override
//        public int getCount() {
//            return mBaseNewTabs.size();
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return view == object;
//        }
//
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            NewTab pager = mBaseNewTabs.get(position);
//            View view = pager.mRootView;
//            container.addView(view);
//            pager.initData();
//            return view;
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView((View) object);
//        }
//    }

    private void processJsonData(String json) {

        Gson gson = new Gson();
        mNewsMenu = gson.fromJson(json, NewsMenu.class);
        System.out.println("解析结果"+mNewsMenu);

    }

}
