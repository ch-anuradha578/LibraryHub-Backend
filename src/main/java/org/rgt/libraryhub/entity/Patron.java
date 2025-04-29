package org.rgt.libraryhub.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "patrons")
public class Patron {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long patronId;
	private String name;

	// Getters and Setters

	public Long getPatronId() {
		return patronId;
	}

	public void setPatronId(Long patronId) {
		this.patronId = patronId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
