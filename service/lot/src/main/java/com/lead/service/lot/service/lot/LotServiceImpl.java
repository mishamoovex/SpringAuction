package com.lead.service.lot.service.lot;

import com.lead.common.exception.NotFoundException;
import com.lead.service.lot.models.dto.LotDto;
import com.lead.service.lot.models.entity.LotEntity;
import com.lead.service.lot.models.request.CreateLotRequest;
import com.lead.service.lot.models.request.UpdateLotRequest;
import com.lead.service.lot.repository.LotRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service("lotService")
@AllArgsConstructor
class LotServiceImpl implements LotService {

    private final LotRepository lotRepository;
    private final ModelMapper modelMapper;

    @Override
    public LotDto save(CreateLotRequest createLotRequest) {
        LotEntity entity = LotEntity.builder()
                .auctionId(createLotRequest.getAuctionId())
                .title(createLotRequest.getTitle())
                .description(createLotRequest.getDescription())
                .imageUrl(createLotRequest.getImageUrl())
                .initialPrice(createLotRequest.getInitialPrice())
                .build();

        LotEntity newLot = lotRepository.save(entity);
        return modelMapper.map(newLot, LotDto.class);
    }

    @Override
    public LotDto update(UpdateLotRequest updateLotRequest) {
        LotEntity entity = findById(updateLotRequest.getLotId());

        entity.setTitle(updateLotRequest.getTitle());
        entity.setDescription(updateLotRequest.getDescription());
        entity.setImageUrl(updateLotRequest.getImageUrl());
        entity.setInitialPrice(updateLotRequest.getInitialPrice());

        LotEntity updatedLot = lotRepository.save(entity);
        return modelMapper.map(updatedLot, LotDto.class);
    }

    @Override
    public LotDto getById(String id) {
        LotEntity entity = findById(id);
        return modelMapper.map(entity, LotDto.class);
    }

    @Override
    public void deleteById(String lotId) {
        lotRepository.deleteById(lotId);
    }

    private LotEntity findById(String lotId) {
        return lotRepository.findById(lotId)
                .orElseThrow(() -> new NotFoundException("Lot with id: " + lotId + " not found"));
    }
}
