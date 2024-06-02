package com.example.image_api.imagaa.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;

import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.apache.http.HttpHeaders.AUTHORIZATION;


public class ImagaaFeignClientConfiguration {

    @Bean
    public addAuthRequestInterceptor addFixRequestInterceptor() {
        return new addAuthRequestInterceptor();
    }

    class addAuthRequestInterceptor implements RequestInterceptor {
        @Override
        public void apply(RequestTemplate template) {
            String credentialsToEncode = "acc_f3de4ef38be7b41" + ":" + "d56e815105a31e7525cf41883cabf6ed";
            String basicAuth = Base64.getEncoder().encodeToString(credentialsToEncode.getBytes(StandardCharsets.UTF_8));
            String authorizationHeader = "Basic " + basicAuth;
            template.header(AUTHORIZATION, authorizationHeader);
        }
    }
}
