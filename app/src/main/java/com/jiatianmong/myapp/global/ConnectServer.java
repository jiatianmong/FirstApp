package com.jiatianmong.myapp.global;

import com.google.gson.Gson;
import com.jiatianmong.myapp.activity.MainActivity;
import com.jiatianmong.myapp.bean.NewsMenu;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;

/**
 * Created by jiatianmong on 2016-10-24 21:28
 */
public class ConnectServer {
    private NewsMenu mNewsMenu;
    public MainActivity mActivity;

    /*
     从服务器获取json数据
      */
    public void getDataFromServer(String type) {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("key", GlobalContents.APIKEY);
        params.addQueryStringParameter("type",type );
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
                //CacheUtils.setCache(GlobalContents.CATEGORY_URL, result, mActivity);
                //System.out.println("设置缓存");
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });

    }
    private ArrayList<NewsMenu.NewsTabData> processJsonData(String json) {

        Gson gson = new Gson();
        mNewsMenu = gson.fromJson(json, NewsMenu.class);
        System.out.println("解析结果" + mNewsMenu.result.data);

        return mNewsMenu.result.data;
    }
}
