package com.wdcloud.myrxjavaorretrofit.activity.Presenter;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;

import com.wdcloud.myrxjavaorretrofit.activity.Model.BookModel;
import com.wdcloud.myrxjavaorretrofit.activity.View.NewBookVIew;
import com.wdcloud.myrxjavaorretrofit.base.presenter.BaseMvpPresenter;
import com.wdcloud.myrxjavaorretrofit.entity.MyBooks;
import com.wdcloud.myrxjavaorretrofit.server.api.ApiUrl;
import com.wdcloud.myrxjavaorretrofit.server.apiserver.ApiServer;
import com.wdcloud.myrxjavaorretrofit.util.RxSchedulerHepler;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import static com.wdcloud.myrxjavaorretrofit.server.interceptor.HttpFactor.getApiService;

/**
 * Created by Umbrella on 2018/12/24.
 */

public class NewBookPresenter extends BaseMvpPresenter<NewBookVIew> implements BookModel{
    private Context context;
    private NewBookVIew newBookVIew;
    private CompositeDisposable mCompositeSubscription;//统一管理retrofit请求
    private MyBooks myBooks;
    public NewBookPresenter(Context context) {
        this.context = context;
    }
    @Override
    public void initData() {
        newBookVIew=getMvpView();
        mCompositeSubscription = new CompositeDisposable();
    }

    @Override
    public void getData(final Dialog dialog) {
        dialog.show();
        ApiServer apiService = getApiService(ApiUrl.BaseUrl);
        apiService.getAllBooks().compose((RxSchedulerHepler.<MyBooks>io_main()))
                .subscribe(new Observer<MyBooks>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("onSubscribe", "");
                    }

                    @Override
                    public void onNext(MyBooks myBooks) {
                        newBookVIew.onBookSuccess(myBooks);
                        Log.d("onNext", myBooks.getItems().toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        dialog.dismiss();
                        Log.d("onError", "");
                    }

                    @Override
                    public void onComplete() {
                        dialog.dismiss();
                        Log.d("onComplete", "");
                    }
                });
    }
    public void onStop() {
        if (mCompositeSubscription != null && !mCompositeSubscription.isDisposed()) {
            mCompositeSubscription.dispose();
        }
    }
}
