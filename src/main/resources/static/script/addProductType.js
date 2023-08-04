/*
$(document).ready(function() {
 // showAllProductTypeMaster();
  showAllProductTypeMasterNew();
});
*/

 $(document).ready(function() {
    getAllMyShopToAddPurchaseOrder();
     
  });
  
  function getAllMyShopToAddPurchaseOrder()
  {
	  $.ajax({
		url:'getAllMyShopToAddPurchaseOrder.json',
		type: 'GET',
		data: {
			
		},
		dataType: 'json',
		success: function(data) {
			
			var boiler=`<option value="0">select</option>`;
			data.forEach(function(val,index){
				const{shopId,shopName}=val;
				
				boiler +=`<option value="${shopId}">${shopName}</option>`;
			});
			
			$('#myShop').html(boiler);
  
         }
  });
  
}



var level=0;

function showAllProductTypeMaster()
{
	$.ajax({
		url:'getAllProductTypeMasterParent.json',
		type: 'GET',
		data: {
			
		},
		dataType: 'json',
		success: function(data) {
			var boiler = `
 <table class="table table-responsive table-bordered border-primary" id="productTypeMaster">
  <thead>
    <tr>
      <th scope="col"></th>
      <th scope="col">Sr. No</th>
      <th scope="col">Type Name</th>
      <th scope="col">Priority</th>
       <th scope="col">Action</th>
    </tr>
  </thead><tbody>`;
  
  data.forEach(function(ownerobj,index){
	  const{productTypeMasterId='',productTypeMasterName='',unique_no=''}=ownerobj;
	  boiler +=`<tr productTypeMasterId="${productTypeMasterId}">
	             <td scope="row"><span class="material-icons">add_circle_outline</span></td>
	             <td scope="row">${++index}</td>
	             <td scope="row">${productTypeMasterName}</td>
	             <td scope="row">${unique_no}</td>
	             <td scope="row">
	            action
	             </td>
	             </tr>`;
	  
  });
  
   boiler +=` </tbody>
              </table>`;
              
         $('#productTypeMasterTable').html(boiler);  
		 var table=	$('#productTypeMaster').DataTable();    
     

            var previousExpandedRow = null;
			$('#productTypeMaster tbody tr').on('click', 'span', function() {
				level=0;
				var row = table.row(this.parentNode.parentNode);
		        var	producttypemasterid=	$(this.parentNode.parentNode).attr('producttypemasterid');
				
				if (row.child.isShown()) {
					row.child.hide();
					$(this).text('add_circle_outline');
				} else {
					
					if (previousExpandedRow && previousExpandedRow.child.isShown()) {
						previousExpandedRow.child.hide();
						previousExpandedRow.node().querySelector('td').innerHTML='<span class="material-icons">add_circle_outline</span>' ; // Update the icon to "+"
					}
					
	                 getSubTypeMasters(row,producttypemasterid,++level);
					// row.child(boiler).show();
					
					$(this).text('remove_circle_outline');
					previousExpandedRow = row;
				}
			});
			
			
  
		},
		error: function(request, error) {
			 alert("Request 1: " + JSON.stringify(request));
		}
	});
}


function addProductTypeMasterModel()
{
	$('#addProductTypeMaster').modal('show');
}

function saveNewProductTypeMaster()
{
	var ProductMasterName=$("#ProductMasterName").val();
	var soldType=$("#soldType option:selected").val();
    var uniqueNo=$("#uniqueNo").val();
	var shopId = $('#myShop option:selected').val();

	if (shopId == 0)
		return false;
    
    $.ajax({
		url:'saveNewProductTypeMaster.json',  
		type: 'GET',
		data: {
			ProductMasterName: ProductMasterName,
			uniqueNo: uniqueNo,
			soldType:soldType,
			shopId:shopId
		},
		dataType: 'json',
		success: function(data) {
			swal("Good job!", "Your New Owner Created!", "success");
		},
		error: function(request, error) {
			 alert("Request 1: " + JSON.stringify(request));
		}
});
}


