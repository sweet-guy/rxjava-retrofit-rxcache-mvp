package com.wdcloud.myrxjavaorretrofit.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import com.wdcloud.myrxjavaorretrofit.HomeActivity;
import com.wdcloud.myrxjavaorretrofit.R;
import com.wdcloud.myrxjavaorretrofit.activity.Presenter.NewFetUserPresenter;
import com.wdcloud.myrxjavaorretrofit.activity.View.NewFetUserView;
import com.wdcloud.myrxjavaorretrofit.base.BaseFragment;
import com.wdcloud.myrxjavaorretrofit.entity.FetUserBean;
import com.wdcloud.myrxjavaorretrofit.util.dialog.ProgressDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Umbrella on 2018/12/25.
 */

public class CourseFragment extends BaseFragment implements NewFetUserView{
    @BindView(R.id.bt_course)
    Button btCourse;
    private Dialog dialog;
    @BindView(R.id.course_text)
    TextView course_text;
    private NewFetUserPresenter newFetUserPresenter;

    @Override
    protected int getContentView() {
        return R.layout.course_layout;
    }

    @Override
    protected void init() {
        newFetUserPresenter = new NewFetUserPresenter(getContext());
        newFetUserPresenter.attachView(this);//绑定view跟presenter
        newFetUserPresenter.initData();//初始化数据
        dialog = new ProgressDialog().setProgressbar(getContext());
        newFetUserPresenter.getFetUserData(dialog,"2001916085","03");
    }

    public static CourseFragment newInstance() {
        CourseFragment fragment = new CourseFragment();
        return fragment;
    }

    @OnClick(R.id.bt_course)
    public void onViewClicked() {
        Intent intent = new Intent(getContext(), HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onFetUserSuccess(FetUserBean fetUserBean) {
        course_text.setText(fetUserBean.getLoginId());
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        newFetUserPresenter.detachView();
    }
}
