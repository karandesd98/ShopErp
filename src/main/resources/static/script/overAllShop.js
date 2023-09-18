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
      <th scope="col">Upload Bill</th>
      <th scope="col">View Bill</th>
      <th scope="col">Action</th>
    </tr>
  </thead><tbody>`;

			var bgColorClss = "";
			data.forEach(function(purchaseOrder, index) {
				const { purchaseOrderId = '', purchaseOrderName = '', purchaseOrderTotalAmount = '', PurchaseBy = '', date = '', goodsAmount = '', otherAmount = '', billUploadPath = '', is_added_to_shop = '' } = purchaseOrder;

				if (is_added_to_shop == "true")
					bgColorClss = "table-success";
				else
					bgColorClss = "";

				boiler += `<tr class="${bgColorClss}">
	             <td scope="row">${++index}</td>
	             <td scope="row">${purchaseOrderName}</td>
	             <td scope="row">${PurchaseBy}</td>
	             <td scope="row">${date}</td>
	               <td scope="row">${goodsAmount}</td>
	             <td scope="row">${otherAmount}</td>
	             <td scope="row">${purchaseOrderTotalAmount}</td>
	             <td scope="row">
	             <div id="uploadBill_${purchaseOrderId}" class="dropzone"></div>
                 </td>
                <td>
                
                <div class="my-gallery">
                  <a href="/uploadedFiles/${billUploadPath}" data-lightbox="gallery">
                  <img class="myProfile" src="/uploadedFiles/${billUploadPath}" alt="Image 1">
                 </a>
                </div>
                
                </td>
                
	             <td scope="row">
	             <div class="dropdown">
                      <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenu2" data-bs-toggle="dropdown" aria-expanded="false"> Action</button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
                         <li><button class="dropdown-item" type="button">Edit</button></li>`;

				if (is_added_to_shop == "true") {
					boiler += `<li><button class="dropdown-item" type="button" onclick="viewProductToPurchaseOrder(${purchaseOrderId},'${is_added_to_shop}')">View Product</button></li>`;
				}
				else {
					boiler += `<li><button class="dropdown-item" type="button" onclick="deletePurchaseOrder(${purchaseOrderId})">Delete</button></li>
                         <li><button class="dropdown-item" type="button" onclick="addProductToPurchaseOrder(${purchaseOrderId})">Add Product</button></li>
                         <li><button class="dropdown-item" type="button" onclick="viewProductToPurchaseOrder(${purchaseOrderId},'${is_added_to_shop}')">View Product</button></li>`;
				}
				boiler += `</ul>
                 </div>
	             </td>
	             </tr>`;

				// <img class="myProfile"  src="/uploadedFiles/${billUploadPath}" />

			});

			boiler += ` </tbody>
              </table>`;

			$('#purchaseTable').html(boiler);

			data.forEach(function(purchaseOrder, index) {
				const { purchaseOrderId = '', purchaseOrderName = '', purchaseOrderTotalAmount = '', PurchaseBy = '', date = '', goodsAmount = '', otherAmount = '' } = purchaseOrder;
				var buttonId = `uploadBill_${purchaseOrderId}`;
				uploadBill(buttonId, purchaseOrderId);

			});

		},
		error: function(request, error) {
			alert("Request 1: " + JSON.stringify(request));
		}
	});

}


