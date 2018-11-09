package com.weijiadev.googlex.view.fragment.home;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.weijiadev.googlex.R;
import com.weijiadev.googlex.app.event.OpenLeftDrawerEventBus;
import com.weijiadev.googlex.app.event.OpenRigthDrawerEventBus;
import com.weijiadev.googlex.view.fragment.base.BaseFragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.toolbar_common)
    Toolbar mToolbarCommon;
    @BindView(R.id.iv_life)
    ImageView mIvLife;
    @BindView(R.id.iv_right)
    ImageView mIvRight;


    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_main_home;
    }

    @Override
    protected void initViews() {

        mToolbarCommon.setTitle(R.string.title_name);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbarCommon);

        mIvLife.setOnClickListener(this);
        mIvRight.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_life:
                Toast.makeText(getContext(), "iv_life", Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(new OpenLeftDrawerEventBus());
                Toast.makeText(getContext(), "OpenLeftDrawerEventBus", Toast.LENGTH_SHORT).show();
                break;


            case R.id.iv_right:
                Toast.makeText(getContext(), "iv_right", Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(new OpenRigthDrawerEventBus());
                Toast.makeText(getContext(), "OpenRigthDrawerEventBus", Toast.LENGTH_SHORT).show();
                break;


            default:
        }
    }


}
