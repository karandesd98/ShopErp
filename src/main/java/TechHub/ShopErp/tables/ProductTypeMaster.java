package TechHub.ShopErp.tables;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PRODUCT_TYPE_MASTER")
public class ProductTypeMaster {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PRODUCT_TYPE_MASTER_ID")
    private int  productTypeMasterId;
	
	@Column(name = "PRODUCT_TYPE_MASTER_NAME")
	private String productTypeMasterName;
	
	@Column(name="UNIQUE_NO")
    private int  unique_no;
	
	@Column(name="PRODUCT_TYPE_MASTER_PARENT_ID")
    private int  productTypeMasterParentId;
	
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

	@Column(name = "IS_DELETED", nullable = false, columnDefinition = "boolean default false") //columnDefinition = "TINYINT(1)"
	private boolean isDeleted;

	public int getProductTypeMasterId() {
		return productTypeMasterId;
	}

	public void setProductTypeMasterId(int productTypeMasterId) {
		this.productTypeMasterId = productTypeMasterId;
	}

	public String getProductTypeMasterName() {
		return productTypeMasterName;
	}

	public void setProductTypeMasterName(String productTypeMasterName) {
		this.productTypeMasterName = productTypeMasterName;
	}

	public int getUnique_no() {
		return unique_no;
	}

	public void setUnique_no(int unique_no) {
		this.unique_no = unique_no;
	}

	public int getProductTypeMasterParentId() {
		return productTypeMasterParentId;
	}

	public void setProductTypeMasterParentId(int productTypeMasterParentId) {
		this.productTypeMasterParentId = productTypeMasterParentId;
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


	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
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
	
	

}
