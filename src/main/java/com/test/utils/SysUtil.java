package com.test.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

/**
 * Created by admin on 2018/4/30.
 */
public class SysUtil {
    public static void sleepSecond(int s) {
        try {
            Thread.sleep(s * 1000);
        } catch (Exception var2) {
        }
    }

    public static String getIpAddress() {
        InetAddress address = null;

        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException var2) {
            var2.printStackTrace();
        }

        return address.getHostAddress();
    }

    public static String getOSUser() {
        String os = null;
        Properties props = System.getProperties();
        os = props.getProperty("user.name");
        return os;
    }

    public static String getOSInfo() {
        Properties props = System.getProperties();
        String osName = props.getProperty("os.name");
        String osVersion = props.getProperty("os.version");
        String rs = osName + "-v" + osVersion;
        return rs;
    }
}
