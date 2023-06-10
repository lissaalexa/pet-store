package pet.store.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PetStoreEmployee {
	private Long id;
    private String firstName;
    private String lastName;
    //add other fields from employee entity
    //excluding petStores
}
