package com.lead.security.web.interceptor;

import com.lead.security.service.security.SecurityService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FeignClientAuthInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private final SecurityService securityService;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        var token = securityService.getAccessToken();

        if (token != null) {
            requestTemplate.header(AUTHORIZATION_HEADER, token);
        }
    }
}
