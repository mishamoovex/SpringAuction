package com.lead.service.lot.service.lot;

import com.lead.service.lot.models.dto.LotDto;
import com.lead.service.lot.models.request.CreateLotRequest;

public interface LotService {
    LotDto save(CreateLotRequest createLotRequest);

    void deleteById(String lotId);
}
