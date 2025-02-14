package com.project.controller;

import com.project.model.ApiResponse;
import com.project.model.request.BookRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/book")
public class BookController {

    private final List<BookRequest> bookList = Arrays.asList(
            new BookRequest(1L, "The Great Gatsby", 10.99),
            new BookRequest(2L, "To Kill a Mockingbird", 12.50),
            new BookRequest(3L, "1984", 14.75),
            new BookRequest(4L, "The Catcher in the Rye", 11.20),
            new BookRequest(5L, "Pride and Prejudice", 9.80),
            new BookRequest(6L, "Moby Dick", 15.30),
            new BookRequest(7L, "War and Peace", 18.99),
            new BookRequest(8L, "The Hobbit", 13.40),
            new BookRequest(9L, "Crime and Punishment", 16.25),
            new BookRequest(10L, "Brave New World", 12.99)
    );

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getBookById(@PathVariable String id) {
        try {
            long bookId = Long.parseLong(id); // Ensure ID is a valid Long

            Optional<BookRequest> book = bookList.stream()
                    .filter(bookRequest -> bookRequest.getId().equals(bookId))
                    .findFirst();
            return book.map(bookRequest -> ResponseEntity.ok(new ApiResponse("Book found successfully", 200, bookRequest))).orElseGet(() -> ResponseEntity.status(404).body(new ApiResponse("Book not found", 404, null)));
        } catch (NumberFormatException e) {
            return ResponseEntity.status(400).body(new ApiResponse("Invalid ID format", 400, null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse("Internal server error", 500, null));
        }
    }

    @GetMapping("/{id}/{name}/{price}")   //TODO => http://localhost:8090/api/book/1/HarryPotter/29.9
    public ResponseEntity<?> getBookByMapping(@PathVariable Map<String, String> map) {

        Long id = Long.parseLong(map.get("id"));
        String name = map.get("name");
        double price = Double.parseDouble(map.get("price"));

        BookRequest bookRequest = new BookRequest(id, name, price);

        return bookRequest.response(bookRequest);
    }

    @GetMapping({"book", "book/{id}"})
    public ResponseEntity<?> optionalBook(@PathVariable(value = "id", required = false) Optional<Long> bookId) {
        try {
            // If bookId is present, find the book by id
            if (bookId.isPresent()) {
                long id = bookId.get();  // Directly get the value from Optional
                Optional<BookRequest> book = bookList.stream()
                        .filter(bookRequest -> bookRequest.getId().equals(id))
                        .findFirst();

                // Return the book if found, else return 404
                return book.map(bookRequest ->
                        ResponseEntity.ok(new ApiResponse("Book found successfully", 200, bookRequest))
                ).orElseGet(() ->
                        ResponseEntity.status(404).body(new ApiResponse("Book not found", 404, null))
                );
            } else {
                // Return all books if no bookId is provided
                return ResponseEntity.ok(new ApiResponse("Get all books", 200, bookList));
            }
        } catch (NumberFormatException e) {
            // Handle case when ID is not in valid format (though this may not occur here due to Optional)
            return ResponseEntity.status(400).body(new ApiResponse("Invalid ID format", 400, null));
        } catch (Exception e) {
            // Catch all other exceptions (server errors, etc.)
            return ResponseEntity.status(500).body(new ApiResponse("Internal server error", 500, null));
        }
    }



}
