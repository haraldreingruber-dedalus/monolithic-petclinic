package org.springframework.samples.petclinic.vets;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RemoteVetService implements IVetService
{

    @Override
	public Collection<VetDTO> allVets() {
        return Arrays.asList(new RestTemplate().getForObject("http://localhost:8089/vets", VetDTO[].class));
    }
}
