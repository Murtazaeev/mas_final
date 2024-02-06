package mas.project.repository;

import mas.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("from users as u left join fetch u.customer where u.customer.id = :id")
    User getCustomerUserDetails(@Param("id") UUID id);

}
