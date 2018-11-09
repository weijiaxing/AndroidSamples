package com.weijiadev.googlex.view.fragment.act;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.weijiadev.googlex.R;
import com.weijiadev.googlex.view.adapter.TabFragmentPagerAdapter;
import com.weijiadev.googlex.view.fragment.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 今日
 */

public class TodayFragment extends BaseFragment {

    @BindView(R.id.tl_act_today)
    TabLayout mViewpagerTab;
    @BindView(R.id.vp_today)
    ViewPager mNewsViewpager;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_act_today;
    }

    @Override
    protected void initViews() {

        //fragment列表  
        List<Fragment> list_fragment = new ArrayList<>();
        //tab名的列表
        List<String> list_Title = new ArrayList<>();

        list_fragment.add(new ActTodayAllFragment());
        list_fragment.add(new ActTodayLpFragment());
        list_fragment.add(new ActTodayHbFragment());

        list_Title.add("全部");
        list_Title.add("理赔");
        list_Title.add("核保");


        //设置名称
        for (int i = 0; i < list_Title.size(); i++) {
            mViewpagerTab.addTab(mViewpagerTab.newTab().setText(list_Title.get(i)));
        }
        TabFragmentPagerAdapter adapter = new TabFragmentPagerAdapter(
                getActivity().getSupportFragmentManager(), list_fragment, list_Title
        );
        //viewpager 加载adapter
        mNewsViewpager.setAdapter(adapter);
        //TableLayout加载viewpager
        mViewpagerTab.setupWithViewPager(mNewsViewpager);

    }

}
