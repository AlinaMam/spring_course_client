package com.zaurtregulov.spring.rest.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
//для совершения http запросов из rest клиента мы будем использовать вспомогательный класс, который предоставляется Spring-ом
//этот класс называется ResTemplate, создадим bean этого класса
@Configuration
@ComponentScan(basePackages = "com.zaurtregulov.spring.rest")
public class MyConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();//бин для осуществления http request
    }
}
