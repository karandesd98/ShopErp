 
 Dropzone.autoDiscover = false;
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

if(shopId==0)
return false;
	
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
      <th scope="col">Upload Bill</th>
      <th scope="col">View Bill</th>
      <th scope="col">Action</th>
    </tr>
  </thead><tbody>`;
  
  var bgColorClss="";
  data.forEach(function(purchaseOrder,index){
	  const{purchaseOrderId='',purchaseOrderName='',purchaseOrderTotalAmount='', PurchaseBy='',date='',goodsAmount='',otherAmount='',billUploadPath='',is_added_to_shop=''}=purchaseOrder;
	   
	   if(is_added_to_shop=="true")
	   bgColorClss="table-success";
	   else
	   bgColorClss="";
	   
	   boiler +=`<tr class="${bgColorClss}">
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
                          
                          if(is_added_to_shop=="true")
	                     {
						boiler += `<li><button class="dropdown-item" type="button" onclick="viewProductToPurchaseOrder(${purchaseOrderId},'${is_added_to_shop}')">View Product</button></li>`;
					     }
	                    else{
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
  
   boiler +=` </tbody>
              </table>`;
              
         $('#purchaseTable').html(boiler);
         
         data.forEach(function(purchaseOrder,index){
			  const{purchaseOrderId='',purchaseOrderName='',purchaseOrderTotalAmount='', PurchaseBy='',date='',goodsAmount='',otherAmount=''}=purchaseOrder;
                var buttonId=`uploadBill_${purchaseOrderId}`;
                uploadBill(buttonId,purchaseOrderId);
			 
			 });
  
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
			swal("Good job!", "Your New Purchase Order Created!", "success");
			getAllpurchase();
			$('#PurchaseOrderModel').modal('hide');
			
		},
		error: function(request, error) {
			 alert("Request 1: " + JSON.stringify(request));
		}
});


  // showAllOwners();

	
}


function deletePurchaseOrder(purchaseOrderId)
{
	
	$.ajax({
		url:'deletePurchaseOrder.json',
		type: 'GET',
		data: {
			purchaseOrderId : purchaseOrderId,
		},
		dataType: 'json',
		success: function(data) {
		getAllpurchase();
			swal("Good job!", "Purchase Order Deleted Successfully...!", "success");
		},
		error: function(request, error) {
			 alert("Request 1: " + JSON.stringify(request));
		}
});

}

function addProductToPurchaseOrder(purchaseOrderId)
{
	$('#AddProductTab').removeClass("hiddenClass");
	$('#AddProductTab').tab('show');
	var shopId=	$('#myShop option:selected').val();
	
	var boiler=`
	        <input type="hidden" id="hdpurchaseOrderId" value="${purchaseOrderId}"/>
	        <input type="hidden" id="hdshopId" value="${shopId}"/>
	        
	         `;   
	  $('#hiddenDiv').html(boiler); 
	  
	 var bakcButton=`<button type="button" class="btn btn-secondary btn-sm" id="backBtn" onclick="backOnPurchase()">Back</button>`;       
     $('#AddProductDiveBackButton').html(bakcButton); 
     
     getAllProductMasterToAddProductToPurchaseOrder();
     
}

function backOnPurchase()
{
	$('#AddProductTab').addClass("hiddenClass");
	$('#ViewProductTab').addClass("hiddenClass");
	$('#nav-home-tab').tab('show');
}


function uploadBill(buttonId,purchaseOrderId)
{
	 // Initialize Dropzone
    var myDropzone = new Dropzone(`#${buttonId}`, {
      url: "/upload", 
      acceptedFiles: "image/*", 
      maxFiles: 2, // 
      init: function() {
        this.on("success", function(file, response) {
         swal("Good job!", "Purchase Order Bill Uploaded Successfully...!", "success");
         getAllpurchase();
        });

        this.on("error", function(file, errorMessage, xhr) {
          console.error(errorMessage);
        });
      },
      
       params: {
        purchaseOrderId: purchaseOrderId,
        description: "Image Description",
      },
       dictDefaultMessage: "Upload Bill",
    });
    
    $(`#${buttonId}`).removeClass('dropzone');
}

