package com.lead.service.service.user;

import com.lead.service.model.dto.RemoteUserDetails;
import com.lead.service.web.UserServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RemoteUserDetailsServiceImpl implements RemoteUserDetailsService {

    private final UserServiceClient userServiceClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userServiceClient.getByEmail(username);
        if (user == null) throw new UsernameNotFoundException("User with username " + username + " not found");
        return new RemoteUserDetails(user);
    }
}
