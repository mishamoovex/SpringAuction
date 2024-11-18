package com.lead.service.auction.repository;

import com.lead.service.auction.models.AuctionStatus;
import com.lead.service.auction.models.entity.AuctionEntity;
import org.springframework.data.jpa.domain.Specification;

public class AuctionSpecifications {

    public static Specification<AuctionEntity> hasOwner(String ownerId) {
        return (root, query, criteriaBuilder) ->
                ownerId == null ? null : criteriaBuilder.equal(root.get("ownerId"), ownerId);
    }

    public static Specification<AuctionEntity> hasAdmin(String adminId) {
        return (root, query, criteriaBuilder) ->
                adminId == null ? null : criteriaBuilder.isMember(adminId, root.get("administrators"));
    }

    public static Specification<AuctionEntity> hasStatus(AuctionStatus status) {
        return (root, query, criteriaBuilder) ->
                status == null ? null : criteriaBuilder.equal(root.get("status"), status);
    }
}
