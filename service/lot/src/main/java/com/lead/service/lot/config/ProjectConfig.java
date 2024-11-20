package com.lead.service.lot.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan({
        "com.lead.common",
        "com.lead.security"
})
public class ProjectConfig {
}
