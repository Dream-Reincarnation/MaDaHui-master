package com.ajiani.maidahui.Utils.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class HttpNetWorkUtils {
    private HttpNetWorkUtils() {
    }

    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager cm = (ConnectivityManager)context.getSystemService("connectivity");
            NetworkInfo info = cm.getActiveNetworkInfo();
            if (info != null) {
                return info.isAvailable();
            }
        }

        return false;
    }

    public static boolean isWifiConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
        NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(1);
        return wifiInfo != null;
    }

    public static boolean isMobileNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
        NetworkInfo mobileNetworkInfo = connectivityManager.getNetworkInfo(0);
        return mobileNetworkInfo != null;
    }

    public static String getIPAddress(Context context) {
        NetworkInfo info = ((ConnectivityManager)context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            if (info.getType() == 0) {
                try {
                    Enumeration en = NetworkInterface.getNetworkInterfaces();

                    while(en.hasMoreElements()) {
                        NetworkInterface intf = (NetworkInterface)en.nextElement();
                        Enumeration enumIpAddr = intf.getInetAddresses();

                        while(enumIpAddr.hasMoreElements()) {
                            InetAddress inetAddress = (InetAddress)enumIpAddr.nextElement();
                            if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                                return inetAddress.getHostAddress();
                            }
                        }
                    }
                } catch (SocketException var6) {
                    var6.printStackTrace();
                }
            } else if (info.getType() == 1) {
                WifiManager wifiManager = (WifiManager)context.getSystemService("wifi");
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                String ipAddress = intIP2StringIP(wifiInfo.getIpAddress());
                return ipAddress;
            }
        }

        return null;
    }

    public static String intIP2StringIP(int ip) {
        return (ip & 255) + "." + (ip >> 8 & 255) + "." + (ip >> 16 & 255) + "." + (ip >> 24 & 255);
    }

    public static String getNewMac() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            Iterator var1 = all.iterator();

            while(var1.hasNext()) {
                NetworkInterface nif = (NetworkInterface)var1.next();
                if (nif.getName().equalsIgnoreCase("wlan0")) {
                    byte[] macBytes = nif.getHardwareAddress();
                    if (macBytes == null) {
                        return null;
                    }

                    StringBuilder res1 = new StringBuilder();
                    byte[] var5 = macBytes;
                    int var6 = macBytes.length;

                    for(int var7 = 0; var7 < var6; ++var7) {
                        byte b = var5[var7];
                        res1.append(String.format("%02X:", b));
                    }

                    if (res1.length() > 0) {
                        res1.deleteCharAt(res1.length() - 1);
                    }

                    return res1.toString();
                }
            }
        } catch (Exception var9) {
            var9.printStackTrace();
        }

        return null;
    }
}
