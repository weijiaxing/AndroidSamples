package com.weijiadev.googlex.view.activity;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.weijiadev.googlex.R;
import com.weijiadev.googlex.app.event.OpenLeftDrawerEventBus;
import com.weijiadev.googlex.app.event.OpenRigthDrawerEventBus;
import com.weijiadev.googlex.view.activity.base.BaseActivity;
import com.weijiadev.googlex.view.fragment.act.ActFragment;
import com.weijiadev.googlex.view.fragment.home.HomeFragment;
import com.weijiadev.googlex.view.fragment.task.TaskFragment;
import com.weijiadev.googlex.view.fragment.transfer.TransferFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class MainActivity extends BaseActivity {

    RadioGroup mRgBottomMenu;
    //数组 存储Fragment
    Fragment[] mFragments;
    //当前Fragent的下标
    private int currentIndex;
    private DrawerLayout mDrawerLayout;


    private String strings[] = {"个人资料", "人脉", "职业", "地点", "反馈", "和我联系", "github", "我的通讯"};
    private DrawerLayout drawerLayout;
    private RelativeLayout left_menu_layout;
    private RelativeLayout right_xiaoxi_layout;
    private TextView text;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initListening() {

    }

    @Override
    protected void initView() {

        mRgBottomMenu = (RadioGroup) findViewById(R.id.rg_bottom_menu);
        mRgBottomMenu = (RadioGroup) findViewById(R.id.rg_bottom_menu);
        EventBus.getDefault().register(this);
        initLeftLayout();
        initRightLayout();

        //将Fragment加入数组
        mFragments = new Fragment[] {
                //主页、新闻、图片、视频、个人
                new HomeFragment(),
                new TaskFragment(),
                new ActFragment(),
                new TransferFragment(),
        };

        //开启事务
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //设置为默认界面 HomeFragment
        ft.add(R.id.main_content,mFragments[0]).commit();
        //RadioGroup选中事件监听 改变fragment
        mRgBottomMenu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        setIndexSelected(0);
                        break;
                    case R.id.rb_news:
                        setIndexSelected(1);
                        break;
                    case R.id.rb_picture:
                        setIndexSelected(2);
                        break;
                    case R.id.rb_movie:
                        setIndexSelected(3);
                        break;
                }
            }
        });
    }


    //设置Fragment页面
    private void setIndexSelected(int index) {
        if (currentIndex == index) {
            return;
        }
        //开启事务
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //隐藏当前Fragment
        ft.hide(mFragments[currentIndex]);
        //判断Fragment是否已经添加
        if (!mFragments[index].isAdded()) {
            ft.add(R.id.main_content,mFragments[index]).show(mFragments[index]);
        }else {
            //显示新的Fragment
            ft.show(mFragments[index]);
        }
        ft.commit();
        currentIndex = index;
    }



    public void initLeftLayout(){
        drawerLayout = (DrawerLayout) findViewById(R.id.id_drawer_layout);
        //设置透明
        drawerLayout.setScrimColor(0x00000000);

        //左边菜单
        left_menu_layout = (RelativeLayout) findViewById(R.id.main_left_drawer_layout);
        View view2 = getLayoutInflater().inflate(R.layout.draw_menu_layout, null);
//        text=(TextView)view2.findViewById(R.id.text);
//        text.setText("左边测试菜单");
        left_menu_layout.addView(view2);
    }

    public void initRightLayout(){
        //左边菜单
        right_xiaoxi_layout = (RelativeLayout) findViewById(R.id.main_right_drawer_layout);
        View view = getLayoutInflater().inflate(R.layout.draw_menu_layout2, null);
//        text=(TextView)view.findViewById(R.id.text);
//        text.setText("右边测试菜单");
        right_xiaoxi_layout.addView(view);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    //定义处理接收的方法 开始时分
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OpenLeftDrawerEventBus(OpenLeftDrawerEventBus openLeftDrawerEventBus) {

        Toast.makeText(mContext, "打开左边侧滑", Toast.LENGTH_SHORT).show();
        drawerLayout.openDrawer(Gravity.LEFT);
    }


    //定义处理接收的方法 开始时分
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OpenRigthDrawerEventBus(OpenRigthDrawerEventBus openRigthDrawerEventBus) {

        Toast.makeText(mContext, "打开右边侧滑", Toast.LENGTH_SHORT).show();
        drawerLayout.openDrawer(Gravity.RIGHT);
    }

}