function getSubTypeMasters(row,producttypemasterid,level)
{
	var boiler=`<div style="text-align: left;"><button type="button" class="btn btn-primary btn-sm" onclick="addSubProductTypeMasterModel('${producttypemasterid}')">Add</button></div>`;
	boiler += `<table class="table table-responsive table-bordered border-primary" id="productTypeSubMaster_${level}">
  <thead>
    <tr>
        <th scope="col"></th>
      <th scope="col">Sr. No</th>
      <th scope="col">Type Name</th>
      <th scope="col">Priority</th>
       <th scope="col">Action</th>
    </tr>
  </thead><tbody>`;
	
	$.ajax({
		url: 'getAllSubProductTypeMaster.json',
		type: 'GET',
		data: {
			producttypemasterid: producttypemasterid	
		},
		dataType: 'json',
		success: function(data) {
			
			data.forEach(function(ownerobj, index) {
				const { productTypeMasterId = '', productTypeMasterName = '', unique_no = '' } = ownerobj;
				boiler += `<tr productTypeMasterId="${productTypeMasterId}">
	             <td scope="row"><span class="material-icons">add_circle_outline</span></td>
	             <td scope="row">${++index}</td>
	             <td scope="row">${productTypeMasterName}</td>
	             <td scope="row">${unique_no}</td>
	             <td scope="row">
	            action
	             </td>
	             </tr>`;

			});
  
   boiler +=`</tbody>
              </table>`;
 
    row.child(boiler).show();
    var tableName='#productTypeSubMaster_'+level;
    var table=	$(tableName).DataTable({
   "searching": false, // Disable search/filtering
   "paging": false // Disable pagination
     }); 
    
           var previousExpandedRow = null;
			$(tableName+' tbody tr').on('click', 'span', function() {
				var row = table.row(this.parentNode.parentNode);
				var producttypemasterid = $(this.parentNode.parentNode).attr('producttypemasterid');

				if (row.child.isShown()) {
					row.child.hide();
					$(this).text('add_circle_outline');
				} else {

					if (previousExpandedRow && previousExpandedRow.child.isShown()) {
						previousExpandedRow.child.hide();
						previousExpandedRow.node().querySelector('td').innerHTML = '<span class="material-icons">add_circle_outline</span>'; // Update the icon to "+"
					}

					getSubTypeMasters(row, producttypemasterid,++level);

					$(this).text('remove_circle_outline');
					previousExpandedRow = row;
				}
			});
    
		},
		error: function(request, error) {
			alert("Request 1: " + JSON.stringify(request));
		}
	});
	
	
}

function addSubProductTypeMasterModel(addSubProductTypeMaster)
{
	$('#subProductTypeForm').data('addSubProductTypeMaster',addSubProductTypeMaster);
	$('#addSubProductTypeMaster').modal('show');
}

function saveSubProductTypeMaster()
{
	var productTypeMasterId = $('#subProductTypeForm').data('addSubProductTypeMaster');
	var SubProductName = $("#SubProductName").val();
	var subUniqueNo = $("#subUniqueNo").val();
 
	$.ajax({
		url: 'saveSubProductTypeMaster.json',
		type: 'GET',
		data: {
			productTypeMasterId: productTypeMasterId,
			SubProductName: SubProductName,
			subUniqueNo:subUniqueNo
		},
		dataType: 'json',
		success: function(data) {
			swal("Good job!", "Your New Owner Created!", "success");
			$('#addSubProductTypeMaster').modal('hide');
		},
		error: function(request, error) {
			alert("Request 1: " + JSON.stringify(request));
		}
	});

}


function showAllProductTypeMasterNew()
{
	
	var shopId = $('#myShop option:selected').val();

	if (shopId == 0)
		return false;
		
	$.ajax({
		url:'getAllProductTypeOfShop.json',
		type: 'GET',
		data: {
			shopId:shopId
		},
		dataType: 'json',
		success: function(data) {
			
		var boiler=	`<div>
				       <button type="button" class="btn btn-dark btn-sm m-2" onclick="addProductTypeMasterModel()">Add Product Type Master</button>
			          </div>`;
			
			 boiler += `
 <table class="table table-responsive table-bordered border-primary" id="productTypeMaster">
  <thead>
    <tr>
      <th scope="col"></th>
      <th scope="col">Sr. No</th>
      <th scope="col">Product Name</th>
      <th scope="col">Search Key</th>
      <th scope="col">Sold Type</th>
       <th scope="col">Action</th>
    </tr>
  </thead><tbody>`;
  
  data.forEach(function(ownerobj,index){
	  const{productTypeMasterId='',productTypeMasterName='',unique_no='',soldType=''}=ownerobj;
	  boiler +=`<tr productTypeMasterId="${productTypeMasterId}">
	             <td scope="row"><span class="material-icons">add_circle_outline</span></td>
	             <td scope="row">${++index}</td>
	             <td scope="row">${productTypeMasterName}</td>
	             <td scope="row">${unique_no}</td>
	             <td scope="row">${soldType}</td>
	             <td scope="row">
	            action
	             </td>
	             </tr>`;
	  
  });
  
   boiler +=` </tbody>
              </table>`;
              
         $('#productTypeMasterTable').html(boiler);  
		 var table=	$('#productTypeMaster').DataTable();    
   
		},
		error: function(request, error) {
			 alert("Request 1: " + JSON.stringify(request));
		}
	});
}