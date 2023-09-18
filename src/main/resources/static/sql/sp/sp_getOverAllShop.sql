DELIMITER //
drop procedure if exists sp_getOverAllShop;
CREATE PROCEDURE sp_getOverAllShop (
IN shopId int
)
BEGIN

drop temporary table if exists overAllShopInfo;
create temporary table overAllShopInfo as(
select
s.shop_id,
s.shop_name,
os.overall_shop_id,
os.active_purchase_order_id,
os.per_itom_purchased_price,
os.per_itom_sold_price,
os.product_name,
os.sold_type,
os.total_itom_quantity,
os.unique_no,
po.purchase_order_name
from shop s
inner join overall_shop os on os.shop_id=s.shop_id and os.is_deleted is not true 
and s.shop_id=shopId
left outer join purchase_order po on po.purchase_order_id=os.active_purchase_order_id
);

select * from overAllShopInfo;


END;

-- call sp_getOverAllShop(1);