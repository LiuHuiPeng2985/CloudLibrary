package com.liu.config;

/*
 * @author  LiuHuiPeng
 * @date    2022/3/24 10:58
 */

import com.liu.interceptor.ResourcesInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

//Spring MVC 相关的配置
@Configuration
@PropertySource("classpath:ignoreUrl.properties")
@ComponentScan("com.liu.controller")
@EnableWebMvc
public class SpringMvcConfig implements WebMvcConfigurer {

    @Value("#{'${ignoreUrl}'.split(',')}")
    private List<String> ignoreUrl;
    @Bean
    public ResourcesInterceptor resourcesInterceptor(){
        return new ResourcesInterceptor(ignoreUrl);
    }

    /**
     * 视图解析
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/admin/",".jsp");
    }

    /**
     * 开启静态资源访问
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(resourcesInterceptor()).addPathPatterns("/**").excludePathPatterns("/css/**","/js/**","/img/**");
    }
}
