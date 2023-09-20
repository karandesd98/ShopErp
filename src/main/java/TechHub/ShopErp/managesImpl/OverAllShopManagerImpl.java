package TechHub.ShopErp.managesImpl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;

import TechHub.ShopErp.Managers.OverAllShopManager;
import TechHub.ShopErp.repository.OverAllShopRepository;
import TechHub.ShopErp.tables.OverAllShop;
import TechHub.ShopErp.utilityAndSecurity.MultipleResultQuery;

@Service
public class OverAllShopManagerImpl implements OverAllShopManager {

	@Autowired
	OverAllShopRepository overAllShopRepository;
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
	private DataSource dataSource;


	
	// Create a new product
		@Override
	    public OverAllShop create(OverAllShop OverAllShop) {
	        return overAllShopRepository.save(OverAllShop);
	    }

	    // Retrieve all products
		@Override
	    public List<OverAllShop> getAll() {
	        return overAllShopRepository.findAll();
	    }

	    // Retrieve a product by ID
		@Override
		public OverAllShop findById(Integer OverAllShoplId) {
			Optional<OverAllShop> optionalProduct = overAllShopRepository.findById(OverAllShoplId);
	        return optionalProduct.orElseGet(() -> new OverAllShop());
		}

	    // Update an existing product
	    public OverAllShop update(OverAllShop OverAllShop) {
	        return overAllShopRepository.save(OverAllShop);
	    }

	    // Delete a product by ID
	    @Override
	    public void deleteById(Integer OverAllShoplId) {
	    	overAllShopRepository.deleteById(OverAllShoplId);
	    }

		@Override
		public Map<Integer, List<Object[]>> addPurchaseOrderToShop(Integer shopId, Integer purchaseOrderId) {

			
			Map<Integer, List<Object[]>> mapObj = null;
			try {
				Connection con =DataSourceUtils.getConnection(dataSource);
				if (con != null) {
					MultipleResultQuery mrq = new MultipleResultQuery(con, "CALL sp_addPurchaseOrderToShop(:shopId, :purchaseOrderId)");
					mrq.setInt("shopId", shopId);
					mrq.setInt("purchaseOrderId", purchaseOrderId);
					mapObj = mrq.execute();
				} else {
					System.out.println("can not connect to database");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return mapObj;
		}

		@Override
		public Map<Integer, List<Object[]>> getOverAllShop(Integer shopId) {
			
			Map<Integer, List<Object[]>> mapObj = null;
			try {
				Connection con =DataSourceUtils.getConnection(dataSource);
				if (con != null) {
					MultipleResultQuery mrq = new MultipleResultQuery(con, "CALL sp_getOverAllShop(:shopId)");
					mrq.setInt("shopId", shopId);
					mapObj = mrq.execute();
				} else {
					System.out.println("can not connect to database");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return mapObj;
		}

		@Override
		public List<Object[]> findByProductNameContaining(String itomName) {
			
			return overAllShopRepository.findByProductNameContaining(itomName);
		}


}
