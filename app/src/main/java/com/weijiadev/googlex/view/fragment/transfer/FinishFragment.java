package com.weijiadev.googlex.view.fragment.transfer;


import com.weijiadev.googlex.R;
import com.weijiadev.googlex.view.fragment.base.BaseFragment;

/**
 * 已完成
 */

public class FinishFragment extends BaseFragment {

//    @BindView(R.id.home_viewpager_tab)
//    TabLayout mViewpagerTab;
//    @BindView(R.id.home_viewpager)
//    ViewPager mNewsViewpager;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_transfer_finish;
    }

    @Override
    protected void initViews() {

//        //fragment列表  
//        List<Fragment> list_fragment = new ArrayList<>();
//        //tab名的列表
//        List<String> list_Title = new ArrayList<>();
//
//        list_fragment.add(new LpAllFragment());
//        list_fragment.add(new LpDclFragment());
//        list_fragment.add(new LpClzFragment());
//
//        list_Title.add("理赔全部");
//        list_Title.add("理赔待处理");
//        list_Title.add("理赔处理中");
//
//
//        //设置名称
//        for (int i = 0; i < list_Title.size(); i++) {
//            mViewpagerTab.addTab(mViewpagerTab.newTab().setText(list_Title.get(i)));
//        }
//        TabFragmentPagerAdapter adapter = new TabFragmentPagerAdapter(
//                getActivity().getSupportFragmentManager(), list_fragment, list_Title
//        );
//        //viewpager 加载adapter
//        mNewsViewpager.setAdapter(adapter);
//        //TableLayout加载viewpager
//        mViewpagerTab.setupWithViewPager(mNewsViewpager);

    }

}
