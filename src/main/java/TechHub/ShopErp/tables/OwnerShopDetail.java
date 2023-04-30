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
    private int  ownerShopDetailId;
	
	 @ManyToOne
	 @JoinColumn(name = "USER_ID")
	 private User user;
	 
	 @ManyToOne
	 @JoinColumn(name = "SHOP_ID")
	 private Shop shop;
	 
	 @Column(name = "IS_ACTIVE", nullable = false, columnDefinition = "TINYINT(1)")
	 private boolean isActive;

	public int getOwnerShopDetailId() {
		return ownerShopDetailId;
	}

	public void setOwnerShopDetailId(int ownerShopDetailId) {
		this.ownerShopDetailId = ownerShopDetailId;
	}

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

}
