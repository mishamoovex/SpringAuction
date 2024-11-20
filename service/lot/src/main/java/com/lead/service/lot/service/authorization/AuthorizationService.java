package com.lead.service.lot.service.authorization;

public interface AuthorizationService {
    boolean isAdmin(String auctionId, String userId);
}
