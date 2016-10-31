package com.jiatianmong.myapp.bean;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.jiatianmong.myapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiatianmong on 2016-10-31 17:28
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context mcontext;
    List<String> mlist;
    List<Integer> mheight;
    public MyAdapter(Context context, List<String> list) {

        mcontext=context;
        mlist=list;

        //随机高度集合
        mheight= new ArrayList<>();
        for(int i=0;i<mlist.size();i++){
            mheight.add((int)(100+Math.random()*300));
        }

    }




    @Override
    public int getItemCount() {

        return mlist.size();
    }


    //找到布局中空间位置
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public MyViewHolder(View arg0) {
            super(arg0);

            tv=(TextView) arg0.findViewById(R.id.id_num);
        }

    }


    //绑定，渲染数据到view中
    @Override
    public void onBindViewHolder(MyViewHolder holder, int arg1) {

        //布局参数得看父控件是什么，不然会闪退
        FrameLayout.LayoutParams lp= (FrameLayout.LayoutParams) holder.tv.getLayoutParams();
        lp.height=mheight.get(arg1);
        holder.tv.setLayoutParams(lp);
        holder.tv.setGravity(Gravity.END|Gravity.START|Gravity.BOTTOM);
        holder.tv.setText(mlist.get(arg1));



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