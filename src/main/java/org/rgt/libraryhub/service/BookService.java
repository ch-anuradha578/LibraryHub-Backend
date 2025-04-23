package org.rgt.libraryhub.service;

import org.rgt.libraryhub.dto.AddBookRequest;
import org.rgt.libraryhub.entity.Book;

import java.util.List;

public interface BookService {
    Book addBook(AddBookRequest addBookRequest);
    List<Book> listAllBooks();
}
