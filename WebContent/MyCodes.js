/**
 * 
 */

function AdminUserManagementTablePager() {
	$('.MODELEDIT').on("click", function() {
		$("#objectid").val($(this).attr('objectid'));
		$("#objectidhidden").val($(this).attr('objectid'));
		$("#username").val($(this).attr('username'));
		$("#fullname").val($(this).attr('fullname'));
		$("#email").val($(this).attr('email'));
		if ($(this).attr("usertype") == 'user') {
			document.getElementById("userTypeUser").checked = true;
			document.getElementById("userTypeAdmin").checked = false;
		} else {
			document.getElementById("userTypeUser").checked = false;
			document.getElementById("userTypeAdmin").checked = true;
		}
		if ($(this).attr("isactive") == "true") {
			document.getElementById("ChkActive").checked = true;
		}
		if ($(this).attr("isapproved") == "true") {
			document.getElementById("ChkApproved").checked = true;
		}

		$('#myModal1').modal('show');
		return false;
	});
}
function AdminChoiceManagementTablePager(){
	$('.QCMODELEDIT').on("click", function() {
		$("#choiceid2").val($(this).attr('id'));
		$("#choiceid2").hide();
		$("#choiceid").val($(this).attr('id'));
		
		
		$("#tdchoice1").val($(this).attr('tdchoice1'));
		$("#tdchoice2").val($(this).attr('tdchoice2'));
		$("#tdchoice3").val($(this).attr('tdchoice3'));
		$("#tdchoice4").val($(this).attr('tdchoice4'));
		$("#tdquestion").val($(this).attr('tdquestion_data'));
		console.log("Deger = "+$(this).attr('tdcorrectanswer'));
		if($(this).attr('tdcorrectanswer') == "0"){
			$("#correctchoice1").prop("checked",true);
			console.log(0);
		}
			
		else if($(this).attr('tdcorrectanswer') == "1"){
			$("#correctchoice2").prop("checked",true);
			console.log(1);
		}
			
		else if($(this).attr('tdcorrectanswer') == "2"){
			$("#correctchoice3").prop("checked",true);
			console.log(2);
		}
			
		else if($(this).attr('tdcorrectanswer') == "3"){
			$("#correctchoice4").prop("checked",true);
			console.log(3);
		}
		 
	
		$('#editChoiceModal').modal('show');
		return false;
	});
}

function ChoiceEvents(){
	$('#btnSaveChangesChoice').on("click", function() {
	

		if ($('#correctchoice1').is(':checked'))
			$('#correctchoice').val("choice1");
		else if ($('#correctchoice2').is(':checked'))
			$('#correctchoice').val("choice2");
		else if ($('#correctchoice3').is(':checked'))
			$('#correctchoice').val("choice3");
		else if ($('#correctchoice4').is(':checked'))
			$('#correctchoice').val("choice4");

		
	});
}





function AdminQuestionManagementTablePager() {
	$('#btnSaveChanges').on("click", function() {

		if ($('#correctchoice1').is(':checked'))
			$('#correctchoice').val("choice1");
		else if ($('#correctchoice2').is(':checked'))
			$('#correctchoice').val("choice2");
		else if ($('#correctchoice3').is(':checked'))
			$('#correctchoice').val("choice3");
		else if ($('#correctchoice4').is(':checked'))
			$('#correctchoice').val("choice4");

	});
	$('#btnSaveChangesAddQ').on("click", function() {
		if ($('#correctchoiceadd1').is(':checked'))
			$('#correctchoiceadd').val("choice1");
		else if ($('#correctchoiceadd2').is(':checked'))
			$('#correctchoiceadd').val("choice2");
		else if ($('#correctchoiceadd3').is(':checked'))
			$('#correctchoiceadd').val("choice3");
		else if ($('#correctchoiceadd4').is(':checked'))
			$('#correctchoiceadd').val("choice4");
	});
	$('.QMODELEDIT').on("click", function() {
		$("#objectid").val($(this).attr('objectid'));
		$("#objectidhidden").val($(this).attr('objectid'));
		$("#questiontext").val($(this).attr('questiontext'));
		if($(this).attr('correctchoice') == "0"){
			$("#correctchoice1").prop("checked",true);
			console.log(0);
		}
			
		else if($(this).attr('correctchoice') == "1"){
			$("#correctchoice2").prop("checked",true);
			console.log(1);
		}
			
		else if($(this).attr('correctchoice') == "2"){
			$("#correctchoice3").prop("checked",true);
			console.log(2);
		}
			
		else if($(this).attr('correctchoice') == "3"){
			$("#correctchoice4").prop("checked",true);
			console.log(3);
		}
			
		$("#positivescore").val($(this).attr('positivescore'));
		$("#negativescore").val($(this).attr('negativescore'));
		console.log($(this).attr('category'));
	
		  var ddl = document.getElementById('categoryname');
			var opts = ddl.options.length;
			for (var i=0; i<opts; i++){
			    if (ddl.options[i].text == $(this).attr("category")){
			        ddl.options[i].selected = true;
			        break;
			    }
			}
	//	$('#categoryname option[value='+$(this).attr('category')+']').attr("selected", "selected");

		$('#myModal1').modal('show');
		return false;
	});
}

