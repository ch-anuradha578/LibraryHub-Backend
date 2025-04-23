package org.rgt.libraryhub.service;

import java.util.List;

import org.rgt.libraryhub.dto.BorrowedBookListRequest;

public interface BorrowedBookService {
    String borrowBookById(Integer bookId, Integer patronId, Integer quantity);
    String returnBookById(Integer bookId, Integer patronId, Integer quantity);
    List<BorrowedBookListRequest> listBorrowedBooks();
    List<BorrowedBookListRequest> listBooksBorrowedByPatron(Integer patronId);
}
