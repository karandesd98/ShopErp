package TechHub.ShopErp.Managers;

import java.util.List;
import java.util.Map;

public interface PurchaseOrderManager {

	List<Object[]> getAllPurchaseOrderOfShop(Integer shopId);

	void saveNewPurchaseOrder(Object... objects);

}
