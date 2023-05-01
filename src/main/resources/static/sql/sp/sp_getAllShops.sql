

DELIMITER //
drop procedure if exists sp_getAllShops;
CREATE PROCEDURE sp_getAllShops ()
BEGIN

select 
s.SHOP_ID,
s.ABOUT,
s.SHOP_ADDRESS,
s.SHOP_NAME,
s.SHOP_TYPE,
u.USER_ID,
u.USER_NAME,
u.EMAIL
from shop s
left outer join owner_shop_detail owd on owd.SHOP_ID=s.SHOP_ID    and IS_DELETED is false
left outer join my_user u on u.USER_ID=owd.USER_ID;

END;

call sp_getAllShops();
