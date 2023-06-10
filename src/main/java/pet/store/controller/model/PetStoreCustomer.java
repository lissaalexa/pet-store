package pet.store.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PetStoreCustomer {
	private Long id;
    private String firstName;
    private String lastName;
    //add other fields from customer entity
    //excluding petStores
}
