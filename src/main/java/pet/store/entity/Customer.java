package pet.store.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Customer {
	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long customerId;
	
    private String customerFirstName;
    private String customerLastName;
    private String customerEmail;
    
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(mappedBy = "customers", cascade = CascadeType.PERSIST)
    private Set<PetStore> petStores = new HashSet<>();
    
    public Long getId() {
    	return customerId;
    }
    
    public void setId(Long id) {
    	this.customerId = id;
    }
    
    public String getFirstName() {
    	return customerFirstName;
    }
    
    public void setFirstName(String firstName) {
    	this.customerFirstName = firstName;
    }
    
    public String getLastName() {
    	return customerLastName;
    }
    
    public void setLastName(String lastName) {
    	this.customerLastName = lastName;
    }
    
    public String getEmail() {
    	return customerEmail;
    }
    
    public void setEmail(String email) {
    	this.customerEmail = email;
    }
}
