$(document).ready(function() {
	var baseUrl = window.location.pathname.split("/");
	$("#companysearch").on('change keyup paste click',function() {
		$.ajax({
			type : "POST",
			url : "/SFTP/sftp/api/company/name",
			data : {
				"companyName" : $(this).val(),
				"companyId" : $("#companyId").val()
			},
			dataType : "json",
			contentType : "application/x-www-form-urlencoded",
			success : function(data) {
				if(data.length == 0){
					$("#companyNameAjax").hide();
					if(!$('#companyNameAjax').is(':visible') && !$('#companyTypeAjax').is(':visible')){
						$("#save").prop("disabled", false);
					}			
				} else {
					$("#companyNameAjax").show();							
					if($('#companyNameAjax').is(':visible') || $('#companyTypeAjax').is(':visible')){
						$("#save").prop("disabled", true);
					}									
				}
			}
		})
	});
	$("input[name='companyType']").change(function() {
		$.ajax({
			type : "POST",
			url : "/SFTP/sftp/api/company/type",
			data : {
				"companyType" : $(this).val(),
				"companyId" : $("#companyId").val()
			},
			dataType : "json",
			contentType : "application/x-www-form-urlencoded",
			success : function(data) {
				if(data.length == 0){
					$("#companyTypeAjax").hide();
					if(!$('#companyNameAjax').is(':visible') && !$('#companyTypeAjax').is(':visible')){
						$("#save").prop("disabled", false);
					}				
				} else {
					$("#companyTypeAjax").show();
					if($('#companyNameAjax').is(':visible') || $('#companyTypeAjax').is(':visible')){
						$("#save").prop("disabled", true);
					}										
				}
			}
		})
	});
});