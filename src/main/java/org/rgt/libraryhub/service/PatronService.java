package org.rgt.libraryhub.service;

import org.rgt.libraryhub.dto.AddPatronRequest;
import org.rgt.libraryhub.entity.Patron;

import java.util.List;

public interface PatronService {
	Patron addPatron(AddPatronRequest addPatronRequest);
	List<Patron> listAllPatrons();
	boolean deletePatron(Long id);
}
