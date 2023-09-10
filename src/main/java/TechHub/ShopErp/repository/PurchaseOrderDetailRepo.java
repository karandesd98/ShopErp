package TechHub.ShopErp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import TechHub.ShopErp.tables.PurchaseOrderDetail;

public interface PurchaseOrderDetailRepo extends JpaRepository<PurchaseOrderDetail, Integer> {

	// public List<PurchaseOrderDetail> findByProductNameContaining(String pName);
	
	@Query(value = "SELECT purchase_order_detail_id, itom_quantity, per_itom_negotiable_price, per_itom_purchased_price, per_itom_sold_price, product_name, sold_type, total_itom_purchased_price, total_negotiable_price, total_sold_price, product_type_master_id, purchase_order_id, shop_id FROM purchase_order_detail WHERE product_name LIKE %:keyword%", nativeQuery = true)
	List<Object[]>  findByProductNameContaining(@Param("keyword") String keyword);
}
