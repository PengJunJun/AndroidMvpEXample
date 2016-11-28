package com.hankou.utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by bykj003 on 2016/9/6.
 */
public class Constant {
    public static final int TAKE_PHOTO_CODE = 0;
    public static final int OPEN_GALLERY_CODE = 1;
    public static final String PHOTO_SAVE_PATH = Environment.getExternalStorageDirectory() +
            File.separator + "hankou";
    public static boolean isChangeTheme = false;
}
