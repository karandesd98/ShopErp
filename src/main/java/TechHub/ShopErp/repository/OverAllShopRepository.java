package TechHub.ShopErp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import TechHub.ShopErp.tables.Invoice;
import TechHub.ShopErp.tables.OverAllShop;

public interface OverAllShopRepository extends JpaRepository<OverAllShop,Integer> {

	@Query(value = "select overall_shop_id, active_purchase_order_detail_id, create_date, is_deleted, per_itom_purchased_price, per_itom_sold_price, product_name, sold_type, total_itom_quantity, unique_no, update_date, shop_id, active_purchase_order_id\r\n"
			      + "from overall_shop where product_name like %:keyword%", nativeQuery = true)
	List<Object[]>  findByProductNameContaining(@Param("keyword") String keyword);

	
}
