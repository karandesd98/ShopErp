package TechHub.ShopErp.Managers;

import java.util.List;

import TechHub.ShopErp.tables.PurchaseOrderDetail;

public interface PurchaseOrderDetaiManager {
	
	 PurchaseOrderDetail create(PurchaseOrderDetail purchaseOrderDetail);
	 List<PurchaseOrderDetail> getAll();
	 PurchaseOrderDetail findById(Integer purchaseOrderDetailId);
	 PurchaseOrderDetail update(PurchaseOrderDetail purchaseOrderDetail);
	 void deleteById(Integer purchaseOrderDetailId);
	 List<Object[]> savePurchaseOrderDetail(StringBuilder productTypeMaseterDetailStr);	
	 List<Object[]> getPurchaseOrderDetail(Integer purchaseOrderId,Integer shopId);
	 
	 public List<Object[]> findByProductNameContaining(String pName);
}
