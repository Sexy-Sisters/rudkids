package com.rudkids.rudkids.global.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.rudkids.rudkids.infrastructure.oauth")
public class FeignConfig {
}