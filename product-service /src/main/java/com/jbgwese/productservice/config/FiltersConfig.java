package com.jbgwese.productservice.config;

import com.jbgwese.productservice.filters.RequestResponseLoggers;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FiltersConfig {
    @Bean
    FilterRegistrationBean<RequestResponseLoggers> createLoggers(RequestResponseLoggers requestResponseLoggers){
        FilterRegistrationBean<RequestResponseLoggers>registrationBean= new FilterRegistrationBean<>();
        registrationBean.setFilter(requestResponseLoggers);
        //only endpoints listed here gets logged
        registrationBean.addUrlPatterns("/products/*");
        return registrationBean;
    }
}
