package org.su.framework;

import org.su.framework.helper.*;
import org.su.framework.util.ClassUtil;

public final class HelperLoader {
    public static void init(){
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                AopHelper.class,//需要在ioc之前加载，需先获取代理对象，然后才能通过iochelper进行依赖注入
                IocHelper.class,
                ControllerHelper.class
        };
        for (Class<?> cls : classList){
            ClassUtil.loadClass(cls.getName(), true);
        }
    }

}
