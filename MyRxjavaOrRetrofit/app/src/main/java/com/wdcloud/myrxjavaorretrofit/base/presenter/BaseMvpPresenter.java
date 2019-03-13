package com.wdcloud.myrxjavaorretrofit.base.presenter;


import com.wdcloud.myrxjavaorretrofit.base.view.BaseMvpView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Umbrella on 2018/12/24.
 */

public class BaseMvpPresenter<T extends BaseMvpView> implements BasePresenter<T> {
    private T mvpView;

    @Override
    public void attachView(T mvpView) {
        this.mvpView = mvpView;
    }

    @Override
    public void detachView() {
        mvpView = null;
    }

    /**
     * 判断 view是否为空
     *
     * @return
     */
    public boolean isAttachView() {
        return mvpView != null;
    }

    /**
     * 返回目标view
     *
     * @return
     */
    public T getMvpView() {
        return mvpView;
    }

    /**
     * 检查view和presenter是否连接
     */
    public void checkViewAttach() {
        if (!isAttachView()) {
            throw new MvpViewNotAttachedException();
        }
    }
    public Map<String, Object> getMapParameter() {
        Map<String, Object> map = new HashMap<>();
        return map;
    }
    /**
     * 自定义异常
     */
    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("请求数据前请先调用 attachView(MvpView) 方法与View建立连接");
        }
    }
}
