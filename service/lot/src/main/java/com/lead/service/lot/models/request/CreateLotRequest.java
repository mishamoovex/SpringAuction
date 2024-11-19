package com.lead.service.lot.models.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public final class CreateLotRequest {
    @NotBlank
    private String auctionId;
    @Size(min = 1, max = 100)
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String imageUrl;
    @NotNull
    private BigDecimal initialPrice;
}
