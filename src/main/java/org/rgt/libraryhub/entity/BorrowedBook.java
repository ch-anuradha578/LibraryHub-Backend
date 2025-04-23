package org.rgt.libraryhub.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "borrowed_books")
public class BorrowedBook {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer bookId;

	private String author;
	private Integer patronId;
	private String patronName;
	private String title;
	private Integer borrowedQuantity;

	// Getters and Setters
	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getPatronId() {
		return patronId;
	}

	public void setPatronId(Integer patronId) {
		this.patronId = patronId;
	}

	public String getPatronName() {
		return patronName;
	}

	public void setPatronName(String patronName) {
		this.patronName = patronName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getBorrowedQuantity() {
		return borrowedQuantity;
	}

	public void setBorrowedQuantity(Integer borrowedQuantity) {
		this.borrowedQuantity = borrowedQuantity;
	}

}
