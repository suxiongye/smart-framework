package org.su.framework.helper;

import org.su.framework.ConfigConstant;
import org.su.framework.util.PropsUtil;

import java.util.Properties;

public final class ConfigHelper {
    private static final Properties CONFIG_PROPS = PropsUtil.loadPros(ConfigConstant.CONFIG_FILE);

    //获取jdbc驱动
    public static String getJdbcDriver(){
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_DRIVER);
    }

    //获取jdbc URL
    public static String getJdbcUrl(){
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_URL);
    }

    //获取jdbc USERNAME
    public static String getJdbcUsername(){
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_USERNAME);
    }

    //获取jdbc PASSWORD
    public static String getJdbcPassword(){
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_PASSWORD);
    }

    //获取应用基础包名
    public static String getAppBasePackage(){
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_BASE_PACKAGE);
    }

    //获取应用jsp包名
    public static String getAppJspPath(){
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_JSP_PATH, "/WEB-INF/view/");
    }

    //获取静态资源路径
    public static String getAppAssetPath(){
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_ASSET_PATH, "/asset/");
    }
    //获取上传文件大小限制
    public static int getAppUploadLimit(){
        return PropsUtil.getInt(CONFIG_PROPS, ConfigConstant.APP_UPLOAD_LIMIT,10);
    }
    //根据属性名获取String类型属性值
    public static String getString(String key){
        return PropsUtil.getString(CONFIG_PROPS, key);
    }
    //根据属性名获取int类型属性值
    public static int getInt(String key){
        return PropsUtil.getInt(CONFIG_PROPS, key);
    }
    //根据属性名获取boolean类型属性值
    public static boolean getBoolean(String key){
        return PropsUtil.getBoolean(CONFIG_PROPS, key);
    }
}
