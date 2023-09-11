package TechHub.ShopErp.tables;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "OVERALL_SHOP")
public class OverAllShop {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="OVERALL_SHOP_ID")
    private Integer overallShopId;
	
	@Column(name = "PRODUCT_NAME")
	private String productName;
	
	@Column(name="UNIQUE_NO")
    private String  unique_no;
	
	@Column(name = "SOLD_TYPE")
	private String soldType;
	
	@Column(name="TOTAL_ITOM_QUANTITY")
    private Double  totalItomQuantity;
	
	@Column(name="PER_ITOM_PURCHASED_PRICE")
    private Double  perItomPurchasedPrice;
	
	@Column(name="PER_ITOM_SOLD_PRICE")
    private Double  perItomSoldPrice;
	
	@Column(name="ACTIVE_PURCHASE_ORDER_DETAIL_ID")
    private Integer  activePurchaseOrderDetailId;
	
	@Column(name = "IS_DELETED", nullable = true, columnDefinition = "boolean default false")
	private Boolean isDeleted;
	
	@CreationTimestamp
	@Column(name = "CREATE_DATE",updatable=false)
	private LocalDateTime createDate;

	@UpdateTimestamp
	@Column(name = "UPDATE_DATE", insertable=false)
	private LocalDateTime updateDate;
	
	// many to one relationsheep
	
	@ManyToOne
	@JoinColumn(name="SHOP_ID")
    private Shop  shopObj;

	public Integer getOverallShopId() {
		return overallShopId;
	}

	public void setOverallShopId(Integer overallShopId) {
		this.overallShopId = overallShopId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getUnique_no() {
		return unique_no;
	}

	public void setUnique_no(String unique_no) {
		this.unique_no = unique_no;
	}

	public String getSoldType() {
		return soldType;
	}

	public void setSoldType(String soldType) {
		this.soldType = soldType;
	}

	public Double getTotalItomQuantity() {
		return totalItomQuantity;
	}

	public void setTotalItomQuantity(Double totalItomQuantity) {
		this.totalItomQuantity = totalItomQuantity;
	}

	public Double getPerItomPurchasedPrice() {
		return perItomPurchasedPrice;
	}

	public void setPerItomPurchasedPrice(Double perItomPurchasedPrice) {
		this.perItomPurchasedPrice = perItomPurchasedPrice;
	}

	public Double getPerItomSoldPrice() {
		return perItomSoldPrice;
	}

	public void setPerItomSoldPrice(Double perItomSoldPrice) {
		this.perItomSoldPrice = perItomSoldPrice;
	}

	public Integer getActivePurchaseOrderDetailId() {
		return activePurchaseOrderDetailId;
	}

	public void setActivePurchaseOrderDetailId(Integer activePurchaseOrderDetailId) {
		this.activePurchaseOrderDetailId = activePurchaseOrderDetailId;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	public Shop getShopObj() {
		return shopObj;
	}

	public void setShopObj(Shop shopObj) {
		this.shopObj = shopObj;
	}
	
	
}
