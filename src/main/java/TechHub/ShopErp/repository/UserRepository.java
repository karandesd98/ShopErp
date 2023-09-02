package TechHub.ShopErp.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import TechHub.ShopErp.tables.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	/*
	 * @Transactional
	 * 
	 * @Modifying
	 * 
	 * @Query(value = "update customer set is_deleted=0 where email = ?1",
	 * nativeQuery = true) public void deleteCustomer(String email);
	 */
}
