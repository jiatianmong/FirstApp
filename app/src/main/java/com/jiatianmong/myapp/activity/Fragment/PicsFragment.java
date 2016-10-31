package com.jiatianmong.myapp.activity.Fragment;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.jiatianmong.myapp.R;
import com.jiatianmong.myapp.bean.MyAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiatianmong on 2016-10-10 15:59
 */
public class PicsFragment extends BaseFragment{
    RecyclerView recyclerView;
    List<String> mlist;
    MyAdapter adapter;


    @Override
    public View initView() {

        initpicData();

        View view = View.inflate(mActivity, R.layout.fragment_pics, null);
        recyclerView=(RecyclerView) view.findViewById(R.id.recyclerview_vertical);
        adapter=new MyAdapter(mActivity,mlist);
        //设置动画
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置分割线
        //recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //设置布局
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void initData() {

    }
    private void initpicData() {
        mlist=new ArrayList<String>();
        for(int i=0;i<50;i++){
            mlist.add("number"+i);
        }

    }

}
