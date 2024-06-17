package mas.project.repository;

import mas.project.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {

    @Query("from invoice as i left join fetch i.order where i.order.id = :id")
    Invoice findInvoiceByOrderId(@Param("id") UUID id);
}