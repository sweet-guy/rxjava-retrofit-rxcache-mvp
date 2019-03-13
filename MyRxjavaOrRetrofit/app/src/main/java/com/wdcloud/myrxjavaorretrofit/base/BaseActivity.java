package com.wdcloud.myrxjavaorretrofit.base;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.wdcloud.myrxjavaorretrofit.base.view.BaseMvpView;
import com.wdcloud.myrxjavaorretrofit.util.ActivityController;
import com.wdcloud.myrxjavaorretrofit.util.ToastUtil;
import com.wdcloud.myrxjavaorretrofit.util.immersion.ImmersionBar;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Umbrella on 2018/12/5.
 */

public abstract class BaseActivity extends FragmentActivity implements BaseMvpView,View.OnClickListener{
    private static long lastClickTime;
    // 两次点击按钮之间的点击间隔不能少于1000毫秒
    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private Unbinder bind;

    public abstract int getContentViewId();
    protected ImmersionBar mImmersionBar;
    public abstract void mViewOnClick(View v);
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};
    private static final int REQUEST_EXTERNAL_STORAGE_FROMTAKEPHOTO = 5;
    private long mExitTime;
    @Override
    public void onClick(View v) {
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            // 超过点击间隔后再将lastClickTime重置为当前点击时间
            lastClickTime = curClickTime;
            mViewOnClick(v);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题
        setContentView(getContentViewId());
        bind = ButterKnife.bind(this);
//        initImmersionBar();
        init();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
        ActivityController.removeActivity(this);
        if (mImmersionBar != null)
            mImmersionBar.destroy();  //在BaseActivity里销毁
    }

    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this).statusBarDarkFont(true, 0.2f).fitsSystemWindows(true).statusBarColor("#ffffffff");
        mImmersionBar.init();
    }
    /**
     * 检测是否开启读写权限
     */
    public void checkpermission() {
        //检测是否有写的权限
        int permissionwrite = ActivityCompat.checkSelfPermission(BaseActivity.this,
                "android.permission.WRITE_EXTERNAL_STORAGE");
        if (permissionwrite != PackageManager.PERMISSION_GRANTED) {
            // 没有写的权限，去申请写的权限，会弹出对话框
            ActivityCompat.requestPermissions(BaseActivity.this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE_FROMTAKEPHOTO);
        }
    }
    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            ToastUtil.showToast(this, "再按一次退出");
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
            ActivityController.finishAll();
            System.exit(0);
        }
    }
    /**
     * 跳转到指定的页面
     *
     * @param clazz
     */
    public void startActivity(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
    /**
     * Toast提示
     **/
    public void showToastMessage(@NonNull String message) {
        if (message.length() > 15) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
    }
    /**
     * 初始化准备
     */
    protected abstract void init();

    public Map<String, Object> getMapParameter() {
        Map<String,Object> map = new HashMap<>();
        return map;
    }
    /**
     * 获取回退栈数量
     **/
    public int getBackStackEntryCount() {
        return getSupportFragmentManager().getBackStackEntryCount();
    }
    /**
     * 获得栈中最顶层的Activity
     *
     * @return
     */
    private boolean isOneActivity() {
        String localClassName = getLocalClassName();
        if ("MainActivity".equals(getLocalClassName())) {
            return true;
        }
        return false;
    }
    /* 按两次退出*/
    private boolean backIndex = true;
    /*物理返回按钮监听*/
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //获取的回退栈的数量,因为四个主fragment没有加入回退栈所以是0
        if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {
            //监听首页搜索的返回按钮
            int count = getBackStackEntryCount();

            if (count == 0 && isOneActivity()) {
                backIsTrurFalse();
            } else {
                onBackPressed();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    private void backIsTrurFalse() {
        if (backIndex) {
            backIndex = false;
            showToastMessage("再按一次退出！");
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    backIndex = true;
                }
            }, 3000);
        } else {
            ActivityController.finishAll();
            System.exit(0);
//            fragments.clear();
//            fragments = null;
            super.onBackPressed();
        }
    }
}
