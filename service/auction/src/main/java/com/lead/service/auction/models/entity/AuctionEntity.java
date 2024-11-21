package com.lead.service.auction.models.entity;

import com.lead.common.model.BaseEntity;
import com.lead.service.auction.models.AuctionStatus;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "auction")
public class AuctionEntity extends BaseEntity {
    @Column(nullable = false)
    private String ownerId;
    @Column(nullable = false, length = 100)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuctionStatus status;
    @Column(nullable = false)
    private LocalDateTime startTime;
    @Column(nullable = false)
    private LocalDateTime endTime;
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "auction_administrators",
            joinColumns = @JoinColumn(name = "auction_id", nullable = false)
    )
    @Column(name = "admin_id", nullable = false)
    private Set<String> administrators;

    @Version
    private Integer version;
}
