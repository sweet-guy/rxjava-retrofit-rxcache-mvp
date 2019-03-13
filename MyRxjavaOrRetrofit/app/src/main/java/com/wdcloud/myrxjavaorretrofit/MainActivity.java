package com.wdcloud.myrxjavaorretrofit;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.allenliu.versionchecklib.core.http.HttpHeaders;
import com.allenliu.versionchecklib.core.http.HttpParams;
import com.allenliu.versionchecklib.core.http.HttpRequestMethod;
import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.DownloadBuilder;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.allenliu.versionchecklib.v2.callback.CustomDownloadingDialogListener;
import com.allenliu.versionchecklib.v2.callback.ForceUpdateListener;
import com.allenliu.versionchecklib.v2.callback.RequestVersionListener;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.wdcloud.myrxjavaorretrofit.GreenDao.Student;
import com.wdcloud.myrxjavaorretrofit.activity.Presenter.NewBookPresenter;
import com.wdcloud.myrxjavaorretrofit.activity.View.NewBookVIew;
import com.wdcloud.myrxjavaorretrofit.base.BaseActivity;
import com.wdcloud.myrxjavaorretrofit.base.BaseFragment;
import com.wdcloud.myrxjavaorretrofit.entity.MyBooks;
import com.wdcloud.myrxjavaorretrofit.entity.UpdateVersion;
import com.wdcloud.myrxjavaorretrofit.fragment.CourseFragment;
import com.wdcloud.myrxjavaorretrofit.fragment.HomeFragment;
import com.wdcloud.myrxjavaorretrofit.fragment.LikeFragment;
import com.wdcloud.myrxjavaorretrofit.fragment.MineFragment;
import com.wdcloud.myrxjavaorretrofit.util.ActivityController;
import com.wdcloud.myrxjavaorretrofit.util.GreenDaoManager;
import com.wdcloud.myrxjavaorretrofit.util.MyAutoUpdate;
import com.wdcloud.myrxjavaorretrofit.util.dialog.ProgressDialog;
import com.wdcloud.myrxjavaorretrofit.util.dialog.NumberProgressBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements AHBottomNavigation.OnTabSelectedListener, NewBookVIew {
    @BindView(R.id.Bottom_navigation)
    AHBottomNavigation BottomNavigation;
    public static List<BaseFragment> fragments;
    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();
    private boolean isStrongUpdate;
    private NumberProgressBar numberProgressBar;
    private NewBookPresenter bookPresenter;
    private Dialog dialog;

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void mViewOnClick(View v) {

    }

    @Override
    protected void init() {
        AddFragments();
        initBottom();
        bookPresenter = new NewBookPresenter(MainActivity.this);
        bookPresenter.attachView(this);//绑定view跟presenter
        bookPresenter.initData();//初始化数据
        dialog = new ProgressDialog().setProgressbar(MainActivity.this);
        bookPresenter.getData(this.dialog);
        checkVersionUpdateAndDownload();
        HashMap<String, Object> map = new HashMap<>();
//        bookPresenter.setBookurl(map);
        Student student = new Student();
        student.setSNum("2");
        student.setName("fjdskjfk");
        student.setAge("sdfa");
        student.setSex("dfajskj");
        GreenDaoManager.getInstance().getSession().getStudentDao().insert(student);
        Student load = GreenDaoManager.getInstance().getSession().getStudentDao().load((long) 2);
        Log.d("数据+++++", load.toString());
//        Uri uri = Uri.parse("https://www.baidu.com/img/bd_logo1.png");
//        text.setText("库昂加");
//        fresco.setImageURI(uri);
    }

    private void initBottom() {
        AHBottomNavigationItem item1 = new AHBottomNavigationItem("首页", R.drawable.ic_index_home);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("备听课", R.drawable.ic_index_course);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("通知", R.drawable.ic_index_recruitment);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem("我的", R.drawable.ic_index_mine);
        bottomNavigationItems.add(item1);
        bottomNavigationItems.add(item2);
        bottomNavigationItems.add(item3);
        bottomNavigationItems.add(item4);
        BottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        BottomNavigation.addItems(bottomNavigationItems);
        BottomNavigation.setOnTabSelectedListener(this);
        BottomNavigation.setAccentColor(ContextCompat.getColor(this, R.color.font_blue));
        BottomNavigation.setInactiveColor(ContextCompat.getColor(this, R.color.c1));
        BottomNavigation.setTitleTextSizeInSp(14, 12);
        BottomNavigation.setNotification("10",2);
        BottomNavigation.setCurrentItem(0);
    }

    @Override
    public boolean onTabSelected(int position, boolean wasSelected) {
        if(wasSelected)
        {
            return false;
        }
        String title = bottomNavigationItems.get(position).getTitle(this);
        switch (title)
        {
            case "首页":
                selectTabFragment(fragments, 0);
                break;
            case "备听课":
                selectTabFragment(fragments, 1);
                break;
            case "通知":
                selectTabFragment(fragments, 2);
                break;
            case "我的":
                selectTabFragment(fragments, 3);
                break;
        }
        return true;
    }
    private void AddFragments()
    {
        if(fragments==null)
        {
            fragments = new ArrayList<>();
            fragments.add(HomeFragment.newInstance());
            fragments.add(CourseFragment.newInstance());
            fragments.add(LikeFragment.newInstance());
            fragments.add(MineFragment.newInstance());
            selectTabFragment(fragments, 0);
        }
    }
    /*下面是fragment得切换*/
    public void selectTabFragment(List<BaseFragment> fragments, int indexFragment) {
        FragmentTransaction transaction = getFragmentTransaction();
        /**先隐藏所有fragment和添加fragment**/
        hideAllFragment(transaction, fragments);
        if (fragments.get(indexFragment).isAdded()) {
            transaction.show(fragments.get(indexFragment));
        } else {
            //添加fragment
            transaction.add(R.id.omniselector_activity_framelayout, fragments.get(indexFragment));
        }
        transaction.commit();
    }
    /**
     * 隐藏所有fragment
     **/
    private void hideAllFragment(FragmentTransaction transaction, List<BaseFragment> fragments) {
        for (int i = 0; i < fragments.size(); i++) {
            if (fragments.get(i).isAdded()) {
                transaction.hide(fragments.get(i));
            }
        }
    }
    private FragmentTransaction getFragmentTransaction() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        return transaction;
    }
    private void checkVersionUpdateAndDownload() {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpParams httpParams = new HttpParams();
        httpParams.put("pl", "Android");
        httpHeaders.put("pl", "Android");
//        httpHeaders.put("", );
//        httpHeaders.put("Content-Type","application/json");
        DownloadBuilder downloadBuilder = AllenVersionChecker
                .getInstance()
                .requestVersion()
                .setHttpHeaders(httpHeaders)
                .setRequestParams(httpParams)
                .setRequestMethod(HttpRequestMethod.POST)
                .setRequestUrl("http://rrt.wdcloud.cc/mobile/index/updateinfo")
                .request(new RequestVersionListener() {
                    @Nullable
                    @Override
                    public UIData onRequestVersionSuccess(String result) {
                        MyAutoUpdate myAutoUpdate = new MyAutoUpdate(MainActivity.this);
                        int updateMode = 0;
                        UpdateVersion updateInfo = myAutoUpdate.getUpdateInfo(result);
                        try {
                            updateMode = myAutoUpdate.compareversion(updateInfo);
                            if (updateInfo == null) {
                                return null;
                            }
                            UIData uiData = null;
                            switch (updateMode) {
                                case MyAutoUpdate.NO_NEED_UPDATE:
                                    break;
                                case MyAutoUpdate.NORMAL_NEED_UPDATE:
                                    uiData = UIData
                                            .create()
                                            .setDownloadUrl(updateInfo.getRows().getAddress())
                                            .setTitle("人人通" + updateInfo.getRows().getLatestversion())
                                            .setContent(updateInfo.getRows().getInfo());
                                    uiData.getVersionBundle().putInt("updateType", MyAutoUpdate.NORMAL_NEED_UPDATE);
                                    isStrongUpdate = false;
                                    break;
                                case MyAutoUpdate.STRONG_NEED_UPDATE:
                                    uiData = UIData
                                            .create()
                                            .setDownloadUrl(updateInfo.getRows().getAddress())
                                            .setTitle("人人通" + updateInfo.getRows().getLatestversion())
                                            .setContent(updateInfo.getRows().getInfo());
                                    uiData.getVersionBundle().putInt("updateType", MyAutoUpdate.STRONG_NEED_UPDATE);
                                    isStrongUpdate = true;
                                    break;
                            }
                            return uiData;
                        } catch (Exception e) {
                            e.printStackTrace();
                            return null;
                        }
                    }

                    @Override
                    public void onRequestVersionFailure(String message) {

                    }
                })
                .setDownloadAPKPath("/storage/emulated/0/LJXYVersionPath/")
                .setCustomDownloadingDialogListener(new CustomDownloadingDialogListener() {
                    @Override
                    public Dialog getCustomDownloadingDialog(Context context, int progress, UIData versionBundle) {
                        int updateType = versionBundle.getVersionBundle().getInt("updateType");
//                        final BaseDialog dialog = new BaseDialog(context, R.style.BaseDialog, R.layout.custom_dialog_two_layout);
                        View contentView = LayoutInflater.from(MainActivity.this).inflate(
                                R.layout.custom_dialog_two_layout, null);
                        final Dialog dialog = new AlertDialog.Builder(context).setView(contentView).show();
//                        final Dialog dialog = new Dialog(MainActivity.this);
                        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//                        dialog.getWindow().setDimAmount(0f);

//                        dialog.setContentView(contentView);
                        TextView tv_title = contentView.findViewById(R.id.tv_title);
//                        LinearLayout lview = contentView.findViewById(R.id.ll_top);
//                        lview.getBackground().setAlpha(0);
                        TextView tv_update_info = contentView.findViewById(R.id.tv_update_info);
                        Button updateComfirmBtn = contentView.findViewById(R.id.versionchecklib_version_dialog_commit);
                        numberProgressBar = contentView.findViewById(R.id.npb);
                        updateComfirmBtn.setVisibility(View.GONE);
                        numberProgressBar.setVisibility(View.VISIBLE);
                        tv_title.setText(versionBundle.getTitle());
                        tv_update_info.setText(versionBundle.getContent());
                        if (updateType == MyAutoUpdate.NORMAL_NEED_UPDATE) {
                            dialog.setCanceledOnTouchOutside(true);
                            contentView.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.cancel();
                                }
                            });
                        } else {
                            dialog.setCanceledOnTouchOutside(false);
                            dialog.setCancelable(false);
                        }
                        return dialog;
                    }

                    @Override
                    public void updateUI(Dialog dialog, int progress, UIData versionBundle) {
                        numberProgressBar.setProgress(progress);
                    }
                });
        if (isStrongUpdate) {
            downloadBuilder.setForceUpdateListener(new ForceUpdateListener() {
                @Override
                public void onShouldForceUpdate() {
//                            IconToast.getToast().ToastShow(MainActivity.this, "必须更新才能使用哦～", IconToast.ALERT_NO_ICON, R.layout.toast_icon_text, R.id.iv_toast_alert, R.id.tv_toast_alert, 15);
                    finish();
                    ActivityController.finishAll();
                    System.exit(0);
                }
            });
        }
        downloadBuilder.executeMission(MainActivity.this);
    }

    @Override
    public void onBookSuccess(MyBooks book) {
        String book_name = book.getItems().get(0).getBook_name();
//        text.setText(book_name);
        showToastMessage(book.getItems().get(0).toString());
    }

    @Override
    protected void onDestroy() {
        bookPresenter.onStop();
        super.onDestroy();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
