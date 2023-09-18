package TechHub.ShopErp.tables;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PURCHASE_ORDER")
public class PurchaseOrder {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PURCHASE_ORDER_ID")
    private Integer  purchaseOrderId;
	
	@Column(name = "PURCHASE_ORDER_NAME")
	private String purchaseOrderName;
	
	@Column(name = "PURCHASE_BY")
	private String purchaseBy;
	
	@Column(name="PURCHASE_ORDER_TOTAL_AMOUNT")
    private int  purchaseOrderTotalAmount;
	
	@Column(name="PURCHASE_ORDER_AMOUNT")
    private int  purchaseOrderAmount;
	
	@Column(name="PURCHASE_OTHER_AMOUNT")
    private int  purchaseOrderOtherAmount;
	
	@Column(name = "PURCHASE_FROM")
	private String purchaseFrom;
	
	@Column(name = "NOTE" , length = 4000)
	private String note;
	
	@Column(name = "PURCHASE_AT")
	private Date purchaseAt;
	
	@Column(name = "PURCHASE_FROM_MOBILE_NO")
	private String purchaseFromMobileNo;
	
	@Column(name = "UPLOADED_BILL_PATH", length=4000)
	private String uploadedBillPath;
	
	@Column(name="CREATED_BY")
    private int  createdBy;
	
	@Column(name="DELETED_BY")
    private int  deletedBy;
	
	@Column(name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;
	
	@Column(name = "DELETED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
	
	@Column(name = "IS_DELETED", nullable = true, columnDefinition = "boolean default false")
	private boolean isDeleted;
	
	@Column(name = "SHOP_ID")
	private int shop_id;
	
	@Column(name = "IS_ADDED_TO_SHOP", nullable = true, columnDefinition = "boolean default false")
	private boolean isAddedToShop;
	
	/*
	// one to many relationSheep
    @OneToMany(mappedBy="purchaseOrderObj")
	private List<PurchaseOrderDetail> purchaseOrderDetailList; 

    public List<PurchaseOrderDetail> getPurchaseOrderDetailList() {
		return purchaseOrderDetailList;
	}

	public void setPurchaseOrderDetailList(List<PurchaseOrderDetail> purchaseOrderDetailList) {
		this.purchaseOrderDetailList = purchaseOrderDetailList;
	}
	*/
	

	public String getPurchaseOrderName() {
		return purchaseOrderName;
	}

	public void setPurchaseOrderName(String purchaseOrderName) {
		this.purchaseOrderName = purchaseOrderName;
	}

	public int getPurchaseOrderTotalAmount() {
		return purchaseOrderTotalAmount;
	}

	public void setPurchaseOrderTotalAmount(int purchaseOrderTotalAmount) {
		this.purchaseOrderTotalAmount = purchaseOrderTotalAmount;
	}

	public String getPurchaseFrom() {
		return purchaseFrom;
	}

	public void setPurchaseFrom(String purchaseFrom) {
		this.purchaseFrom = purchaseFrom;
	}

	public String getPurchaseFromMobileNo() {
		return purchaseFromMobileNo;
	}

	public void setPurchaseFromMobileNo(String purchaseFromMobileNo) {
		this.purchaseFromMobileNo = purchaseFromMobileNo;
	}

	public String getUploadedBillPath() {
		return uploadedBillPath;
	}

	public void setUploadedBillPath(String uploadedBillPath) {
		this.uploadedBillPath = uploadedBillPath;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public int getDeletedBy() {
		return deletedBy;
	}

	public void setDeletedBy(int deletedBy) {
		this.deletedBy = deletedBy;
	}

	public Date getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "PurchaseOrder [purchaseOrderId=" + purchaseOrderId + ", purchaseOrderName=" + purchaseOrderName
				+ ", purchaseOrderTotalAmount=" + purchaseOrderTotalAmount + ", purchaseFrom=" + purchaseFrom
				+ ", purchaseFromMobileNo=" + purchaseFromMobileNo + ", uploadedBillPath=" + uploadedBillPath
				+ ", createdBy=" + createdBy + ", deletedBy=" + deletedBy + ", deletedAt=" + deletedAt + ", createdAt="
				+ createdAt + ", isDeleted=" + isDeleted + "]";
	}

	public int getShop_id() {
		return shop_id;
	}

	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}

	public void setPurchaseOrderId(Integer purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	public String getPurchaseBy() {
		return purchaseBy;
	}

	public void setPurchaseBy(String purchaseBy) {
		this.purchaseBy = purchaseBy;
	}

	public Date getPurchaseAt() {
		return purchaseAt;
	}

	public void setPurchaseAt(Date purchaseAt) {
		this.purchaseAt = purchaseAt;
	}

	public int getPurchaseOrderAmount() {
		return purchaseOrderAmount;
	}

	public void setPurchaseOrderAmount(int purchaseOrderAmount) {
		this.purchaseOrderAmount = purchaseOrderAmount;
	}

	public int getPurchaseOrderOtherAmount() {
		return purchaseOrderOtherAmount;
	}

	public void setPurchaseOrderOtherAmount(int purchaseOrderOtherAmount) {
		this.purchaseOrderOtherAmount = purchaseOrderOtherAmount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public boolean isAddedToShop() {
		return isAddedToShop;
	}

	public void setAddedToShop(boolean isAddedToShop) {
		this.isAddedToShop = isAddedToShop;
	}


}
