package org.springframework.samples.petclinic.vets;

import java.util.Collection;

import org.ff4j.FF4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VetService implements IVetService {

	@Autowired
    private LocalVetService localService;
	
	@Autowired
    private RemoteVetService remoteService;

    @Autowired
    public FF4j ff4j;
	
    @Override
	public Collection<VetDTO> allVets() 
    {
    	if ( !ff4j.getFeature("REMOTE_VET_SERVICE").isEnable() ) 
    	{
    		// use local fallback
    		return localService.allVets();
        }
    	else
    	{
    		// use new fancy remote vet service
    		return remoteService.allVets();
    	}
    }
}