function getAllProductMasterToAddProductToPurchaseOrder()
{
	var shopId=	$('#myShop option:selected').val();
	var purchaseOrderId=$('#hdpurchaseOrderId').val();
	
	$.ajax({
		url: 'getAllProductTypeOfShop.json',
		type: 'GET',
		data: {
			shopId: shopId,
			purchaseOrderId: purchaseOrderId
		},
		dataType: 'json',
		success: function(data) {
			
		var boiler =`
 <table class="table table-responsive table-bordered border-primary" id="productTypeMaster">
  <thead>
    <tr>
      <th scope="col">Sr. No</th>
      <th scope="col">Product Name</th>
      <th scope="col">Search Key</th>
      <th scope="col">Sold Type</th>
      <th scope="col">Action</th>
    </tr>
  </thead><tbody>`;
    
	var bgColorClss="";
    var buttonName="";
  data.forEach(function(ownerobj,index){
	  const{productTypeMasterId='',productTypeMasterName='',unique_no='',soldType='', purchaseOrderDetailId='' }=ownerobj;
	 
	  if (purchaseOrderDetailId != "0") {
		  bgColorClss = "table-success";
		  buttonName="Edit";
	  }
	  else {
		  bgColorClss = "";
		  buttonName="Save";
	  }
	 
	  boiler +=`<tr class="${bgColorClss}" productTypeMasterId="${productTypeMasterId}"  soldType="${soldType}">
	             <td scope="row">${++index}</td>
	             <td scope="row">${productTypeMasterName}</td>
	             <td scope="row">${unique_no}</td>
	             <td scope="row">${soldType}</td>
	             <td scope="row"><button type="button" productTypeMasterName="${productTypeMasterName}" purchaseOrderDetailId="${purchaseOrderDetailId}"  class="btn btn-info" onclick="addProductAddModel(this)">${buttonName}</button></td>
	             </tr>`;
	             
	  
  });
  
   boiler +=` </tbody>
              </table>`;
              
         $('#AddProductDive').html(boiler);  
		 var table=	$('#productTypeMaster').DataTable();    
			
       
		},
		error: function(request, error) {
			alert("Request 1: " + JSON.stringify(request));
		}
	});
	
}


