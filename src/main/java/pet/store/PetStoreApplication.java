package pet.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "pet.store")
@EntityScan(basePackages = "pet.store.entity")
public class PetStoreApplication {
	public static void main (String[] args) {
		SpringApplication.run(PetStoreApplication.class, args);
	}
}
