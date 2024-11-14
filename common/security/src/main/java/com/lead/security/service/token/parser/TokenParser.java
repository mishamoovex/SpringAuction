package com.lead.security.service.token.parser;

import com.lead.security.model.AuthTokenDetails;

public interface TokenParser {
    AuthTokenDetails parse(String token);
}
