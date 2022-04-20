package org.springframework.samples.petclinic.vets;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class VetService {

    private final VetRepository vets;

    public VetService( VetRepository vets ) {
        this.vets = vets;
    }

    public Collection<VetDTO> allVets() {
        return this.vets.findAll().stream()
        		.map( vet -> new VetDTO( vet ) )
        		.collect(Collectors.toList() );
    }
}
