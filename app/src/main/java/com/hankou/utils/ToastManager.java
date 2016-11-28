package com.hankou.utils;

import android.widget.LinearLayout;
import android.widget.Toast;
import com.hankou.base.HankouApplication;

/**
 * Created by 彭俊俊 on 15/9/12.
 * <p>
 * 显示自定义的Toast
 */
public class ToastManager {

    public static Toast mToast;

    public static LinearLayout mToastLayout;

    public static void showToast(int res) {
        if (mToast == null) {
            mToast = Toast.makeText(HankouApplication.getApplication(), "", Toast.LENGTH_SHORT);
            mToastLayout = (LinearLayout) mToast.getView();
        }
        mToast.setText(res);
        mToast.show();
    }

    public static void showToast(String res) {
        if (mToast == null) {
            mToast = Toast.makeText(HankouApplication.getApplication(), "", Toast.LENGTH_SHORT);
            mToastLayout = (LinearLayout) mToast.getView();
        }
        mToast.setText(res);
        mToast.show();
    }

}
