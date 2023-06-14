package pet.store.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pet.store.controller.model.PetStoreCustomer;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreEmployee;
import pet.store.dao.CustomerDao;
import pet.store.dao.EmployeeDao;
import pet.store.dao.PetStoreDao;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;

@Service
public class PetStoreService {
	
	@Autowired
    private PetStoreDao petStoreDao;
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private CustomerDao customerDao;

	@Transactional (readOnly = false) 
	public PetStoreData savePetStore(PetStoreData petStoreData) {
		Long petStoreId = petStoreData.getPetStoreId();
		PetStore petStore = findOrCreatePetStore (petStoreId);
		
		if(petStoreId == null) {
			petStore = findOrCreatePetStore(petStoreId);
		} else {
			petStore = findPetStoreById(petStoreId); 
			if(petStore == null) {
				throw new NoSuchElementException("Pet store with ID=" + petStoreId + " was not found."); 
			}
		}
		copyPetStoreFields(petStore, petStoreData);
		return new PetStoreData(petStoreDao.save(petStore));
	}
	
	@Transactional (readOnly = false)
	public PetStoreEmployee saveEmployee(Long petStoreId, PetStoreEmployee petStoreEmployee) {
	    PetStore petStore = findPetStoreById(petStoreId);
	    Long employeeId = petStoreEmployee.getId();
	    Employee employee = findOrCreateEmployee(petStoreId, employeeId);

	    copyEmployeeFields(employee, petStoreEmployee);
	    employee.setPetStore(petStore);
	    petStore.getEmployees().add(employee);
	    Employee savedEmployee = employeeDao.save(employee);
	    return new PetStoreEmployee(savedEmployee);
	}
	
	@Transactional (readOnly = false)
	public List<PetStoreData> retrieveAllPetStores() {
        List<PetStore> petStores = petStoreDao.findAll();
        List<PetStoreData> petStoreDataList = new ArrayList<>();

        for (PetStore petStore : petStores) {
            PetStoreData petStoreData = new PetStoreData(petStore);
            petStoreData.setCustomers(null);
            petStoreData.setEmployees(null); 
            petStoreDataList.add(petStoreData);
        }
        return petStoreDataList;
	}

	@Transactional (readOnly = false)
	public PetStoreData retrievePetStoreById(Long petStoreId) {
	    PetStore petStore = petStoreDao.findById(petStoreId)
	        .orElseThrow(() -> new NoSuchElementException("Pet store with ID=" + petStoreId + " was not found."));

	    return new PetStoreData(petStore);
	}
	
	@Transactional (readOnly = false)
	public void deletePetStoreById(Long petStoreId) {
	    PetStore petStore = findPetStoreById(petStoreId);
	    petStoreDao.delete(petStore);
	}
	
	@Transactional (readOnly = false)
	public PetStoreCustomer saveCustomer(Long petStoreId, PetStoreCustomer petStoreCustomer) {
		PetStore petStore = findPetStoreById(petStoreId);
		Long customerId = petStoreCustomer.getId();
		Customer customer = findOrCreateCustomer(petStoreId, customerId);
		
		copyCustomerFields(customer, petStoreCustomer);
		//customer.setPetStore(petStore);
		petStore.getCustomers().add(customer);
		Customer savedCustomer = customerDao.save(customer);
		return new PetStoreCustomer(savedCustomer);
	}
	   
	private void copyCustomerFields(Customer customer, PetStoreCustomer petStoreCustomer) {
		customer.setCustomerId(petStoreCustomer.getId());
		customer.setCustomerFirstName(petStoreCustomer.getFirstName());
		customer.setCustomerLastName(petStoreCustomer.getLastName());
		customer.setCustomerEmail(petStoreCustomer.getEmail());
	}

	private void copyPetStoreFields (PetStore petStore, PetStoreData petStoreData) {
		petStore.setPetStoreName(petStoreData.getPetStoreName());
		petStore.setPetStoreId(petStoreData.getPetStoreId());
		petStore.setPetStoreAddress(petStoreData.getPetStoreAddress());
		petStore.setPetStoreCity(petStoreData.getPetStoreCity());
		petStore.setPetStoreState(petStoreData.getPetStoreState());
		petStore.setPetStoreZip(petStoreData.getPetStoreZip());
		petStore.setPetStorePhone(petStoreData.getPetStorePhone());
	}

	private PetStore findOrCreatePetStore(Long petStoreId) {
		if(Objects.isNull(petStoreId)) {
			return new PetStore();
		} else {
			return findPetStoreById(petStoreId);
		}
	}	

	private PetStore findPetStoreById(Long petStoreId) {
		return petStoreDao.findById(petStoreId)
			.orElseThrow(() -> new NoSuchElementException("Pet store with ID=" + petStoreId + " was not found."));
	}
	
	private Employee findOrCreateEmployee(Long petStoreId, Long employeeId) {
        if (employeeId == null) {
            return new Employee();
        } else {
            return findEmployeeById(petStoreId, employeeId);
        }
    }

    private Employee findEmployeeById(Long petStoreId, Long employeeId) {
        Employee employee = employeeDao.findById(employeeId)
                .orElseThrow(() -> new NoSuchElementException("Employee with ID=" + employeeId + " was not found."));
        if (employee.getPetStore().getId().equals(petStoreId)) {
            return employee;
        } else {
            throw new IllegalArgumentException("Employee with ID=" + employeeId + " does not belong to Pet Store with ID=" + petStoreId);
        }
    }

    private void copyEmployeeFields(Employee employee, PetStoreEmployee petStoreEmployee) {
        employee.setEmployeeFirstName(petStoreEmployee.getFirstName());
        employee.setEmployeeLastName(petStoreEmployee.getLastName());
        employee.setEmployeePhone(petStoreEmployee.getPhone());
        employee.setEmployeeJobTitle(petStoreEmployee.getJobTitle());
    }

	private Customer findOrCreateCustomer(Long petStoreId, Long customerId) {
		  if (customerId == null) {
	            return new Customer();
	        } else {
	            return findCustomerById(petStoreId, customerId);
	        }
	    }

	private Customer findCustomerById(Long petStoreId, Long customerId) {
		Customer customer = customerDao.findById(customerId)
                .orElseThrow(() -> new NoSuchElementException("Customer with ID=" + customerId + " was not found."));
        boolean found = false;
        for (PetStore petStore : customer.getPetStores()) {
        	if(petStore.getPetStoreId() == petStoreId) {
        		found = true;
        		break;
        	}
        }
        if(!found) {
        	throw new IllegalArgumentException("The customer with ID=" + customerId + 
        			" is not a memeber of the pet store wih ID=" + petStoreId);
        }
        return customer;
	}
}