package com.seniorlibs.event.listener;

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2020/7/7.
 * Mender:
 * Modify:
 * Description: 全局监听器接口
 */
public interface IBrowserListener {

    /**
     * 打开Url
     *
     * @param url
     */
    void openUrl(String url);

    /**
     * 获取JS
     *
     * @return
     */
    String getJs();

    /**
     * 返回书架
     *
     * @return
     */
    void gotoBookShelf();

    /**
     * 分享
     *
     * @param title
     * @param url
     * @param description
     * @param shareType，包括类型如下
     *     public static final int SHARE_TYPE_UNKOWN = 0;
     *     public static final int SHARE_TYPE_IMG = 1;
     *     public static final int SHARE_TYPE_TEXT = 4;
     *     public static final int SHARE_TYPE_IMG_AND_TEXT = 8;
     *     public static final int SHARE_TYPE_IMG_LOCAL = 16;
     *     public static final int SHARE_TYPE_NEWS_LIST = 32;
     * @param imageUrl
     */
    void share(String title, String url, String description, int shareType, String imageUrl);
}