function addProductAddModel(src)
{
	var productTypeMasterName = $(src).attr('productTypeMasterName');
	var purchaseOrderDetailId = $(src).attr('purchaseOrderDetailId');
	var parentTR = $(src).closest("tr");
    var producttypemasterid=$(parentTR).attr('producttypemasterid');
    var soldType=$(parentTR).attr('soldType');
    
	$('#addProductModel').modal('show');


 
if(purchaseOrderDetailId=="0")
{
	 var boiler=`
	<form  method="post"> 
			
			<table class="table">
               <thead>
               </thead>
              <tbody>
				    <tr>
				      <th scope="row" style="text-align: left;">Total Itom :-</th>
				      <td colspan="3"><input type="number" class="form-control" id="totalItomCountId" style="width: 35%;"></td>
				    </tr>
				    
				     <tr>
				      <th scope="row" style="text-align: left;">Per Itom Price :-</th>
				      <td ><input type="number" onblur="getTotalPurchasePrice()" class="form-control" id="perItomPriceId" style="width:  75%;"></td>
				     
				       <th scope="row" style="text-align: left;width: 26%;">Total Price :-</th>
				      <td ><input type="number" class="form-control" id="totalPriceId" style="width:  75%;"></td>
				    </tr>
				    
				     <tr>
				      <th scope="row" style="text-align: left;">Sold Price :-</th>
				      <td ><input type="number" onblur="getTotalSoldPrice()" class="form-control" id="soldPriceId" style="width:  75%;"></td>
				     
				       <th scope="row" style="text-align: left;width: 26%;">Total Sold Price :-</th>
				      <td ><input type="number" class="form-control" id="totalSoldPriceId" style="width:  75%;"></td>
				    </tr>
				    
				     <tr>
				      <th scope="row" style="text-align: left;width: 21%;">Negotiable Price :-</th>
				      <td ><input type="number" onblur="getTotalNegotiableSoldPrice()" class="form-control" id="NegotiableSoldPriceId" style="width:  75%;"></td>
				     
				       <th scope="row" style="text-align: left;width: 26%;">Total Negotiable Price :-</th>
				      <td ><input type="number" class="form-control" id="totalNigotiablePrice" style="width:  75%;"></td>
				    </tr>
					 
				  </tbody>
				</table>
		
			</form> 
	`;
	
	  var footerButton=`
	    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
		<button type="button" class="btn btn-primary" productTypeMasterName="${productTypeMasterName}" purchaseOrderDetailId="${purchaseOrderDetailId}"  soldType="${soldType}"  onclick="saveProductMasterDetail(this,${producttypemasterid})">Save</button>
	`;
	
	$('#addProductLabel').html(`<h4>${productTypeMasterName}</h4>`);
	$('#addProductBody').html(boiler);
	$('#addProductFooter').html(footerButton);
	
	}
	else
	{
		
		$.ajax({
		url:'getPurchaseOrderDetailToEdit.json',
		type: 'GET',
		data: {
			purchaseOrderDetailId : purchaseOrderDetailId,
		},
		dataType: 'json',
		success: function(data) {
	//	 alert(data);
		 
		 	 var boiler=`
	<form  method="post"> 
			
			<table class="table">
               <thead>
               </thead>
              <tbody>
				    <tr>
				      <th scope="row" style="text-align: left;">Total Itom :-</th>
				      <td colspan="3"><input type="number" class="form-control" id="totalItomCountId" style="width: 35%;"></td>
				    </tr>
				    
				     <tr>
				      <th scope="row" style="text-align: left;">Per Itom Price :-</th>
				      <td ><input type="number" onblur="getTotalPurchasePrice()" class="form-control" id="perItomPriceId" style="width:  75%;"></td>
				     
				       <th scope="row" style="text-align: left;width: 26%;">Total Price :-</th>
				      <td ><input type="number" class="form-control" id="totalPriceId" style="width:  75%;"></td>
				    </tr>
				    
				     <tr>
				      <th scope="row" style="text-align: left;">Sold Price :-</th>
				      <td ><input type="number" onblur="getTotalSoldPrice()" class="form-control" id="soldPriceId" style="width:  75%;"></td>
				     
				       <th scope="row" style="text-align: left;width: 26%;">Total Sold Price :-</th>
				      <td ><input type="number" class="form-control" id="totalSoldPriceId" style="width:  75%;"></td>
				    </tr>
				    
				     <tr>
				      <th scope="row" style="text-align: left;width: 21%;">Negotiable Price :-</th>
				      <td ><input type="number" onblur="getTotalNegotiableSoldPrice()" class="form-control" id="NegotiableSoldPriceId" style="width:  75%;"></td>
				     
				       <th scope="row" style="text-align: left;width: 26%;">Total Negotiable Price :-</th>
				      <td ><input type="number" class="form-control" id="totalNigotiablePrice" style="width:  75%;"></td>
				    </tr>
					 
				  </tbody>
				</table>
		
			</form> 
	`;
	
	  var footerButton=`
	    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
		<button type="button" class="btn btn-primary" productTypeMasterName="${productTypeMasterName}" purchaseOrderDetailId="${purchaseOrderDetailId}"  soldType="${soldType}"  onclick="saveProductMasterDetail(this,${producttypemasterid})">Save</button>
	`;
		 
	$('#addProductLabel').html(`<h4>${productTypeMasterName}</h4>`);
	$('#addProductBody').html(boiler);
	$('#addProductFooter').html(footerButton);
	
	
	$('#totalItomCountId').val(data.totalItom);
	
	$('#perItomPriceId').val(data.perItomPrice);
	$('#totalPriceId').val(data.totalPurchasedPrice);
	
	$('#soldPriceId').val(data.soldPrice);
	$('#totalSoldPriceId').val(data.totalsoldPrice);
	
	$('#NegotiableSoldPriceId').val(data.negotiablePrice);
	$('#totalNigotiablePrice').val(data.totalnegotiablePrice);

		},
		error: function(request, error) {
			 alert("Request 1: " + JSON.stringify(request));
		}
});
		
	}
	
	
}

