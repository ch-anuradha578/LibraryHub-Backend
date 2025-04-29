package org.rgt.libraryhub.dto;

public class BorrowedBookListRequest {
	private Long bookId;
	private String title;
	private String author;
	private Long patronId; 
	private String patronName;
	private Integer borrowedQuantity;

	public BorrowedBookListRequest(Long bookId, String title, String author, Long patronId, String patronName,
			Integer borrowedQuantity) {
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.patronId = patronId;
		this.patronName = patronName;
		this.borrowedQuantity = borrowedQuantity;
	}

	// Getters and Setters
	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
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

	public Long getPatronId() {
		return patronId;
	}

	public void setPatronId(Long patronId) {
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
