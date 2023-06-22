package com.rudkids.core.config;

import com.rudkids.core.config.properties.GoogleProperties;
import com.rudkids.core.config.properties.KakaoProperties;
import com.rudkids.core.config.properties.SmsProperties;
import com.rudkids.core.config.properties.TossPaymentProperties;
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
