package vn.edu.fpt.mss.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.fpt.mss.entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

    List<Invoice> findByCustomerIdOrderByInvoiceDateDesc(Integer customerId);

    @EntityGraph(attributePaths = "lines")
    @Query("select i from Invoice i where i.invoiceId = :id")
    Optional<Invoice> findWithLinesById(@Param("id") Integer id);
}