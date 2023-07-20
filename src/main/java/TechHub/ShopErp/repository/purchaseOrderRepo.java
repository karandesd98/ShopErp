package TechHub.ShopErp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import TechHub.ShopErp.tables.Invoice;
import TechHub.ShopErp.tables.PurchaseOrder;

public interface purchaseOrderRepo extends JpaRepository<PurchaseOrder, Integer>  {

}
