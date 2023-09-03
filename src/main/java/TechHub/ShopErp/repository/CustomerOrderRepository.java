package TechHub.ShopErp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import TechHub.ShopErp.tables.CustomerOrder;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Integer>{

}
