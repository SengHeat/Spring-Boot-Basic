package com.project.model.request;

import com.project.model.ApiResponse;
import org.springframework.http.ResponseEntity;

public class BookRequest {

    private Long id;
    private String name;
    private double price;

    public BookRequest(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public ResponseEntity<?> response(BookRequest request) {
        return ResponseEntity.ok(new ApiResponse("Successfully get book ",200, request));
    }
}
