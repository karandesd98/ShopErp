package TechHub.ShopErp.Managers;

import java.util.List;
import java.util.Map;

import TechHub.ShopErp.tables.OverAllShop;

public interface OverAllShopManager {

	 OverAllShop create(OverAllShop overAllShop);
	 List<OverAllShop> getAll();
	 OverAllShop findById(Integer OverAllShopId);
	 OverAllShop update(OverAllShop OverAllShop);
	 void deleteById(Integer OverAllShopId);
	Map<Integer, List<Object[]>> addPurchaseOrderToShop(Integer shopId, Integer purchaseOrderId);
	Map<Integer, List<Object[]>> getOverAllShop(Integer shopId);
	List<Object[]> findByProductNameContaining(String itomName);
}
