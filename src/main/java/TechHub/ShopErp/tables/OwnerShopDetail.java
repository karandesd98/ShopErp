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
@Table(name = "OWNER_SHOP_DETAIL")
public class OwnerShopDetail {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="OWNER_SHOP_DETAIL_ID")
    private Integer  ownerShopDetailId;
	
	 @ManyToOne
	 @JoinColumn(name = "USER_ID")
	 private User user;
	 
	 @ManyToOne
	 @JoinColumn(name = "SHOP_ID")
	 private Shop shop;
	 
	 @Column(name = "IS_ACTIVE", nullable = false, columnDefinition = "TINYINT(1)")
	 private boolean isActive;
	 
	 @Column(name = "IS_DELETED", nullable = false, columnDefinition = "TINYINT(1)")
	 private boolean isDeleted;
	 



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getOwnerShopDetailId() {
		return ownerShopDetailId;
	}

	public void setOwnerShopDetailId(Integer ownerShopDetailId) {
		this.ownerShopDetailId = ownerShopDetailId;
	}

}