function getTotalPurchasePrice()
{
var totalItomCount=	$('#totalItomCountId').val();
var perItomPrice =	$('#perItomPriceId').val();
var totaoPurchasePrice=totalItomCount * perItomPrice;
$('#totalPriceId').val(totaoPurchasePrice);
}

function getTotalSoldPrice()
{
	var totalItomCount = $('#totalItomCountId').val();
	var perItomSoldPrice = $('#soldPriceId').val();
	
	var totalSoldPrice = totalItomCount * perItomSoldPrice;
	$('#totalSoldPriceId').val(totalSoldPrice);
}


function getTotalNegotiableSoldPrice()
{
	var totalItomCount = $('#totalItomCountId').val();
	var NegotiableSoldPrice = $('#NegotiableSoldPriceId').val();

	var totalNigotiablePrice = totalItomCount * NegotiableSoldPrice;
	$('#totalNigotiablePrice').val(totalNigotiablePrice);
}


function saveProductMasterDetail(src, producttypemasterid)
{
var purchaseOrderId=$('#hdpurchaseOrderId').val();
var shopId=$('#hdshopId').val();

var productTypeMasterName=$(src).attr('productTypeMasterName');
var purchaseOrderDetailId=$(src).attr('purchaseOrderDetailId');

var soldType=$(src).attr('soldType');

var totalItomCount=$('#totalItomCountId').val();

 var perItomPurchasedPriceId=$('#perItomPriceId').val();
var totalPrice=$('#totalPriceId').val();

var soldPrice=$('#soldPriceId').val();
var totalSoldPrice=$('#totalSoldPriceId').val();

var NegotiableSoldPrice=$('#NegotiableSoldPriceId').val();
var totalNigotiablePrice=$('#totalNigotiablePrice').val();


$.ajax({
		url:'savePurchaseOrderDeail.json',
		type: 'GET',
		data: {
			producttypemasterid: producttypemasterid,
			purchsaeOrderId:purchaseOrderId,
			shopId: shopId,
			totalItomCount: totalItomCount,
			purchaseOrderDetailId:purchaseOrderDetailId,
			
			perItomPurchasedPriceId :perItomPurchasedPriceId,
			totalPrice: totalPrice,
			
			soldPrice: soldPrice,
			totalSoldPrice:totalSoldPrice,
			
			NegotiableSoldPrice: NegotiableSoldPrice,
			totalNigotiablePrice: totalNigotiablePrice,
			
			productTypeMasterName: productTypeMasterName,
			soldType: soldType
		},
		dataType: 'json',
		success: function(data) {
			swal("Good job!", "Purchase Order Detail Saved Successfully...!", "success");
			addProductToPurchaseOrder(purchaseOrderId);	
		},
		error: function(request, error) {
			 alert("Request 1: " + JSON.stringify(request));
		}
});

}




function viewProductToPurchaseOrder(purchaseOrderId,is_added_to_shop)	 
{
	$('#ViewProductTab').removeClass("hiddenClass");
	$('#ViewProductTab').tab('show');
	var shopId=	$('#myShop option:selected').val();
	
	var boiler=`
	        <input type="hidden" id="hdpurchaseOrderId" value="${purchaseOrderId}"/>
	        <input type="hidden" id="hdshopId" value="${shopId}"/>
	        <input type="hidden" id="hd_is_added_to_shop" value="${is_added_to_shop}"/>
	         `;   
	  $('#hiddenDiv').html(boiler); 
	  
	 var bakcButton=`<button type="button" class="btn btn-secondary btn-sm" id="backBtn" onclick="backOnPurchase()">Back</button>`;       
     $('#ViewProductDiveBackButton').html(bakcButton); 
     
     getAllProductMasterToViewProductToPurchaseOrder();
     
}


