package com.lead.service.auction.service;

import com.lead.service.auction.models.dto.AuctionDto;
import com.lead.service.auction.models.request.CreateAuctionRequest;

public interface AuctionService {
    AuctionDto save(String ownerId, CreateAuctionRequest createAuctionRequest);
}
