package com.lead.security.service.user;

import com.lead.security.model.AuthUserDetails;
import com.lead.security.web.UserDetailsClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    private final UserDetailsClient userServiceClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userServiceClient.getByEmail(username);
        if (user == null) throw new UsernameNotFoundException("User with username " + username + " not found");
        return new AuthUserDetails(user);
    }
}
