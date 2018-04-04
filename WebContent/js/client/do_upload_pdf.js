$(function(){

	var size_max = 1;//1 megabyte
	var text_success_china="<font color='green'>您的档案已上传成功！ </font>";
	var text_fail_size_china="<font color='red'>" + "允许尺寸 : 1MB !" + " </font>";
	var alert_fail_size_china="档案过大，超出允许尺寸1MB!";
	
var url = ReturnHosing_tomcat()+"UploadDataController/upload_picture_company";

	var checktxt_picture_company = false;
    $('input[name="txt_picture_company"]').ajaxfileupload({
        'action': url,
        'valid_extensions' : ['jpg','png','gif'],
        'onComplete': function(response) {     
        	 if (response.status==false) {
                 $("#id_upload_success_picture_company_strong_message").html("<font color='red'>" +response.message + " </font>");
               
             }
        	if(checktxt_picture_company){
                 if (response.status==true) {
                	   var pram = response.pram;
                	 if(extras_ValidateSingleInput(pram)){
                		  $("#id_upload_success_picture_company_strong_message").html(text_success_china);
                          $("#id_upload_success_picture_company").val(pram);
                	 }else{
     	            	$.messager.alert('错误',"上传失败！!",'error');
     	            }
                   
                 }
        	}
        },
        'onStart': function() {
        	
        	var fileSize = this.get(0).files[0].size;
        	var Msize=extras_convertbyte_toM(fileSize);
          	if(Msize>size_max){
         		checktxt_picture_company = false;
         		showdialog('dialogmanual',0,alert_fail_size_china,'dialog','r20');             
         						
         			$('#id_upload_success_picture_company_strong_message').html(text_fail_size_china);
  	         		$("#id_upload_success_picture_company").val('');
             	
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
        	if (response.status==false) {
                $("#id_upload_success_certificate_strong_message").html("<font color='red'>" +response.message + " </font>");
            }
        	if(checktxt_certificate){
                if (response.status==true) {
                   var pram = response.pram;
               	 if(extras_ValidatePDF(pram)){
               		 $("#id_upload_success_certificate_strong_message").html(text_success_china);
                     $("#id_upload_success_certificate").val(pram);
               	 }else{
  	            	$.messager.alert('错误',"上传失败！!",'error');
               	 }
                }
        	}

        },
        'onStart': function() {
        	var fileSize = this.get(0).files[0].size;
          	var Msize=extras_convertbyte_toM(fileSize);
         	if(Msize>size_max){
         		checktxt_certificate = false;
         		showdialog('dialogmanual',0,alert_fail_size_china,'dialog','r20');           
        				$('#id_upload_success_certificate_strong_message').html(text_fail_size_china);
     	         		$("#id_upload_success_certificate").val('');
            
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
        	if (response.status==false) {
                $("#id_upload_success_license_strong_message").html("<font color='red'>" +response.message + " </font>");
    
            }
        	if(checktxt_license){
                if (response.status==true) {
                    var pram = response.pram;
                    if(extras_ValidatePDF(pram)){
                        $("#id_upload_success_license_strong_message").html(text_success_china);
                        $("#id_upload_success_license").val(pram);
                    }else{
                    	$.messager.alert('错误',"上传失败！",'error');
                    }
            
                }
        	}

        },
        'onStart': function() {
        	var fileSize = this.get(0).files[0].size;
        	var Msize=extras_convertbyte_toM(fileSize);
         	if(Msize>size_max){
         		checktxt_license=false;
         			showdialog('dialogmanual',0,alert_fail_size_china,'dialog','r20');     
	       			$('#id_upload_success_license_strong_message').html(text_fail_size_china);
	         		$("#id_upload_success_license").val('');
	     
         		return false;
         	}
         	checktxt_license=true;
         	return true;
        }
    });
    
});//end ready