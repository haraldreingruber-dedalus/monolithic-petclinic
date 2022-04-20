package org.springframework.samples.petclinic.vets;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VetService {

    private final VetRepository vets;

    public VetService( VetRepository vets ) {
        this.vets = vets;
    }

    public Collection<VetDTO> allVets() {
        return Arrays.asList(new RestTemplate().getForObject("http://localhost:8089/vets", VetDTO[].class));
    }
}
