package org.su.framework.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.su.framework.annotation.Inject;
import org.su.framework.util.ArrayUtil;
import org.su.framework.util.CollectionUtil;
import org.su.framework.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

public final class IocHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(IocHelper.class);
    static {
        //获取所有Bean类和Bean实例关系
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (CollectionUtil.isNotEmpty(beanMap)) {
            //遍历map，对有inject注解的变量进行注入
            for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
                //获取类和实例
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                //获取Bean类定义的所有成员变量
                Field[] beanFields = beanClass.getDeclaredFields();
                if (ArrayUtil.isNotEmpty(beanFields)) {
                    for (Field beanField : beanFields) {
                        //判断是否有带inject注解
                        if (beanField.isAnnotationPresent(Inject.class)){
                            //获取实例进行注入
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            if (beanFieldInstance != null){
                                //初始化对应field变量值
                                ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
                                LOGGER.error("inject: " + beanInstance.toString() + " "+ beanField.toString() + " "+beanFieldInstance.toString());
                            }
                        }
                    }
                }
            }
        }
    }
}
