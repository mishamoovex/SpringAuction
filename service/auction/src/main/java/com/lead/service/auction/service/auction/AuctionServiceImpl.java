package com.lead.service.auction.service.auction;

import com.lead.common.exception.BadRequestDataException;
import com.lead.common.exception.BadStateException;
import com.lead.common.exception.NotFoundException;
import com.lead.service.auction.models.AdminRole;
import com.lead.service.auction.models.AuctionStatus;
import com.lead.service.auction.models.dto.AuctionDto;
import com.lead.service.auction.models.entity.AuctionEntity;
import com.lead.service.auction.models.request.CreateAuctionRequest;
import com.lead.service.auction.models.request.UpdateAuctionRequest;
import com.lead.service.auction.repository.AuctionRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;

@Service("auctionService")
@AllArgsConstructor
public class AuctionServiceImpl implements AuctionService {

    private final AuctionRepository auctionRepository;
    private final ModelMapper modelMapper;
    private final Clock clock;

    @Transactional
    @Override
    public AuctionDto save(String ownerId, CreateAuctionRequest createRequest) {
        validateAuctionDateRange(createRequest.getStartTime(), createRequest.getEndTime());

        AuctionEntity entity = AuctionEntity.builder()
                .ownerId(ownerId)
                .name(createRequest.getName())
                .startTime(createRequest.getStartTime())
                .endTime(createRequest.getEndTime())
                .status(AuctionStatus.PENDING)
                .build();

        AuctionEntity newAuction = auctionRepository.save(entity);
        return modelMapper.map(newAuction, AuctionDto.class);
    }

    @Transactional
    @Override
    public AuctionDto update(UpdateAuctionRequest updateRequest) {
        AuctionEntity entity = findById(updateRequest.getAuctionId());

        validateAuctionUpdateState(entity);
        validateAuctionDateRange(updateRequest.getStartTime(), updateRequest.getEndTime());

        entity.setName(updateRequest.getName());
        entity.setStartTime(updateRequest.getStartTime());
        entity.setEndTime(updateRequest.getEndTime());

        AuctionEntity updatedAuction = auctionRepository.save(entity);
        return modelMapper.map(updatedAuction, AuctionDto.class);
    }

    @Override
    public AuctionDto updateStatus(String auctionId, AuctionStatus status) {
        AuctionEntity entity = findById(auctionId);

        if (!isAuctionStatusUpdateAvailable(entity.getStatus(), status)) {
            throw new BadStateException("Invalid auction status");
        }

        entity.setStatus(status);

        AuctionEntity updatedAuction = auctionRepository.save(entity);
        return modelMapper.map(updatedAuction, AuctionDto.class);
    }

    @Override
    public void delete(String auctionId) {
        auctionRepository.deleteById(auctionId);
    }

    @Override
    public AuctionDto get(String auctionId) {
        AuctionEntity entity = findById(auctionId);
        return modelMapper.map(entity, AuctionDto.class);
    }

    @Override
    public Page<AuctionDto> findAll(
            String userId,
            AdminRole adminRole,
            AuctionStatus status,
            Pageable pageable
    ) {
        return auctionRepository.getPagedAuctions(
                        resolveId(userId, adminRole, AdminRole.OWNER),
                        resolveId(userId, adminRole, AdminRole.ADMIN),
                        status,
                        pageable
                )
                .map(entity -> modelMapper.map(entity, AuctionDto.class));
    }

    @Transactional(readOnly = true)
    @Override
    public boolean isOwner(String auctionId, String ownerId) {
        AuctionEntity auction = findById(auctionId);
        return auction.getOwnerId().equals(ownerId);
    }

    private AuctionEntity findById(String auctionId) {
        return auctionRepository.findById(auctionId)
                .orElseThrow(() -> new NotFoundException("Auction with id: " + auctionId + " not found"));
    }

    private void validateAuctionDateRange(LocalDateTime start, LocalDateTime end) {
        var now = LocalDateTime.now(clock);
        var isValid = start.isAfter(now) && start.isBefore(end);
        if (!isValid) {
            throw new BadRequestDataException("Invalid date range");
        }
    }

    private void validateAuctionUpdateState(AuctionEntity entity) {
        var isUpdateStateAvailable = entity.getStatus() == AuctionStatus.PENDING;
        if (!isUpdateStateAvailable) {
            throw new BadStateException("Invalid auction status");
        }
    }

    private boolean isAuctionStatusUpdateAvailable(AuctionStatus oldStatus, AuctionStatus newStatus) {
        switch (oldStatus) {
            case PENDING -> {
                return newStatus == AuctionStatus.STARTED;
            }
            case STARTED -> {
                return newStatus == AuctionStatus.CANCELED || newStatus == AuctionStatus.FINISHED;
            }
            default -> {
                return false;
            }
        }
    }

    private String resolveId(String userId, AdminRole currentRole, AdminRole targetRole) {
        return currentRole == targetRole ? userId : null;
    }
}
