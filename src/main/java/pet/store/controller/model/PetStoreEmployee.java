package pet.store.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.entity.Employee;

@Data
@NoArgsConstructor
public class PetStoreEmployee {
	private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String jobTitle;
    
	public Long getEmployeeId() {
		return id;
	}

	public PetStoreEmployee(Employee savedEmployee) {
		id = savedEmployee.getEmployeeId();
		firstName = savedEmployee.getEmployeeFirstName();
		lastName = savedEmployee.getEmployeeLastName();
		phone = savedEmployee.getEmployeePhone();
		jobTitle = savedEmployee.getEmployeeJobTitle();
	}
	
	
}
