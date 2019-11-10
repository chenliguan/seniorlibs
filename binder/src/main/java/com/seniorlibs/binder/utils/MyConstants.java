package com.seniorlibs.binder.utils;

import android.os.Environment;

public class MyConstants {

    /**
     * 得到内置存储的绝对路径
     */
    public static final String CHAPTER_2_PATH = Environment.getExternalStorageDirectory().getPath() + "/binder_client/";

    public static final String CACHE_FILE_PATH = CHAPTER_2_PATH + "usercache";

    public static final int MSG_FROM_CLIENT = 0;
    public static final int MSG_FROM_SERVICE = 1;

}
