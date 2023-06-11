package pet.store.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;


@Data
@NoArgsConstructor
public class PetStoreData {
	private Long petStoreId;
    private String petStoreName;
    private String petStoreAddress;
    private String petStoreCity;
    private String petStoreState;
    private String petStoreZip;
    private String petStorePhone;
	private Set<CustomerData> customers = new HashSet<>();
	private Set<EmployeeData> employees = new HashSet<>();

	public PetStoreData(PetStore petStore) {
		petStoreId = petStore.getPetStoreId();
		petStoreName = petStore.getPetStoreName();
		petStoreAddress = petStore.getPetStoreAddress();
		petStoreCity = petStore.getPetStoreCity();
		petStoreState = petStore.getPetStoreState();
		petStoreZip = petStore.getPetStoreZip();
		petStorePhone = petStore.getPetStorePhone();

		for (Customer customer : petStore.getCustomers()) {
			customers.add(new CustomerData(customer) );
		}

		for (Employee employee : petStore.getEmployees()) {
			employees.add(new EmployeeData(employee) );
		}
	}

	@Data
	@NoArgsConstructor
	public static class CustomerData {
		private Long customerId;
	    private String customerFirstName;
	    private String customerLastName;
	    private String customerEmail;
	
	    public CustomerData(Customer customer) {
		    customerId = customer.getCustomerId();
		    customerFirstName = customer.getCustomerFirstName();
		    customerLastName = customer.getCustomerLastName();
		    customerEmail = customer.getCustomerEmail();
	    }
	}
		    
	@Data
	@NoArgsConstructor
	public static class EmployeeData {
		private Long employeeId;
	    private String employeeFirstName;
	    private String employeeLastName;
		private String employeePhone;
		private String employeeJobTitle;
	
		public EmployeeData(Employee employee) {
	       employeeId = employee.getEmployeeId();
	       employeeFirstName = employee.getEmployeeFirstName();
	       employeeLastName = employee.getEmployeeLastName();
	       employeePhone = employee.getEmployeePhone();
	       employeeJobTitle = employee.getEmployeeJobTitle();   
		}
	}
}