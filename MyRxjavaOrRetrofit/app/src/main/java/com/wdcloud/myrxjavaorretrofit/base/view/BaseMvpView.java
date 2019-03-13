package com.wdcloud.myrxjavaorretrofit.base.view;


/**
 * @ClassName :    BaseMvpView
 * @Describe :     View的基类
 * @Author :
 * @Time :        2017/4/5  15:55
 */

public interface BaseMvpView {
    /**这里可以放一些公用的view操作方法*/
    void showProgress();//可以显示进度条
    void hideProgress();//可以隐藏进度条
}
