package org.su.framework.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.su.framework.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class BeanHelper {
    //定义Bean映射（存放Bean类和Bean实例的映射关系）
    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<Class<?>, Object>();
    static {
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        for (Class<?> beanClass: beanClassSet){
            Object object = ReflectionUtil.newInstance(beanClass);
            BEAN_MAP.put(beanClass, object);
        }
    }

    //获取bean映射
    public static Map<Class<?>, Object> getBeanMap(){
        return BEAN_MAP;
    }

    //获取BEAN实例
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> cls){
        if (!BEAN_MAP.containsKey(cls)){
            throw new RuntimeException("can not get bean by class:"+ cls);
        }
        return (T) BEAN_MAP.get(cls);
    }

    //设置bean实例
    public static void setBean(Class<?> cls, Object object){
        BEAN_MAP.put(cls, object);
    }
}
