package com.lead.service.user.controller;

import com.lead.service.user.controller.dto.RegisterRequestDTO;
import com.lead.service.user.controller.dto.UpdateRequestDTO;
import com.lead.service.user.model.User;
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

@RestController
@RequestMapping("v1/user")
@AllArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> save(@Valid @RequestBody RegisterRequestDTO registerRequest) {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(
            @Valid @RequestBody UpdateRequestDTO updateRequest,
            @PathVariable String id
    ) {
        return null;
    }

    @PutMapping("/{id}/email")
    public ResponseEntity<User> updateEmail(
            @RequestParam @Email(message = "Email is not valid") String email,
            @PathVariable String id
    ) {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable String id) {
        return null;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<User>> findAll() {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        return null;
    }
}
