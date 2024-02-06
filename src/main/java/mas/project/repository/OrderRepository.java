package mas.project.repository;

import mas.project.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    @Query("from orders as o join fetch o.customer where o.customer.id = :cusId order by o.orderDate desc")
    List<Order> findCustomerOrdersByCustomerId(@Param("cusId") UUID cusId);

    @Query("from orders as o where o.id = :orderId ")
    Order findOrderById(@Param("orderId") UUID orderId);

}
