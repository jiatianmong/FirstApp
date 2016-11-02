package com.jiatianmong.myapp.activity.Fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.jiatianmong.myapp.R;
import com.jiatianmong.myapp.activity.MusicsDetailActivity;

/**
 * Created by jiatianmong on 2016-10-10 15:59
 */
public class MusicsFragment extends BaseFragment{

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_musics, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.image_music);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, MusicsDetailActivity.class);
                mActivity.startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void initData() {

    }


}
