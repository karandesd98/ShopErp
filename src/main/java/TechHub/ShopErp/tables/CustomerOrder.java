package TechHub.ShopErp.tables;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "CUSTOMER_ORDER")
public class CustomerOrder {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CUSTOMER_ORDER_ID")
    private Integer customerOrderId;
	
	@Column(name = "ORDER_NAME")
	private String orderName;
	
	@Column(name = "TOTAL_ORDER_AMOUNT")
	private Double totalOrderAmount;
	
	@Column(name = "TOTAL_PAID_AMOUNT")
	private Double totalPaidAmount;
	
	@Column(name = "TOTAL_PENDING_TO_PAY_AMOUNT")
	private Double totalPendingToPayAmount;
	
	@Column(name = "PROMISSED_DATE")
	private LocalDateTime promissedDate;
	
	@Column(name = "REASON_OF_NOT_PAID")
	private String reasonOfNotPaid;
	
	
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
	@JoinColumn(name = "CUSTOMER_ID")
	private Customer customerObj;
	
	@ManyToOne
	@JoinColumn(name="SHOP_ID")
    private Shop  shopObj;
	
	// one to many relationsheep
	@OneToMany(mappedBy = "customerOrderObj", cascade = CascadeType.ALL)
	private List<CustomerOrderDetail> customerOrderDetails;
	
	
	public Integer getCustomerOrderId() {
		return customerOrderId;
	}

	public void setCustomerOrderId(Integer customerOrderId) {
		this.customerOrderId = customerOrderId;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public Double getTotalOrderAmount() {
		return totalOrderAmount;
	}

	public void setTotalOrderAmount(Double totalOrderAmount) {
		this.totalOrderAmount = totalOrderAmount;
	}

	public Double getTotalPaidAmount() {
		return totalPaidAmount;
	}

	public void setTotalPaidAmount(Double totalPaidAmount) {
		this.totalPaidAmount = totalPaidAmount;
	}

	public Double getTotalPendingToPayAmount() {
		return totalPendingToPayAmount;
	}

	public void setTotalPendingToPayAmount(Double totalPendingToPayAmount) {
		this.totalPendingToPayAmount = totalPendingToPayAmount;
	}

	public LocalDateTime getPromissedDate() {
		return promissedDate;
	}

	public void setPromissedDate(LocalDateTime promissedDate) {
		this.promissedDate = promissedDate;
	}

	public String getReasonOfNotPaid() {
		return reasonOfNotPaid;
	}

	public void setReasonOfNotPaid(String reasonOfNotPaid) {
		this.reasonOfNotPaid = reasonOfNotPaid;
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
	

}
