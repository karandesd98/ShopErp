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
@Table(name = "SHOP_CUSTOMER")
public class ShopCustomer {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="SHOP_CUSTOMER_ID")
    private Integer shopCustomerId;
	
	@Column(name = "CUSTOMER_NAME")
	private String customerName;

	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "MOBILE_NO")
	private String mobileNo;
	
	@Column(name = "ADDRESS")
	private String address;
	
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
	@JoinColumn(name ="SHOP_ID")
	private Shop shopObj;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User userObj;
	
	
	// setters and getter
	public Integer getShopCustomerId() {
		return shopCustomerId;
	}

	public void setShopCustomerId(Integer shopCustomerId) {
		this.shopCustomerId = shopCustomerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public User getUserObj() {
		return userObj;
	}

	public void setUserObj(User userObj) {
		this.userObj = userObj;
	}
	
}
