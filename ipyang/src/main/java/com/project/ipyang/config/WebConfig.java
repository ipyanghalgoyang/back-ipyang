package com.project.ipyang.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//    private String resourcePath = "/upload/**";  //view 에서 접근할 경로
//    private String savePath = "classpath:/static/images/";
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler(resourcePath)
//                .addResourceLocations(savePath);
//    }
//
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(new ByteArrayHttpMessageConverter());
//        converters.add(new ResourceHttpMessageConverter());
//    }
//
//}