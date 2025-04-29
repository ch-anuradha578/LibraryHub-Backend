package org.rgt.libraryhub.service;

import org.rgt.libraryhub.dto.AddPatronRequest;
import org.rgt.libraryhub.entity.BorrowedBook;
import org.rgt.libraryhub.entity.Patron;
import org.rgt.libraryhub.repository.BorrowedBookRepository;
import org.rgt.libraryhub.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatronServiceImpl implements PatronService {

	@Autowired
	private PatronRepository patronRepo;

	@Autowired
	private BorrowedBookRepository borrowedBookRepo;

	@Override
	public Patron addPatron(AddPatronRequest addPatronRequest) {
		Patron patron = new Patron();
		patron.setName(addPatronRequest.getName());

		return patronRepo.save(patron);
	}

	@Override
	public List<Patron> listAllPatrons() {
		return patronRepo.findAll();
	}

	@Override
	public boolean deletePatron(Long patronId) {
		if (!patronRepo.existsById(patronId)) {
			return false;
		}
		List<BorrowedBook> borrowedBooks = borrowedBookRepo.findByPatron_PatronId(patronId);
		if (!borrowedBooks.isEmpty()) {
			return false;
		}
		patronRepo.deleteById(patronId);
		return true;
	}

}