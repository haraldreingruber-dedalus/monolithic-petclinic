package org.springframework.samples.petclinic.vets;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LocalVetService implements IVetService 
{
    private final VetRepository vets;

    public LocalVetService( VetRepository vets ) {
        this.vets = vets;
    }

    public Collection<VetDTO> allVets() {
        return this.vets.findAll().stream()
        		.map( vet -> new VetDTO( vet ) )
        		.collect(Collectors.toList() );
    }
}
