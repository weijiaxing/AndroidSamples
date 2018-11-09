package com.weijiadev.googlex.view.fragment.transfer;


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

public class TransferFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.toolbar_common4)
    Toolbar mToolbarCommon4;
    @BindView(R.id.toolbar_common)
    Toolbar mToolbarCommon;
    @BindView(R.id.bt_dsc)
    Button mBtDsc;
    @BindView(R.id.bt_ywc)
    Button mBtYwc;
    @BindView(R.id.tv_centre)
    TextView mTvCenter;
    @BindView(R.id.iv_life)
    ImageView mIvLife;
    @BindView(R.id.iv_right)
    ImageView mIvRight;


    private ToBeUploadFragment toBeUploadFragment;
    private FinishFragment finishFragment;
    public static final int TOBEUPLOAD_FRAGMENT_TYPE = 1;
    public static final int FINISH_FRAGMENT_TYPE = 2;
    public int currentFragmentType = -1;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_main_transfer;
    }

    @Override
    protected void initViews() {

        mTvCenter.setText("传输列表");
        mIvRight.setVisibility(View.GONE);
        mToolbarCommon4.setTitle(R.string.title_name);
        mToolbarCommon.setBackgroundResource(R.color.tab);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbarCommon4);

        mBtDsc.setOnClickListener(this);
        mBtYwc.setOnClickListener(this);
        initFragment();
    }

    private void initFragment() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment mainFragment = fragmentManager.findFragmentByTag("message");
        if (mainFragment != null) {
            transaction.replace(R.id.fl_content4, mainFragment);
            transaction.commit();
        } else {
            loadFragment(TOBEUPLOAD_FRAGMENT_TYPE);
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bt_dsc:
                mBtDsc.setTextColor(getResources().getColor(R.color.tab));
                mBtYwc.setTextColor(Color.BLACK);
                mBtDsc.setBackgroundResource(R.drawable.btn_left_select);
                mBtYwc.setBackgroundResource(R.drawable.btn_right_disselect);
                switchFragment(TOBEUPLOAD_FRAGMENT_TYPE);
                break;

            case R.id.bt_ywc:
                mBtDsc.setTextColor(Color.BLACK);
                mBtYwc.setTextColor(getResources().getColor(R.color.tab));
                mBtDsc.setBackgroundResource(R.drawable.btn_left_disselect);
                mBtYwc.setBackgroundResource(R.drawable.btn_right_select);
                switchFragment(FINISH_FRAGMENT_TYPE);
                break;

            default:

        }

    }


    private void switchFragment(int type) {
        switch (type) {
            case TOBEUPLOAD_FRAGMENT_TYPE:
                loadFragment(TOBEUPLOAD_FRAGMENT_TYPE);
                break;
            case FINISH_FRAGMENT_TYPE:
                loadFragment(FINISH_FRAGMENT_TYPE);
                break;
        }
    }


    private void loadFragment(int type) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (type == TOBEUPLOAD_FRAGMENT_TYPE) {
            if (toBeUploadFragment == null) {
                toBeUploadFragment = new ToBeUploadFragment();
                transaction.add(R.id.fl_content4, toBeUploadFragment, "1");
            } else {
                transaction.show(toBeUploadFragment);
            }
            if (finishFragment != null) {
                transaction.hide(finishFragment);
            }
            currentFragmentType = TOBEUPLOAD_FRAGMENT_TYPE;
        } else {
            if (finishFragment == null) {
                finishFragment = new FinishFragment();
                transaction.add(R.id.fl_content4, finishFragment, "2");
            } else {
                transaction.show(finishFragment);
            }
            if (toBeUploadFragment != null) {
                transaction.hide(toBeUploadFragment);
            }
            currentFragmentType = FINISH_FRAGMENT_TYPE;
        }
        transaction.commitAllowingStateLoss();
    }
}
