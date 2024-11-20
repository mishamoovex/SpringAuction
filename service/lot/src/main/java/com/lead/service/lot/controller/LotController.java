package com.lead.service.lot.controller;

import com.lead.service.lot.models.dto.LotDto;
import com.lead.service.lot.models.request.CreateLotRequest;
import com.lead.service.lot.service.lot.LotService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/lot")
@AllArgsConstructor
public class LotController {

    private LotService lotService;

    @PostMapping
    @PreAuthorize("@auctionService.isAdmin(#request.auctionId, authentication.principal.id)")
    public ResponseEntity<LotDto> save(@RequestBody CreateLotRequest request) {
        return ResponseEntity.ok(lotService.save(request));
    }
}
