package org.rgt.libraryhub.dto;

public class ReturnRequest {
    private Integer bookId;
    private Integer patronId;
    private Integer borrowedQuantity;

    // Getters and Setters
    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getPatronId() {
        return patronId;
    }

    public void setPatronId(Integer patronId) {
        this.patronId = patronId;
    }

	public Integer getBorrowedQuantity() {
		return borrowedQuantity;
	}

	public void setBorrowedQuantity(Integer borrowedQuantity) {
		this.borrowedQuantity = borrowedQuantity;
	}
    
}
