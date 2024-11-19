package com.lead.service.lot.models.entity;

import com.lead.common.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "lot")
public class LotEntity extends BaseEntity {
    @Column(nullable = false)
    private String auctionId;
    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String imageUrl;
    @Column(nullable = false)
    private BigDecimal initialPrice;
}
