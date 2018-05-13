package com.test.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;

/**
 * Created by admin on 2018/4/30.
 */
public class FileUtil {
    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static String userDirPath = System.getProperty("user.dir") + File.separator;
    public static String targetPath = userDirPath + "target" + File.separator;
    public static String surefireReportPath = targetPath + "surefire-reports" + File.separator;
    public static String screenShotPath = surefireReportPath + "screenShot" + File.separator;
    public static String configPath = userDirPath + "config" + File.separator;
    public static String dataPath = userDirPath + "data" + File.separator;
    public static String driverPath = userDirPath + "driver" + File.separator;

    //默认截图是jpg格式
    private static String pictureFormat = ".jpg";

    public static String classPathResourcePath(Class clazz, String path) {
        if (null == clazz)
            return null;
        if (StringUtils.isBlank(path))
            return null;

        try {
            URL resource = clazz.getResource("/" + path);
            if (null == resource)
                return null;

            return resource.getPath();
        } catch (Exception e) {

        }
        return null;
    }

    //获取项目根目录下的文件
    public static String getUserDirResourcePath(String file) {
        if (StringUtils.isBlank(file))
            return null;
        return userDirPath + file;
    }

    //获取项目根目录的config文件夹下的文件
    public static String configResoucePath(String file) {
        if (StringUtils.isBlank(file))
            return null;
        return configPath + file;
    }

    //获取项目根目录的data文件夹下的文件
    public static String dataResoucePath(String file) {
        if (StringUtils.isBlank(file))
            return null;
        return dataPath + file;
    }

    //获取项目根目录的driver文件夹下的文件
    public static String driverResourcePath(String file) {
        if (StringUtils.isBlank(file))
            return null;
        return driverPath + file;
    }

    public static String saveScreenShot(WebDriver driver, String name) throws Exception {
        if (null == driver)
            return null;
        if (StringUtils.isBlank(name))
            throw new Exception("请输入截图的名称");

        String picturePath = screenShotPath + name + "_" + LocalDateUtil.getNowDateTimeStrOnlyDigit() + pictureFormat;
        File jpg = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(jpg, new File(picturePath));
        logger.info("截图成功, 图片保存在: {}", picturePath);
        return picturePath;
    }
}
