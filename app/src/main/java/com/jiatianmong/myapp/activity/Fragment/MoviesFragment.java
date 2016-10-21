package com.jiatianmong.myapp.activity.Fragment;

import android.view.View;

import com.jiatianmong.myapp.R;

/**
 * Created by jiatianmong on 2016-10-10 15:59
 */
public class MoviesFragment extends BaseFragment{

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_movies, null);
        return view;
    }

    @Override
    public void initData() {

    }


}
