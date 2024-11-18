package com.lead.service.auction.service.auction;

import com.lead.service.auction.models.AdminRole;
import com.lead.service.auction.models.AuctionStatus;
import com.lead.service.auction.models.dto.AuctionDto;
import com.lead.service.auction.models.request.CreateAuctionRequest;
import com.lead.service.auction.models.request.UpdateAuctionRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuctionService {
    AuctionDto save(String ownerId, CreateAuctionRequest createRequest);

    AuctionDto update(UpdateAuctionRequest updateRequest);

    AuctionDto updateStatus(String auctionId, AuctionStatus status);

    void delete(String auctionId);

    AuctionDto get(String auctionId);

    Page<AuctionDto> findAll(String userId, AdminRole role, AuctionStatus status, Pageable pageable);

    boolean isOwner(String auctionId, String ownerId);
}
