$(document).ready(function() {
	 itomCount=0;
	getAllMyShopToAddPurchaseOrder();
});

function getAllMyShopToAddPurchaseOrder() {
	$.ajax({
		url: 'getAllMyShopToAddPurchaseOrder.json',
		type: 'GET',
		data: {

		},
		dataType: 'json',
		success: function(data) {

			var boiler = `<option value="0">select</option>`;
			data.forEach(function(val, index) {
				const { shopId, shopName } = val;

				boiler += `<option value="${shopId}"  onclick="getAllCustomersOfShop()">${shopName}</option>`;
			});

			$('#myShop').html(boiler);

		}
	});
}

function getAllCustomersOfShop() {
	var shopId = $('#myShop option:selected').val();
	if (shopId == 0) {
		$('#addCustomerButn').html('');
		return false;
	}

	var butnBoiler = `<button type="button"  class="btn btn-dark btn-sm m-2" onclick="addCustomerModel()">Add Customer</button>`;
	$('#addCustomerButn').html(butnBoiler);

	$.ajax({
		url: 'getAllCustomer.json',
		type: 'GET',
		data: {
			shopId: shopId
		},
		dataType: 'json',
		success: function(data) {

			console.log(data);

			var boiler = `
 <table class="table table-responsive table-bordered border-primary" id="customerTable">
  <thead>
    <tr>
      <th scope="col">Sr. No</th>
      <th scope="col">User Name</th>
      <th scope="col">Role</th>
      <th scope="col">Image</th>
      <th scope="col">Address</th>
      <th scope="col">Mobile No.</th>
      <th scope="col">Email</th>
      <th scope="col">Action</th>
    </tr>
  </thead><tbody>`;

			data.forEach(function(purchaseOrder, index) {
				const { Name = '', image = '', role = '', mobileNo = '', email = '', address = '',customerId='',customerShpDetailId='0' } = purchaseOrder;
				boiler += `<tr>
	             <td scope="row">${++index}</td>
	             <td scope="row">${Name}</td>
	             <td scope="row">${role}</td>
	             <td scope="row">${image}</td>
	             <td scope="row">${address}</td>
	             <td scope="row">${mobileNo}</td>
	              <td scope="row">${email}</td>
	            
	            
	             <td scope="row">
	             <div class="dropdown">
                      <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenu2" data-bs-toggle="dropdown" aria-expanded="false"> Action</button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
                         <li><button class="dropdown-item" type="button">Edit</button></li>
                         <li><button class="dropdown-item" type="button" onclick="deleteCustomer('${email}')">Delete</button></li>
                          <li><button class="dropdown-item" type="button" onclick="addOrder(${customerShpDetailId},'${Name}')">Add Order</button></li>
                         <li><button class="dropdown-item" type="button" onclick="bTab()">B Tab</button></li>
                        
                  </ul>
                 </div>
	             </td>
	             </tr>`;
			});
			boiler += ` </tbody>
              </table>`;
			$('#showAllUsersOfThisShop').html(boiler);
			$('#customerTable').DataTable();
		},
		error: function(request, error) {
			alert("Request 1: " + JSON.stringify(request));
		}
	});

}

function getMeDateAndTime() {
	const currentDate = new Date();
	const dayOfWeek = currentDate.getDay();
	const dayNames = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
	const dayName = dayNames[dayOfWeek];
	const dayOfMonth = currentDate.getDate();
	const month = currentDate.getMonth();
	const year = currentDate.getFullYear();
	const formattedDate = `${dayName}, ${month + 1}/${dayOfMonth}/${year}`;
	console.log(formattedDate);
	return formattedDate;

}

