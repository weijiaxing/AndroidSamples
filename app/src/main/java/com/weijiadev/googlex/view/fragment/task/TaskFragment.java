package com.weijiadev.googlex.view.fragment.task;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.weijiadev.googlex.R;
import com.weijiadev.googlex.view.fragment.base.BaseFragment;

import butterknife.BindView;


public class TaskFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.toolbar_common2)
    Toolbar mToolbarCommon2;
    @BindView(R.id.toolbar_common)
    Toolbar mToolbarCommon;
    @BindView(R.id.bt_lp)
    Button mBtLp;
    @BindView(R.id.bt_hb)
    Button mBtHb;
    @BindView(R.id.tv_centre)
    TextView mTvCenter;
    @BindView(R.id.iv_life)
    ImageView mIvLife;
    @BindView(R.id.iv_right)
    ImageView mIvRight;


    private LiPeiFragment liPeiFragment;
    private HeBaoFragment heBaoFragment;
    public static final int LIPEI_FRAGMENT_TYPE = 1;
    public static final int HEBAO_FRAGMENT_TYPE = 2;
    public int currentFragmentType = -1;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_main_task;
    }

    @Override
    protected void initViews() {

        mTvCenter.setText("处理");
        mToolbarCommon2.setTitle(R.string.title_name);
        mToolbarCommon.setBackgroundResource(R.color.tab);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbarCommon2);

        mBtLp.setOnClickListener(this);
        mBtHb.setOnClickListener(this);
        initFragment();
    }

    private void initFragment() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment mainFragment = fragmentManager.findFragmentByTag("message");
        if (mainFragment != null) {
            transaction.replace(R.id.fl_content2, mainFragment);
            transaction.commit();
        } else {
            loadFragment(LIPEI_FRAGMENT_TYPE);
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bt_lp:
                mBtLp.setTextColor(getResources().getColor(R.color.tab));
                mBtHb.setTextColor(Color.BLACK);
                mBtLp.setBackgroundResource(R.drawable.btn_left_select);
                mBtHb.setBackgroundResource(R.drawable.btn_right_disselect);
                switchFragment(LIPEI_FRAGMENT_TYPE);
                break;

            case R.id.bt_hb:
                mBtLp.setTextColor(Color.BLACK);
                mBtHb.setTextColor(getResources().getColor(R.color.tab));
                mBtLp.setBackgroundResource(R.drawable.btn_left_disselect);
                mBtHb.setBackgroundResource(R.drawable.btn_right_select);
                switchFragment(HEBAO_FRAGMENT_TYPE);
                break;

            default:

        }

    }


    private void switchFragment(int type) {
        switch (type) {
            case LIPEI_FRAGMENT_TYPE:
                loadFragment(LIPEI_FRAGMENT_TYPE);
                break;
            case HEBAO_FRAGMENT_TYPE:
                loadFragment(HEBAO_FRAGMENT_TYPE);
                break;
        }
    }

    private void loadFragment(int type) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (type == LIPEI_FRAGMENT_TYPE) {
            if (liPeiFragment == null) {
                liPeiFragment = new LiPeiFragment();
                transaction.add(R.id.fl_content2, liPeiFragment, "1");
            } else {
                transaction.show(liPeiFragment);
            }
            if (heBaoFragment != null) {
                transaction.hide(heBaoFragment);
            }
            currentFragmentType = LIPEI_FRAGMENT_TYPE;
        } else {
            if (heBaoFragment == null) {
                heBaoFragment = new HeBaoFragment();
                transaction.add(R.id.fl_content2, heBaoFragment, "2");
            } else {
                transaction.show(heBaoFragment);
            }
            if (liPeiFragment != null) {
                transaction.hide(liPeiFragment);
            }
            currentFragmentType = HEBAO_FRAGMENT_TYPE;
        }
        transaction.commitAllowingStateLoss();
    }

}
