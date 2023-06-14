package pet.store.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.PetStoreCustomer;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreEmployee;
import pet.store.service.PetStoreService;

@RestController
@RequestMapping("/pet_store")
@Slf4j
public class PetStoreController {
	 
	@Autowired
	 private PetStoreService petStoreService;

	@PostMapping("/{petStoreId}/employee")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreEmployee addEmployee(
	@PathVariable Long petStoreId,
	@RequestBody PetStoreEmployee employee) {
	log.info("Adding new employee to pet store: {}", petStoreId);
		return petStoreService.saveEmployee(petStoreId, employee);
}
	
	@PostMapping("/{petStoreId}/customer")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreCustomer addCustomer(
	@PathVariable Long petStoreId,
	@RequestBody PetStoreCustomer customer) {
	log.info("Adding new customer to pet store: {}", petStoreId);
		return petStoreService.saveCustomer(petStoreId, customer);
}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreData insertPetStore(
	@RequestBody PetStoreData petStoreData) {
	log.info("Create a new pet store: {}", petStoreData);
		return petStoreService.savePetStore(petStoreData);
}
	
	@PutMapping("/{petStoreId}")
	public PetStoreData updatePetStore(@PathVariable Long petStoreId, @RequestBody PetStoreData petStoreData) {
	petStoreData.setPetStoreId(petStoreId);
	log.info("Updating pet store {}", petStoreData);
	return petStoreService.savePetStore(petStoreData);
	}
	
	@GetMapping
    public List<PetStoreData> retrieveAllPetStores() {
    log.info("Retrieve all pet stores called.");
    return petStoreService.retrieveAllPetStores();
    }
	
	@GetMapping("/{petStoreId}")
	public PetStoreData retrievePetStoreById(@PathVariable Long petStoreId) {
	log.info("Retrieve pet store with ID+{}", petStoreId);
	return petStoreService.retrievePetStoreById(petStoreId);
	}
	
	@DeleteMapping("/{petStoreId}")
	public Map<String, String> deletePetStoreById(@PathVariable Long petStoreId) {
	log.info("Delete pet store data with ID={}", petStoreId);
	petStoreService.deletePetStoreById(petStoreId);
	return Map.of("message", "Delete pet store with ID=" + petStoreId + " was successful.");
	}
}