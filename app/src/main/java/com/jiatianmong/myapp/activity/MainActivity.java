package com.jiatianmong.myapp.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.jiatianmong.myapp.R;
import com.jiatianmong.myapp.activity.Fragment.LeftMenuFragment;
import com.jiatianmong.myapp.activity.Fragment.MoviesFragment;
import com.jiatianmong.myapp.activity.Fragment.MusicsFragment;
import com.jiatianmong.myapp.activity.Fragment.NewsFragment;
import com.jiatianmong.myapp.activity.Fragment.PicsFragment;

public class MainActivity extends SlidingFragmentActivity {

    private static final String TAG_NEW = "TAG_NEW";
    private static final String TAG_MUSIC = "TAG_MUSIC";
    private static final String TAG_MOVIE = "TAG_MOVIE";
    private static final String TAG_PIC = "TAG_PIC";
    private static final String TAG_LEFT_MENU = "TAG_LEFT_MENU";
    private FragmentTransaction mFragmentTransaction;
    FragmentTransaction view;
    private FragmentManager mSupportFragmentManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除掉当前activity头title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        setBehindContentView(R.layout.left_menu);
        SlidingMenu slidingMenu = getSlidingMenu();
        slidingMenu.setMode(SlidingMenu.LEFT);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);// 边缘触摸
        slidingMenu.setBehindOffset(200);// 屏幕预留500像素宽度
        slidingMenu.setFadeDegree(0.35f);// 设置渐入渐出效果的值

        init_fragment();
    }

    private void init_fragment() {
        mSupportFragmentManager = getSupportFragmentManager();
        //开启事务
        mFragmentTransaction = mSupportFragmentManager.beginTransaction();


        // 用fragment替换帧布局;参1:帧布局容器的id;参2:是要替换的fragment;参3:标记

        mFragmentTransaction.replace(R.id.fl_left_menu, new LeftMenuFragment(), TAG_LEFT_MENU);


        mFragmentTransaction.replace(R.id.fl_main, new NewsFragment(), TAG_NEW);


        mFragmentTransaction.commit();

    }

    public void setFragment(int checkedId){


        switch (checkedId){
            case R.id.rb_news:

                mFragmentTransaction = mSupportFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.fl_main, new NewsFragment(), TAG_NEW);
                mFragmentTransaction.commit();
                break;
            case R.id.rb_musics:

                mFragmentTransaction = mSupportFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.fl_main, new MusicsFragment(), TAG_MUSIC);
                mFragmentTransaction.commit();
                break;
            case R.id.rb_movies:

                mFragmentTransaction = mSupportFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.fl_main, new MoviesFragment(), TAG_MOVIE);
                mFragmentTransaction.commit();
                break;
            case R.id.rb_pics:

                mFragmentTransaction = mSupportFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.fl_main, new PicsFragment(), TAG_PIC);
                mFragmentTransaction.commit();
                break;
        }

    }







}
