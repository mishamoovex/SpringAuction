package com.lead.service.lot.models.dto;

import java.math.BigDecimal;

public record LotDto(
        String auctionId,
        String title,
        String description,
        String imageUrl,
        BigDecimal initialPrice
) {
}
