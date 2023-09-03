package TechHub.ShopErp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import TechHub.ShopErp.tables.CustomerOrderDetail;

public interface CustomerOrderDetailRepository extends JpaRepository<CustomerOrderDetail, Integer> {

}
