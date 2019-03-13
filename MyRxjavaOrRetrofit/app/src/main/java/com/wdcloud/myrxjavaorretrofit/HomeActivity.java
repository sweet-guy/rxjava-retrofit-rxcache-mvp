package com.wdcloud.myrxjavaorretrofit;

import android.Manifest;
import android.app.Dialog;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wdcloud.myrxjavaorretrofit.activity.Presenter.NewLoginPresenter;
import com.wdcloud.myrxjavaorretrofit.activity.View.NewLoginView;
import com.wdcloud.myrxjavaorretrofit.base.BaseActivity;
import com.wdcloud.myrxjavaorretrofit.entity.MyBuilder;
import com.wdcloud.myrxjavaorretrofit.entity.NewLoginBean;
import com.wdcloud.myrxjavaorretrofit.server.cache.Repository;
import com.wdcloud.myrxjavaorretrofit.util.dialog.ProgressDialog;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;
import com.wdcloud.myrxjavaorretrofit.app.MyApplication;
@RuntimePermissions
public class HomeActivity extends BaseActivity implements NewLoginView{

    @BindView(R.id.home_button)
    Button homeButton;
    @BindView(R.id.home_text)
    TextView homeText;
    private NewLoginPresenter newLoginPresenter;
    private Dialog dialog;
    private Map<String, Object> commonalityMap;

    @Override
    public int getContentViewId() {
        return R.layout.activity_home;
    }

    @Override
    public void mViewOnClick(View v) {

    }

    @Override
    protected void init() {
        commonalityMap = new HashMap<>();
        HomeActivityPermissionsDispatcher.nWithCheck(this);
        MyBuilder build = new MyBuilder.Builder().name("123").password("456").build();
        dialog = new ProgressDialog().setProgressbar(HomeActivity.this);
        build.getName().toString();
        homeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeText.setText("lambda**-*-*-*");
            }
        });
    }

    @OnClick(R.id.home_button)
    public void onViewClicked() {
        Repository repository = MyApplication.getRepository();
        newLoginPresenter = new NewLoginPresenter(HomeActivity.this,repository);
        newLoginPresenter.attachView(this);//绑定view跟presenter
        newLoginPresenter.initData();//初始化数据
        newLoginPresenter.getLoginData(dialog,"2001916085","111111","104","153");
    }

    @Override
    public void LoginSuccess(NewLoginBean newLoginBean) {
        homeText.setText(newLoginBean.getId());
    }

    @NeedsPermission({Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR, Manifest.permission.CAMERA, Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS, Manifest.permission.GET_ACCOUNTS, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.RECORD_AUDIO, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void n() {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        HomeActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnShowRationale({Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR, Manifest.permission.CAMERA, Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS, Manifest.permission.GET_ACCOUNTS, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.RECORD_AUDIO, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void o(final PermissionRequest request) {
    }

    @OnPermissionDenied({Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR, Manifest.permission.CAMERA, Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS, Manifest.permission.GET_ACCOUNTS, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.RECORD_AUDIO, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void d() {
    }

    @OnNeverAskAgain({Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR, Manifest.permission.CAMERA, Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS, Manifest.permission.GET_ACCOUNTS, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.RECORD_AUDIO, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void a() {
    }

    @Override
    public void showProgress() {
        dialog.show();
    }

    @Override
    public void hideProgress() {
        dialog.dismiss();
    }
}
