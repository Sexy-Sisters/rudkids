package com.rudkids.core.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {
    "com.rudkids.core.auth.infrastructure",
    "com.rudkids.core.payment.infrastructure",
    "com.rudkids.core.schedule.infrastructure"
})
public class FeignConfig {
}