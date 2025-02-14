package com.project.controller;

import com.project.model.ApiResponse;
import com.project.model.request.LoginFormRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/auth/v1/user", headers = {"key=value", "keys=value"})
public class AuthController {

    List<LoginFormRequest> dataLst = new ArrayList<>(
            Arrays.asList(
                    new LoginFormRequest(1L, "john_doe", "SecurePass123!"),
                    new LoginFormRequest(2L, "alice_wonder", "WonderLand@2024"),
                    new LoginFormRequest(3L, "michael_smith", "Smithy#456"),
                    new LoginFormRequest(4L, "emily_rose", "EmilyR0se!"),
                    new LoginFormRequest(5L, "david_clark", "D@vidC789"),
                    new LoginFormRequest(6L, "sophia_lee", "LeeSophia2023*"),
                    new LoginFormRequest(7L, "william_jones", "WilliamJ_99"),
                    new LoginFormRequest(8L, "olivia_martin", "MartinOlivia##"),
                    new LoginFormRequest(9L, "james_anderson", "J@mesAnd#321"),
                    new LoginFormRequest(10L, "charlotte_white", "CWhite!654")
            )
    );

    @PostMapping(value = "/login", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> login(@Valid @RequestBody LoginFormRequest request) {
        if ("admin".equals(request.getUsername()) && "password".equals(request.getPassword())) {
            return request.response(request);
        }
        return ResponseEntity.status(401).body(new ApiResponse("Invalid credentials", 401, null));
    }

    @PostMapping(value = "/update", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> update(@RequestBody LoginFormRequest request) {
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

    @GetMapping("/{id}") /// Specify path variable in mapping
    public ResponseEntity<?> getUserProfile(
            @PathVariable String id,
            HttpServletRequest request
            ){
        Optional<LoginFormRequest> user = dataLst.stream()
                .filter(u -> u.getId().toString().equals(id))
                .findFirst();

        System.out.println(request.getHeader("key"));

        if(user.isPresent()){
            return ResponseEntity.ok(new ApiResponse("Successfully", 200, user));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse("Invalid credentials", 404, user));
        }
    }

}
