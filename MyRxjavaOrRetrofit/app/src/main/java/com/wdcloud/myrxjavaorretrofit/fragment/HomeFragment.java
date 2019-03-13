package com.wdcloud.myrxjavaorretrofit.fragment;

import android.app.Dialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wdcloud.myrxjavaorretrofit.R;
import com.wdcloud.myrxjavaorretrofit.activity.Presenter.HomeFragmentPresenter;
import com.wdcloud.myrxjavaorretrofit.activity.Presenter.NewFetUserPresenter;
import com.wdcloud.myrxjavaorretrofit.activity.View.HomeFragmentView;
import com.wdcloud.myrxjavaorretrofit.base.BaseFragment;
import com.wdcloud.myrxjavaorretrofit.entity.ClassResultBean;
import com.wdcloud.myrxjavaorretrofit.entity.MyBooks;
import com.wdcloud.myrxjavaorretrofit.entity.NjIInfoBean;
import com.wdcloud.myrxjavaorretrofit.entity.SchoolDataBean;
import com.wdcloud.myrxjavaorretrofit.entity.SchoolDataResult;
import com.wdcloud.myrxjavaorretrofit.entity.TabBean;
import com.wdcloud.myrxjavaorretrofit.util.dialog.ProgressDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Umbrella on 2018/12/25.
 */

public class HomeFragment extends BaseFragment implements HomeFragmentView{
    @BindView(R.id.dialog_show)
    Button dialogShow;
    @BindView(R.id.dialog_dismiss)
    Button dialogDismiss;
    @BindView(R.id.select_text)
    TextView select_text;
    private TabDialog tabDialog;
    private HomeFragmentPresenter homeFragmentPresenter;
    private Dialog dialog;
    private List<TabBean> xdlist=new ArrayList<>();
    private List<TabBean> xklist=new ArrayList<>();
    private List<TabBean> njlist=new ArrayList<>();
    Map<String,Object> schoolmap=new HashMap<>();
    Map<String,Object> classmap=new HashMap<>();
    Map<String,Object> njmap=new HashMap<>();
    String XDs="";
    String XKs="";
    String Allselect="";
    @Override
    protected int getContentView() {
        return R.layout.home_layout;
    }

    @Override
    protected void init() {
        homeFragmentPresenter = new HomeFragmentPresenter(getContext());
        homeFragmentPresenter.attachView(this);//绑定view跟presenter
        homeFragmentPresenter.initData();//初始化数据
        dialog = new ProgressDialog().setProgressbar(getContext());
        schoolmap.put("page",1);
        schoolmap.put("rows",1000);
        homeFragmentPresenter.getSelectSchoolData(dialog,schoolmap);
        tabDialog = new TabDialog(getContext(),R.style.bottom_dialog);
        tabDialog.setOnXDSureListener(new TabDialog.OnXDSureListener() {
            @Override
            public void onXDSureClick(String XD, String ID) {
                classmap.put("page",1);
                classmap.put("id",ID);
                classmap.put("rows",1000);
                homeFragmentPresenter.getSelectClassData(dialog,classmap);
//                for (int i = 0; i <10; i++) {
//                    xklist.add( new TabBean("学科"+i,"1"+i));
//                }
//                tabDialog.setXKList(xklist);
                XDs="";
                XDs=XDs+XD;
            }
        });
        tabDialog.setOnXKSureListener(new TabDialog.OnXKSureListener() {
            @Override
            public void onXKSureClick(String XK, String ID) {
                njmap.put("grade_id",ID);
                njmap.put("id",classmap.get("id"));
                njmap.put("page",1);
                njmap.put("rows",1000);
                homeFragmentPresenter.getSelectNJData(dialog,njmap);
                XKs="";
                XKs=XKs+XK;
            }
        });
        tabDialog.setOnNJSureListener(new TabDialog.OnNJSureListener() {
            @Override
            public void onNJSureClick(String NJ, String ID) {
                Allselect=XDs+XKs+NJ;
                select_text.setText(Allselect);
                Toast.makeText(getContext(),Allselect,Toast.LENGTH_LONG).show();
            }
        });
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @OnClick({R.id.dialog_show, R.id.dialog_dismiss})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dialog_show:
                tabDialog.show();
                break;
            case R.id.dialog_dismiss:
                tabDialog.dismiss();
                break;
        }
    }

    @Override
    public void onSchoolSuccess(SchoolDataResult schoolDataResult) {
        SchoolDataResult.ResultBean resultBean = schoolDataResult.getResultBean();
        List<SchoolDataResult.SchoolBean> data = resultBean.getData();
        for (SchoolDataResult.SchoolBean bean : data) {
            String id = bean.getId();
            String school_name = bean.getSchool_name();
            xdlist.add(new TabBean(school_name,id));
        }
        tabDialog.setXDList(xdlist);
    }

    @Override
    public void onClassSuccess(ClassResultBean classResultBean) {
        List<ClassResultBean.ClassinfoBean> result = classResultBean.getResult();
        for (ClassResultBean.ClassinfoBean bean: result) {
            String grade_name = bean.getGrade_name();
            String id = bean.getId();
            xklist.add(new TabBean(grade_name,id));
        }
        tabDialog.setXKList(xklist);
    }

    @Override
    public void onNJSuccess(NjIInfoBean njIInfoBean) {
        List<NjIInfoBean.NjResultBean> data = njIInfoBean.getNjResultData().getData();
        for (NjIInfoBean.NjResultBean bean:data) {
            String classes_name = bean.getClasses_name();
            String id = bean.getId();
            njlist.add(new TabBean(classes_name,id));
        }
        tabDialog.setNJList(njlist);
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
