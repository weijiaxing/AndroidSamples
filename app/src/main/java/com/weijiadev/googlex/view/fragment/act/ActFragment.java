package com.weijiadev.googlex.view.fragment.act;


import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.weijiadev.googlex.R;
import com.weijiadev.googlex.view.fragment.base.BaseFragment;

import butterknife.BindView;


public class ActFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.toolbar_common3)
    Toolbar mToolbarCommon3;
    @BindView(R.id.toolbar_common)
    Toolbar mToolbarCommon;
    @BindView(R.id.bt_jr)
    Button mBtJr;
    @BindView(R.id.bt_ygq)
    Button mBtYgq;
    @BindView(R.id.bt_wl)
    Button mBtWl;

    @BindView(R.id.tv_centre)
    TextView mTvCenter;

    private TodayFragment todayFragment;
    private OverdueFragment overdueFragment;
    private FutureFragment futureFragment;

    public static final int TODAY_FRAGMENT_TYPE = 1;
    public static final int OVERDUE_FRAGMENT_TYPE = 2;
    public static final int FUTURE_FRAGMENT_TYPE = 3;
    public int currentFragmentType = -1;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_main_act;
    }

    @Override
    protected void initViews() {

        mTvCenter.setText("处理");
        mToolbarCommon3.setTitle(R.string.title_name);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbarCommon3);
        mToolbarCommon.setBackgroundResource(R.color.tab);

        mBtJr.setOnClickListener(this);
        mBtYgq.setOnClickListener(this);
        mBtWl.setOnClickListener(this);
        initFragment();

    }

    private void initFragment() {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment mainFragment = fragmentManager.findFragmentByTag("message");
        if (mainFragment != null) {
            transaction.replace(R.id.fl_content3, mainFragment);
            transaction.commit();
        } else {
            loadFragment(TODAY_FRAGMENT_TYPE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_jr:
                mBtJr.setTextColor(getResources().getColor(R.color.tab));
                mBtYgq.setTextColor(Color.BLACK);
                mBtWl.setTextColor(Color.BLACK);
                mBtJr.setBackgroundResource(R.drawable.btn_left_select);
                mBtYgq.setBackgroundResource(R.drawable.btn_right_disselect);
                mBtWl.setBackgroundResource(R.drawable.btn_right_disselect);

                switchFragment(TODAY_FRAGMENT_TYPE);
                break;

            case R.id.bt_ygq:
                mBtJr.setTextColor(Color.BLACK);
                mBtYgq.setTextColor(getResources().getColor(R.color.tab));
                mBtWl.setTextColor(Color.BLACK);
                mBtJr.setBackgroundResource(R.drawable.btn_left_disselect);
                mBtWl.setBackgroundResource(R.drawable.btn_left_disselect);
                mBtYgq.setBackgroundResource(R.drawable.btn_right_select);
                switchFragment(OVERDUE_FRAGMENT_TYPE);
                break;

            case R.id.bt_wl:
                mBtWl.setTextColor(getResources().getColor(R.color.tab));
                mBtJr.setTextColor(Color.BLACK);
                mBtYgq.setTextColor(Color.BLACK);
                mBtJr.setBackgroundResource(R.drawable.btn_left_disselect);
                mBtYgq.setBackgroundResource(R.drawable.btn_left_disselect);
                mBtWl.setBackgroundResource(R.drawable.btn_right_select);
                switchFragment(FUTURE_FRAGMENT_TYPE);
                break;

            default:

        }
    }


    private void switchFragment(int type) {
        switch (type) {
            case TODAY_FRAGMENT_TYPE:
                loadFragment(TODAY_FRAGMENT_TYPE);
                break;

            case OVERDUE_FRAGMENT_TYPE:
                loadFragment(OVERDUE_FRAGMENT_TYPE);
                break;

            case FUTURE_FRAGMENT_TYPE:
                loadFragment(FUTURE_FRAGMENT_TYPE);
                break;
        }
    }

    private void loadFragment(int type) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (type == TODAY_FRAGMENT_TYPE) {
            if (todayFragment == null) {
                todayFragment = new TodayFragment();
                transaction.add(R.id.fl_content3, todayFragment, "1");
            } else {
                transaction.show(todayFragment);
            }

            if (overdueFragment != null) {
                transaction.hide(overdueFragment);
            }
            if (futureFragment != null) {
                transaction.hide(futureFragment);
            }

            currentFragmentType = TODAY_FRAGMENT_TYPE;

        } else if (type == OVERDUE_FRAGMENT_TYPE) {
            if (overdueFragment == null) {
                overdueFragment = new OverdueFragment();
                transaction.add(R.id.fl_content3, overdueFragment, "2");
            } else {
                transaction.show(overdueFragment);
            }

            if (todayFragment != null) {
                transaction.hide(todayFragment);
            }
            if (futureFragment != null) {
                transaction.hide(futureFragment);
            }
            currentFragmentType = OVERDUE_FRAGMENT_TYPE;
        } else {
            if (futureFragment == null) {
                futureFragment = new FutureFragment();
                transaction.add(R.id.fl_content3, futureFragment, "3");
            } else {
                transaction.show(futureFragment);
            }

            if (todayFragment != null) {
                transaction.hide(todayFragment);
            }
            if (overdueFragment != null) {
                transaction.hide(overdueFragment);
            }
            currentFragmentType = FUTURE_FRAGMENT_TYPE;
        }

        transaction.commitAllowingStateLoss();
    }
}
