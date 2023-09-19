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

function getMyOverAllShop()
{
	var shopId = $('#myShop option:selected').val();

	if (shopId == 0)
		return false;

	$.ajax({
		url: 'getOverAllShop.json',
		type: 'GET',
		data: {
			shopId: shopId
		},
		dataType: 'json',
		success: function(data) {

			var boiler = `<div>
				<button type="button" class="btn btn-dark btn-sm m-2">New Purchase</button>
			    </div>`;
			boiler += `
 <table class="table table-bordered border-primary">
  <thead>
    <tr>
      <th scope="col">Sr. No</th>
      <th scope="col">ProductName Name</th>
      <th scope="col">Sold Type</th>
      <th scope="col">Total Quantity</th>
      <th scope="col">Unique No</th>
      <th scope="col">Purchased Price</th>
      <th scope="col">Sold Price</th>
      <th scope="col">Action</th>
    </tr>
  </thead><tbody>`;
  


			data.forEach(function(itom, index) {
		const { shopId1 = '', shopName = '', overAllShopId = '', purchaseOrderName = '', activePerChaseOrderId = '',
				 perItomPurchasePrice = '', perItomSoldPrice = '', productName = '', soldType = '',
				  totalItomQuantity='',uniqueNo=''  } = itom;

				boiler += `<tr>
	             <td scope="row">${++index}</td>
	             <td scope="row">${productName}</td>
	             <td scope="row">${soldType}</td>
	             <td scope="row">${totalItomQuantity}</td>
	               <td scope="row">${uniqueNo}</td>
	             <td scope="row">${perItomPurchasePrice}</td>
	             <td scope="row">${perItomSoldPrice}</td>
	             
                
                
	             <td scope="row">
	             <div class="dropdown">
                      <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenu2" data-bs-toggle="dropdown" aria-expanded="false"> Action</button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
                         <li><button class="dropdown-item" type="button">Edit</button></li>`;
				boiler += `</ul>
                 </div>
	             </td>
	             </tr>`;

			});

			boiler += ` </tbody>
              </table>`;

			$('#overAllShop').html(boiler);
		},
		error: function(request, error) {
			alert("Request 1: " + JSON.stringify(request));
		}
	});

}


