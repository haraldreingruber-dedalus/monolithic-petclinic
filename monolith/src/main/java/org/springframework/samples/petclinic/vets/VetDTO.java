/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.vets;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

/**
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Arjen Poutsma
 */
@SuppressWarnings("serial")
public class VetDTO implements Serializable 
{
    private Set<SpecialtyDTO> specialties;
    
    private String firstName;

    private String lastName;

    public VetDTO(){}
    
    public VetDTO( Vet vet )
    {
    	this.firstName = vet.getFirstName();
    	this.lastName = vet.getLastName();
    	this.specialties = new HashSet<>();
    	
    	if ( vet.getSpecialties() != null ) {
    		this.specialties.addAll( vet.getSpecialties().stream()
    				.map( specialty -> new SpecialtyDTO( specialty ) )
    				.collect(Collectors.toList()) );
    	}
    }
   
    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public List<SpecialtyDTO> getSpecialties() {
        List<SpecialtyDTO> sortedSpecs = new ArrayList<>(this.specialties);
        PropertyComparator.sort(sortedSpecs, new MutableSortDefinition("name", true, true));
        return Collections.unmodifiableList(sortedSpecs);
    }

    public int getNrOfSpecialties() {
        return getSpecialties().size();
    }
}
