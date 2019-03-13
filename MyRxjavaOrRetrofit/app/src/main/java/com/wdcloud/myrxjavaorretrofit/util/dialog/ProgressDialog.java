package com.wdcloud.myrxjavaorretrofit.util.dialog;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.wdcloud.myrxjavaorretrofit.R;

/**
 * Created by Umbrella on 2018/12/27.
 */

public class ProgressDialog {
    public Dialog setProgressbar(Context context)
    {
        Dialog progressDialog = new Dialog(context, R.style.progress_dialog);
        progressDialog.setContentView(R.layout.dialog);
        progressDialog.setCancelable(true);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        TextView msg = (TextView) progressDialog.findViewById(R.id.id_tv_loadingmsg);
        msg.setText("加载中");
        return progressDialog;
    }
}
