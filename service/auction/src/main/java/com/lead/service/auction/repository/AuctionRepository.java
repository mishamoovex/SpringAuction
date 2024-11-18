package com.lead.service.auction.repository;

import com.lead.service.auction.models.entity.AuctionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AuctionRepository extends JpaRepository<AuctionEntity, String>,
        JpaSpecificationExecutor<AuctionEntity> {
}
