package com.jiatianmong.myapp.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.jiatianmong.myapp.R;
import com.lidroid.xutils.ViewUtils;

/**
 * 新闻详情页面
 *
 * @author Kevin
 * @date 2015-10-22
 */
public class NewsDetailActivity extends Activity implements OnClickListener {


	private WebView mWebView;
	private ProgressBar mProgressBar;
	private String mUrl;
	private ImageButton btBack;
	private ImageButton btSize;
	private ImageButton btShare;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_news_detail);

		ViewUtils.inject(this);
		mProgressBar = (ProgressBar) findViewById(R.id.pb_Newsdetail);
		btBack = (ImageButton) findViewById(R.id.btn_back);
		btSize = (ImageButton) findViewById(R.id.btn_size);
		btShare = (ImageButton) findViewById(R.id.btn_share);
		btBack.setOnClickListener(this);
		btSize.setOnClickListener(this);
		btShare.setOnClickListener(this);

		mWebView = (WebView) findViewById(R.id.wv_content);
		WebSettings settings = mWebView.getSettings();
		settings.setBuiltInZoomControls(true);// 显示缩放按钮(wap网页不支持)
		settings.setUseWideViewPort(true);// 支持双击缩放(wap网页不支持)
		settings.setJavaScriptEnabled(true);// 支持js功能

		mUrl = getIntent().getStringExtra("url");
		mWebView.loadUrl(mUrl);


		mWebView.setWebViewClient(new WebViewClient() {
			// 开始加载网页
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
				System.out.println("开始加载网页了");
				mProgressBar.setVisibility(View.VISIBLE);
			}

			// 网页加载结束
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				System.out.println("网页加载结束");
				mProgressBar.setVisibility(View.INVISIBLE);
			}

			// 所有链接跳转会走此方法
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				System.out.println("跳转链接:" + url);
				view.loadUrl(url);// 在跳转链接时强制在当前webview中加载
				return true;
			}
		});

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_back:
			//
				finish();
			break;
			case R.id.btn_size:
				// 修改网页字体大小
				showChooseDialog();
			break;
			case R.id.btn_share:
			//

			break;
		}
	}


	private int mTempWhich;// 记录临时选择的字体大小(点击确定之前)

	private int mCurrenWhich = 2;// 记录当前选中的字体大小(点击确定之后), 默认正常字体

	private void showChooseDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("字体设置");

		String[] items = new String[] { "超大号字体", "大号字体", "正常字体", "小号字体",
				"超小号字体" };

		builder.setSingleChoiceItems(items, mCurrenWhich,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						mTempWhich = which;
					}
				});

		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// 根据选择的字体来修改网页字体大小

				WebSettings settings = mWebView.getSettings();

				switch (mTempWhich) {
					case 0:
						// 超大字体
						settings.setTextSize(WebSettings.TextSize.LARGEST);
						// settings.setTextZoom(22);
						break;
					case 1:
						// 大字体
						settings.setTextSize(WebSettings.TextSize.LARGER);
						break;
					case 2:
						// 正常字体
						settings.setTextSize(WebSettings.TextSize.NORMAL);
						break;
					case 3:
						// 小字体
						settings.setTextSize(WebSettings.TextSize.SMALLER);
						break;
					case 4:
						// 超小字体
						settings.setTextSize(WebSettings.TextSize.SMALLEST);
						break;

					default:
						break;
				}

				mCurrenWhich = mTempWhich;
			}
		});

		builder.setNegativeButton("取消", null);

		builder.show();
	}
}