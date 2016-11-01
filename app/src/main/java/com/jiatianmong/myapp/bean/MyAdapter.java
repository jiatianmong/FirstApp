package com.jiatianmong.myapp.bean;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiatianmong.myapp.R;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiatianmong on 2016-10-31 17:28
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context mcontext;
    List<Integer> mheight;
    private PicsMenu mPicsMenu;
    private BitmapUtils mBitmapUtils;
    int posion;


    public MyAdapter(Context context, PicsMenu list) {

        mcontext = context;
        mPicsMenu = list;

        getPosion();
        mBitmapUtils = new BitmapUtils(context);
        mBitmapUtils.configDefaultLoadingImage(R.mipmap.jiazai);

        //随机高度集合
        mheight = new ArrayList<>();
        for (int i = 0; i < mPicsMenu.data.size(); i++) {
            mheight.add((int) (200 + Math.random() * 300));
        }

    }


    @Override
    public int getItemCount() {

        return mPicsMenu.data.size()-1;
    }

    private ImageView mImageView;

    public int getPosion() {
        return posion;
    }

    //找到布局中空间位置
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ImageView iv;

        public MyViewHolder(View arg0) {
            super(arg0);
            tv = (TextView) arg0.findViewById(R.id.id_num);
            mImageView = (ImageView) arg0.findViewById(R.id.item_img);

        }

    }

//设置监听
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }

    private OnItemClickLitener mOnItemClickLitener;


    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
    private BitmapDisplayConfig mConfig;
    //绑定，渲染数据到view中
    @Override
    public void onBindViewHolder(MyViewHolder holder, int arg1) {

        posion = arg1;
        mConfig = new BitmapDisplayConfig();
        //布局参数得看父控件是什么，不然会闪退
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) holder.tv.getLayoutParams();
        lp.height = mheight.get(arg1);
        holder.tv.setLayoutParams(lp);
        holder.tv.setGravity(Gravity.END | Gravity.START | Gravity.BOTTOM);
        holder.tv.setText(mPicsMenu.data.get(arg1).fromPageTitleEnc);
        //图片错乱  不过会造成性能下降。
        holder.setIsRecyclable(false);
        mBitmapUtils.display(mImageView, mPicsMenu.data.get(arg1).middleURL);

    }


    //先执行onCreateViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int arg1) {

        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                mcontext).inflate(R.layout.pics_item, parent,
                false));
        return holder;
    }




}