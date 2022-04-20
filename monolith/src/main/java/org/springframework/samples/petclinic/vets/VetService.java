package org.springframework.samples.petclinic.vets;

import java.util.Collection;

import org.springframework.stereotype.Service;

@Service
public class VetService {

    private final VetRepository vets;

    public VetService( VetRepository vets ) {
        this.vets = vets;
    }

    public Collection<Vet> allVets() {
        return this.vets.findAll();
    }
}
