package com.lead.service.lot.repository;

import com.lead.service.lot.models.entity.LotEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LotRepository extends CrudRepository<LotEntity, String> {

    List<LotEntity> findAllByAuctionId(String auctionId);

    void deleteAllByAuctionId(String auctionId);
}
