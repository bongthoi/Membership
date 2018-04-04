$(function() {
	create_capchar();
	$("#btnsend").click(function(){
	if(check_form()){
		check_capchar(function(output){
			if(output==true){
				var email = $("#txtemail").val();
				var fullname = $("#txtfullname").val();
				var phone = $("#txtphone").val();
				var title = $("#txttitle").val();
				var content = $("#txtcontent").val();
				//alert(content);
				var obj={
						'email':email,
						'fullname':fullname,
						'phone':phone,
						'title':title,
						'content':content
				};
				var pdata = {
							'str':JSON.stringify(obj)	
					};
			
				Return_get("ContactController","send_contact",pdata,"GET",function(data){
					
					if(data!=null){
						var error=parseInt(data);
						if(error==0){
							showdialog('dialogmanual',1,'','dialog','r11');
							create_capchar();
							clear_content();
						}
						else{
							showdialog('dialogmanual',1,'','dialog','r12');							
						}
					}
					else{
						showdialog('dialogmanual',1,'','dialog','r13');
						
					}
				});
			}
		});
	}
	/*else{
			showdialog('dialogmanual',0,'Không thành công, vui lòng nhập đầy đủ thông tin','','');
			return;
		}*/
	});
	
	function create_capchar() {
		var url = ReturnHosing_tomcat();
		url = url + "CapcharController/createcapchar";
		$("#captcha img").attr("src",url + "?" + Math.random()+'&keymap=contact' );
	};
	$("#btnrefresh").click(function() {
		create_capchar();
	});

	function check_capchar(callback) {
        //check capchar
        var capchar = $("#txtcapchar").val();
        if (capchar != null && capchar != "") {
            var pdata = {
                'strcapchar': capchar,
                'keymap':'contact'
            };
            Return_get("CapcharController", "check_capchar", pdata, "GET", function(data) {
                if (data != null) {
                    if (parseInt(data) == 0) {
                        $("#lberrorcapchar").text("");
                        callback(true);
                    } else {
                    	 $opso_get(function(out,mes){
         					$("#lberrorcapchar").text(mes);
         					callback(false);
                    	 },'dialog','r14');              
                        
                    }
                } else {
                    $("#lberrorcapchar").text('Error');
                    callback(false);
                }
            });
        } else {
        	 $opso_get(function(out,mes){
					$("#lberrorcapchar").text(mes);
					callback(false);
					$("#txtcapchar").focus();		            
         	 },'dialog','r15');
            
            
        }
    }
	function clear_content(){
		$("#txtemail").val('');
		$("#txtfullname").val('');
		$("#txtphone").val('');
		$("#txttitle").val('');
		$("#txtcontent").val('');
		$("#txtcapchar").val('');
	
	}
	function check_form(){
    	var email=$("#txtemail").val();
    	var fullname=$("#txtfullname").val();
    	var phone = $("#txtphone").val();
    	var title = $("#txttitle").val();
    	var content=$("#txtcontent").val();
    	var check = true;
    	if(email==null || email==""){
    		 $opso_get(function(out,mes){
					$("#lbemail_contact").text(mes);
					$("#txtemail").focus();
					
    		 },'dialog','r16');
    		 check=false;	
			return check;
    		
    	}else{
    		$("#lbemail_contact").text('');
    	}
    	if(fullname==null || fullname==""){
    		 $opso_get(function(out,mes){
					$("#lbfullname").text(mes);
					$("#txtfullname").focus();
											            
 		    },'dialog','r17');  
    		 check=false;	
			return check;
    	}else{
    		$("#lbfullname").text('');
    	}
    	if(phone==null || phone==""){
    		 $opso_get(function(out,mes){
					$("#lbphone").text(mes);
					$("#txtphone").focus();
					
		    },'dialog','r18');
    		 check=false;
			return check;
    		
    	}else{
    		$("#lbphone").text('');
    	}
    	if(title==null || title==""){
    		 $opso_get(function(out,mes){
					$("#lbtitle").text(mes);
					$("#txttitle").focus();
					
		    },'dialog','r19');
    		check=false;    		
    		return check;
    	}else{
    		$("#lbtitle").text('');
    	}
    	
    	 return check;  	
    }

})