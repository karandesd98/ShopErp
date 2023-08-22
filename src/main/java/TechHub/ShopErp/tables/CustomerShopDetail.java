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
@Table(name = "CUSTOMER_SHOP_DETAIL")
public class CustomerShopDetail {
	
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CUSTOMER_SHOP_DETAIL_ID")
    private Integer customerShopDetailId;
	
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
	@JoinColumn(name="CUSTOMER_ID")
    private Customer customerObj;
	
	@ManyToOne
	@JoinColumn(name="SHOP_ID")
    private Shop  shopObj;
	
	
	

	public Integer getCustomerShopDetailId() {
		return customerShopDetailId;
	}

	public void setCustomerShopDetailId(Integer customerShopDetailId) {
		this.customerShopDetailId = customerShopDetailId;
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

	public Customer getCustomerObj() {
		return customerObj;
	}

	public void setCustomerObj(Customer customerObj) {
		this.customerObj = customerObj;
	}

	public Shop getShopObj() {
		return shopObj;
	}

	public void setShopObj(Shop shopObj) {
		this.shopObj = shopObj;
	}
	
	

}
