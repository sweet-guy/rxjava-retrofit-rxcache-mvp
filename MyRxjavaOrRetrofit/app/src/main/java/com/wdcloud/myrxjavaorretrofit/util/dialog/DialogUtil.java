package com.wdcloud.myrxjavaorretrofit.util.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.wdcloud.myrxjavaorretrofit.R;
import com.wdcloud.myrxjavaorretrofit.base.BaseActivity;
import com.wdcloud.myrxjavaorretrofit.util.listener.RemindSelect;
import com.wdcloud.myrxjavaorretrofit.util.listener.SelectKeShiListener;
import com.wdcloud.myrxjavaorretrofit.util.listener.SelectTimeEnum;
import com.wdcloud.myrxjavaorretrofit.util.listener.SelectTimeListener;
import com.wdcloud.myrxjavaorretrofit.util.listener.TimeSelectListenerImp;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;




/**
 * Created by Umbrella on 2018/5/17.
 */

public class DialogUtil {
    private PopupWindow popupWindow;
    private PopupWindow popupWindowInput;
    // 设置popupWindow背景半透明  1.0f  0.5f
    public void backgroundAlpha(BaseActivity activity, float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        activity.getWindow().setAttributes(lp);
    }

    public void showSettingPermisson(BaseActivity activity) {
        new AlertDialog.Builder(activity)
                .setPositiveButton("确认", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    getAppDetailSettingIntent(activity);
                })
                .setNegativeButton("拒绝", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                })
                .setCancelable(false)
                .setMessage("不再允许询问该权限该功能不可用,请去设置去授权")
                .show();
    }

    private void getAppDetailSettingIntent(BaseActivity activity) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", activity.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", activity.getPackageName());
        }
        activity.startActivity(localIntent);
    }

//    public void showPermissionDialog(BaseActivity activity, PermissionRequest request) {
//        showPermissionDialog(activity, request, "需要相机和存储读写权限");
//    }

//    public void showPermissionDialog(BaseActivity activity, PermissionRequest request, String s) {
//        new AlertDialog.Builder(activity)
//                .setPositiveButton("允许", (dialog, which) -> {
//                    request.proceed();
//                })
//                .setNegativeButton("拒绝", (dialog, which) -> request.cancel())
//                .setCancelable(false)
//                .setMessage(s)
//                .show();
//    }

    private void setPopuBackColor(PopupWindow popupWindow) {
        ColorDrawable dw = new ColorDrawable(-00000);
        popupWindow.setBackgroundDrawable(dw);
    }

    public void dismissPopupwindow() {
        if (popupWindow != null) {
            popupWindow.dismiss();
            popupWindow = null;
        }
    }

    public void dismiss() {
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }

    public void dismissInputPopupwindow() {
        if (popupWindowInput != null) {
            popupWindowInput.dismiss();
            popupWindowInput = null;
        }
    }

    public void showKeShi(Context context, @NonNull List<String> list, SelectKeShiListener l) {
        OptionsPickerView optionsPickerView = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                l.selectListener(list.get(options1));
            }
        }).setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)//设置文字大小
                .setOutSideCancelable(false)// default is true
                .setCyclic(true, true, true)
                .build();
        optionsPickerView.setPicker(list);//条件选择器
        optionsPickerView.show();
    }

    public void scheduleRemindDialog(Context context, String[] remindArr, RemindSelect l) {
        OptionsPickerView optionsPickerView = new OptionsPickerView.Builder(context,
                (options1, options2, options3, v)
                        -> l.remindSelect(options1, remindArr[options1])).setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)//设置文字大小
                .setOutSideCancelable(false)// default is true
                .setCyclic(true, true, true)
                .build();
        optionsPickerView.setPicker(Arrays.asList(remindArr));//条件选择器
        optionsPickerView.show();
    }
    /*日期选择器 SelectTimeListener*/
    public void shoTimeSelectDialog(BaseActivity baseActivity, int index, SelectTimeListener listener) {
        shoTimeSelectDialog(baseActivity, index, true, false, false, false, SelectTimeEnum.def, listener);
    }

    public void shoTimeSelectDialog(BaseActivity baseActivity, int index, SelectTimeEnum timeEnum, SelectTimeListener listener) {
        shoTimeSelectDialog(baseActivity, index, true, false, false, false, timeEnum, listener);
    }

    public void shoTimeSelectDialog(BaseActivity baseActivity, int index,
                                    boolean isShowYY,
                                    boolean isShowHH,
                                    boolean isShowMM,
                                    boolean isShowSS,
                                    SelectTimeListener listener){
        shoTimeSelectDialog(baseActivity, index, isShowYY, isShowHH, isShowMM, isShowSS, SelectTimeEnum.def, listener);

    }

    public void shoTimeSelectDialog(BaseActivity baseActivity, int index,
                                    boolean isShowYY,
                                    boolean isShowHH,
                                    boolean isShowMM,
                                    boolean isShowSS,
                                    SelectTimeEnum timeEnum,
                                    SelectTimeListener listener) {
//因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        Calendar[] dates = getCalendar(timeEnum);
        TimePickerView timePickerView =
                new TimePickerView.Builder(baseActivity, new TimeSelectListenerImp(listener, index))
                        .setType(new boolean[]{isShowYY, true, true, isShowHH, isShowMM, isShowSS})// 默认全部显示
                        .setTitleBgColor(ContextCompat.getColor(baseActivity, R.color.baise))//标题背景颜色 Night mode
                        .setCancelText("取消")//取消按钮文字
                        .setSubmitText("确定")//确认按钮文字
                        .setContentSize(16)//滚轮文字大小
                        .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                        .isCyclic(true)//是否循环滚动
                        .setSubmitColor(ContextCompat.getColor(baseActivity, R.color.font_blue))//确定按钮文字颜色
                        .setCancelColor(ContextCompat.getColor(baseActivity, R.color.c1))//取消按钮文字颜色

                        .setRangDate(dates[0], dates[1])//起始终止年月日设定
                        .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
                        .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                        .build();
        timePickerView.setDate(Calendar.getInstance());
        timePickerView.show();

    }

    private Calendar[] getCalendar(SelectTimeEnum timeEnum) {
        Calendar[] calendars = new Calendar[2];
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        if (timeEnum == SelectTimeEnum.TK) {
            startDate.set(1970,0,1);
            endDate.set(2025, 0, 1);
//            startDate.setTime(new Date(0));//代表是听课
//            endDate.setTime(new Date());
        } else if (timeEnum == SelectTimeEnum.BK) {
            startDate.set(1970,0,1);
            endDate.set(2025, 0, 1);
//            startDate.setTime(new Date(0));//代表是备课
//            endDate.setTime(new Date());
        } else if (timeEnum == SelectTimeEnum.SX) {
            startDate.set(1970,0,1);
            endDate.set(2025, 0, 1);
//            startDate.setTime(new Date(0));//代表是筛选的时间
//            endDate.setTime(new Date());
        } else {
            startDate.set(1970,0,1);
            endDate.set(2025, 0, 1);
//            startDate.setTime(new Date(0));//代表是默认的
//            endDate.setTime(new Date());
        }

        calendars[0] = startDate;
        calendars[1] = endDate;
        return calendars;
    }

    public PopupWindow getPopupWindow() {
        return popupWindow;
    }

    public enum Location {

        LEFT,
        RIGHT,
        TOP,
        BOTTOM;

    }

}
