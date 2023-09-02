$(document).ready(function() {
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
				const { Name = '', image = '', role = '', mobileNo = '', email = '', address = '' } = purchaseOrder;
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
                          <li><button class="dropdown-item" type="button" onclick="aTab()">A Tab</button></li>
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

function aTab() {
	$('#addCustomerButn').addClass('d-lg-none');
	$('#showAllUsersOfThisShop').addClass('d-lg-none');
	$('#bTab').addClass('d-lg-none');
	$('#aTab').removeClass('d-lg-none');
	$('#hiddenDiv').removeClass('d-lg-none');

	var boiler = ` <h4>Hii, You can do anything here. </h4> `;
	$('#hiddenDiv').html(boiler);

	var bakcButton = `<button type="button" class="btn btn-secondary btn-sm" id="backBtn" onclick="backOnPurchase()">Back</button>`;
	$('#AddProductDiveBackButton').html(bakcButton);

	// getAllProductMasterToAddProductToPurchaseOrder();

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



