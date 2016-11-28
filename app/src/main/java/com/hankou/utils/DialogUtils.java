package com.hankou.utils;

import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.hankou.R;

/**
 * Created by bykj003 on 2016/11/3.
 */

public class DialogUtils {
    /**
     * 显示对话框
     *
     * @param context
     */
    public static void showBottomSheetDialog(Context context) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_bottom_sheet, null);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();
    }
}
