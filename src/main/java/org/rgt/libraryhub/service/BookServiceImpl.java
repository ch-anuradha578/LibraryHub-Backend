package org.rgt.libraryhub.service;

import org.rgt.libraryhub.dto.AddBookRequest;
import org.rgt.libraryhub.entity.Book;
import org.rgt.libraryhub.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepo;

    @Override
    public Book addBook(AddBookRequest addBookRequest) {
        // Check if a book with the same bookId already exists
        Book existingBook = bookRepo.findById(addBookRequest.getBookId()).orElse(null);
        
        if (existingBook != null) {
            // If the book already exists, throw an exception
            throw new IllegalArgumentException("Book with the given ID already exists.");
        }

        // Proceed to create a new book if no duplicates are found
        Book book = new Book();
        book.setBookId(addBookRequest.getBookId());
        book.setTitle(addBookRequest.getTitle());
        book.setAuthor(addBookRequest.getAuthor());
        book.setTotalQuantity(addBookRequest.getTotalQuantity());
        book.setAvailableQuantity(addBookRequest.getTotalQuantity());
        book.setIsAvailable("Yes");

        return bookRepo.save(book);
    }

    @Override
    public List<Book> listAllBooks() {
        return bookRepo.findAll();
    }
}
