package mas.project.repository;

import mas.project.dto.CustomerDetailsDTO;
import mas.project.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends CrudRepository<Customer, UUID> {
    @Query("SELECT new mas.project.dto.CustomerDetailsDTO(c.id, c.email, u.name, u.surname) FROM Customer c JOIN c.user u")
    List<CustomerDetailsDTO> getAllCustomerDetails();
}
