package org.rgt.libraryhub.service;

import org.rgt.libraryhub.dto.AddBookRequest;
import org.rgt.libraryhub.entity.Book;
import org.rgt.libraryhub.entity.BorrowedBook;
import org.rgt.libraryhub.repository.BookRepository;
import org.rgt.libraryhub.repository.BorrowedBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepo;

	@Autowired
	private BorrowedBookRepository borrowedBookRepo;

	@Override
	public Book addBook(AddBookRequest addBookRequest) {
		Book book = new Book();
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

	@Override
	public boolean deleteBook(Long bookId) {
		if (bookRepo.existsById(bookId)) {
			List<BorrowedBook> borrowedBooks = borrowedBookRepo.findByBook_BookId(bookId);
			if (!borrowedBooks.isEmpty()) {
				return false;
			}
			bookRepo.deleteById(bookId);
			return true;
		}
		return false;
	}

}
