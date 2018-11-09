package com.weijiadev.googlex.view.fragment.task;


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
 * Created by loptop on 2017/4/8.
 */

public class HeBaoFragment extends BaseFragment {

    @BindView(R.id.hebap_viewpager_tab)
    TabLayout mViewpagerTab;
    @BindView(R.id.hebao_viewpager)
    ViewPager mNewsViewpager;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_task_hebao;
    }

    @Override
    protected void initViews() {

        //fragment列表  
        List<Fragment> list_fragment = new ArrayList<>();
        //tab名的列表
        List<String> list_Title = new ArrayList<>();

        list_fragment.add(new HbAllFragment());
        list_fragment.add(new HbDclFragment());
        list_fragment.add(new HbClzFragment());

        list_Title.add("核保全部");
        list_Title.add("核保待处理");
        list_Title.add("核保处理中");


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
