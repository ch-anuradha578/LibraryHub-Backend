package org.rgt.libraryhub.service;

import org.rgt.libraryhub.dto.AddPatronRequest;
import org.rgt.libraryhub.entity.Patron;
import org.rgt.libraryhub.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatronServiceImpl implements PatronService {

    @Autowired
    private PatronRepository patronRepo;

    @Override
    public Patron addPatron(AddPatronRequest addPatronRequest) {
        // Check if a patron with the same patronId already exists
        Optional<Patron> existingPatron = patronRepo.findById(addPatronRequest.getPatronId());
        if (existingPatron.isPresent()) {
            // If the patron already exists, throw an exception
            throw new IllegalArgumentException("Patron with the given ID already exists.");
        }

        // Proceed to create a new patron if no duplicates are found
        Patron patron = new Patron();
        patron.setPatronId(addPatronRequest.getPatronId());
        patron.setName(addPatronRequest.getName());

        return patronRepo.save(patron);
    }

    @Override
    public List<Patron> listAllPatrons() {
        return patronRepo.findAll();
    }
}
