package org.rgt.libraryhub.repository;

import java.util.List;

import org.rgt.libraryhub.entity.BorrowedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Long> {
    List<BorrowedBook> findByBook_BookIdAndPatron_PatronId(Long bookId, Long patronId);
    List<BorrowedBook> findByBook_BookId(Long bookId); 
    List<BorrowedBook> findByPatron_PatronId(Long patronId);
}
