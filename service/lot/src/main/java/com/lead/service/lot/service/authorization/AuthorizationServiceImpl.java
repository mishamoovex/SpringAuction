package com.lead.service.lot.service.authorization;

import com.lead.service.lot.service.lot.LotService;
import com.lead.service.lot.web.AuctionServiceClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service("authorizationService")
@AllArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {

    private final AuctionServiceClient auctionServiceClient;
    private final LotService lotService;

    @Override
    public boolean isAuctionAdmin(String auctionId, String userId) {
        return auctionServiceClient.isAdmin(auctionId, userId);
    }

    @Override
    public boolean isLotAdmin(String lotId, String userId) {
        var lot = lotService.getById(lotId);
        return auctionServiceClient.isAdmin(lot.getAuctionId(), userId);
    }
}
