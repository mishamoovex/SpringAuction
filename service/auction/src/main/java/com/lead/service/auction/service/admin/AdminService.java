package com.lead.service.auction.service.admin;

public interface AdminService {
    void addAdmin(String auctionId, String adminId);

    void removeAdmin(String auctionId, String adminId);
}
