package com.lead.service.user.controller;

import com.lead.service.user.controller.dto.RegisterRequestDTO;
import com.lead.service.user.controller.dto.UpdateRequestDTO;
import com.lead.service.user.repository.entity.UserEntity;
import com.lead.service.user.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UserEntity> save(
            @Valid @RequestBody RegisterRequestDTO registerRequest
    ) {
        try {
            UserEntity user = userService.save(registerRequest);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            throw new NotImplementedException();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> update(
            @Valid @RequestBody UpdateRequestDTO updateRequest,
            @PathVariable String id
    ) {
        throw new NotImplementedException();
    }

    @PutMapping("/{id}/email")
    public ResponseEntity<UserEntity> updateEmail(
            @RequestParam @Email(message = "Email is not valid") String email,
            @PathVariable String id
    ) {
        throw new NotImplementedException();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> findById(@PathVariable String id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<UserEntity>> findAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        return ResponseEntity.noContent().build();
    }
}
