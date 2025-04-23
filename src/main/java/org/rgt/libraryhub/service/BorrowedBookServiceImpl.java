package org.rgt.libraryhub.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.rgt.libraryhub.dto.BorrowedBookListRequest;
import org.rgt.libraryhub.entity.Book;
import org.rgt.libraryhub.entity.BorrowedBook;
import org.rgt.libraryhub.entity.Patron;
import org.rgt.libraryhub.repository.BookRepository;
import org.rgt.libraryhub.repository.BorrowedBookRepository;
import org.rgt.libraryhub.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BorrowedBookServiceImpl implements BorrowedBookService {

	@Autowired
	private BorrowedBookRepository borrowedBookRepo;

	@Autowired
	private PatronRepository patronRepo;

	@Autowired
	private BookRepository bookRepo;

	@Override
	public String borrowBookById(Integer bookId, Integer patronId, Integer quantity) {
		if (quantity == null || quantity <= 0)
			return "Invalid quantity";

		Patron patron = patronRepo.findById(patronId).orElse(null);
		if (patron == null)
			return "Patron ID not found";

		Book book = bookRepo.findById(bookId).orElse(null);
		if (book == null)
			return "Book ID not found";

		if (book.getAvailableQuantity() < quantity)
			return "Not enough copies available";

		// Decrease available quantity
		book.setAvailableQuantity(book.getAvailableQuantity() - quantity);
		book.setIsAvailable(book.getAvailableQuantity() == 0 ? "No" : "Yes");
		bookRepo.save(book);

		// Check if this patron already borrowed this book
		List<BorrowedBook> existingBorrowList = borrowedBookRepo.findByBookIdAndPatronId(bookId, patronId);

		if (!existingBorrowList.isEmpty()) {
			// Update existing borrow record
			BorrowedBook existingBorrow = existingBorrowList.get(0);
			existingBorrow.setBorrowedQuantity(existingBorrow.getBorrowedQuantity() + quantity);
			borrowedBookRepo.save(existingBorrow);
		} else {
			// Create a new borrow record for this patron-book combo
			BorrowedBook borrowedBook = new BorrowedBook();
			borrowedBook.setBookId(book.getBookId());
			borrowedBook.setTitle(book.getTitle());
			borrowedBook.setAuthor(book.getAuthor());
			borrowedBook.setPatronId(patron.getPatronId());
			borrowedBook.setPatronName(patron.getName());
			borrowedBook.setBorrowedQuantity(quantity);
			borrowedBookRepo.save(borrowedBook);
		}

		return "Book borrowed successfully by Patron ID: " + patronId;
	}

	@Override
	public String returnBookById(Integer bookId, Integer patronId, Integer borrowedQuantity) {
		if (borrowedQuantity == null || borrowedQuantity <= 0)
			return "Invalid quantity";

		Patron patron = patronRepo.findById(patronId).orElse(null);
		if (patron == null)
			return "Patron ID not found";

		Book book = bookRepo.findById(bookId).orElse(null);
		if (book == null)
			return "Book ID not found";

		// Find the borrow record for this patron and book
		List<BorrowedBook> borrowedBooks = borrowedBookRepo.findByBookIdAndPatronId(bookId, patronId);
		if (borrowedBooks.isEmpty())
			return "Book not borrowed by this patron";

		int totalBorrowed = borrowedBooks.stream().mapToInt(BorrowedBook::getBorrowedQuantity).sum();

		if (borrowedQuantity > totalBorrowed) {
			return "Patron didn't borrow that many copies";
		}

		// Update the available quantity of the book
		book.setAvailableQuantity(book.getAvailableQuantity() + borrowedQuantity);
		book.setIsAvailable(book.getAvailableQuantity() == 0 ? "No" : "Yes");
		bookRepo.save(book);

		// Now update the borrowed book records
		int quantityToReturn = borrowedQuantity;
		for (BorrowedBook borrowedBook : borrowedBooks) {
			if (quantityToReturn == 0)
				break;

			int borrowed = borrowedBook.getBorrowedQuantity();

			if (quantityToReturn >= borrowed) {
				quantityToReturn -= borrowed;
				borrowedBookRepo.delete(borrowedBook);
			} else {
				borrowedBook.setBorrowedQuantity(borrowed - quantityToReturn);
				borrowedBookRepo.save(borrowedBook);
				quantityToReturn = 0;
			}
		}

		return "Book returned successfully by Patron ID: " + patronId;
	}

	@Override
	public List<BorrowedBookListRequest> listBooksBorrowedByPatron(Integer patronId) {
		List<BorrowedBook> borrowedBooks = borrowedBookRepo.findByPatronId(patronId);

		if (borrowedBooks.isEmpty()) {
			return Collections.emptyList();
		}

		return borrowedBooks.stream().map(bb -> new BorrowedBookListRequest(bb.getBookId(), bb.getTitle(),
				bb.getAuthor(), bb.getPatronId(), bb.getPatronName(), bb.getBorrowedQuantity()))
				.collect(Collectors.toList());
	}

	@Override
	public List<BorrowedBookListRequest> listBorrowedBooks() {
		List<BorrowedBook> borrowedBooks = borrowedBookRepo.findAll();

		if (borrowedBooks.isEmpty()) {
			return Collections.emptyList();
		}

		return borrowedBooks.stream().map(bb -> new BorrowedBookListRequest(bb.getBookId(), bb.getTitle(),
				bb.getAuthor(), bb.getPatronId(), bb.getPatronName(), bb.getBorrowedQuantity()))
				.collect(Collectors.toList());
	}

}
