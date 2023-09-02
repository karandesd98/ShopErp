package TechHub.ShopErp.Managers;

import java.util.List;

import TechHub.ShopErp.tables.Customer;

public interface CustomerManager {
  Customer save(Customer c);
  List<Object[]> getAllCustomerOfShop(Integer shopId);
}
