package com.lead.service.lot.service.lot;

import com.lead.service.lot.models.dto.LotDto;
import com.lead.service.lot.models.request.CreateLotRequest;
import com.lead.service.lot.models.request.UpdateLotRequest;

import java.util.List;

public interface LotService {
    LotDto save(CreateLotRequest createLotRequest);

    LotDto update(UpdateLotRequest updateLotRequest);

    LotDto getById(String id);

    List<LotDto> getAllByAuction(String auctionId);

    void deleteById(String lotId);

    void deleteAllByAuction(String auctionId);
}
