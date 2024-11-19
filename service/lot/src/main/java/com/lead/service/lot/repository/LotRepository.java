package com.lead.service.lot.repository;

import com.lead.service.lot.models.entity.LotEntity;
import org.springframework.data.repository.CrudRepository;

public interface LotRepository extends CrudRepository<LotEntity, String> {
}
