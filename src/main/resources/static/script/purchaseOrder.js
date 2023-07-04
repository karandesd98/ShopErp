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


function getAllpurchase()
{
var shopId=	$('#myShop option:selected').val();
	
	$.ajax({
		url:'getAllPurchaseOrderOfShop.json',
		type: 'GET',
		data: {
			shopId : shopId
		},
		dataType: 'json',
		success: function(data) {
			
		var boiler =`<div>
				<button type="button" class="btn btn-dark btn-sm m-2" onclick="addPurchaseOrderModel()">New Purchase</button>
			    </div>`;
			 boiler += `
 <table class="table table-bordered border-primary">
  <thead>
    <tr>
      <th scope="col">Sr. No</th>
      <th scope="col">Purchase Name</th>
      <th scope="col">Purchase By</th>
      <th scope="col">Date</th>
      <th scope="col">Goods Amount</th>
      <th scope="col">Other Amount</th>
      <th scope="col">Total Amount</th>
      <th scope="col">Purchase Bill</th>
      <th scope="col">Action</th>
    </tr>
  </thead><tbody>`;
  
  data.forEach(function(purchaseOrder,index){
	  const{purchaseOrderId='',purchaseOrderName='',purchaseOrderTotalAmount=''}=purchaseOrder;
	   boiler +=`<tr>
	             <td scope="row">${++index}</td>
	             <td scope="row">${purchaseOrderName}</td>
	             <td scope="row">-</td>
	             <td scope="row">-</td>
	               <td scope="row">-</td>
	             <td scope="row">-</td>
	             <td scope="row">${purchaseOrderTotalAmount}</td>
	             <td scope="row">-</td>
	             <td scope="row">
	             <div class="dropdown">
                      <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenu2" data-bs-toggle="dropdown" aria-expanded="false"> Action</button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
                         <li><button class="dropdown-item" type="button">Action</button></li>
                         <li><button class="dropdown-item" type="button">Another action</button></li>
                         <li><button class="dropdown-item" type="button">Something else here</button></li>
                       </ul>
                 </div>
	             </td>
	             </tr>`;
	  
  });
  
   boiler +=` </tbody>
              </table>`;
              
         $('#purchaseTable').html(boiler);
  
		},
		error: function(request, error) {
			 alert("Request 1: " + JSON.stringify(request));
		}
	});
	
}

function addPurchaseOrderModel()
{
	$('#PurchaseOrderModel').modal('show');
}


function saveNewPurchaseOrder()
{
	
	var pName = $("#pName").val();
	var PurchaseBy = $("#PurchaseBy").val();
	var DateOfPurchaseOrder = $("#DateOfPurchaseOrder").val();
	var GoodsAmount = $("#GoodsAmount").val();
	var otherAmount = $("#otherAmount").val();
	var totalAmount = $("#totalAmount").val();
	var notForPurchaseOrder = $("#notForPurchaseOrder").val();
    var shopId=	$('#myShop option:selected').val();
    
	$.ajax({
		url:'saveNewPurchaseOrder.json',
		type: 'GET',
		data: {
			pName: pName,
			PurchaseBy: PurchaseBy,
			DateOfPurchaseOrder: DateOfPurchaseOrder,
			GoodsAmount: GoodsAmount,
			otherAmount: otherAmount,
			totalAmount: totalAmount,
			notForPurchaseOrder: notForPurchaseOrder,
			shopId : shopId
		},
		dataType: 'json',
		success: function(data) {
		//	var boiler=`<h1>${data.msg}</h1>`
			swal("Good job!", "Your New Purchase Order Created!", "success");
		},
		error: function(request, error) {
			 alert("Request 1: " + JSON.stringify(request));
		}
});


  // showAllOwners();

	
}