package com.lead.service.user.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
        "com.lead.security",
        "com.lead.common"
})
class ProjectConfig {
}
