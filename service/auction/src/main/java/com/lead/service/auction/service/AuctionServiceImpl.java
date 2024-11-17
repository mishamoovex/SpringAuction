package com.lead.service.auction.service;

import com.lead.service.auction.exception.InvalidDateRangeException;
import com.lead.service.auction.models.AuctionStatus;
import com.lead.service.auction.models.dto.AuctionDto;
import com.lead.service.auction.models.entity.AuctionEntity;
import com.lead.service.auction.models.request.CreateAuctionRequest;
import com.lead.service.auction.repository.AuctionRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AuctionServiceImpl implements AuctionService {

    private final AuctionRepository auctionRepository;
    private final ModelMapper modelMapper;
    private final Clock clock;

    @Transactional
    @Override
    public AuctionDto save(String ownerId, CreateAuctionRequest createAuctionRequest) {
        validateDateRange(createAuctionRequest.getStartTime(), createAuctionRequest.getEndTime());

        AuctionEntity entity = AuctionEntity.builder()
                .ownerId(ownerId)
                .name(createAuctionRequest.getName())
                .startTime(createAuctionRequest.getStartTime())
                .endTime(createAuctionRequest.getEndTime())
                .status(AuctionStatus.PENDING)
                .build();

        AuctionEntity newAuction = auctionRepository.save(entity);
        return modelMapper.map(newAuction, AuctionDto.class);
    }

    private void validateDateRange(LocalDateTime start, LocalDateTime end) {
        var now = LocalDateTime.now(clock);
        var isValid = start.isAfter(now) && start.isBefore(end);
        if (!isValid) {
            throw new InvalidDateRangeException(
                    "Invalid auction date range startDate: " + start + ", endDate: " + end
            );
        }
    }
}
