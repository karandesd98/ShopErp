package TechHub.ShopErp.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PURCHASE_ORDER_DETAIL")
public class PurchaseOrderDetail {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PURCHASE_ORDER_DETAIL_ID")
    private Integer  purchaseOrderDetailId;
	
	@Column(name = "PRODUCT_NAME")
	private String productName;
	
	@Column(name = "SOLD_TYPE")
	private String soldType;
	
	@Column(name="ITOM_QUANTITY")
    private Double  itomQuantity;

	@Column(name="PER_ITOM_PURCHASED_PRICE")
    private Double  perItomPurchasedPrice;
	
	@Column(name="TOTAL_ITOM_PURCHASED_PRICE")
    private Double  totalItomPurchasedPrice;
	
	@Column(name="PER_ITOM_SOLD_PRICE")
    private Double  perItomSoldPrice;
	 
	@Column(name="TOTAL_SOLD_PRICE")
    private Double  totalSoldPrice;
	
	@Column(name="PER_ITOM_NEGOTIABLE_PRICE")
    private Double  perItomNegotiablePrice;
	
	@Column(name="TOTAL_NEGOTIABLE_PRICE")
    private Double totalNegotiablePrice;
	
	
	//many to one relationsheep
	 @ManyToOne
	 @JoinColumn(name = "PRODUCT_TYPE_MASTER_ID")
	 private ProductTypeMaster productTypeMasterObj;
	 
	 @ManyToOne
	 @JoinColumn(name = "PURCHASE_ORDER_ID")
	 private PurchaseOrder purchaseOrderObj;
	 
	 @ManyToOne
	 @JoinColumn(name = "SHOP_ID")
	 private Shop shopObj;
	 
	 
	 
	 

	public Integer getPurchaseOrderDetailId() {
		return purchaseOrderDetailId;
	}

	public void setPurchaseOrderDetailId(Integer purchaseOrderDetailId) {
		this.purchaseOrderDetailId = purchaseOrderDetailId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSoldType() {
		return soldType;
	}

	public void setSoldType(String soldType) {
		this.soldType = soldType;
	}

	public Double getItomQuantity() {
		return itomQuantity;
	}

	public void setItomQuantity(Double itomQuantity) {
		this.itomQuantity = itomQuantity;
	}

	public Double getPerItomPurchasedPrice() {
		return perItomPurchasedPrice;
	}

	public void setPerItomPurchasedPrice(Double perItomPurchasedPrice) {
		this.perItomPurchasedPrice = perItomPurchasedPrice;
	}

	public Double getTotalItomPurchasedPrice() {
		return totalItomPurchasedPrice;
	}

	public void setTotalItomPurchasedPrice(Double totalItomPurchasedPrice) {
		this.totalItomPurchasedPrice = totalItomPurchasedPrice;
	}

	public Double getPerItomSoldPrice() {
		return perItomSoldPrice;
	}

	public void setPerItomSoldPrice(Double perItomSoldPrice) {
		this.perItomSoldPrice = perItomSoldPrice;
	}

	public Double getTotalSoldPrice() {
		return totalSoldPrice;
	}

	public void setTotalSoldPrice(Double totalSoldPrice) {
		this.totalSoldPrice = totalSoldPrice;
	}

	public Double getPerItomNegotiablePrice() {
		return perItomNegotiablePrice;
	}

	public void setPerItomNegotiablePrice(Double perItomNegotiablePrice) {
		this.perItomNegotiablePrice = perItomNegotiablePrice;
	}

	public Double getTotalNegotiablePrice() {
		return totalNegotiablePrice;
	}

	public void setTotalNegotiablePrice(Double totalNegotiablePrice) {
		this.totalNegotiablePrice = totalNegotiablePrice;
	}

	public ProductTypeMaster getProductTypeMasterObj() {
		return productTypeMasterObj;
	}

	public void setProductTypeMasterObj(ProductTypeMaster productTypeMasterObj) {
		this.productTypeMasterObj = productTypeMasterObj;
	}

	public PurchaseOrder getPurchaseOrderObj() {
		return purchaseOrderObj;
	}

	public void setPurchaseOrderObj(PurchaseOrder purchaseOrderObj) {
		this.purchaseOrderObj = purchaseOrderObj;
	}

	
	
}
