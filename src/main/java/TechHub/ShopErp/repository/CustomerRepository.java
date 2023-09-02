package TechHub.ShopErp.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import TechHub.ShopErp.tables.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Transactional
	@Modifying
	@Query(value = "update customer set is_deleted=0 where email = ?1", nativeQuery = true)
	public void deleteCustomer(String email);
}
