package com.ohgiraffers.metachatbe.config;

import com.ohgiraffers.metachatbe.filter.ResponseFilter;
import com.ohgiraffers.metachatbe.summary.command.application.service.AiCommunicationService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<ResponseFilter> responseFilter(AiCommunicationService aiCommunicationService) {
        FilterRegistrationBean<ResponseFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new ResponseFilter(aiCommunicationService));
        registrationBean.addUrlPatterns("/voice/*"); // 필터를 적용할 경로

        return registrationBean;
    }
}