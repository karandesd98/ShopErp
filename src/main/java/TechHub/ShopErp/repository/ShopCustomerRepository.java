package TechHub.ShopErp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import TechHub.ShopErp.tables.ShopCustomer;

public interface ShopCustomerRepository extends JpaRepository<ShopCustomer, Integer> {

}
