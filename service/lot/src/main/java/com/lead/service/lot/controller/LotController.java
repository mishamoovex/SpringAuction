package com.lead.service.lot.controller;

import com.lead.service.lot.models.dto.LotDto;
import com.lead.service.lot.models.request.CreateLotRequest;
import com.lead.service.lot.models.request.UpdateLotRequest;
import com.lead.service.lot.service.lot.LotService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<LotDto> save(@RequestBody @Valid CreateLotRequest request) {
        return ResponseEntity.ok(lotService.save(request));
    }

    @PutMapping
    @PreAuthorize("@auctionService.isAdmin(#request.auctionId, authentication.principal.id)")
    public ResponseEntity<LotDto> update(@RequestBody @Valid UpdateLotRequest request) {
        return ResponseEntity.ok(lotService.update(request));
    }

    @DeleteMapping("/{auctionId}")
    @PreAuthorize("@auctionService.isAdmin(#auctionId, authentication.principal.id)")
    public ResponseEntity<Void> delete(@PathVariable("auctionId") String auctionId) {
        lotService.deleteById(auctionId);
        return ResponseEntity.noContent().build();
    }
}
