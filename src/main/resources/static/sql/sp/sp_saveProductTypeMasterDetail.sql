DELIMITER //
drop procedure if exists sp_saveProductTypeMasterDetail;
CREATE PROCEDURE sp_saveProductTypeMasterDetail(
In productTypeMasterData LONGTEXT 
)
BEGIN

DECLARE k INT UNSIGNED DEFAULT 0;
DECLARE row_count1 INT UNSIGNED; 
DECLARE xpath1 TEXT;
DECLARE xpath2 TEXT;
DECLARE xpath3 TEXT;
DECLARE xpath4 TEXT;
DECLARE xpath5 TEXT;
DECLARE xpath6 TEXT;
DECLARE xpath7 TEXT;
DECLARE xpath8 TEXT;
DECLARE xpath9 TEXT;
DECLARE xpath10 TEXT;
DECLARE xpath11 TEXT;
DECLARE xpath12 TEXT;
DECLARE xpath13 TEXT;

DROP TEMPORARY TABLE IF EXISTS tempPurchaseOrderDetail;
CREATE TEMPORARY TABLE tempPurchaseOrderDetail(product_type_master_id int,  purchase_order_id int, shop_id int ,
 itom_quantity DOUBLE , per_itom_purchased_price DOUBLE, total_itom_purchased_price DOUBLE, per_itom_sold_price DOUBLE, total_sold_price DOUBLE, per_itom_negotiable_price DOUBLE, total_negotiable_price DOUBLE,        
 product_name VARCHAR(100), sold_type VARCHAR(50), purchase_order_detail_id int);

 --   select * from tempPurchaseOrderDetail;

SET row_count1 := extractValue(productTypeMasterData,'count(/productMaters/productMater)');

WHILE k < row_count1 DO        
    SET k := k + 1;
SET xpath1 := concat('/productMaters/productMater/producttypemasterid[', k, ']');
SET xpath2 := concat('/productMaters/productMater/purchaseOrderId[', k, ']');
SET xpath3 := concat('/productMaters/productMater/shopId[', k, ']');
SET xpath4 := concat('/productMaters/productMater/totalItomCount[', k, ']');

SET xpath5 := concat('/productMaters/productMater/perItomPurchasedPrice[', k, ']');
SET xpath6 := concat('/productMaters/productMater/totalPrice[', k, ']');

SET xpath7 := concat('/productMaters/productMater/soldPrice[', k, ']');
SET xpath8 := concat('/productMaters/productMater/totalSoldPrice[', k, ']');

SET xpath9 := concat('/productMaters/productMater/NegotiableSoldPrice[', k, ']');
SET xpath10 := concat('/productMaters/productMater/totalNigotiablePrice[', k, ']');

SET xpath11 := concat('/productMaters/productMater/productTypeMasterName[', k, ']');
SET xpath12 := concat('/productMaters/productMater/soldType[', k, ']');
SET xpath13 := concat('/productMaters/productMater/purchaseOrderDetailId[', k, ']');

INSERT INTO  tempPurchaseOrderDetail(product_type_master_id ,  purchase_order_id , shop_id  ,
 itom_quantity  , per_itom_purchased_price , total_itom_purchased_price , per_itom_sold_price , total_sold_price , per_itom_negotiable_price , total_negotiable_price ,        
 product_name , sold_type,purchase_order_detail_id ) VALUES (
		    ExtractValue(productTypeMasterData, xpath1),
            ExtractValue(productTypeMasterData, xpath2),
			ExtractValue(productTypeMasterData, xpath3),
            
            ExtractValue(productTypeMasterData, xpath4) ,
			
			ExtractValue(productTypeMasterData, xpath5),
			ExtractValue(productTypeMasterData, xpath6),
            
            ExtractValue(productTypeMasterData, xpath7),
			ExtractValue(productTypeMasterData, xpath8),
            
            ExtractValue(productTypeMasterData, xpath9),
			ExtractValue(productTypeMasterData, xpath10),
            
            ExtractValue(productTypeMasterData, xpath11),
            ExtractValue(productTypeMasterData, xpath12),
            
              ExtractValue(productTypeMasterData, xpath13)
	);
    
END WHILE;

 select * from tempPurchaseOrderDetail;



INSERT INTO purchase_order_detail(
product_type_master_id,
purchase_order_id,
shop_id,
itom_quantity,
per_itom_purchased_price,
total_itom_purchased_price,
per_itom_sold_price,
total_sold_price,
per_itom_negotiable_price,
total_negotiable_price,
product_name,
sold_type
)SELECT 
product_type_master_id,
purchase_order_id,
shop_id,
itom_quantity,
per_itom_purchased_price,
total_itom_purchased_price,
per_itom_sold_price,
total_sold_price,
per_itom_negotiable_price,
total_negotiable_price,
product_name,
sold_type FROM tempPurchaseOrderDetail where purchase_order_detail_id =0;

 
 
 
 update
purchase_order_detail pod
inner join tempPurchaseOrderDetail temp on temp.purchase_order_detail_id=pod.purchase_order_detail_id
set
pod.itom_quantity=temp.itom_quantity,
pod.per_itom_purchased_price=temp.per_itom_purchased_price,
pod.total_itom_purchased_price=temp.total_itom_purchased_price,
pod.per_itom_sold_price=temp.per_itom_sold_price,
pod.total_sold_price=temp.total_sold_price,
pod.per_itom_negotiable_price=temp.per_itom_negotiable_price,
pod.total_negotiable_price=temp.total_negotiable_price
;
 


END;

 --    call sp_saveProductTypeMasterDetail('<productMaters><productMater><producttypemasterid>1</producttypemasterid><purchaseOrderId>6</purchaseOrderId><shopId>1</shopId><totalItomCount>5.0</totalItomCount><perItomPurchasedPrice>500.0</perItomPurchasedPrice><totalPrice>2500.0</totalPrice><soldPrice>600.0</soldPrice><totalSoldPrice>3000.0</totalSoldPrice><NegotiableSoldPrice>550.0</NegotiableSoldPrice><totalNigotiablePrice>2750.0</totalNigotiablePrice><productTypeMasterName>khat 15-15</productTypeMasterName><soldType>PER_ITOM</soldType><purchaseOrderDetailId>0</purchaseOrderDetailId></productMater></productMaters>');