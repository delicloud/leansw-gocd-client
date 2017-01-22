package com.thoughtworks.lean.gocd.config;

import com.thoughtworks.lean.gocd.impl.GoClientImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {

    @Bean
    public RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate;
    }

    @Bean
    public GoClientImpl getGoClientImpl () {
        return new GoClientImpl("http://gocd-server:8153/go/", "admin", "badger");
    }


}
