package org.rgt.libraryhub.controller;

import org.rgt.libraryhub.dto.AddBookRequest;
import org.rgt.libraryhub.entity.Book;
import org.rgt.libraryhub.repository.BookRepository;
import org.rgt.libraryhub.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	private BookRepository bookRepo;

	// Add a new book
	@PostMapping("/add")
	public ResponseEntity<?> addBook(@RequestBody AddBookRequest request) {
		if (request.getTitle() == null || request.getTitle().isEmpty() || request.getAuthor() == null
				|| request.getAuthor().isEmpty() || request.getTotalQuantity() == null
				|| request.getTotalQuantity() <= 0) {
			return new ResponseEntity<>("Missing Fields. Title, Author and TotalQuantity fields are required.",
					HttpStatus.BAD_REQUEST);
		}

		try {
			Book newBook = bookService.addBook(request);
			return new ResponseEntity<>(newBook, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	// List all books
	@GetMapping
	public ResponseEntity<?> listAllBooks() {
		List<Book> books = bookService.listAllBooks();
		if (books.isEmpty()) {
			return new ResponseEntity<>("No Books found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(books, HttpStatus.OK);
	}

	// Delete a book by ID
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable Long id) {
		boolean deleted = bookService.deleteBook(id);
		if (deleted) {
			return new ResponseEntity<>("Book record deleted successfully", HttpStatus.OK);
		} else {
			if (!bookRepo.existsById(id)) {
				return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>("Book cannot be deleted because it is currently borrowed",
						HttpStatus.BAD_REQUEST);
			}
		}
	}
}
