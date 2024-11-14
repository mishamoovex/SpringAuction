package com.lead.service.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan({"com.lead.core", "com.lead.common.security"})
public class ProjectConfig {
}
