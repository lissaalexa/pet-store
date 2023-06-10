package pet.store.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class PetStore {
	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long petStoreId;
	
    private String petStoreName;
    private String petStoreAddress;
    private String petStoreCity;
    private String petStoreState;
    private String petStoreZip;
    private String petStorePhone;
    
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "pet_store_customer",
            joinColumns = @JoinColumn(name = "pet_store_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id"))
    private Set<Customer> customers = new HashSet<>();
    
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "petStore", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Employee> employees = new HashSet<>();
    
    public Long getId() {
    	return petStoreId;
    }
    
    public void setId(Long id) {
    	this.petStoreId = id;
    }
    
    public String getName() {
    	return petStoreName;
    }
    
    public void setName(String name) {
    	this.petStoreName = name;
    }

    public String getAddress() {
    	return petStoreAddress;
    }

    public void setAddress(String address) {
    	this.petStoreAddress = address;
    }
    
    public String getCity() {
    	return petStoreCity;
    }
    
    public void setCity(String city) {
    	this.petStoreCity = city;
    }

  public String getState() {
    	return petStoreState;
    }
    
    public void setState(String state) {
    	this.petStoreState = state;
    }

    public String getZip() {
    	return petStoreZip;
    }
    
    public void setZip(String zip) {
    	this.petStoreZip = zip;
    }

    public String getPhone() {
    	return petStorePhone;
    }
    
    public void setPhone(String phone) {
    	this.petStorePhone = phone;
    }
    

    public Set<Customer> getCustomers() {
    	return customers;
    }
    
    public Set<Employee> getEmployees() {
    	return employees;
    }
    
    
}
