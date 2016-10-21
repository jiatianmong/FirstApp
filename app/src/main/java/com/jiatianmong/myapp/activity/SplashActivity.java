package com.jiatianmong.myapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.jiatianmong.myapp.R;
import com.jiatianmong.myapp.utils.ShaPreUtils;

/**
 * Created by jiatianmong on 2016-10-20 16:55
 */
public class SplashActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题

        setContentView(R.layout.activity_splash);

        RelativeLayout rl_root = (RelativeLayout) findViewById(R.id.rl_root);
        //闪屏页面初始化函数
        init_splash(rl_root);

    }

    private void init_splash(RelativeLayout rl_root) {

        /**
         fromDegrees：旋转的开始角度。

         toDegrees：旋转的结束角度。

         pivotXType：X轴的伸缩模式，可以取值为ABSOLUTE、RELATIVE_TO_SELF、RELATIVE_TO_PARENT。

         pivotXValue：X坐标的伸缩值。

         pivotYType：Y轴的伸缩模式，可以取值为ABSOLUTE、RELATIVE_TO_SELF、RELATIVE_TO_PARENT。

         pivotYValue：Y坐标的伸缩值。
         */
        //旋转动画 （以下是参数类型）
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(1000);  //动画时间
        rotateAnimation.setFillAfter(true); //保持动画结束状态

        //缩放动画
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(1000);  //动画时间
        scaleAnimation.setFillAfter(true); //保持动画结束状态

        //渐变动画   参数从无到有（0到1）
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(1000);  //动画时间
        alphaAnimation.setFillAfter(true); //保持动画结束状态

        //动画集合
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);

        rl_root.setAnimation(animationSet);

        animationSet.setAnimationListener(new Animation.AnimationListener() {

            private boolean enter_splash_flag;
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //闪屏页面动画结束，在此跳转页面
                //如果是第一次进入，让其跳转新手引导界面，否则跳转主页面
                Intent mIntent;
                enter_splash_flag = ShaPreUtils.getBoolean(getApplicationContext(), "enter_splash_flag", true);

                if (enter_splash_flag) {
                    //新手引导界面
                    mIntent = new Intent(getApplicationContext(), GuideActivity.class);
                    System.out.println("进入闪屏页面");

                } else {
                    //主页面
                    mIntent = new Intent(getApplicationContext(), MainActivity.class);
                    System.out.println("进入主界面");
                }
                startActivity(mIntent);
                finish();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }






}
