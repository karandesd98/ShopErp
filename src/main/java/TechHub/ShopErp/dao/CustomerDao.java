package TechHub.ShopErp.dao;

import java.util.List;

public interface CustomerDao {
	List<Object[]> getAllCustomerOfShop(Integer shopId);
}
