package com.codedito.zuul;

import com.codedito.zuul.util.UserContextInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
@EnableZuulProxy
public class ZuulApplication {

    @Bean
    public RestTemplate getRestTemplate() {
        final RestTemplate template = new RestTemplate();
        final List interceptors = template.getInterceptors();

        if (interceptors == null) {
            template.setInterceptors(Collections.singletonList(new UserContextInterceptor()));
        } else {
            interceptors.add(new UserContextInterceptor());
            template.setInterceptors(interceptors);
        }

        return template;
    }

    public static void main(final String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }
}
