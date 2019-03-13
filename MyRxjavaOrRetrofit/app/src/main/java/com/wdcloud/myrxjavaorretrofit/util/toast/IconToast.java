package com.wdcloud.myrxjavaorretrofit.util.toast;

import android.content.Context;
import android.os.Handler;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by EarlyHeart on 2018/3/11.
 */

public class IconToast{
    public static IconToast mIconToast;
    private static Toast toast;
    private static Handler mHandler = new Handler();

    private IconToast() {
    }

    public static IconToast getToast() {
        if (mIconToast == null) {
            mIconToast = new IconToast();
        }
        return mIconToast;
    }

    public static final int ALERT_NO_ICON = -1;
    /**
     * 显示
     */
    public void ToastShow(Context context, String str, int iconId, int layouId, int ivId, int textId) {

        if(context==null || str==null){
            return;
        }

        View view = LayoutInflater.from(context).inflate(layouId, null);
//        View.inflate()
        TextView text = view.findViewById(textId);
        if(text == null){
            return;
        }
        ImageView icon = view.findViewById(ivId);
        if(icon == null){
            return;
        }
        if(iconId == ALERT_NO_ICON){
            icon.setVisibility(View.GONE);
            text.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        }else {
            icon.setVisibility(View.VISIBLE);
            icon.setImageResource(iconId);
        }
        if(str != null && str.length() > 12){
            text.setTextSize(TypedValue.COMPLEX_UNIT_DIP,16);
        }

        text.setText(str); // 设置显示文字
        if(toast == null){
            toast = new Toast(context);
            toast.setGravity(Gravity.CENTER, 0, 0); // Toast显示的位置
            toast.setDuration(Toast.LENGTH_SHORT); // Toast显示的时间
        }

        toast.setView(view);

        toast.show();
    }

    public void ToastShow(Context context, String str, int iconId, int layouId, int ivId, int textId, int textSizeDip) {

        if(context==null || str==null){
            return;
        }

        View view = LayoutInflater.from(context).inflate(layouId, null);
//        View.inflate()
        TextView text = view.findViewById(textId);
        if(text == null){
            return;
        }
        ImageView icon = view.findViewById(ivId);
        if(icon == null){
            return;
        }
        text.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSizeDip);
        if(iconId == ALERT_NO_ICON){
            icon.setVisibility(View.GONE);
        }else {
            icon.setVisibility(View.VISIBLE);
            icon.setImageResource(iconId);
        }

        text.setText(str); // 设置显示文字
        if(toast == null){
            toast = new Toast(context);
            toast.setGravity(Gravity.CENTER, 0, 0); // Toast显示的位置
            toast.setDuration(Toast.LENGTH_SHORT); // Toast显示的时间
        }

        toast.setView(view);


        toast.show();
    }

    public void toastCancel() {
        if (toast != null) {
            toast.cancel();
        }
    }
}