package com.lead.service.auction.models.dto;

import com.lead.service.auction.models.AuctionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuctionDto {
    private String id;
    private String ownerId;
    private String name;
    private AuctionStatus status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
