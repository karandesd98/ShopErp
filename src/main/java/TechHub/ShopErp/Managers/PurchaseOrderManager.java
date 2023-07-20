package TechHub.ShopErp.Managers;

import java.util.List;
import java.util.Map;

import TechHub.ShopErp.tables.PurchaseOrder;

public interface PurchaseOrderManager {

	List<Object[]> getAllPurchaseOrderOfShop(Integer shopId);

	void saveNewPurchaseOrder(Object... objects);
	void deletePurchaseOrder(Integer purchaseOrderId);

	void save(PurchaseOrder purchaseOrder);

}
