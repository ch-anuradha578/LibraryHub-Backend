package org.rgt.libraryhub.controller;

import java.util.List;

import org.rgt.libraryhub.dto.BorrowRequest;
import org.rgt.libraryhub.dto.BorrowedBookListRequest;
import org.rgt.libraryhub.dto.ReturnRequest;
import org.rgt.libraryhub.service.BorrowedBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/borrowed-books")
public class BorrowedBookController {

	@Autowired
	private BorrowedBookService borrowedBookService;

	// Borrow a book
	@PutMapping("/borrow")
	public ResponseEntity<String> borrowBook(@RequestBody BorrowRequest request) {
		if (request.getBookId() == null || request.getPatronId() == null || request.getQuantity() == null) {
			return ResponseEntity.badRequest()
					.body("Missing fields. 'bookId', 'patronId', and 'quantity' are all required.");
		}

		if (request.getQuantity() <= 0) {
			return ResponseEntity.badRequest().body("Quantity must be greater than 0.");
		}

		String result = borrowedBookService.borrowBookById(request.getBookId(), request.getPatronId(),
				request.getQuantity());

		if (result.startsWith("Book borrowed successfully")) {
			return ResponseEntity.ok(result);
		}

		return switch (result) {
		case "Patron ID not found" -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patron ID not found.");
		case "Book ID not found" -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book ID not found.");
		case "Not enough copies available" ->
			ResponseEntity.status(HttpStatus.CONFLICT).body("Not enough copies available.");
		case "Invalid quantity" -> ResponseEntity.badRequest().body("Quantity must be greater than 0.");
		default -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + result);
		};
	}

	// Return a book
	@PutMapping("/return")
	public ResponseEntity<String> returnBook(@RequestBody ReturnRequest request) {
		if (request.getBookId() == null || request.getPatronId() == null || request.getBorrowedQuantity() == null) {
			return ResponseEntity.badRequest()
					.body("Invalid return request. Book ID, Patron ID, and Borrowed Quantity are required.");
		}

		String result = borrowedBookService.returnBookById(request.getBookId(), request.getPatronId(),
				request.getBorrowedQuantity());

		if (result.startsWith("Book returned successfully")) {
			return ResponseEntity.ok(result);
		}

		return switch (result) {
		case "Patron ID not found" -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patron ID not found.");
		case "Book ID not found" -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book ID not found.");
		case "Book not borrowed by this patron" ->
			ResponseEntity.status(HttpStatus.CONFLICT).body("Book is not currently borrowed by this patron.");
		case "Patron didn't borrow that many copies" ->
			ResponseEntity.status(HttpStatus.CONFLICT).body("Patron didn't borrow that many copies.");
		case "Invalid quantity" -> ResponseEntity.badRequest().body("Quantity must be greater than 0.");
		default -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + result);
		};
	}

	// List all borrowed books (for all patrons)
    @GetMapping("/borrowed")
    public ResponseEntity<?> listBorrowedBooks() {
        List<BorrowedBookListRequest> books = borrowedBookService.listBorrowedBooks();
        if (books.isEmpty()) {
            return new ResponseEntity<>("No Borrowed Books found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // List borrowed books by patron
    @GetMapping("/borrowed/{patronId}")
    public ResponseEntity<?> listBooksBorrowedByPatron(@PathVariable Integer patronId) {
        List<BorrowedBookListRequest> books = borrowedBookService.listBooksBorrowedByPatron(patronId);
        if (books.isEmpty()) {
            return new ResponseEntity<>("No Books Borrowed by this Patron ID: " + patronId, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

}
