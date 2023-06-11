package pet.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.PetStoreData;
import pet.store.service.PetStoreService;

@RestController
@RequestMapping("/pet_store")
@Slf4j
public class PetStoreController {
	 
	@Autowired
	 private PetStoreService petStoreService;

	@PostMapping("/pet_store")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreData insertPetStore(
	@RequestBody PetStoreData petStoreData) {
	log.info("Create a new pet store: {}", petStoreData);
		return petStoreService.savePetStore(petStoreData);
}
	
	@PutMapping("/pet_store/{petStoreId}")
	public PetStoreData updatePetStore(@PathVariable Long petStoreId, @RequestBody PetStoreData petStoreData) {
	petStoreData.setPetStoreId(petStoreId);
	log.info("Updating pet store {}", petStoreData);
	return petStoreService.savePetStore(petStoreData);
	}
}

	//@GetMapping("/pet_store")
	//public List<PetStoreData> retrieveAllPetStoreData() {
	//log.info("Retrieve all pet stores called.");
	//return petStoreService.retrieveAllPetStoreData();
//}

	//@GetMapping("/pet_store/{petStoreId}")
	//public PetStoreData retrievePetStoreById(@PathVariable Long petStoreId) {
	//log.info("Retrieving pet store with ID={}", petStoreId);
	//return petStoreService.retrievePetStoreId(petStoreId);
//}

	//@DeleteMapping("/pet_store")
	//public void deleteAllPetStoreData() {
		//log.info("Attempting to delete all pet store data");
		//throw new UnsupportedOperationException("Deleting all pet store data is not allowed");
//}

	//@DeleteMapping("/pet_store/{petStoreId}")
	//public Map<String, String> deletePetStoreById(@PathVariable Long petStoreId) {
	//log.info("Deleting pet store data with ID={}", petStoreId);
	//petStoreService.deletePetStoreById(petStoreId);
	//return Map.of("message", "Delete of pet store with ID=" + petStoreId + " was successful.");
//}

	//@PostMapping("/pet_store/{petStoreId}")
	//@ResponseStatus(code = HttpStatus.CREATED)
	//public PetStoreData insertPetStore(@PathVariable Long petStoreId, @RequestBody PetStoreData petStoreData) {
	//log.info("Creating pet store {} with ID={}", petStoreId, petStoreData);
	//return petStoreService.savePetStore(petStoreId, petStoreData);
	//}
//}
