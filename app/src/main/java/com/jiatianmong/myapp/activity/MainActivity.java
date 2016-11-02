package com.jiatianmong.myapp.activity;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.view.WindowManager;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.jiatianmong.myapp.R;
import com.jiatianmong.myapp.activity.Fragment.LeftMenuFragment;
import com.jiatianmong.myapp.activity.Fragment.MoviesFragment;
import com.jiatianmong.myapp.activity.Fragment.MusicsFragment;
import com.jiatianmong.myapp.activity.Fragment.NewsFragment;
import com.jiatianmong.myapp.activity.Fragment.PicsFragment;

public class MainActivity extends SlidingFragmentActivity {
    private SlidingMenu.CanvasTransformer mTransformer;
    private static final String TAG_NEW = "TAG_NEW";
    private static final String TAG_MUSIC = "TAG_MUSIC";
    private static final String TAG_MOVIE = "TAG_MOVIE";
    private static final String TAG_PIC = "TAG_PIC";
    private static final String TAG_LEFT_MENU = "TAG_LEFT_MENU";

    private FragmentTransaction mFragmentTransaction;
    FragmentTransaction view;
    private FragmentManager mSupportFragmentManager;
    private NewsFragment mNewsFragment;
    private MusicsFragment mMusicsFragment;
    private MoviesFragment mMoviesFragment;
    private PicsFragment mPicsFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除掉当前activity头title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        initAnimation();

        initSlidingMenu();

        init_fragment();


    }

    //伸缩模式
    private void initAnimation() {
        mTransformer = new SlidingMenu.CanvasTransformer(){
            @Override
            public void transformCanvas(Canvas canvas, float percentOpen) {
                canvas.scale(percentOpen, 1, 0, 0);
            }

        };
    }

    private void initSlidingMenu() {
        setContentView(R.layout.activity_main);
        setBehindContentView(R.layout.left_menu);
        SlidingMenu slidingMenu = getSlidingMenu();
        // 设置是否淡入淡出
        slidingMenu.setFadeEnabled(true);
        // 设置渐入渐出效果的值
        slidingMenu.setFadeDegree(0.35f);
        // 设置滑动方向
        slidingMenu.setMode(SlidingMenu.LEFT);
        // 全屏：TOUCHMODE_FULLSCREEN ；边缘：TOUCHMODE_MARGIN ；不打开：TOUCHMODE_NONE
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);// 边缘触摸
        //获取屏幕宽度
        WindowManager wm1 = this.getWindowManager();
        int width1 = wm1.getDefaultDisplay().getWidth();

        slidingMenu.setBehindOffset(width1/2);// 屏幕预留1/2像素宽度

        slidingMenu.setBehindScrollScale(0.0f);
        slidingMenu.setBehindCanvasTransformer(mTransformer);
    }
    private void init_fragment() {
        mSupportFragmentManager = getSupportFragmentManager();
        //开启事务
        mFragmentTransaction = mSupportFragmentManager.beginTransaction();


        // 用fragment替换帧布局;参1:帧布局容器的id;参2:是要替换的fragment;参3:标记
        mFragmentTransaction.replace(R.id.fl_left_menu, new LeftMenuFragment(), TAG_LEFT_MENU);



        if (mPicsFragment == null) {
            mPicsFragment = new PicsFragment();
        }
        mFragmentTransaction.add(R.id.fl_main, mPicsFragment, TAG_PIC);

        if (mMusicsFragment == null) {
            mMusicsFragment = new MusicsFragment();
        }
        mFragmentTransaction.add(R.id.fl_main, mMusicsFragment, TAG_MUSIC);



        if (mNewsFragment == null) {
            mNewsFragment = new NewsFragment();
        }
        mFragmentTransaction.add(R.id.fl_main, mNewsFragment, TAG_NEW);

        if (mMoviesFragment == null) {
            mMoviesFragment = new MoviesFragment();
        }
        mFragmentTransaction.add(R.id.fl_main, mMoviesFragment, TAG_MOVIE);

        mFragmentTransaction.commit();

    }

    public void setFragment(int checkedId){


        mFragmentTransaction = mSupportFragmentManager.beginTransaction();
        hideFragments(mFragmentTransaction);
        switch (checkedId){
            case R.id.rb_news:

                if (mNewsFragment == null) {
                    mNewsFragment = new NewsFragment();
                    mFragmentTransaction.add(R.id.fl_main, mNewsFragment,TAG_NEW);
                } else {
                    mFragmentTransaction.show(mNewsFragment);
                }
                break;
            case R.id.rb_musics:

                if (mMusicsFragment == null) {
                    mMusicsFragment = new MusicsFragment();
                    mFragmentTransaction.add(R.id.fl_main, mMusicsFragment,TAG_MUSIC);
                } else {
                    mFragmentTransaction.show(mMusicsFragment);
                }
                break;
            case R.id.rb_movies:

                if (mMoviesFragment == null) {
                    mMoviesFragment = new MoviesFragment();
                    mFragmentTransaction.add(R.id.fl_main, mMoviesFragment,TAG_MOVIE);
                } else {
                    mFragmentTransaction.show(mMoviesFragment);
                }
                break;
            case R.id.rb_pics:

                if (mPicsFragment == null) {
                    mPicsFragment = new PicsFragment();
                    mFragmentTransaction.add(R.id.fl_main, mPicsFragment,TAG_PIC);
                } else {
                    mFragmentTransaction.show(mPicsFragment);
                }
                break;
        }
        mFragmentTransaction.commitAllowingStateLoss();

    }


    private void hideFragments(FragmentTransaction transaction) {
        if (mNewsFragment!=null){
            transaction.hide(mNewsFragment);
        }
        if (mMusicsFragment!=null){
            transaction.hide(mMusicsFragment);
        }
        if (mMoviesFragment!=null){
            transaction.hide(mMoviesFragment);
        }
        if (mPicsFragment!=null){
            transaction.hide(mPicsFragment);
        }
    }



}
