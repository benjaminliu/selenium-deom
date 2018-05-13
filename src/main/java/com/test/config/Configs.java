package com.test.config;

import com.test.utils.ConfigUtil;
import com.test.utils.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Created by admin on 2018/4/30.
 */
public class Configs {
    private static Logger logger = LoggerFactory.getLogger(Configs.class);

    private static final String path = "config.properties";
    private static volatile Configs instance;
    private static Object lockObj = new Object();

    public static Configs getInstance() {
        if (instance == null) {
            synchronized (lockObj) {
                if (null == instance)
                    instance = new Configs();
            }
        }
        return instance;
    }

    private Properties properties;
    public int RetryTime;
    public int RetryWait;
    public long TimeOut;
    public String BaseUrl;

    private Configs() {
        String realPath = FileUtil.configResoucePath(path);
        properties = ConfigUtil.loadConfig(realPath);

        RetryTime = getPropertyInt("RetryTime");
        RetryWait = getPropertyInt("RetryWait");
        TimeOut = getPropertyLong("TimeOut");
    }

    public String getPropertyString(String key) {
        Object o = getProperty(key);
        if (null != o) {
            return o.toString();
        }
        return null;
    }

    public int getPropertyInt(String key) {
        String s = getPropertyString(key);
        if (StringUtils.isNoneBlank(s)) {
            try {
                int res;
                res = Integer.parseInt(s);
                return res;
            } catch (Exception e) {

            }
        }
        return 0;
    }

    public long getPropertyLong(String key) {
        String s = getPropertyString(key);
        if (StringUtils.isNoneBlank(s)) {
            try {
                Long res;
                res = Long.parseLong(s);
                return res;
            } catch (Exception e) {

            }
        }
        return 0;
    }

    public Object getProperty(String key) {
        return properties.get(key);
    }

    public static void main(String[] args) {
        Configs configs = new Configs();
        logger.info("{}", configs.properties.size());
    }
}
