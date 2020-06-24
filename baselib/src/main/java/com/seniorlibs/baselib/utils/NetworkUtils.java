package com.seniorlibs.baselib.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.annotation.StringDef;
import android.telephony.TelephonyManager;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2020/6/24
 * Mender:
 * Modify:
 * Description: 网络工具类
 */
public class NetworkUtils {

    // 网络类型
    @StringDef({NETWORK_TYPE.UNKNOWN, NETWORK_TYPE.WIFI, NETWORK_TYPE.DATA_NET_2G, NETWORK_TYPE.DATA_NET_3G, NETWORK_TYPE.DATA_NET_4G, NETWORK_TYPE.DATA_NET_5G})
    @Retention(RetentionPolicy.SOURCE)
    public @interface NETWORK_TYPE {
        String UNKNOWN = "UNKNOWN";   // 未知
        String WIFI = "WIFI";
        String DATA_NET_2G = "2G";
        String DATA_NET_3G = "3G";
        String DATA_NET_4G = "4G";
        String DATA_NET_5G = "5G";
    }

    private static final String TAG = "NetWorkTools";


    /**
     * 判断当前网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        if (context == null) {
            return false;
        }

        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager == null) {
            return false;
        }

        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null) {
            return networkInfo.isAvailable();
        }

        return false;
    }

    /**
     * 判断WIFI是否接连
     *
     * @param context
     * @return
     */
    public static boolean isWifiConnected(Context context) {
        if (context == null) {
            return false;
        }

        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager == null) {
            return false;
        }

        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }

        return false;
    }

    /**
     * 获取网络类型
     *
     * @param context
     * @return
     */
    public static String getNetworkType(Context context) {
        String networkType = NETWORK_TYPE.UNKNOWN;

        if (context == null) {
            return networkType;
        }

        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager != null) {
            NetworkInfo networkInfo = manager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                int type = networkInfo.getType();
                if (type == ConnectivityManager.TYPE_WIFI) {
                    networkType = NETWORK_TYPE.WIFI;
                } else if (type == ConnectivityManager.TYPE_MOBILE) {
                    int subType = networkInfo.getSubtype();
                    switch (subType) {
                        case TelephonyManager.NETWORK_TYPE_GPRS:
                        case TelephonyManager.NETWORK_TYPE_EDGE:
                        case TelephonyManager.NETWORK_TYPE_CDMA:
                        case TelephonyManager.NETWORK_TYPE_1xRTT:
                        case TelephonyManager.NETWORK_TYPE_IDEN: //api<8 : replace by 11
                            networkType = NETWORK_TYPE.DATA_NET_2G;
                            break;
                        case TelephonyManager.NETWORK_TYPE_UMTS:
                        case TelephonyManager.NETWORK_TYPE_EVDO_0:
                        case TelephonyManager.NETWORK_TYPE_EVDO_A:
                        case TelephonyManager.NETWORK_TYPE_HSDPA:
                        case TelephonyManager.NETWORK_TYPE_HSUPA:
                        case TelephonyManager.NETWORK_TYPE_HSPA:
                        case TelephonyManager.NETWORK_TYPE_EVDO_B: //api<9 : replace by 14
                        case TelephonyManager.NETWORK_TYPE_EHRPD:  //api<11 : replace by 12
                        case TelephonyManager.NETWORK_TYPE_HSPAP:  //api<13 : replace by 15
                            networkType = NETWORK_TYPE.DATA_NET_3G;
                            break;
                        case TelephonyManager.NETWORK_TYPE_LTE:    //api<11 : replace by 13
                            networkType = NETWORK_TYPE.DATA_NET_4G;
                            break;
                        default:
                            // http://baike.baidu.com/item/TD-SCDMA 中国移动 联通 电信 三种3G制式
                            String subTypeName = networkInfo.getSubtypeName();
                            if ("TD-SCDMA".equalsIgnoreCase(subTypeName) || "WCDMA".equalsIgnoreCase(subTypeName) || "CDMA2000".equalsIgnoreCase(subTypeName)) {
                                networkType = NETWORK_TYPE.DATA_NET_3G;
                            } else {
                                networkType = subTypeName;
                            }
                            break;
                    }
                }
            }
        }

        return networkType;
    }

    /**
     * 获取当前已连接的wifi热点的名称
     *
     * @param context
     * @return
     */
    public static String getCurrConnectWifiSSID(Context context) {
        if (context == null) {
            return null;
        }

        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        if (wifiInfo != null) {
            return wifiInfo.getSSID();
        }

        return null;
    }

    /**
     * 打开网络设置界面
     *
     * @param context
     */
    public static void openNetworkSettings(Context context) {
        if (context == null) {
            return;
        }

        Intent intent = null;

        if (android.os.Build.VERSION.SDK_INT > 10) {
            intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
        } else {
            intent = new Intent();
            ComponentName component = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
            intent.setComponent(component);
            intent.setAction("android.intent.action.VIEW");
        }

        context.startActivity(intent);
    }
}
