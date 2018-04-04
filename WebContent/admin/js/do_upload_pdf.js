$(function(){

var size_max = 1024*1000;
var url = extras_Hosting["tomcatSpring_context"]+"UploadDataController/upload_picture_company";

function get_a_element(filename){
	var _href=extras_Hosting["tomcat"]+'upload/company/'+filename;
	var _text=	filename;
	var _a="<a href='"+_href+"' target='blank'>"+_text+"</a>";
	return _a;
}

	var checktxt_picture_company = false;
    $('input[name="txt_picture_company"]').ajaxfileupload({
        'action': url,
        'valid_extensions' : ['jpg','png','gif'],
        'onComplete': function(response) {   
        	if(checktxt_picture_company){
        		 if (response.status==false) {
                     $("#id_upload_success_picture_company_strong_message").html("<font color='red'>" +response.message + " </font>");
                     alert(response.message);
                 }
                 if (response.status==true) {
                     var pram = response.pram;
                     $("#id_upload_success_picture_company_strong_message").html("<font color='green'>" + get_a_element(pram) + " </font>");
                     $("#id_upload_success_picture_company").val(pram);
                 }
        	}
        },
        'onStart': function() {
        	var fileSize = this.get(0).files[0].size;
         	if(fileSize>size_max){
         		 //showdialog('dialogmanual',1,'','dialog','r20');    
         		checktxt_picture_company = false;
         		$.messager.alert('Alert',"File too large, accept size : 1000KB !",'info');
         		// $opso_get(function(out,mes){  					
  					$('#id_upload_success_picture_company_strong_message').html("<font color='red'>" + "Accept size : 1000KB !" + " </font>");
  	         		$("#id_upload_success_picture_company").val('');
             	// },'dialog','r21');   
         		return false;
         	}
         	checktxt_picture_company=true;
         	return true;
        }
    });
    
    
    var checktxt_certificate = false;
    $('input[name="txt_certificate"]').ajaxfileupload({
        'action': url,
        'valid_extensions' : ['pdf'],
        'onComplete': function(response) {    
        	if(checktxt_certificate){
        		if (response.status==false) {
                    $("#id_upload_success_certificate_strong_message").html("<font color='red'>" +response.message + " </font>");
                    alert(response.message);
                }
                if (response.status==true) {
                    var pram = response.pram;
                    $("#id_upload_success_certificate_strong_message").html("<font color='green'>" + get_a_element(pram)  + " </font>");
                    $("#id_upload_success_certificate").val(pram);
                }
        	}
        },
        'onStart': function() {
        	var fileSize = this.get(0).files[0].size;
         	if(fileSize>size_max){
         		//showdialog('dialogmanual',1,'','dialog','r20');        
         		checktxt_certificate=false;
         		$.messager.alert('Alert',"File too large, accept size : 1000KB !",'info');
        		 //$opso_get(function(out,mes){  					
 					$('#id_upload_success_certificate_strong_message').html("<font color='red'>" + "Accept size : 1000KB !" + " </font>");
 	         		$("#id_upload_success_certificate").val('');
            	// },'dialog','r21');   
         		return false;
         	}
         	checktxt_certificate=true;
         	return true;
        }
    });
    
    var checktxt_license=false;
    $('input[name="txt_license"]').ajaxfileupload({
        'action': url,
        'valid_extensions' : ['pdf'],
        'onComplete': function(response) {     
        	if(checktxt_license){
        		if (response.status==false) {
                    $("#id_upload_success_license_strong_message").html("<font color='red'>" +response.message + " </font>");
                    alert(response.message);
                }
                if (response.status==true) {
                    var pram = response.pram;
                    $("#id_upload_success_license_strong_message").html("<font color='green'>" + get_a_element(pram) + " </font>");
                    $("#id_upload_success_license").val(pram);
                }
        	}
        },
        'onStart': function() {
        	var fileSize = this.get(0).files[0].size;
         	if(fileSize>size_max){
         		//showdialog('dialogmanual',1,'','dialog','r20');   
         		checktxt_license = false;
         		$.messager.alert('Alert',"File too large, accept size : 1000KB !",'info');
	       		// $opso_get(function(out,mes){  					
						$('#id_upload_success_license_strong_message').html("<font color='red'>" + "Accept size : 1000KB !" + " </font>");
		         		$("#id_upload_success_license").val('');
	           	 //},'dialog','r21');   
         		
         		return false;
         	}
         	checktxt_license = true;
         	return true;
        }
    });
    
});//end ready