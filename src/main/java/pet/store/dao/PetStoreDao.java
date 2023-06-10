package pet.store.dao;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import pet.store.entity.PetStore;

public interface PetStoreDao extends JpaRepository<PetStore, Long> {

	Optional<PetStore> findByPetStoreName(String petStoreData);

	Set<PetStore> findAllByPetStoreIn(String petStoreName);
}
