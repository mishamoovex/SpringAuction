package com.lead.service.lot.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LotDto {
    private String auctionId;
    private String title;
    private String description;
    private String imageUrl;
    private BigDecimal initialPrice;
}
