package com.wdcloud.myrxjavaorretrofit.activity.Presenter;

import android.app.Dialog;
import android.content.Context;

import com.wdcloud.myrxjavaorretrofit.activity.Model.BookModel;
import com.wdcloud.myrxjavaorretrofit.activity.Model.FetUserModel;
import com.wdcloud.myrxjavaorretrofit.activity.View.NewBookVIew;
import com.wdcloud.myrxjavaorretrofit.activity.View.NewFetUserView;
import com.wdcloud.myrxjavaorretrofit.base.presenter.BaseMvpPresenter;
import com.wdcloud.myrxjavaorretrofit.entity.FetUserBean;
import com.wdcloud.myrxjavaorretrofit.entity.MyBooks;
import com.wdcloud.myrxjavaorretrofit.server.api.ApiUrl;
import com.wdcloud.myrxjavaorretrofit.server.apiserver.ApiServer;
import com.wdcloud.myrxjavaorretrofit.util.RxSchedulerHepler;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.SafeObserver;

import static com.wdcloud.myrxjavaorretrofit.server.interceptor.HttpFactor.getApiService;

/**
 * Created by Umbrella on 2018/12/25.
 */

public class NewFetUserPresenter extends BaseMvpPresenter<NewFetUserView> implements FetUserModel {
    private Context context;
    private NewFetUserView newFetUserView;
    private CompositeDisposable mCompositeSubscription;//统一管理retrofit请求
    private FetUserBean fetUserBean;
    public NewFetUserPresenter(Context context) {
        this.context = context;
    }
    @Override
    public void initData() {
        newFetUserView=getMvpView();
        mCompositeSubscription = new CompositeDisposable();
    }

    @Override
    public void getFetUserData(final Dialog dialog, String loginid, String logintype) {
        dialog.show();
        ApiServer apiService = getApiService(ApiUrl.BaseUrl);
        apiService.getFetUser(loginid,logintype).compose(RxSchedulerHepler.<FetUserBean>io_main())
                .subscribe(new Observer<FetUserBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FetUserBean fetUserBean) {

                        newFetUserView.onFetUserSuccess(fetUserBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        dialog.dismiss();
                    }

                    @Override
                    public void onComplete() {
                        dialog.dismiss();
                    }
                });
    }
}
