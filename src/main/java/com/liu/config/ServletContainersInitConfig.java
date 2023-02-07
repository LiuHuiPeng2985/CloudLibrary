package com.liu.config;

/*
 * @author  LiuHuiPeng
 * @date    2022/3/24 10:57
 */

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//用于初始化 Servlet 容器的配置类
public class ServletContainersInitConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
