package com.lead.service.auction.service.auction;

import com.lead.service.auction.models.dto.AuctionDto;
import com.lead.service.auction.models.request.CreateAuctionRequest;
import com.lead.service.auction.models.request.UpdateAuctionRequest;

public interface AuctionService {
    AuctionDto save(String ownerId, CreateAuctionRequest createAuctionRequest);

    AuctionDto update(UpdateAuctionRequest updateAuctionRequest);

    void delete(String auctionId);

    boolean isOwner(String auctionId, String ownerId);
}
