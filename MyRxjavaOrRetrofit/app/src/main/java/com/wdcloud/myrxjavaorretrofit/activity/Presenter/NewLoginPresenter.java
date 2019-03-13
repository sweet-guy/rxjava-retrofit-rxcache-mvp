package com.wdcloud.myrxjavaorretrofit.activity.Presenter;

import android.app.Dialog;
import android.content.Context;

import com.wdcloud.myrxjavaorretrofit.activity.Model.LoginModel;
import com.wdcloud.myrxjavaorretrofit.activity.View.NewLoginView;
import com.wdcloud.myrxjavaorretrofit.base.presenter.BaseMvpPresenter;
import com.wdcloud.myrxjavaorretrofit.entity.NewLoginBean;
import com.wdcloud.myrxjavaorretrofit.server.api.ApiUrl;
import com.wdcloud.myrxjavaorretrofit.server.apiserver.ApiServer;
import com.wdcloud.myrxjavaorretrofit.server.cache.Repository;
import com.wdcloud.myrxjavaorretrofit.util.RxSchedulerHepler;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import static com.wdcloud.myrxjavaorretrofit.server.interceptor.HttpFactor.getApiService;
/**
 * Created by Umbrella on 2018/12/25.
 */

public class NewLoginPresenter extends BaseMvpPresenter<NewLoginView> implements LoginModel {
    private Context context;
    private NewLoginView newLoginView;
    private CompositeDisposable mCompositeSubscription;//统一管理retrofit请求
    private NewLoginBean newLoginBean;
    private Repository repository;
    public NewLoginPresenter(Context context,Repository repository) {
        this.context = context;
        this.repository=repository;
    }
    @Override
    public void initData() {
        newLoginView=getMvpView();
        mCompositeSubscription = new CompositeDisposable();
    }

    @Override
    public void getLoginData(final Dialog dialog, String account, String password, String yyxtdm, String yddbbh) {
//        dialog.show();
        newLoginView.showProgress();
        ApiServer apiService = getApiService(ApiUrl.BaseUrl);
        Observable<NewLoginBean> newLoginBeanObservable = apiService.getnewLogin(account, password, yyxtdm, yddbbh);
//        Repository repository = MyApplication.getRepository();
        Observable<NewLoginBean> allLessons = repository.getNewLogin(newLoginBeanObservable, 1, false);
        allLessons.compose(RxSchedulerHepler.<NewLoginBean>io_main())
                .subscribe(new Observer<NewLoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NewLoginBean newLoginBean) {
                        newLoginView.LoginSuccess(newLoginBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        newLoginView.hideProgress();
                    }

                    @Override
                    public void onComplete() {
                       newLoginView.hideProgress();
                    }
                });
//        apiService.getnewLogin(account,password,yyxtdm,yddbbh).compose(RxSchedulerHepler.<NewLoginBean>io_main())
//                .subscribe(new Observer<NewLoginBean>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(NewLoginBean newLoginBean) {
//                        newLoginView.LoginSuccess(newLoginBean);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        newLoginView.hideProgress();
////                        dialog.dismiss();
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        newLoginView.hideProgress();
////                        dialog.dismiss();
//                    }
//                });
    }
}