function addOrder(cutomerShopDetailId,custName) {
	
	$('#addOrderTab').removeClass("d-lg-none");
	$('#addOrderTab').tab('show');
	
	var ordername=`${custName}-`+getMeDateAndTime();
	
	

	var hiddenValue=`<div id='customerShopDetailIdHD' value='${cutomerShopDetailId}'></div>
	                 <div id='custNameHD' value='${custName}'></div>`;
	
	$('#hiddenInfo').html(hiddenValue);
	
	 var bakcButton = `<button type="button" class="btn btn-secondary btn-sm" style="padding: 0px; width: 40px;" id="backBtn" onclick="backOnAddOrder()">  Back</button>`;
	//   $('#backBtn').html(bakcButton);
	
	var infoBoiler=`<div class="card border-primary mb-3 mt-1" style="max-width: 20rem;">
                   <div class="card-body text-primary" style="padding: 0px;">
                     <div class="list-group">
                        <a href="#" class="list-group-item list-group-item-action list-group-item-primary">
                      <b>Order Name :-</b> <input type="text" class="form-control" id="orderName" value="${ordername}" style="padding: 1px;">
                         </a>
                    </div>
                   </div>
                   </div>`;
                   
    //   $('#infoDive').html(infoBoiler);
    
    
    // for search 
    
    
  var serachBoiler=`<div class="card stickyTop">
                    <div class="card-body" style="display: flex;">
                    
                      <div   class="search-container"  style="display: inline-block;width: 40%;">
                        <input class="form-control me-2" type="text"  onkeyup="serchTextHere(this)" id="searchInput" placeholder="Search..." aria-label="Search" style="width: 90%;">
                        <div class="search-result"></div>
                     </div>
                     
                     <div style="display: inline-block;width: 59%;">
                         <button style="margin-left: 20px;" class="btn btn-outline-success" type="button" onclick="addEmptyItomRow()">Add Manually</button>
                         <input class="form-control me-2" type="text"  id="TotalBillPrice" placeholder="Total..." aria-label="Search" style="width: 22%;display: inline-block;">
                         <button style="margin-left: 28%;" type="button" onclick="orderFinalization()" class="btn btn-info">Proceed..</button>
                    </div> 
                      
                    </div>
                </div>`;
                
                // <button class="btn btn-outline-success" type="button">Search</button>
                
var productTable=`<table class="table">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Itom Name</th>
      <th scope="col">Sold Type</th>
      <th scope="col">Quantity</th>
      <th scope="col">Price</th>
      <th scope="col">Action</th>
    </tr>
  </thead>
  <tbody id="itomBody">

  </tbody>
</table>`;
    
       
       
   
var boiler=   `<div class="card border-secondary text-secondary mt-2">
               <div class="card-header"><span style="margin-right: 46%;">${bakcButton}</span>  <span class="text-center"><h4 style="display: inline;color: #3aa5b7;">${custName} Order</h4></span></div>
                 <div class="card-body text-secondary">
                  ${infoBoiler}
                  ${serachBoiler}
                 <div>${productTable}</div>
             </div>
            </div>`;
              
 $('#orderDetail').html(boiler);
	
	
}

var itomCount=0;
function addEmptyItomRow()
{
	itomCount =itomCount +1;
	var boiler=`<tr id="${itomCount}_trId" counter="${itomCount}">
      <td scope="col">${itomCount}</td>
      <td scope="col"><input style="width: 161px;" type="text" class="form-control" id="${itomCount}_itomName" aria-describedby="emailHelp"></td>
     <td scope="col">
      <select class="form-select" aria-label="Default select example" id="${itomCount}_soldType" style="width: 161px;">
					<option selected value="PER_ITOM">Per Itom</option>
					<option value="PER_KG">Per Kg</option>
					<option value="PER_LITER">Per Liter</option>
	 </select>
	 </td>
      <td scope="col"><input style="width: 161px;" type="text" class="form-control" id="${itomCount}_quantity" aria-describedby="emailHelp"></td>
      <td scope="col"><input style="width: 161px;" type="text" class="form-control" id="${itomCount}_Price" aria-describedby="emailHelp"></td>
      <td scope="col">
      <lord-icon src="https://cdn.lordicon.com/kfzfxczd.json" onclick="removeTR(${itomCount})" trigger="hover" colors="primary:#c76f16"  style="width:37;height:37px"></lord-icon>
      </td>
    </tr>`;
    
	$('#itomBody').append(boiler)
	
	$(`#${itomCount}_Price`).val(0);
}

function removeTR(itomCount)
{
	$(`#${itomCount}_trId`).remove();
	calculateTotalPriceToPay();
}



function bTab() {
	$('#addCustomerButn').addClass('d-lg-none');
	$('#showAllUsersOfThisShop').addClass('d-lg-none');
	$('#aTab').addClass('d-lg-none');
	$('#bTab').removeClass('d-lg-none');
	$('#hiddenDiv').removeClass('d-lg-none');

	var boiler = ` <h4>Hii, I am B Tab</h4> `;
	$('#hiddenDiv').html(boiler);

	var bakcButton = `<button type="button" class="btn btn-secondary btn-sm" id="backBtn" onclick="backOnPurchase()">Back</button>`;
	$('#AddProductDiveBackButton').html(bakcButton);

	// getAllProductMasterToAddProductToPurchaseOrder();
}




