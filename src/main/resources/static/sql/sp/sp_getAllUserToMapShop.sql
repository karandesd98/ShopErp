DELIMITER //
drop procedure if exists sp_getAllUserToMapShop;
CREATE PROCEDURE sp_getAllUserToMapShop (IN shopId int)
BEGIN

select 
u.USER_ID,
u.USER_NAME,
u.EMAIL,
if(osd.OWNER_SHOP_DETAIL_ID is null ,'not mapped','mapped') as mappedStatus

from my_user u
left outer join owner_shop_detail osd on osd.USER_ID=u.USER_ID
and osd.SHOP_ID=shopId;  

END;

call sp_getAllUserToMapShop(1);