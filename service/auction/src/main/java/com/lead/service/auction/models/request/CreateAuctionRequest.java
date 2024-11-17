package com.lead.service.auction.models.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateAuctionRequest {
    @NotBlank
    @Size(max = 100)
    private String name;
    @NotNull
    private LocalDateTime startTime;
    @NotNull
    private LocalDateTime endTime;
}
