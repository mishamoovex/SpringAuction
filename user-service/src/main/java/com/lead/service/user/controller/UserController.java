package com.lead.service.user.controller;

import com.lead.service.user.models.dto.RegisterRequestDTO;
import com.lead.service.user.models.dto.UpdateRequestDTO;
import com.lead.service.user.models.dto.UserDTO;
import com.lead.service.user.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
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
    public ResponseEntity<UserDTO> save(@Valid @RequestBody RegisterRequestDTO request) {
        return ResponseEntity.ok(userService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(
            @Valid @RequestBody UpdateRequestDTO updateRequest,
            @PathVariable String id
    ) {
        return ResponseEntity.ok(userService.update(id, updateRequest));
    }

    @PutMapping("/{id}/email")
    public ResponseEntity<UserDTO> updateEmail(
            @RequestParam  String email,
            @PathVariable String id
    ) {
        return ResponseEntity.ok(userService.updateEmail(id, email));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
