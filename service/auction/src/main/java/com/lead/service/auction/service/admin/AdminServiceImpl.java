package com.lead.service.auction.service.admin;

import com.lead.common.exception.AlreadyExistsException;
import com.lead.common.exception.NotFoundException;
import com.lead.service.auction.models.entity.AuctionEntity;
import com.lead.service.auction.repository.AuctionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    private AuctionRepository auctionRepository;

    @Transactional
    @Override
    public void addAdmin(String auctionId, String adminId) {
        AuctionEntity auction = findById(auctionId);
        Set<String> admins = auction.getAdministrators();

        if (admins.contains(adminId)) {
            throw new AlreadyExistsException("Admin with id: " + adminId + " already exists");
        }

        admins.add(adminId);
        auction.setAdministrators(admins);
        auctionRepository.save(auction);
    }

    @Transactional
    @Override
    public void removeAdmin(String auctionId, String adminId) {
        AuctionEntity auction = findById(auctionId);
        Set<String> admins = auction.getAdministrators();

        if (!admins.contains(adminId)) {
            throw new NotFoundException("Admin with id: " + adminId + " does not exist");
        }

        admins.remove(adminId);
        auction.setAdministrators(admins);
        auctionRepository.save(auction);
    }

    @Override
    public boolean isAdmin(String auctionId, String adminId) {
        AuctionEntity auction = findById(auctionId);
        return auction.getOwnerId().equals(adminId) || auction.getAdministrators().contains(adminId);
    }

    private AuctionEntity findById(String auctionId) {
        return auctionRepository.findById(auctionId)
                .orElseThrow(() -> new NotFoundException("Auction with id: " + auctionId + " not found"));
    }
}
