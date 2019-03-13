package com.wdcloud.myrxjavaorretrofit.util;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Message;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;

/**
 * Created by Umbrella on 2018/12/11.
 */

public class ProgressDialogHandler extends android.os.Handler{
    public static final int SHOW_PROGRESS_DIALOG = 1;
    public static final int DISMISS_PROGRESS_DIALOG = 2;
    private Context context;
    private boolean cancelable;
    private ProgressCancelListener mProgressCancelListener;

    private MaterialDialog mProgressDialog;
    public ProgressDialogHandler(Context context, ProgressCancelListener mProgressCancelListener, boolean cancelable) {
        super();
        this.context = context;
        this.mProgressCancelListener = mProgressCancelListener;
        this.cancelable = cancelable;
    }
    private void initProgressDialog()
    {
        if (mProgressDialog == null) {
            mProgressDialog = new MaterialDialog.Builder(context)
                    .canceledOnTouchOutside(cancelable)
                    .content("正在加载...")
                    .progress(true, 0)
                    .theme(Theme.LIGHT)
                    .build();
            mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    mProgressCancelListener.onCancelProgress();
                }
            });
            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }
        }
    }
    //隐藏dialog
    private void dismissProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }
    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {

            case SHOW_PROGRESS_DIALOG:
                initProgressDialog();
                break;

            case DISMISS_PROGRESS_DIALOG:
                dismissProgressDialog();
                break;

        }
    }
}
