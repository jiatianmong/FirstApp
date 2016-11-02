package com.jiatianmong.myapp.activity.Fragment;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jiatianmong.myapp.R;
import com.jiatianmong.myapp.activity.MainActivity;
import com.jiatianmong.myapp.activity.PicssDetailActivity;
import com.jiatianmong.myapp.bean.EditTextWithDel;
import com.jiatianmong.myapp.bean.FileService;
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
public class PicsFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    RecyclerView recyclerView;
    MyAdapter adapter;
    private PicsMenu mPicsMenu;
    private int mPosion;
    private SwipeRefreshLayout mSwipeLayout;
    private static final int REFRESHPIC_COMPLETE = 0X111;
    private EditTextWithDel mEditFind;
    private ImageButton mBtEnter;
    public static String SERVER_PICSURL = null;
    public static String find_content = "汽车";
    public static String pager = "1";
    public static int sum = 1;
    private ImageButton mLeftImageButton;

    @Override
    public View initView() {

        View view = View.inflate(mActivity, R.layout.fragment_pics, null);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_vertical);

        mLeftImageButton = (ImageButton) view.findViewById(R.id.ib_picmenu);
        mEditFind = (EditTextWithDel) view.findViewById(R.id.ed_findpic);
        mBtEnter = (ImageButton) view.findViewById(R.id.imb_enter);
        mBtEnterLister();
        //设置动画
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置分割线
        //recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //设置布局
        //下拉刷新控件初始化
        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.image_swipe);
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);

        return view;
    }


    private void mBtEnterLister() {
        mBtEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                find_content = mEditFind.getText().toString().trim();
                //地缓存
                String cache = FileService.getFileFromSd(find_content);
                if (!TextUtils.isEmpty(cache)) {
                    //有缓存先加载缓存数据
                    processJsonData(cache);
                }
                SERVER_PICSURL = GlobalContents.SERVER_PICSURL1 + find_content + GlobalContents.SERVER_PICSURL2 + pager + GlobalContents.SERVER_PICSURL3;
                getDataFromServer();
            }
        });
        mLeftImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle();
            }
        });
    }


    @Override
    public void initData() {


        String cache = FileService.getFileFromSd(find_content);
        if (!TextUtils.isEmpty(cache)) {
            //有缓存先加载缓存数据
            processJsonData(cache);

            mSwipeLayout.setRefreshing(true);
            //进入页面先自动刷新，显示转圈
            mSwipeLayout.post(new Runnable() {
                @Override
                public void run() {
                    mSwipeLayout.setRefreshing(true);
                    mHandler.sendEmptyMessageDelayed(REFRESHPIC_COMPLETE, 1000);
                }
            });
        }
        SERVER_PICSURL = GlobalContents.SERVER_PICSURL1 + find_content + GlobalContents.SERVER_PICSURL2 + pager + GlobalContents.SERVER_PICSURL3;
        getDataFromServer();
    }


    public void getDataFromServer() {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, SERVER_PICSURL,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        //返回数据
                        String result = responseInfo.result;
                        //解析数据
                        System.out.println("result"+result);
                        processJsonData(result);
                        FileService.saveContentToSdcard(result,find_content);
                        System.out.println("设置缓存");
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        Toast.makeText(mActivity, "获取数据失败", Toast.LENGTH_SHORT).show();
                    }

                });
    }


    public void toggle() {
        MainActivity mainUI = (MainActivity) mActivity;
        SlidingMenu slidingMenu = mainUI.getSlidingMenu();
        slidingMenu.toggle();// 如果当前状态是开, 调用后就关; 反之亦然
    }

    private void processJsonData(String json) {

        Gson gson = new Gson();
        mPicsMenu = gson.fromJson(json, PicsMenu.class);
        System.out.println("解析结果----->" + mPicsMenu.data);

        adapter = new MyAdapter(mActivity, mPicsMenu);
        mPosion = adapter.getPosion();
        //设置监听
        adapterLister();

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);


    }

    /**
     * 图片列表的点击事件
     */
    private void adapterLister() {
        adapter.setOnItemClickLitener(new MyAdapter.OnItemClickLitener()
        {

            @Override
            public void onItemClick(View view, int position)
            {
//                Toast.makeText(mActivity, position + " click",
//                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mActivity, PicssDetailActivity.class);
                intent.putExtra("picsurl", mPicsMenu.data.get(position).middleURL);
                //intent.putExtra("picsurl", mPicsMenu.data.get(position).replaceUrl.get(1).ObjURL);
                mActivity.startActivity(intent);

            }

            @Override
            public void onItemLongClick(View view, int position)
            {
//                Toast.makeText(mActivity, position + " long click",
//                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case REFRESHPIC_COMPLETE:
                    //从网络获取数据
                    if (sum == 1) {
                        SERVER_PICSURL = GlobalContents.SERVER_PICSURL1 + find_content + GlobalContents.SERVER_PICSURL2 + "1" + GlobalContents.SERVER_PICSURL3;
                    }
                    if (sum == 2) {
                        SERVER_PICSURL = GlobalContents.SERVER_PICSURL1 + find_content + GlobalContents.SERVER_PICSURL2 + "2" + GlobalContents.SERVER_PICSURL3;
                    }
                    if (sum == 3) {
                        SERVER_PICSURL = GlobalContents.SERVER_PICSURL1 + find_content + GlobalContents.SERVER_PICSURL2 + "3" + GlobalContents.SERVER_PICSURL3;
                    }
                    getDataFromServer();
                    mSwipeLayout.setRefreshing(false);
                    break;

            }
        }
    };

    @Override
    public void onRefresh() {
        ++sum;
        if (sum == 3) {
            sum = 1;
        }
        mHandler.sendEmptyMessageDelayed(REFRESHPIC_COMPLETE, 1000);
    }
}
