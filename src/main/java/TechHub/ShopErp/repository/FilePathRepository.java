package TechHub.ShopErp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import TechHub.ShopErp.tables.PurchaseOrder;

public interface FilePathRepository extends JpaRepository<PurchaseOrder, Long> {
}
