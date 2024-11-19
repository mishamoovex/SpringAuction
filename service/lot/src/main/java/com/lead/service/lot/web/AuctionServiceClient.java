package com.lead.service.lot.web;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "auction-service",
        path = "/v1/auction"
)
public interface AuctionServiceClient {

    @GetMapping("/{auctionId}/isAdmin")
    boolean isAdmin(@PathVariable String auctionId);
}
