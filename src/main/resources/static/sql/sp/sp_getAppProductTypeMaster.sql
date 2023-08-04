DELIMITER //
drop procedure if exists sp_getAppProductTypeMaster;
CREATE PROCEDURE sp_getAppProductTypeMaster ()
BEGIN

select 
ptm.product_type_master_id,
ptm.product_type_master_name,
ptm.product_type_master_parent_id,
ptm.unique_no

from product_type_master ptm;

END;

-- call sp_getAppProductTypeMaster();