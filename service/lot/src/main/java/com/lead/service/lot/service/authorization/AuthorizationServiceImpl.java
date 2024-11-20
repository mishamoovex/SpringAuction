package com.lead.service.lot.service.authorization;

import com.lead.service.lot.web.AuctionServiceClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service("authorizationService")
@AllArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {

    private final AuctionServiceClient auctionServiceClient;

    @Override
    public boolean isAdmin(String auctionId, String userId) {
        return auctionServiceClient.isAdmin(auctionId, userId);
    }
}
