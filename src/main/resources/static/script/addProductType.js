function addProductTypeMasterModel()
{
	$('#addProductTypeMaster').modal('show');
}

function saveNewProductTypeMaster()
{
	var ProductMasterName=$("#ProductMasterName").val();
    var uniqueNo=$("#uniqueNo").val();
    
    $.ajax({
		url:'saveNewProductTypeMaster.json',  
		type: 'GET',
		data: {
			ProductMasterName: ProductMasterName,
			uniqueNo: uniqueNo
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