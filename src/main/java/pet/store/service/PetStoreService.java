package pet.store.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pet.store.controller.model.PetStoreData;
import pet.store.dao.PetStoreDao;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;

@Service
public class PetStoreService {
	@Autowired
    private PetStoreDao petStoreDao;

@Transactional (readOnly = false) 
public PetStoreData savePetStore(PetStoreData petStoreData) {
	Long petStoreId = petStoreData.getPetStoreId();
		PetStore petStore = new PetStore(); //(petStoreData.getPetStoreId(), petStoreData.getPetStoreName());
		setFieldsInPetStore (petStore, petStoreData);
		return new PetStoreData (petStoreDao.save(petStore));
	}

private void setFieldsInPetStore(PetStore petStore, PetStoreData petStoreData) {
		petStore.setPetStoreName(petStoreData.getPetStoreName());
        petStore.setPetStoreId(petStoreData.getPetStoreId());
        petStore.setPetStoreAddress(petStoreData.getPetStoreAddress());
        petStore.setPetStoreCity(petStoreData.getPetStoreCity());
        petStore.setPetStoreState(petStoreData.getPetStoreState());
        petStore.setPetStoreZip(petStoreData.getPetStoreZip());
        petStore.setPetStorePhone(petStoreData.getPetStorePhone());
    }

public PetStore findOrCreatePetStore(Long petStoreData, String petStoreName) {
	PetStore petStore;
		if(Objects.isNull(petStoreData)) {
			Optional<PetStore> opPetStore = petStoreDao.findByPetStoreName(petStoreName);
		if(opPetStore.isPresent()) {
			throw new DuplicateKeyException("Pet Store with name " + petStoreName + " already exists.");
		}
			petStore = new PetStore();
		} else {
			petStore = findPetStoreById(petStoreData);
		}
		  return petStore;
}	

@Transactional(readOnly = true)
public List<PetStoreData> retrieveAllPetStoreData() {
	List<PetStore> petStoreData = petStoreDao.findAll();
	List<PetStoreData> response = new LinkedList<>();
	
	for(PetStore store : petStoreData) {
		response.add(new PetStoreData(store));
		}
	return response;
	}

@Transactional(readOnly = true)
public PetStoreData retrievePetStoreId(Long petStoreId) {
	PetStore petStore = findPetStoreById(petStoreId);
	
	if (!petStore.getId().equals(petStoreId)) {
		throw new IllegalStateException ("Pet store with ID=" + petStoreId + " is not valid.");
	}
	return new PetStoreData(petStore);
	}

@Transactional(readOnly = false)
public void deletePetStoreById(Long petStoreId) {
	PetStore petStore = findPetStoreById(petStoreId);
	petStoreDao.delete(petStore);
	}

@Transactional(readOnly = false)
public PetStoreData savePetStore(Long petStoreId, PetStoreData petStoreData) {
	PetStore petStore;
    if (petStoreId != null) {
        petStore = findPetStoreById(petStoreId);
    } else {
        petStore = findOrCreatePetStore(petStoreData.getPetStoreId(), petStoreData.getPetStoreName());
    }
    Set<PetStore> petStores = petStoreDao.findAllByPetStoreIn(petStoreData.getPetStoreName());
    setPetStoreFields(petStore, petStoreData);
    petStoreDao.save(petStore);
    return new PetStoreData(petStore);
}

private void setPetStoreFields(PetStore petStore, PetStoreData petStoreData) {
	petStore.setPetStoreName(petStoreData.getPetStoreName());
	petStore.setPetStoreId(petStoreData.getPetStoreId());
	petStore.setPetStoreAddress(petStoreData.getPetStoreAddress());
	petStore.setPetStoreCity(petStoreData.getPetStoreCity());
	petStore.setPetStoreState(petStoreData.getPetStoreState());
	petStore.setPetStoreZip(petStoreData.getPetStoreZip());
	petStore.setPetStorePhone(petStoreData.getPetStorePhone());
	}

private PetStore findOrCreatePetStoreById(Long petStoreId, String petStorePhone) {
	PetStore petStore;
	if(petStoreId != null) {
		petStore = findPetStoreById(petStoreId);
	} else {
		petStore = petStoreDao.findByPetStoreName(petStorePhone)
                .orElseGet(PetStore::new);
	}
	return petStore;
	}	

private PetStore findPetStoreById (Long petStoreId) {
	return petStoreDao.findById(petStoreId)
			.orElseThrow(() -> new NoSuchElementException(
					"Pet Store with ID=" + petStoreId + " does not exist."));
	}
}
