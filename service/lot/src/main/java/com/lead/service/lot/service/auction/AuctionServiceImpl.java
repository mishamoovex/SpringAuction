package com.lead.service.lot.service.auction;

import com.lead.service.lot.web.AuctionServiceClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service("auctionService")
@AllArgsConstructor
public class AuctionServiceImpl implements AuctionService {

    private final AuctionServiceClient auctionServiceClient;

    @Override
    public boolean isAdmin(String auctionId, String userId) {
        return auctionServiceClient.isAdmin(auctionId);
    }
}
