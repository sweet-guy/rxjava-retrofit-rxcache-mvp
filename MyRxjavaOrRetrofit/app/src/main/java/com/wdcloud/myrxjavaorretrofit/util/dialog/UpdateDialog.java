package com.wdcloud.myrxjavaorretrofit.util.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import com.wdcloud.myrxjavaorretrofit.R;

/**
 * Created by Umbrella on 2018/12/13.
 */

public class UpdateDialog extends AlertDialog{
    public UpdateDialog(Context context, int theme) {
        super(context, theme);
    }

    public UpdateDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog_two_layout);
    }
}
