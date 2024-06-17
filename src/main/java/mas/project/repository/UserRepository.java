package mas.project.repository;

import mas.project.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
    @Query("from User u left join fetch u.customer where u.customer.id = :id")
    User getCustomerUserDetails(UUID id);
}
