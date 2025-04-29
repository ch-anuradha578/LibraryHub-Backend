package org.rgt.libraryhub.service;

import java.util.List;

import org.rgt.libraryhub.dto.BorrowedBookListRequest;

public interface BorrowedBookService {
	String borrowBookById(Long bookId, Long patronId, Integer quantity);
	String returnBookById(Long bookId, Long patronId, Integer borrowedQuantity);
    List<BorrowedBookListRequest> listBorrowedBooks();
    List<BorrowedBookListRequest> listBooksBorrowedByPatron(Long patronId);
}
