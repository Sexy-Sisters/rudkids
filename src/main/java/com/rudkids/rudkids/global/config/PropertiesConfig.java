package com.rudkids.rudkids.global.config;

import com.rudkids.rudkids.global.config.properties.GoogleProperties;
import com.rudkids.rudkids.global.config.properties.KakaoProperties;
import com.rudkids.rudkids.global.config.properties.SmsProperties;
import com.rudkids.rudkids.global.config.properties.TossPaymentProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({
    GoogleProperties.class,
    SmsProperties.class,
    KakaoProperties.class,
    TossPaymentProperties.class})
public class PropertiesConfig {
}
