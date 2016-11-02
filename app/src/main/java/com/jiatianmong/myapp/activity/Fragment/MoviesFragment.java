package com.jiatianmong.myapp.activity.Fragment;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiatianmong.myapp.R;
import com.jiatianmong.myapp.activity.MoviesDetailActivity;

/**
 * Created by jiatianmong on 2016-10-10 15:59
 */
public class MoviesFragment extends BaseFragment{
    private WebView mWebView;
    private GridView gv_home;
    private String[] mTitleStrs;
    private int[] mDrawableIds;
    private String[] mMoviesUrl;
    @Override
    public View initView() {

        View view = View.inflate(mActivity, R.layout.fragment_movies, null);

        gv_home = (GridView) view.findViewById(R.id.gv_home);

    return view;
    }

    @Override
    public void initData() {
    //准备数据(文字(9组),图片(9张))
        mTitleStrs = new String[]{
                "优酷电影","爱奇艺电影","土豆电影","响巢看看电影","暴风极致影院",
                "搜狐视频电影","新浪大片","腾讯视频电影","电影网影院","央视网电影",
                "电影天堂","乐视电影","百度随心看","百度视频电影","风行电影",
                "PPTV电影","哔哩哔哩","嘀哩嘀哩"
        };
        mDrawableIds = new int[]{
                R.mipmap.youku,R.mipmap.aiqiyi,
                R.mipmap.tudou,R.mipmap.xiangchao,
                R.mipmap.baofeng,R.mipmap.souhu,
                R.mipmap.xinlang,R.mipmap.tengxun,
                R.mipmap.dianyyingwang,
                R.mipmap.yingshi,R.mipmap.diainyigntiantang,
                R.mipmap.leshi,R.mipmap.baidu,
                R.mipmap.baiduteo,R.mipmap.fegnixng,
                R.mipmap.pptv,R.mipmap.bilibili,R.mipmap.dilidili
        };

        mMoviesUrl = new String[]{
                "http://movie.youku.com/","http://www.iqiyi.com/dianying/","http://movie.tudou.com/","http://movie.kankan.com/","http://hd.baofeng.com/",
                "http://tv.sohu.com/movie/","http://ent.sina.com.cn/video/","http://v.qq.com/movie/index.html","http://www.1905.com/vod/","http://www.1905.com/vod/",
                "http://www.dytt8.net/","http://movie.le.com/","http://suixinkan.baidu.com/","http://v.baidu.com/movie","http://www.fun.tv/movie/",
                "http://movie.pptv.com/","http://www.bilibili.com/","http://www.dilidili.com/"
        };

        //九宫格控件设置数据适配器(等同ListView数据适配器)
        gv_home.setAdapter(new MyAdapter());
        //注册九宫格单个条目点击事件
        gv_home.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //点中列表条目索引position
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mActivity, MoviesDetailActivity.class);
                switch (position) {
                    case 0:
                        intent.putExtra("movies_url", mMoviesUrl[position]);
                        mActivity.startActivity(intent);
                        break;
                    case 1:
                        intent.putExtra("movies_url", mMoviesUrl[position]);
                        mActivity.startActivity(intent);
                        break;
                    case 2:
                        intent.putExtra("movies_url", mMoviesUrl[position]);
                        mActivity.startActivity(intent);
                        break;
                    case 3:
                        intent.putExtra("movies_url", mMoviesUrl[position]);
                        mActivity.startActivity(intent);
                        break;
                    case 4:
                        intent.putExtra("movies_url", mMoviesUrl[position]);
                        mActivity.startActivity(intent);
                        break;
                    case 5:
                        intent.putExtra("movies_url", mMoviesUrl[position]);
                        mActivity.startActivity(intent);
                        break;
                    case 6:
                        intent.putExtra("movies_url", mMoviesUrl[position]);
                        mActivity.startActivity(intent);
                        break;
                    case 7:
                        intent.putExtra("movies_url", mMoviesUrl[position]);
                        mActivity.startActivity(intent);
                        break;
                    case 8:
                        intent.putExtra("movies_url", mMoviesUrl[position]);
                        mActivity.startActivity(intent);
                        break;
                    case 9:
                        intent.putExtra("movies_url", mMoviesUrl[position]);
                        mActivity.startActivity(intent);
                        break;
                    case 10:
                        intent.putExtra("movies_url", mMoviesUrl[position]);
                        mActivity.startActivity(intent);
                        break;
                    case 11:
                        intent.putExtra("movies_url", mMoviesUrl[position]);
                        mActivity.startActivity(intent);
                        break;
                    case 12:
                        intent.putExtra("movies_url", mMoviesUrl[position]);
                        mActivity.startActivity(intent);
                        break;
                    case 13:
                        intent.putExtra("movies_url", mMoviesUrl[position]);
                        mActivity.startActivity(intent);
                        break;
                    case 14:
                        intent.putExtra("movies_url", mMoviesUrl[position]);
                        mActivity.startActivity(intent);
                        break;
                    case 15:
                        intent.putExtra("movies_url", mMoviesUrl[position]);
                        mActivity.startActivity(intent);
                        break;
                    case 16:
                        intent.putExtra("movies_url", mMoviesUrl[position]);
                        mActivity.startActivity(intent);
                        break;
                    case 17:
                        intent.putExtra("movies_url", mMoviesUrl[position]);
                        mActivity.startActivity(intent);
                        break;
                }
            }
        });
    }
    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            //条目的总数	文字组数 == 图片张数
            return mTitleStrs.length;
        }
        @Override
        public Object getItem(int position) {
            return mTitleStrs[position];
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(mActivity, R.layout.gridview_item, null);
            TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
            ImageView iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
            tv_title.setText(mTitleStrs[position]);
            iv_icon.setBackgroundResource(mDrawableIds[position]);
            return view;
        }
    }

}
