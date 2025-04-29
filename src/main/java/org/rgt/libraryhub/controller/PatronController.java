package org.rgt.libraryhub.controller;

import org.rgt.libraryhub.dto.AddPatronRequest;
import org.rgt.libraryhub.entity.Patron;
import org.rgt.libraryhub.repository.PatronRepository;
import org.rgt.libraryhub.service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {

	@Autowired
	private PatronService patronService;

	@Autowired
	private PatronRepository patronRepo;

	// Add a new patron
	@PostMapping("/add")
	public ResponseEntity<?> addPatron(@RequestBody AddPatronRequest addPatronRequest) {
		if (addPatronRequest.getName() == null || addPatronRequest.getName().isEmpty()) {
			return ResponseEntity.badRequest().body("Invalid patron data. Patron Name are required."); // missing
		}

		try {
			Patron newPatron = patronService.addPatron(addPatronRequest);
			return ResponseEntity.ok(newPatron);

		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	// List all patrons
	@GetMapping
	public ResponseEntity<?> listAllPatrons() {
		List<Patron> patrons = patronService.listAllPatrons();
		if (patrons.isEmpty()) {
			return new ResponseEntity<>("No Patrons found", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(patrons);
	}

	// Delete a patron by ID
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deletePatron(@PathVariable Long id) {
		boolean deleted = patronService.deletePatron(id);
		if (deleted) {
			return new ResponseEntity<>("Patron deleted successfully", HttpStatus.OK);
		} else {
			if (!patronRepo.existsById(id)) {
				return new ResponseEntity<>("Patron not found", HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>("Patron cannot be deleted because they have borrowed books",
						HttpStatus.BAD_REQUEST);
			}
		}
	}

}