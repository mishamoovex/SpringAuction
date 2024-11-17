package com.lead.service.auction.models.dto;

import com.lead.service.auction.models.AuctionStatus;

import java.time.LocalDateTime;

public record AuctionDto(
        String id,
        String ownerId,
        String name,
        AuctionStatus status,
        LocalDateTime startTime,
        LocalDateTime endTime
) {
}
