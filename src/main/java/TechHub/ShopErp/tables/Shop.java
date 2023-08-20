package TechHub.ShopErp.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SHOP")
public class Shop {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="SHOP_ID")
    private Integer  shop_id;

    @Column(name = "SHOP_NAME")
    private String shopName;

    @Column(name = "SHOP_ADDRESS")
    private String shopAddress;
    
    @Column(name = "SHOP_TYPE")
    private String shopType;

    @Column(name = "ABOUT")
    private String about;

	

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}

	public String getShopType() {
		return shopType;
	}

	public void setShopType(String shopType) {
		this.shopType = shopType;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public Integer getShop_id() {
		return shop_id;
	}

	public void setShop_id(Integer shop_id) {
		this.shop_id = shop_id;
	}

}
