package com.lead.service.auction.repository;

import com.lead.service.auction.models.AuctionStatus;
import com.lead.service.auction.models.entity.AuctionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuctionRepository extends JpaRepository<AuctionEntity, String> {

    @Query("""
            SELECT a FROM auction a
            WHERE (:ownerId IS NULL OR a.ownerId = :ownerId)
                AND (:adminId IS NULL OR :adminId MEMBER OF a.administrators)
                AND (:status IS NULL OR a.status = :status)
            """)
    Page<AuctionEntity> getPagedAuctions(String ownerId, String adminId, AuctionStatus status, Pageable pageable);
}
