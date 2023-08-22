package TechHub.ShopErp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import TechHub.ShopErp.tables.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
