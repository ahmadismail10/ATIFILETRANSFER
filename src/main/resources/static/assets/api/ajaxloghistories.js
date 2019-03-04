$(document).ready(function() {
	$("#refreshbutton").on("click",function() {
		$("#refreshbutton").addClass("fa-spin");
		$.ajax({
			type : "GET",
			url : "/SFTP/sftp/api/histories/list",
			dataType : "json",
			contentType : "application/x-www-form-urlencoded",
			success : function(data) {
				var mytable = $("#dataTables-example").DataTable(); 
				mytable.clear();
				$.each(data, function(index, element) {
					var status = "";
					if(element.logHistoryStatus=="Success"){
						status = "color: green";
					} else {
						status = "color: red";
					}
					var no = index+1;
					mytable.row.add([no,element.companyParameterDTO.companyDTO.companyName, element.logHistoryFileName,element.companyParameterDTO.companyParameterType,element.logHistoryDate, element.logHistoryTime, "<text style='"+ status +"'>"+element.logHistoryStatus+"</style>", "<a href='/sftp/histories/detail/"+element.logHistoryId+"'><span class='glyphicon glyphicon-eye-open' title='View'></span></a>"]).draw();
				});
				$("#refreshbutton").removeClass("fa-spin");	
			},
			error : function(request, status, error) {
				$("#refreshbutton").removeClass("fa-spin");	
				alert("An error occurred : " + request.responseText);
            },
            timeout : 20000
		})
	});
});