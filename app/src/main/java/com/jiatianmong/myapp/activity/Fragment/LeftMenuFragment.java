package com.jiatianmong.myapp.activity.Fragment;

import android.view.View;
import android.widget.RadioGroup;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jiatianmong.myapp.R;
import com.jiatianmong.myapp.activity.MainActivity;

/**
 * Created by jiatianmong on 2016-10-10 15:47
 */
public class LeftMenuFragment extends BaseFragment {


    private RadioGroup mRadioGroup;
    private int mSelectMenu;
    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_left_menu, null);
        mRadioGroup = (RadioGroup) view.findViewById(R.id.rb_group);
        mRadioGroupListener();
        return view;
    }

    private void mRadioGroupListener() {
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mSelectMenu = checkedId;
                showFragment(checkedId);
                toggle();
            }
        });
    }

    @Override
    public void initData() {

    }

    private void showFragment(int checkedId){
        MainActivity activity = (MainActivity) mActivity;
        activity.setFragment(checkedId);
    }

    /**
     * 打开或者关闭侧边栏
     */
    protected void toggle() {
        MainActivity mainUI = (MainActivity) mActivity;
        SlidingMenu slidingMenu = mainUI.getSlidingMenu();
        slidingMenu.toggle();// 如果当前状态是开, 调用后就关; 反之亦然
    }

}
