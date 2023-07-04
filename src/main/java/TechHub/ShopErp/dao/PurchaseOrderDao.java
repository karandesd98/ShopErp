package TechHub.ShopErp.dao;

import java.util.List;
import java.util.Map;

public interface PurchaseOrderDao {

	List<Object[]> getAllPurchaseOrderOfShop(Integer shopId);

	void saveNewPurchaseOrder(Object... objects);

}
