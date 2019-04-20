package com.example.springbootdemo.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration
public class MyConfiguration extends WebMvcConfigurationSupport {
    private Logger log = LoggerFactory.getLogger(WebMvcConfigurationSupport.class);
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        log.info("MyWebConfig跨域拦截器开始处理----------");
        //设置允许跨域的路径
        registry.addMapping("/**")
                //设置允许跨域请求的域名
                .allowedOrigins("*")
                //是否允许证书 不再默认开启
                .allowCredentials(true)
                //设置允许的方法
                .allowedMethods("*")
                //跨域允许时间
                .maxAge(3600);
    }

}
