package TechHub.ShopErp.managesImpl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TechHub.ShopErp.Managers.PurchaseOrderDetaiManager;
import TechHub.ShopErp.helper.ResorcenotFoundException;
import TechHub.ShopErp.repository.PurchaseOrderDetailRepo;
import TechHub.ShopErp.tables.PurchaseOrderDetail;

@Service
public class PurchaseOrderDetaiManagerImpl implements PurchaseOrderDetaiManager {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	PurchaseOrderDetailRepo purchaseOrderDetailRepo;
	
	// Create a new product
	@Override
    public PurchaseOrderDetail create(PurchaseOrderDetail purchaseOrderDetail) {
        return purchaseOrderDetailRepo.save(purchaseOrderDetail);
    }

    // Retrieve all products
	@Override
    public List<PurchaseOrderDetail> getAll() {
        return purchaseOrderDetailRepo.findAll();
    }

    // Retrieve a product by ID
	@Override
	public PurchaseOrderDetail findById(Integer purchaseOrderDetailId) {
		Optional<PurchaseOrderDetail> optionalProduct = purchaseOrderDetailRepo.findById(purchaseOrderDetailId);
        return optionalProduct.orElseGet(() -> new PurchaseOrderDetail());
	}

    // Update an existing product
    public PurchaseOrderDetail update(PurchaseOrderDetail purchaseOrderDetail) {
        return purchaseOrderDetailRepo.save(purchaseOrderDetail);
    }

    // Delete a product by ID
    @Override
    public void deleteById(Integer purchaseOrderDetailId) {
    	purchaseOrderDetailRepo.deleteById(purchaseOrderDetailId);
    }



	 public List<Object[]> savePurchaseOrderDetail(StringBuilder str) {
		 String purchaseOrderDetail=str.toString();
		 
	        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("sp_saveProductTypeMasterDetail");
	        storedProcedure.registerStoredProcedureParameter("productTypeMasterData", String.class, ParameterMode.IN);
	        storedProcedure.setParameter("productTypeMasterData", purchaseOrderDetail);
	        
	        // Execute the stored procedure
	        storedProcedure.execute();

	        // Retrieve the result sets
	        List<Object[]> results = storedProcedure.getResultList();
	        
	        return results;
	    }

	@Override
	public List<Object[]> getPurchaseOrderDetail(Integer purchaseOrderId, Integer shopId) {
		
	    String nativeQuery="select * from purchase_order_detail pod where pod.purchase_order_id= ? and pod.shop_id= ?";
        Query query = entityManager.createNativeQuery(nativeQuery);
        query.setParameter(1, purchaseOrderId);
        query.setParameter(2, shopId);

        @SuppressWarnings("unchecked")
        List<Object[]> resultList = query.getResultList();
        return resultList;
        
        }
	 
	 
	


}
