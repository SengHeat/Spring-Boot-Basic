package com.project.controller;

import com.project.model.ApiResponse;
import com.project.model.request.LoginFormRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth/v1")
public class AuthController {

    @PostMapping(value = "/login", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> login(@Valid @RequestBody LoginFormRequest request) {
        if ("admin".equals(request.getUsername()) && "password".equals(request.getPassword())) {
            return request.response(request);
        }
        return ResponseEntity.status(401).body(new ApiResponse("Invalid credentials", 401, null));
    }

    @PostMapping(value = "/update", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> update(@RequestBody LoginFormRequest request) {
        List<LoginFormRequest> dataLst = new ArrayList<>();
        dataLst.add(new LoginFormRequest(1L, "user1", "password1"));
        dataLst.add(new LoginFormRequest(2L, "user2", "password2"));
        dataLst.add(new LoginFormRequest(3L, "user3", "password3"));
        dataLst.add(new LoginFormRequest(4L, "user4", "password4"));
        dataLst.add(new LoginFormRequest(5L, "user5", "password5"));
        dataLst.add(new LoginFormRequest(6L, "user6", "password6"));
        dataLst.add(new LoginFormRequest(7L, "user7", "password7"));
        dataLst.add(new LoginFormRequest(8L, "user8", "password8"));
        dataLst.add(new LoginFormRequest(9L, "user9", "password9"));
        dataLst.add(new LoginFormRequest(10L, "user10", "password10"));
        Optional<LoginFormRequest> user = dataLst.stream()
                .filter(u -> u.getId().equals(request.getId()))
                .findFirst();
        if (user.isPresent()) {
            // Update logic can go here if needed
            return request.response(request);
        } else {
            return ResponseEntity.status(401).body(new ApiResponse("Invalid credentials", 401, null));
        }
    }
}
