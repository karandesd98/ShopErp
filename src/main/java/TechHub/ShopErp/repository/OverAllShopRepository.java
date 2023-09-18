package TechHub.ShopErp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import TechHub.ShopErp.tables.Invoice;
import TechHub.ShopErp.tables.OverAllShop;

public interface OverAllShopRepository extends JpaRepository<OverAllShop,Integer> {

	
}
