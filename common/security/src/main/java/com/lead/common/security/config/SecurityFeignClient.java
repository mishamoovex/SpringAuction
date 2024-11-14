package com.lead.common.security.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("com.lead.common.security.web")
public class SecurityFeignClient {
}
