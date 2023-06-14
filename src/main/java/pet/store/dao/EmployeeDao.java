package pet.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pet.store.entity.Employee;

@Repository
@Component
public interface EmployeeDao extends JpaRepository<Employee, Long> {

}
