package pet.store.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Employee {
	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long employeeId;
    private String employeeFirstName;
    private String employeeLastName;
	private String employeePhone;
	private String employeeJobTitle;
    
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pet_store_id")
    private PetStore petStore;
    
    public Long getId() {
    	return employeeId;
    }
    
    public void setId(Long id) {
    	this.employeeId = id;
    }
    
    public String getEmployeeFirstName() {
    	return employeeFirstName;
    }
    
    public void setEmployeeFirstName(String firstName) {
    	this.employeeFirstName = firstName;
    }
    
    public String getEmployeeLastName() {
    	return employeeLastName;
    }
    
    public void setEmployeeLastName(String lastName) {
    	this.employeeLastName = lastName;
    }
    
	public String getEmployeePhone() {
		return employeePhone;
	}
	
	public void setEmployeePhone(String phone) {
    	this.employeePhone = phone;
    }

	public String getEmployeeJobTitle() {
		return employeeJobTitle;
	}
	
	public void setEmployeeJobTitle(String jobTitle) {
    	this.employeeJobTitle = jobTitle;
    }
}
