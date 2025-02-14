package com.project.exception;

import com.project.model.ApiResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomErrorController implements ErrorController {

    @GetMapping("/error")
    public ResponseEntity<ApiResponse> handleError() {
        // Create a custom error response object
        ApiResponse apiResponse = new ApiResponse("An error occurred", 500, "Internal Server Error");

        // Return the error response with a 500 status code (Internal Server Error)
        return ResponseEntity.status(500).body(apiResponse);
    }
}
