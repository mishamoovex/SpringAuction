package com.lead.service.user.controller;

import com.lead.service.user.models.dto.UserDetailsDto;
import com.lead.service.user.models.dto.UserDto;
import com.lead.service.user.models.request.RegisterRequest;
import com.lead.service.user.models.request.UpdateRequest;
import com.lead.service.user.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
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

@AllArgsConstructor
@Validated
@RestController
@RequestMapping("v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDetailsDto> save(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.save(request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("principal.id == #id")
    public ResponseEntity<UserDto> update(
            @Valid @RequestBody UpdateRequest updateRequest,
            @PathVariable String id
    ) {
        return ResponseEntity.ok(userService.update(id, updateRequest));
    }

    @PutMapping("/{id}/email")
    @PreAuthorize("principal.id == #id")
    public ResponseEntity<UserDto> updateEmail(
            @RequestParam @Email String email,
            @PathVariable String id
    ) {
        return ResponseEntity.ok(userService.updateEmail(id, email));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable String id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping("/account")
    public ResponseEntity<UserDetailsDto> findAccountByEmail(@RequestParam @Email String email) {
        return ResponseEntity.ok(userService.getUserDetailsByEmail(email));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("principal.id == #id")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
