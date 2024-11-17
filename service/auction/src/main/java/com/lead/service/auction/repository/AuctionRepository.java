package com.lead.service.auction.repository;

import com.lead.service.auction.models.entity.AuctionEntity;
import org.springframework.data.repository.CrudRepository;

public interface AuctionRepository extends CrudRepository<AuctionEntity, String> {
}
