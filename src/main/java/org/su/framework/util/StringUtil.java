package org.su.framework.util;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {
    //字符串分隔符
    public static final String SEPARATOR = String.valueOf((char)29);
    //判断字符串是否为空
    public static boolean isEmpty(String str){
        if(str != null){
            str = str.trim();
        }
        return StringUtils.isEmpty(str);
    }

    //判断字符串是否非空
    public static boolean isNotEmpty(String string){
        return !isEmpty(string);
    }
}
