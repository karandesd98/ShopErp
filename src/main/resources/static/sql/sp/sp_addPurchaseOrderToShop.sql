DELIMITER //
drop procedure if exists sp_addPurchaseOrderToShop;
CREATE PROCEDURE sp_addPurchaseOrderToShop (
IN shopId int,
IN purchaseOrderId int
)
BEGIN
drop temporary table if exists dataToInsertOrUpdate;
create temporary table dataToInsertOrUpdate as(
select 
s.shop_id,
s.shop_name,
po.purchase_order_id,
pod.product_name,
pod.itom_quantity,
pod.per_itom_purchased_price,
pod.per_itom_sold_price,
pod.sold_type,
os.overall_shop_id

from shop s
inner join purchase_order po on po.shop_id=s.shop_id
and s.shop_id=shopId and po.purchase_order_id=purchaseOrderId  
and po.is_deleted is not true
and po.is_added_to_shop is not true
inner join purchase_order_detail pod on pod.shop_id=s.shop_id and pod.purchase_order_id=po.purchase_order_id
left outer join overall_shop os on os.product_name=pod.product_name and os.shop_id=s.shop_id 
);

select * from dataToInsertOrUpdate;

insert  into overall_shop (shop_id,active_purchase_order_detail_id, product_name, total_itom_quantity, per_itom_purchased_price, per_itom_sold_price, sold_type) 
select 
dti.shop_id,
purchase_order_id,
product_name,
itom_quantity,
per_itom_purchased_price,
per_itom_sold_price,
sold_type
from dataToInsertOrUpdate dti
 where dti.overall_shop_id is null; 
 
 
 update 
 overall_shop os
 inner join dataToInsertOrUpdate dti on dti.overall_shop_id=os.overall_shop_id
 set
 os.per_itom_purchased_price=dti.per_itom_purchased_price,
 os.per_itom_sold_price=dti.per_itom_sold_price,
 os.total_itom_quantity= (os.total_itom_quantity + dti.itom_quantity ),
 os.active_purchase_order_detail_id=dti.purchase_order_id
 ; 
 
UPDATE purchase_order po
SET po.is_added_to_shop= 1
WHERE po.purchase_order_id = purchaseOrderId;

END;

 -- call sp_addPurchaseOrderToShop(1,2);