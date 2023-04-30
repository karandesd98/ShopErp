package TechHub.ShopErp.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "shops")
public class Shop {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "shop_name")
    private String shopName;

    @Column(name = "shop_address")
    private String shopAddress;
    
    @Column(name = "shop_type")
    private String shopType;

    @Column(name = "about")
    private String about;

}
