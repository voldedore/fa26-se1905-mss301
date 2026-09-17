package vn.edu.fpt.mss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.fpt.mss.entity.InvoiceLine;

public interface InvoiceLineRepository extends JpaRepository<InvoiceLine, Integer> {
}