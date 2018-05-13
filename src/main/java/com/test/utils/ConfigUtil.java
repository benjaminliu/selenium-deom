package com.test.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by admin on 2018/4/30.
 */
public class ConfigUtil {
    private static Logger logger = LoggerFactory.getLogger(ConfigUtil.class);

    public static Properties loadConfig(String path) {
        BufferedInputStream in;
        Properties prop = new Properties();
        try {
            in = new BufferedInputStream(new FileInputStream(path));
            prop.load(in);
        } catch (Exception e) {
            logger.error("Error when loading Configuration File : " + path, e);
        }
        return prop;
    }
}
