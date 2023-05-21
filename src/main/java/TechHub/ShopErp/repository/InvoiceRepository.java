package TechHub.ShopErp.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import TechHub.ShopErp.tables.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

}