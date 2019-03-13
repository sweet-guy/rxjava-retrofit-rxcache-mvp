package com.wdcloud.myrxjavaorretrofit.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

	private static Toast mToast;

	/**
	 * @param context
	 *            上下文
	 * @param
	 *
	 * @param message
	 *            提示信息
	 */
	public static void showToast(Context context, String message) {
		if (mToast == null) {// 当mToast==null新建，否则只改变提示信息
			mToast = Toast.makeText(context, message, 0);
		}
		mToast.setText(message);
		mToast.show();
	}
}
