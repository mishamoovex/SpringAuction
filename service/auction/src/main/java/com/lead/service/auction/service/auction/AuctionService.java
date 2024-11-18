package com.lead.service.auction.service.auction;

import com.lead.service.auction.models.dto.AuctionDto;
import com.lead.service.auction.models.request.CreateAuctionRequest;

public interface AuctionService {
    AuctionDto save(String ownerId, CreateAuctionRequest createAuctionRequest);

    void delete(String auctionId);

    boolean isOwner(String auctionId, String ownerId);
}
