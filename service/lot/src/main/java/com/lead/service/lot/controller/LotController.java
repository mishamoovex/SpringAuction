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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/lot")
@AllArgsConstructor
public class LotController {

    private LotService lotService;

    @PostMapping
    @PreAuthorize("@authorizationService.isAuctionAdmin(#request.auctionId, authentication.principal.id)")
    public ResponseEntity<LotDto> save(@RequestBody @Valid CreateLotRequest request) {
        return ResponseEntity.ok(lotService.save(request));
    }

    @PutMapping
    @PreAuthorize("@authorizationService.isLotAdmin(#request.lotId, authentication.principal.id)")
    public ResponseEntity<LotDto> update(@RequestBody @Valid UpdateLotRequest request) {
        return ResponseEntity.ok(lotService.update(request));
    }

    @GetMapping("/{lotId}")
    public ResponseEntity<LotDto> getById(@PathVariable String lotId) {
        return ResponseEntity.ok(lotService.getById(lotId));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<LotDto>> getAllByAuction(@RequestParam String auctionId) {
        return ResponseEntity.ok(lotService.getAllByAuction(auctionId));
    }

    @DeleteMapping("/{lotId}")
    @PreAuthorize("@authorizationService.isLotAdmin(#lotId, authentication.principal.id)")
    public ResponseEntity<Void> delete(@PathVariable String lotId) {
        lotService.deleteById(lotId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAll")
    @PreAuthorize("@authorizationService.isAuctionAdmin(#auctionId, authentication.principal.id)")
    public ResponseEntity<Void> deleteAllByAuction(@RequestParam String auctionId) {
        lotService.deleteAllByAuction(auctionId);
        return ResponseEntity.noContent().build();
    }
}
