package org.rgt.libraryhub.repository;

import java.util.List;

import org.rgt.libraryhub.entity.BorrowedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Integer> {
    List<BorrowedBook> findByBookIdAndPatronId(Integer bookId, Integer patronId);
    List<BorrowedBook> findByPatronId(Integer patronId);
}
