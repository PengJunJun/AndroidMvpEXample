package com.hankou.utils;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import com.hankou.base.HankouApplication;

import java.io.InputStream;

public class Res {
    private static Resources sResources = HankouApplication.getApplication().getResources();

    public static String getString(int resId) {
        return sResources.getString(resId);
    }

    public static String getString(int resId, Object... params) {
        return sResources.getString(resId, params);
    }

    public static String[] getStringArray(int arrayId) {
        return sResources.getStringArray(arrayId);
    }

    public static String getArrayString(int arrayId, int index) {
        TypedArray array = sResources.obtainTypedArray(arrayId);
        return array.getString(index);
    }

    public static int[] getIntArray(int arrayId) {
        return sResources.getIntArray(arrayId);
    }

    public static Drawable getDrawable(int drawableId) {
        return sResources.getDrawable(drawableId);
    }

    public static int getColor(int colorId) {
        return sResources.getColor(colorId);
    }

    public static InputStream openRaw(int resId) {
        return sResources.openRawResource(resId);
    }
}