function showAllOwners() {

	$.ajax({
		url: 'getAllOwners.json',
		type: 'GET',
		data: {

		},
		dataType: 'json',
		success: function(data) {
			var boiler = `
 <table class="table table-bordered border-primary">
  <thead>
    <tr>
      <th scope="col">Sr. No</th>
      <th scope="col">Owner Name</th>
      <th scope="col">Owner Email</th>
      <th scope="col">Owner Desc</th>
       <th scope="col">Action</th>
    </tr>
  </thead><tbody>`;

			data.forEach(function(ownerobj, index) {
				const { ownerName = '', ownerEmail = '', ownerDesc = '' } = ownerobj;
				boiler += `<tr>
	             <td scope="row">${++index}</td>
	             <td scope="row">${ownerName}</td>
	             <td scope="row">${ownerEmail}</td>
	             <td scope="row">${ownerDesc}</td>
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

			boiler += ` </tbody>
              </table>`;

			$('#wonersTable').html(boiler);

		},
		error: function(request, error) {
			alert("Request 1: " + JSON.stringify(request));
		}
	});



	// swal("Good job!", "onload function working properly!", "success");
}


function addCustomerModel() {
	$('#addNewCustomer').modal('show');
}

function saveNewCustomer() {

	var shopId = $('#myShop option:selected').val();

	if (shopId == 0) {
		$('#addCustomerButn').html('');
		return false;
	}

	var name = $("#name").val();
	var mobileNo = $("#mobileNo").val();
	var email = $("#email").val();
	var password = $("#password").val();
	var address = $("#address").val();


	$.ajax({
		url: 'saveNewCustomer.json',
		type: 'GET',
		data: {
			name: name,
			mobileNo: mobileNo,
			email: email,
			password: password,
			address: address,
			shopId: shopId
		},
		dataType: 'json',
		success: function(data) {
			swal("Good job!", "Your New Customer Created!", "success");
		},
		error: function(request, error) {
			alert("Request 1: " + JSON.stringify(request));
		}
	});


}

function deleteCustomer(email) {
	const apiUrl = `/admin/delete/${email}`;

	fetch(apiUrl, {
		method: 'DELETE',
		headers: {
			'Content-Type': 'application/json'
		}
	})
		.then(response => {
			if (response.ok) {
				console.log('User deleted successfully');
				alert('This ' + email + ' User deleted successfully');
				getAllCustomersOfShop();
				// Handle any UI updates or further actions after successful deletion
			} else {
				console.error('Failed to delete user');
				alert('Failed to delete user');
				// Handle error cases
			}
		})
		.catch(error => {
			console.error('Error occurred:', error);
		});

}


function backOnAddOrder()
{
	itomCount=0;
	$('#addOrderTab').addClass('d-lg-none');
	$('#nav-home-tab').tab('show');
	// getAllCustomersOfShop();	
}


function serchTextHere(src){


	var query = $(src).val();
	if (query.length >= 2) {

		$.ajax({
			url: 'getAllItomToSell.json',
			type: 'GET',
			data: {
				itomName: query
			},
			dataType: 'json',
			success: function(jMainArray) {

				$('.search-result').show();
				
				var tdBoiler=``;
				jMainArray.forEach(function(itom, index) {
					const { Name = '', soldType = '', soldPrice = '', purchasedPrice = '' } = itom;

					tdBoiler += `<tr>
				              <th scope="row">${++index}</th>    
				              <td>${Name}</td>
                              <td>${soldType}</td>
                              <td>${soldPrice}</td>
                              <td><button  class="btn btn-outline-success" type="button"  onclick="addEmptyItomRowWithProductInfo(this,'${Name}','${soldType}','${soldPrice}','${purchasedPrice}')">Add</button></td>
                              </tr>`;

				});
				
			var serchBoiler = `<table class="table">
                                   <tbody>
                                    ${tdBoiler}
                                   </tbody>
                                 </table>`;
                                 
                $('.search-result').html(serchBoiler);                 


			}
		});

	} else {
		$('.search-result').hide();

	}

}

function hideSerchText()
{
$('.search-result').hide();
}


function addEmptyItomRowWithProductInfo(src,Name,soldType,soldPrice,purchasedPrice)
{
	hideSerchText();
	
	itomCount =itomCount +1;
	var boiler=`<tr id="${itomCount}_trId" counter="${itomCount}">
      <td scope="col">${itomCount}</td>
      <td scope="col"><input style="width: 161px;" type="text" class="form-control" id="${itomCount}_itomName" aria-describedby="emailHelp"></td>
     <td scope="col">
      <select class="form-select" aria-label="Default select example" id="${itomCount}_soldType" style="width: 161px;">
					<option selected value="PER_ITOM">Per Itom</option>
					<option value="PER_KG">Per Kg</option>
					<option value="PER_LITER">Per Liter</option>
	 </select>
	 </td>
      <td scope="col"><input style="width: 161px;" type="text" class="form-control" id="${itomCount}_quantity" aria-describedby="emailHelp"></td>
      <td scope="col"><input style="width: 161px;" type="text" class="form-control" id="${itomCount}_Price" aria-describedby="emailHelp"></td>
      <td scope="col">
      <lord-icon src="https://cdn.lordicon.com/kfzfxczd.json" onclick="removeTR(${itomCount})" trigger="hover" colors="primary:#c76f16"  style="width:37;height:37px"></lord-icon>
      </td>
    </tr>`;

	$('#itomBody').append(boiler);
	
	$(`#${itomCount}_itomName`).val(Name);
	$(`#${itomCount}_quantity`).val(1);
	$(`#${itomCount}_Price`).val(soldPrice);
	
	calculateTotalPriceToPay();
	
}

function calculateTotalPriceToPay()
{
	var totalPrice=0;
	$("#itomBody tr").each(function(index) {
		var itomCount = $(this).attr("counter");
		var soldPrice = $(`#${itomCount}_Price`).val();
		soldPrice= parseInt(soldPrice);
		if (!isNaN(soldPrice)) {
          totalPrice = totalPrice + soldPrice;
        }
		
		
	});
	
	$(`#TotalBillPrice`).val(totalPrice);
	
}

function orderFinalization() {
	
	$('#OrderFinalizationTab').removeClass("d-lg-none");
	$('#OrderFinalizationTab').tab('show');
	 var orderName = $(`#orderName`).val();
	 
	 var bakcButton = `<button type="button" class="btn btn-secondary btn-sm" style="padding: 0px; width: 40px;margin-top: -11px;margin-right: -43px;margin-left: 5px;" id="backBtn" onclick="backOnCustomerOrder()">  Back</button>`;
	
	
	var boiler=   `<div class="card border-secondary text-secondary mt-2">
               <div class="card-header" style="padding: 0px;"><span style="margin-right: 46%;">${bakcButton}</span>  <span class="text-center"><h4 style="display: inline;color: #3aa5b7;">${orderName}</h4></span></div>
                 <div class="card-body text-secondary">
                   <div id="finalOrder"></div>
                 </div>
            </div>`;
              
   $('#orderFinalizationDetail').html(boiler);
   
   
 
   var totalPrice=0;
   var jsonMainObject = {};
   var jsonArray = [];
	$("#itomBody tr").each(function(index) {
		var jsonObject = {};
		var itomCount = $(this).attr("counter");
	    
	    var itomName= $(`#${itomCount}_itomName`).val();
	    var soldType= $(`#${itomCount}_soldType option:selected`).val();
	    var quantity= $(`#${itomCount}_quantity`).val();
		var soldPrice = $(`#${itomCount}_Price`).val();
		
		jsonObject.itomName=itomName;
		jsonObject.soldType=soldType;
		jsonObject.quantity=quantity;
		jsonObject.soldPrice=soldPrice;
		
		jsonArray.push(jsonObject);
		
		soldPrice= parseInt(soldPrice);
		if (!isNaN(soldPrice)) {
          totalPrice = totalPrice + soldPrice;
        }
	   });
	   
	   jsonMainObject.itomes=jsonArray;
       jsonMainObject.totalPrice=totalPrice;
       jsonMainObject.orderName=orderName;
       
       finalOrderSubmission(jsonMainObject);
       
	}
	
	
	function finalOrderSubmission(jsonMainObject)
	{
		var boiler =`<table class="table">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Itom Name</th>
      <th scope="col">Sold Type</th>
      <th scope="col">Quantity</th>
      <th scope="col">Price</th>
    </tr>
  </thead>
  <tbody id="orderFinalizationBody">
    
  </tbody>
</table>`;

$('#finalOrder').html(boiler);

var jsonArray= jsonMainObject.itomes;
var tBody=``;
jsonArray.forEach(function(item,index) {

  const{itomName='',soldType='',quantity='',soldPrice=''}=item;
	
   tBody += `<tr>
      <th scope="row">${++index}</th>
      <td>${itomName}</td>
      <td>${soldType}</td>
      <td>${quantity}</td>
      <td>${soldPrice}</td>
    </tr>`;
});

  tBody += `<tr>
      <th scope="row" colspan="4" style="text-align: right;">Total :-</th>
      <td>${jsonMainObject.totalPrice}</td>
    </tr>`;
    
     tBody += `<tr>
      <th scope="row" colspan="5" style="text-align: center;">
      <button type="button" class="btn btn-success btn-sm">Save</button>
      </th>
    </tr>`;
    
  $('#orderFinalizationBody').html(tBody);

}
	
function backOnCustomerOrder() {
	$('#OrderFinalizationTab').addClass('d-lg-none');
	$('#addOrderTab').tab('show');
}

