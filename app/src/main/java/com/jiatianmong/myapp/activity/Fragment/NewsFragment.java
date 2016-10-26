package com.jiatianmong.myapp.activity.Fragment;

import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jiatianmong.myapp.R;
import com.jiatianmong.myapp.activity.MainActivity;
import com.jiatianmong.myapp.activity.pager.NewsTabPager;
import com.jiatianmong.myapp.bean.NewsMenu;
import com.viewpagerindicator.TabPageIndicator;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by jiatianmong on 2016-10-10 15:59
 */
public class NewsFragment extends BaseFragment {
    private HttpURLConnection conne;
    private ViewPager mViewPager;
    private List mHtnlData;
    private NewsMenu mNewsMenu;
    private TextView mTextView;
    public static ArrayList<String> mNewPagerTitle = new ArrayList<>();
    private ArrayList<NewsTabPager> mNewsTabPagerList;
    private ImageButton mImageButton;
    private TabPageIndicator mIndicator;

    private NewsTabPager mNewsTabPager;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_news, null);
        mViewPager = (ViewPager) view.findViewById(R.id.vp_newstabpager);
        mTextView = (TextView) view.findViewById(R.id.tv_title);
        mIndicator = (TabPageIndicator) view.findViewById(R.id.indicator);
        mImageButton = (ImageButton) view.findViewById(R.id.btn_imagemenu);
        mTextView.setText("News");
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle();
            }
        });
        mTextView.setTextColor(Color.WHITE);
        com.lidroid.xutils.ViewUtils.inject(this, view);

        init_listtitle();

        return view;
    }

    private void init_listtitle() {
        mNewPagerTitle.add("头条");
        mNewPagerTitle.add("社会");
        mNewPagerTitle.add("国内");
        mNewPagerTitle.add("国际");
        mNewPagerTitle.add("娱乐");
        mNewPagerTitle.add("体育");
        mNewPagerTitle.add("军事");
        mNewPagerTitle.add("科技");
        mNewPagerTitle.add("财经");
        mNewPagerTitle.add("时尚");
    }

    @Override
    public void initData() {
        init_PagerAdapte();


    }


    private void init_PagerAdapte() {
        mNewsTabPagerList = new ArrayList<>();
        for (int i = 0; i < mNewPagerTitle.size(); i++) {
            NewsTabPager mBaseNewTab = new NewsTabPager(mActivity, mNewPagerTitle.get(i));
            mNewsTabPagerList.add(mBaseNewTab);
        }
        //虽然ViewPager的预加载，但对ViewPager设置监听，只到当前页才加载数据，会出现内容不对头，加载不出，所以还是预加载
        mViewPager.setAdapter(new NewsTabadapter());

        //在页签内容加载数据完再绑定，将ViewPager和指示器绑定
        mIndicator.setViewPager(mViewPager);

    }




    class NewsTabadapter extends PagerAdapter {


        //4制定指示器对应标签
        @Override
        public CharSequence getPageTitle(int position) {
            String Title = mNewPagerTitle.get(position);
            return Title;
        }

        @Override
        public int getCount() {
            return mNewsTabPagerList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            mNewsTabPager = mNewsTabPagerList.get(position);
            View view = mNewsTabPager.mRootView;
            container.addView(view);
            mNewsTabPager.initData(position);

            //先加载首页
            if (position == 0) {
                mNewsTabPager.initData(0);
            }
            return view;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


    public void toggle() {
        MainActivity mainUI = (MainActivity) mActivity;
        SlidingMenu slidingMenu = mainUI.getSlidingMenu();
        slidingMenu.toggle();// 如果当前状态是开, 调用后就关; 反之亦然
    }


}
