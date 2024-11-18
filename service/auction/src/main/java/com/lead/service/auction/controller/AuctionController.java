package com.lead.service.auction.controller;

import com.lead.security.model.AuthUserDetails;
import com.lead.service.auction.models.dto.AuctionDto;
import com.lead.service.auction.models.request.CreateAuctionRequest;
import com.lead.service.auction.service.admin.AdminService;
import com.lead.service.auction.service.auction.AuctionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auction")
@AllArgsConstructor
public class AuctionController {

    private final AuctionService auctionService;
    private final AdminService adminService;

    @PostMapping
    public ResponseEntity<AuctionDto> save(
            @RequestBody @Valid CreateAuctionRequest request,
            @AuthenticationPrincipal AuthUserDetails userDetails
    ) {
        return ResponseEntity.ok(auctionService.save(userDetails.getId(), request));
    }

    @GetMapping("{auctionId}/isAdmin")
    public ResponseEntity<Boolean> isAdmin(
            @PathVariable String auctionId,
            @RequestParam String adminId
    ) {
        return ResponseEntity.ok(adminService.isAdmin(auctionId, adminId));
    }

    @PutMapping("{auctionId}/admin")
    @PreAuthorize("@auctionService.isOwner(#auctionId,authentication.principal.id)")
    public ResponseEntity<Void> addAdmin(
            @PathVariable String auctionId,
            @RequestParam String adminId
    ) {
        adminService.addAdmin(auctionId, adminId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{auctionId}/admin")
    @PreAuthorize("@auctionService.isOwner(#auctionId,authentication.principal.id)")
    public ResponseEntity<Void> removeAdmin(
            @PathVariable String auctionId,
            @RequestParam String adminId
    ) {
        adminService.removeAdmin(auctionId, adminId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{auctionId}")
    @PreAuthorize("@auctionService.isOwner(#auctionId,authentication.principal.id)")
    public ResponseEntity<Void> delete(@PathVariable String auctionId) {
        auctionService.delete(auctionId);
        return ResponseEntity.noContent().build();
    }
}
