package com.wdcloud.myrxjavaorretrofit.activity.Presenter;

import android.app.Dialog;
import android.content.Context;

import com.google.gson.Gson;
import com.wdcloud.myrxjavaorretrofit.activity.Model.HomeFragmentModel;
import com.wdcloud.myrxjavaorretrofit.activity.View.HomeFragmentView;
import com.wdcloud.myrxjavaorretrofit.base.presenter.BaseMvpPresenter;
import com.wdcloud.myrxjavaorretrofit.entity.ClassBean;
import com.wdcloud.myrxjavaorretrofit.entity.ClassResultBean;
import com.wdcloud.myrxjavaorretrofit.entity.MyBooks;
import com.wdcloud.myrxjavaorretrofit.entity.NjBean;
import com.wdcloud.myrxjavaorretrofit.entity.NjIInfoBean;
import com.wdcloud.myrxjavaorretrofit.entity.SchoolDataBean;
import com.wdcloud.myrxjavaorretrofit.entity.SchoolDataResult;
import com.wdcloud.myrxjavaorretrofit.server.api.ApiUrl;
import com.wdcloud.myrxjavaorretrofit.server.apiserver.ApiServer;
import com.wdcloud.myrxjavaorretrofit.util.RxSchedulerHepler;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import static com.wdcloud.myrxjavaorretrofit.server.interceptor.HttpFactor.getApiService;
/**
 * Created by Umbrella on 2019/1/10.
 */

public class HomeFragmentPresenter extends BaseMvpPresenter<HomeFragmentView> implements HomeFragmentModel {
    private Context context;
    private HomeFragmentView homeFragmentView;
    private CompositeDisposable mCompositeSubscription;//统一管理retrofit请求
    private MyBooks myBooks;
    private Gson gson;

    public HomeFragmentPresenter(Context context) {
        this.context = context;
    }
    @Override
    public void initData() {
        homeFragmentView=getMvpView();
        mCompositeSubscription = new CompositeDisposable();
        gson = new Gson();
    }

    @Override
    public void getSelectSchoolData(Dialog dialog, Map<String, Object> map) {
//        dialog.show();
        homeFragmentView.showProgress();
        ApiServer apiService = getApiService(ApiUrl.BaseUrl);
        apiService.getSchoolData(map).compose(RxSchedulerHepler.io_main())
                .subscribe(new Observer<SchoolDataBean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(SchoolDataBean schoolDataBean) {
                String rows = schoolDataBean.getRows();
                SchoolDataResult schoolDataResult = gson.fromJson(rows, SchoolDataResult.class);
                homeFragmentView.onSchoolSuccess(schoolDataResult);
                homeFragmentView.showProgress();
            }

            @Override
            public void onError(Throwable e) {
//                dialog.dismiss();
                homeFragmentView.hideProgress();
            }

            @Override
            public void onComplete() {
//                dialog.dismiss();
                homeFragmentView.hideProgress();
            }
        });
    }

    @Override
    public void getSelectClassData(Dialog dialog, Map<String, Object> map) {
//        dialog.show();
        homeFragmentView.showProgress();
        ApiServer apiService = getApiService(ApiUrl.BaseUrl);
        apiService.getClassData(map).compose(RxSchedulerHepler.io_main())
                .subscribe(new Observer<ClassBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ClassBean classBean) {
                ClassResultBean classResultBean = gson.fromJson(classBean.getRows(), ClassResultBean.class);
                homeFragmentView.onClassSuccess(classResultBean);
            }

            @Override
            public void onError(Throwable e) {
//                dialog.dismiss();
                homeFragmentView.hideProgress();
            }

            @Override
            public void onComplete() {
//                dialog.dismiss();
                homeFragmentView.hideProgress();
            }
        });
    }

    @Override
    public void getSelectNJData(Dialog dialog, Map<String, Object> map) {
//        dialog.show();
        homeFragmentView.showProgress();
        ApiServer apiService = getApiService(ApiUrl.BaseUrl);
        apiService.getNJData(map).compose(RxSchedulerHepler.io_main())
                .subscribe(new Observer<NjBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NjBean njBean) {
                        NjIInfoBean njIInfoBean = gson.fromJson(njBean.getRows(), NjIInfoBean.class);
                        homeFragmentView.onNJSuccess(njIInfoBean);
                    }

                    @Override
                    public void onError(Throwable e) {
//                        dialog.dismiss();
                        homeFragmentView.hideProgress();
                    }

                    @Override
                    public void onComplete() {
//                        dialog.dismiss();
                        homeFragmentView.hideProgress();
                    }
                });
    }
}
