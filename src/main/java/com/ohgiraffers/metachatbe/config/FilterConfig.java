package com.ohgiraffers.metachatbe.config;

import com.ohgiraffers.metachatbe.filter.ResponseFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<ResponseFilter> loggingFilter() {
        FilterRegistrationBean<ResponseFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new ResponseFilter());
        registrationBean.addUrlPatterns("/voice/*"); // 필터를 적용할 경로

        return registrationBean;
    }
}