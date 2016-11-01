package com.jiatianmong.myapp.activity.Fragment;

import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jiatianmong.myapp.R;

/**
 * Created by jiatianmong on 2016-10-10 15:59
 */
public class MoviesFragment extends BaseFragment{
    private WebView mWebView;
    @Override
    public View initView() {

        View view = View.inflate(mActivity, R.layout.fragment_movies, null);

        mWebView = (WebView) view.findViewById(R.id.wv_movies);
        WebSettings settings = mWebView.getSettings();
        settings.setBuiltInZoomControls(true);// 显示缩放按钮(wap网页不支持)
        settings.setUseWideViewPort(true);// 支持双击缩放(wap网页不支持)
        settings.setJavaScriptEnabled(true);// 支持js功能
        String mUrl = "http://www.dilidili.com/";
        mWebView.loadUrl(mUrl);



        mWebView.setWebViewClient(new WebViewClient() {
            // 开始加载网页
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                System.out.println("开始加载网页了");
            }

            // 网页加载结束
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                System.out.println("网页加载结束");
            }

            // 所有链接跳转会走此方法
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                System.out.println("跳转链接:" + url);
                view.loadUrl(url);// 在跳转链接时强制在当前webview中加载
                return true;
            }
        });

    return view;
    }

    @Override
    public void initData() {

    }


}
