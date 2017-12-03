package org.su.framework.util;

public final class CastUtil {
    //转为String类型
    public static String castString(Object object){
        return CastUtil.castString(object, "");
    }

    //转为String类型（提供默认值）
    public static String castString(Object object, String defaultValue){
        return object != null ? String.valueOf(object) : defaultValue;
    }

    //转为double类型
    public static double castDouble(Object object){
        return castDouble(object, 0);
    }

    //转为double类型（提供默认值）
    public static double castDouble(Object object, double defaultValue){
        double doubleValue = defaultValue;
        if(object != null){
            String strValue = castString(object);
            if(StringUtil.isNotEmpty(strValue)){
                try {
                    doubleValue = Double.parseDouble(strValue);
                }catch (NumberFormatException e){
                    doubleValue = defaultValue;
                }
            }
        }
        return doubleValue;
    }

    //转为long型
    public static long castLong(Object object){
        return castLong(object, 0);
    }
    //转为long型（提供默认值）
    public static long castLong(Object object, long defaultValue){
        long longValue = defaultValue;
        if(object != null){
            String strValue = castString(object);
            if(StringUtil.isNotEmpty(strValue)){
                try{
                    longValue = Long.parseLong(strValue);
                }catch (NumberFormatException e){
                    longValue = defaultValue;
                }
            }
        }
        return longValue;
    }

    //转为int型
    public static int castInt(Object object){
        return castInt(object, 0);
    }
    //转为int型（提供默认值）
    public static int castInt(Object object, int defaultValue){
        int intValue = defaultValue;
        if(object != null){
            String strValue = castString(object);
            if(StringUtil.isNotEmpty(strValue)){
                try {
                    intValue = Integer.parseInt(strValue);
                }catch (NumberFormatException e){
                    intValue = defaultValue;
                }
            }
        }
        return intValue;
    }

    //转为boolean
    public static boolean castBoolean(Object object){
        return castBoolean(object, false);
    }
    //转为boolean（提供默认值）
    public static boolean castBoolean(Object object, Boolean defaultValue){
        boolean boolValue = defaultValue;
        if (object != null){
            boolValue = Boolean.parseBoolean(castString(object));
        }
        return boolValue;
    }
}
