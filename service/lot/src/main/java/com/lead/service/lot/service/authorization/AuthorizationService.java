package com.lead.service.lot.service.authorization;

public interface AuthorizationService {
    boolean isAuctionAdmin(String auctionId, String userId);

    boolean isLotAdmin(String lotId, String userId);
}