function AdminAddNewUser() {
	$('#AddNewUser').on("click", function() {
		$('#myModalAddUser').modal('show');
		return false;
	});
}

function AdminAddQuestion(){
	$('#AddNewQuestion').on("click", function() {
		$('#myModalAddQuestion').modal('show');
		return false;
	});
}

function AdminUserTypeSelection() {
	$('#btnSaveChanges').on("click", function() {
		if ($('#userTypeAdmin').is(':checked'))
			$('#tbForUserType').val("admin");
		else if ($('#userTypeUser').is(':checked'))
			$('#tbForUserType').val("user");

		if ($('#ChkActive').is(':checked'))
			$('#tbForIsActive').val("1");
		else
			$('#tbForIsActive').val("0");

		if ($('#ChkApproved').is(':checked'))
			$('#tbForIsApproved').val("1");
		else
			$('#tbForIsApproved').val("0");
	});

	$('#btnSaveChangesAddUser').on("click", function() {
		if ($('#genderMaleAddUser').is(':checked'))
			$('#tbForGenderAddUser').val("1");
		else if ($('#genderFemaleAddUser').is(':checked'))
			$('#tbForGenderAddUser').val("0");

		if ($('#userTypeAdminAdduser').is(':checked'))
			$('#tbForUserTypeAddUser').val("admin");
		else if ($('#userTypeUserAddUser').is(':checked'))
			$('#tbForUserTypeAddUser').val("user");

		if ($('#ChkActiveAddUser').is(':checked'))
			$('#tbForIsActiveAddUser').val("1");
		else
			$('#tbForIsActiveAddUser').val("0");

		if ($('#ChkApprovedAddUser').is(':checked'))
			$('#tbForIsApprovedAddUser').val("1");
		else
			$('#tbForIsApprovedAddUser').val("0");
	});

}

function AdminHintOperationsEditModal() {
		$('.HintModal').on("click", function() {
		$("#hintid2").val($(this).attr('id'));
		$("#hintid2").hide();
		$("#tdhint_optionhide").val($(this).attr('tdquestion'));
		$('#tdhint_optionhide').hide();
		$("#hintid").val($(this).attr('id'));
		$("#tdhint").val($(this).attr('tdhint'));

		  var ddl = document.getElementById('questiontext');
			var opts = ddl.options.length;
			for (var i=0; i<opts; i++){
			    if (ddl.options[i].text == $(this).attr("tdquestion")){
			        ddl.options[i].selected = true;
			        break;
			    }
			}
		$('#editHintModal').modal('show');
		return false;
	});
}


function AdminSongOperationsEditModal() {
	$('.SongModal').on("click", function() {
		$("#id").val($(this).attr('id'));
		$("#idhidden").val($(this).attr('id'));
		$("#name").val($(this).attr('name'));
		
		var ddl = document.getElementById('ddlSinger');
		var opts = ddl.options.length;
		for (var i=0; i<opts; i++){
		    if (ddl.options[i].text == $(this).attr("singerName")){
		        ddl.options[i].selected = true;
		        break;
		    }
		}
		
		var ddl2 = document.getElementById('ddlSongType');
		var opts2 = ddl2.options.length;
		for (var j=0; j<opts; j++){
		    if (ddl2.options[j].text == $(this).attr("typeName")){
		        ddl2.selectedIndex = j;
		        break;
		    }
		}
		$('#songModal').modal('show');
		return false;
	});
}

function AdminAddNewSong() {
	$('#AddNewSong').on("click", function() {
		$('#addSongModal').modal('show');
		return false;
	});
}

function AdminSingerOperationsEditModal() {
	$('.SingerModal').on("click", function() {
		$("#id").val($(this).attr('id'));
		$("#idhidden").val($(this).attr('id'));
		$("#namesurname").val($(this).attr('namesurname'));
		$("#birthdate").val($(this).attr('birthdate'));
		if ($(this).attr("singertype") == 'Person') {
			document.getElementById("singerTypePerson").checked = true;
			document.getElementById("singerTypeBand").checked = false;
		} else {
			document.getElementById("singerTypePerson").checked = false;
			document.getElementById("singerTypeBand").checked = true;
		}
		$('#singerModal').modal('show');
		return false;
	});
}

function AdminSingerTypeSelection() {
	$('#btnSaveChanges').on("click", function() {
		if ($('#singerTypePerson').is(':checked'))
			$('#tbForSingerType').val("Person");
		else if ($('#singerTypeBand').is(':checked'))
			$('#tbForSingerType').val("Band");
		console.log("singer type edit");
	});
	
	$('#btnSaveChangesAddSong').on("click", function() {
		if ($('#singerTypePersonAddSong').is(':checked'))
			$('#tbForSingerTypeAddSong').val("Person");
		else if ($('#singerTypeBandAddSong').is(':checked'))
			$('#tbForSingerTypeAddSong').val("Band");
	});
}

function AdminSongTypeOperationsEditModal() {
	$('.SongTypeModal').on("click", function() {
		$("#id").val($(this).attr('id'));
		$("#idhidden").val($(this).attr('id'));
		$("#name").val($(this).attr('name'));
		$('#songTypeModal').modal('show');
		return false;
	});
}
