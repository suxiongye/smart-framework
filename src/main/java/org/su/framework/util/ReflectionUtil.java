package org.su.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class ReflectionUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);

    //创建实例
    public static Object newInstance(Class<?> cls){
        Object instance;
        try {
            instance = cls.newInstance();
        }catch (Exception e){
            LOGGER.error("new instance failure", e);
            throw new RuntimeException(e);
        }
        return  instance;
    }

    //根据类名创建实例
    public static Object newInstance(String className){
        Class<?> cls = ClassUtil.loadClass(className);
        return newInstance(cls);
    }

    //调用方法
    public static Object invokeMethod(Object object, Method method, Object... args){
        Object result;
        try {
            method.setAccessible(true);
            result = method.invoke(object, args);
        }catch (Exception e){
            LOGGER.error("invoke method failure", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    //设置成员变量
    public static void setField(Object object, Field field, Object value){
        try {
            field.setAccessible(true);
            field.set(object, value);
        }catch (Exception e){
            LOGGER.error("set field failure", e);
            throw new RuntimeException(e);
        }
    }


}