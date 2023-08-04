package TechHub.ShopErp.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import TechHub.ShopErp.tables.PurchaseOrderDetail;

public interface PurchaseOrderDetailRepo extends JpaRepository<PurchaseOrderDetail, Integer> {

}
