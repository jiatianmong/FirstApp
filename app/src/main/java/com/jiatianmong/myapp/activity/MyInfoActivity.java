package com.jiatianmong.myapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jiatianmong.myapp.R;
import com.lidroid.xutils.ViewUtils;


/**
 * 新闻详情页面
 *
 * @author Kevin
 * @date 2015-10-22
 */
public class MyInfoActivity extends Activity  {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.pager_myinfo);

        ViewUtils.inject(this);
        ImageButton iBClose = (ImageButton) findViewById(R.id.btn_myinfoslose);


        TextView appurl = (TextView) findViewById(R.id.appurl);
        TextView emaile = (TextView) findViewById(R.id.email);
        TextView author = (TextView) findViewById(R.id.author_name);




        iBClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });





    }




}
