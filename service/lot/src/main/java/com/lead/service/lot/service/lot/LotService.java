package com.lead.service.lot.service.lot;

import com.lead.service.lot.models.dto.LotDto;
import com.lead.service.lot.models.request.CreateLotRequest;
import com.lead.service.lot.models.request.UpdateLotRequest;

public interface LotService {
    LotDto save(CreateLotRequest createLotRequest);

    LotDto update(UpdateLotRequest updateLotRequest);

    void deleteById(String lotId);
}
