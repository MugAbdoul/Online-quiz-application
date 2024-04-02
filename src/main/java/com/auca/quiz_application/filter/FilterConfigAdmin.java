// package com.auca.quiz_application.filter;

// import org.springframework.boot.web.servlet.FilterRegistrationBean;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// public class FilterConfigAdmin {

//     @Bean
//     public FilterRegistrationBean<FilterAdmin> myFilter() {
//         FilterRegistrationBean<FilterAdmin> registrationBean = new FilterRegistrationBean<>();
//         registrationBean.setFilter(new FilterAdmin());
//         registrationBean.addUrlPatterns("/secured/*"); // URL patterns to which the filter should be applied
//         return registrationBean;
//     }
// }