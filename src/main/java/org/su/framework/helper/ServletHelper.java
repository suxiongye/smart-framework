package org.su.framework.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public final class ServletHelper {
    private final static Logger LOGGER = LoggerFactory.getLogger(ServletHelper.class);

    //使得线程独自有一份servletHelper实例
    private static final ThreadLocal<ServletHelper> SERVLET_HELPER_HOLDER = new ThreadLocal<>();
    private HttpServletRequest request;
    private HttpServletResponse response;

    private ServletHelper(HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        this.response = response;
    }

    //初始化
    public static void init(HttpServletRequest request, HttpServletResponse response){
        SERVLET_HELPER_HOLDER.set(new ServletHelper(request, response));
    }

    //销毁
    public static void destroy(){
        SERVLET_HELPER_HOLDER.remove();
    }

    //获取request对象
    private static HttpServletRequest getRequest(){
        return SERVLET_HELPER_HOLDER.get().request;
    }

    //获取response对象
    private static HttpServletResponse getResponse(){
        return SERVLET_HELPER_HOLDER.get().response;
    }

    //获取session对象
    private static HttpSession getSession(){
        return getRequest().getSession();
    }

    //获取ServletContext对象
    private static ServletContext getServletContext(){
        return getRequest().getServletContext();
    }

    //将属性放入request
    public static void setRequestAttribute(String key, Object value){
        getRequest().setAttribute(key, value);
    }

    //从request中获取属性
    @SuppressWarnings("unchecked")
    public static<T> T getRequestAttribute(String key){
        return (T) getRequest().getAttribute(key);
    }

    //从request中移除属性
    public static void removeRequestAttribute(String key){
        getRequest().removeAttribute(key);
    }

    //重定向响应
    public static void sendRedirect(String location){
        try {
            getResponse().sendRedirect(getRequest().getContextPath() + location);
        }catch (IOException e){
            LOGGER.error("redirect failure", e);
        }
    }

    //将属性放入session中
    public static void setSessionAttribute(String key, Object value){
        getSession().setAttribute(key, value);
    }

    //从session中获取属性
    public static <T> T getSessionAttribute(String key){
        return (T) getSession().getAttribute(key);
    }

    //从session中移除属性
    public void removeSessionAttribute(String key){
        getSession().removeAttribute(key);
    }

    //使session失效
    public static void invalidateSession(){
        getSession().invalidate();
    }
}
