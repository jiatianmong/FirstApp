package com.jiatianmong.myapp.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jiatianmong.myapp.R;
import com.jiatianmong.myapp.utils.ShaPreUtils;

import java.util.ArrayList;

/**
 * Created by jiatianmong on 2016-10-20 19:07
 */
public class GuideActivity extends Activity{

    private int[] mViewPagerId = new int[]{R.mipmap.splash_new, R.mipmap.splash_music, R.mipmap.splash_movie, R.mipmap.splash_start};
    private ViewPager mViewPager;
    private LinearLayout llContainer;
    private int mPointDis;
    private ImageView ivRedPoint;// 小红点
    private Button btnStart;
    private ArrayList<ImageView> mImageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        //1首先找到控件
        mViewPager = (ViewPager) findViewById(R.id.vp_guide);
        llContainer = (LinearLayout) findViewById(R.id.ll_container);
        ivRedPoint = (ImageView) findViewById(R.id.iv_red_point);
        btnStart = (Button) findViewById(R.id.bt_start);
        btnStart.setVisibility(View.INVISIBLE);

        //2初始化ViewPager
        init_viewpager();


        //4适配器
        mViewPager.setAdapter(new GuideAdapter());

        // 监听layout方法结束的事件,位置确定好之后再获取圆点间距
        count_point_dis();

        //对mViewPager和btnStart设置监听
        mViewPager_btnStart_Listener();

    }

    private void mViewPager_btnStart_Listener() {

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // 当页面滑动过程中的回调
                // System.out.println("当前位置:" + position + ";移动偏移百分比:"+ positionOffset);
                // 更新小红点距离
                int leftMargin = (int) (mPointDis * positionOffset) + position
                        * mPointDis;// 计算小红点当前的左边距
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ivRedPoint
                        .getLayoutParams();
                params.leftMargin = leftMargin;// 修改左边距

                // 重新设置布局参数
                ivRedPoint.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
                // 某个页面被选中
                if (position == mImageViews.size() - 1) {// 最后一个页面显示开始体验的按钮
                    btnStart.setVisibility(View.VISIBLE);
                } else {
                    btnStart.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // 页面状态发生变化的回调
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //更新sp, 已经不是第一次进入了
                ShaPreUtils.setBoolean(getApplicationContext(), "first_enter_flag", false);
                //跳到主页面
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
    }

    private void count_point_dis() {
        // 计算两个圆点的距离
        // 移动距离=第二个圆点left值 - 第一个圆点left值
        // measure->layout(确定位置)->draw(activity的onCreate方法执行结束之后才会走此流程)
        // mPointDis = llContainer.getChildAt(1).getLeft()- llContainer.getChildAt(0).getLeft();
        // System.out.println("圆点距离:" + mPointDis);

        // 监听layout方法结束的事件,位置确定好之后再获取圆点间距
        // 视图树
        ivRedPoint.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onGlobalLayout() {
                        // 移除监听,避免重复回调
                         ivRedPoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        // layout方法执行结束的回调
                        mPointDis = llContainer.getChildAt(1).getLeft() - llContainer.getChildAt(0).getLeft();
                        System.out.println("圆点距离:" + mPointDis);
                    }
                });
    }



    /**
     * 初始化控件，声明一个数组存放三张图片的id，并设置为ImageView的背景
     * 然后放到一个集合当中，方便适配器中取view
     * =顺便初始化滑动的小灰点
     */
    private void init_viewpager() {
        this.mImageViews = new ArrayList<>();
        for (int i = 0; i < mViewPagerId.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(mViewPagerId[i]);
            mImageViews.add(imageView);

            //初始化小圆点
            ImageView poitView = new ImageView(this);
            poitView.setImageResource(R.drawable.shape_point_gray);//设置shape形状
            // 初始化布局参数, 宽高包裹内容,父控件是谁,就是谁声明的布局参数
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            if (i > 0) {
                // 从第二个点开始设置左边距
                params.leftMargin = 10;
            }
            poitView.setLayoutParams(params);// 设置布局参数
            llContainer.addView(poitView);//给容器添加圆点


        }

    }
    /**
     * 页面适配器
     */
    class GuideAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mImageViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        /**
         * @param container 容器
         * @param position
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView view = mImageViews.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
