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
@Table(name = "CUSTOMER_ORDER_DETAIL")
public class CustomerOrderDetail {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CUSTOMER_ORDER_DETAIL_ID")
    private Integer customerOrderDetailId;
	
	@Column(name = "PRODUCT_NAME")
	private String productName;
	
	@Column(name = "PRODUCT_PRICE")
	private Double productPrice;
	
	@Column(name = "SOLD_PRICE")
	private Double soldPrice;
	
	@Column(name = "QUANTITY")
	private Integer quantity;
	
	@Column(name = "SOLT_TYPE")
	private String soldType;
	
	@Column(name = "TOTAL_PRODUCT_PRICE")
	private Double totalProductPrice;
	
	// many to one relationsheep
	@ManyToOne
	@JoinColumn(name = "CUSTOMER_ORDER_ID")
	private CustomerOrder customerOrderObj;

}
