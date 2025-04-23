package org.rgt.libraryhub.dto;

public class BorrowedBookListRequest {
	private Integer bookId;
	private String title;
	private String author;
	private Integer patronId; 
	private String patronName;
	private Integer borrowedQuantity;

	public BorrowedBookListRequest(Integer bookId, String title, String author, Integer patronId, String patronName,
			Integer borrowedQuantity) {
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.patronId = patronId;
		this.patronName = patronName;
		this.borrowedQuantity = borrowedQuantity;
	}

	// Getters and Setters
	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Integer getBorrowedQuantity() {
		return borrowedQuantity;
	}

	public void setBorrowedQuantity(Integer borrowedQuantity) {
		this.borrowedQuantity = borrowedQuantity;
	}
}