function getAllProductMasterToViewProductToPurchaseOrder()
{
	var shopId=	$('#myShop option:selected').val();
	var purchaseOrderId=$('#hdpurchaseOrderId').val();
	var is_added_to_shop=$('#hd_is_added_to_shop').val();
	
	$.ajax({
		url: 'getAllProductOfPurchaseOrder.json',
		type: 'GET',
		data: {
			shopId: shopId,
			purchaseOrderId: purchaseOrderId
		},
		dataType: 'json',
		success: function(data) {
			
		var boiler =`
 <table class="table table-responsive table-bordered border-primary" id="purchaseOrderDetail">
  <thead>
    <tr>
      <th scope="col">Sr. No</th>
      <th scope="col">Product Name</th>
      <th scope="col">Search Key</th>
      <th scope="col">Sold Type</th>
      <th scope="col">Quantity</th>
      
      <th scope="col">P. Price</th>
      <th scope="col">Sold Price</th>
      <th scope="col">N. Price</th>
      
      <th scope="col">T.P. Price</th>
      <th scope="col">T. Sold Price</th>
      <th scope="col">T.N. Price</th>
      
    </tr>
  </thead><tbody>`;
  
  
  data.forEach(function(ownerobj,index){
	  const{purchaseOrderDetailId='',productName='',soldType='',itomQuantity='',
	        perItomPurchasedPrice=0,perItomSoldPrice=0,perItomNegotiablePrice=0,
	        totalItomPurchasedPrice=0,totalSoldPrice=0,total_negotiable_price=0  }=ownerobj;
	  boiler +=`<tr purchaseOrderDetailId="${purchaseOrderDetailId}"  soldType="${soldType}">
	             <td scope="row">${++index}</td>
	             <td scope="row">${productName}</td>
	             <td scope="row">-</td>
	             <td scope="row">${soldType}</td>
	             <td scope="row">${itomQuantity}</td>
	             
	             <td scope="row">${perItomPurchasedPrice}</td>
	             <td scope="row">${perItomSoldPrice}</td>
	             <td scope="row">${perItomNegotiablePrice}</td>
	             
	             <td scope="row">${totalItomPurchasedPrice}</td>
	             <td scope="row">${totalSoldPrice}</td>
	             <td scope="row">${total_negotiable_price}</td>
	             </tr>`;
	  
  });
  
   boiler +=` </tbody>
              </table>`;
              
			if (is_added_to_shop != "true") {
				boiler += `<div class="text-center"> 
                     <div class="d-flex justify-content-center">
                       <button class="btn btn-primary" onclick="addToShop(${shopId},${purchaseOrderId})" >Add To Shop</button>
                     </div>
                    </div>
                  `;
			}  
              
         $('#ViewProductDive').html(boiler);  
		 var table=	$('#purchaseOrderDetail').DataTable({
			 "paging": false,
             "bInfo": false 
           });  
			
       
		},
		error: function(request, error) {
			alert("Request 1: " + JSON.stringify(request));
		}
	});
	
}

function addToShop(shopId,purchaseOrderId)
{
	   var confirmation = window.confirm("Are you sure you want to add to shop?");
	if (confirmation) {
		$.ajax({
			url: 'addPurchaseOrderToShop.json',
			type: 'GET',
			data: {
				shopId: shopId,
				purchaseOrderId: purchaseOrderId
			},
			dataType: 'json',
			success: function(data) {
				swal("Good job!", data.msg, "success");
				backOnPurchase();
				getAllpurchase();

			}
		});

	} else {

	}
	
}

