package com.jiatianmong.myapp.activity.Fragment;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jiatianmong.myapp.R;
import com.jiatianmong.myapp.bean.MyAdapter;
import com.jiatianmong.myapp.bean.PicsMenu;
import com.jiatianmong.myapp.global.GlobalContents;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * Created by jiatianmong on 2016-10-10 15:59
 */
public class PicsFragment extends BaseFragment{
    RecyclerView recyclerView;
    MyAdapter adapter;
    private PicsMenu mPicsMenu;
    private int mPosion;

    @Override
    public View initView() {

        View view = View.inflate(mActivity, R.layout.fragment_pics, null);
        recyclerView=(RecyclerView) view.findViewById(R.id.recyclerview_vertical);

        //设置动画
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置分割线
        //recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //设置布局



        return view;
    }

    @Override
    public void initData() {


//        String cache = FileService.getFileFromSd(mPicsMenu.data.get(mPosion).middleURL);
//        if (!TextUtils.isEmpty(cache)) {
//            //有缓存先加载缓存数据
//            processJsonData(cache);
//        }
        getDataFromServer();


    }


    public void getDataFromServer() {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, GlobalContents.SERVER_PICSURL,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        //返回数据
                        String result = responseInfo.result;
                        //解析数据
                        //System.out.println("result"+result);
                        processJsonData(result);
                        //FileService.saveContentToSdcard(mPicsMenu.data.toString(), mPicsMenu.data.get(mPosion).middleURL);
                        //System.out.println("设置缓存");
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        Toast.makeText(mActivity, "获取数据失败", Toast.LENGTH_SHORT).show();
                    }

                });
    }


    private void processJsonData(String json) {

        Gson gson = new Gson();
        mPicsMenu = gson.fromJson(json, PicsMenu.class);
        System.out.println("解析结果----->" + mPicsMenu.data);

        adapter=new MyAdapter(mActivity,mPicsMenu);
        mPosion = adapter.getPosion();


        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);


    }




}
