package com.lead.service.auction.models.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateAuctionRequest {

    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
