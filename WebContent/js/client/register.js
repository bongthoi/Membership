$(function(){	
	create_capchar_r();
	$('#distpicker_e').distpicker({
		  province: '---- 所在省 ----',
		  city: '---- 所在市 ----',
		  district: '---- 所在区 ----',
		  autoselect:'3'
		});
	
	function create_capchar_r() {
		var url = ReturnHosing_tomcat();
		url = url + "CapcharController/createcapchar_register";
		$("#captcha_r img").attr("src", url  + "?" + Math.random()+'&keymap=register'  );
	};

	$("#btnregister").click(function(){
	 	
		if(check_form_r()){
			check_capchar_r(function(output){
				if(output==true){
					var email=$("#txtemail_regis").val();
			    	var pass1=$("#txtpassword1_regis").val();
			    	var type = '001';// SELLER
			    	var shopname=$("#txtshopname_regis").val();
			    	var nation=$("#nation").val();
			    	var address = $("#txt_address_regis").val();
			    	var sdt=$("#txtphone_regis").val();    
			    	var pro = $("#txt_pro").val();
			    	var dist = $("#txt_dist").val();
			    	var license = $("#id_upload_success_license").val();
		
			    	var lang = get_lang_current();
			    	var provice=$("#distpicker_e").find('#slprovince').val();
			    	var city=$("#distpicker_e").find('#slcity').val();
			    	var district=$("#distpicker_e").find('#sldistrict').val();
					var obj={
							'email':email,
							'pass':pass1,
							'type':type,
							'shop_name':shopname,
							'nation':nation,
							'address':address,
							'sdt':sdt,
							'license':license,
							'lang':lang,
							'city':city,
							'provice':provice,
							'district':district
							
					};
					var pdata = {
								'str':JSON.stringify(obj)	
						};
					var urlpost=ReturnHosing_tomcat()+"CustomerController/add_custome";
					blockbg();
					$.post(urlpost,pdata,function(data){
						unblockbg();
						if(data!=null){
							var error=data.result;
							console.log(error);
							if(error=='0'){
								console.log("000");
								$opso_get(function(out,mes){
									//$("#erorr_register").text(mes);	
									showdialog('dialogmanual',1,mes,'dialog','r34');
								},'dialog','r34'); 
								
								create_capchar_r();
								clear_content_r();
							}else if(error=="27"){
								$opso_get(function(out,mes){
									$("#erorr_register").text(mes);			
									showdialog('dialogmanual',1,mes,'dialog','r57');
								},'dialog','r57'); 
							}
							else{
								$opso_get(function(out,mes){
									showdialog('dialogmanual',1,mes,'dialog','r34');			
								},'dialog','r34'); 
							
								return;
							}
						}
						else{
							$opso_get(function(out,mes){
								$("#erorr_register").text(mes);			
								showdialog('dialogmanual',1,mes,'dialog','r35');
							},'dialog','r35'); 
						
							return;
						}
					},'json');//end p[ost
				}//end ouuput
			});//end capcha
		}//end if check form
	});
	
	$("#btnrefresh_r").click(function() {
		create_capchar_r();
	});	
	function check_form_r(){
    	var email=$("#txtemail_regis").val();
    	var pass1=$("#txtpassword1_regis").val();
    	var pass2 = $("#txtpassword2_regis").val();
    	var shopname=$("#txtshopname_regis").val();
    	var add = $("#txt_address_regis").val();
    	
    	if(email==null || email==""){
    		$opso_get(function(out,mes){
				$("#lbmail_regis").text(mes);
				$("#txtemail_regis").focus();				
			},'dialog','r36');  
    		return false;
    	}
    	else{
    		$("#lbmail_regis").text('');
    	}
      	
    	if(isValidEmailAddress(email)==false ){
    		$opso_get(function(out,mes){
				$("#lbmail_regis").text(mes);
				$("#txtemail_regis").focus();				
			},'dialog','r58');  
    		return false;
    	}
    	else{
    		$("#lbmail_regis").text('');
    	}
    	
    	if(pass1==null || pass1==""){
    		$opso_get(function(out,mes){
				$("#lbpassword1_regis").text(mes);
				$("#txtpassword1_regis").focus();
			},'dialog','r37');
    		return false;
    	}
    	else{
    		$("#lbpassword1_regis").text('');
    	}
    	if(pass1.length < 6){
    		$opso_get(function(out,mes){
				$("#lbpassword1_regis").text(mes);
				$("#txtpassword1_regis").focus();
			},'dialog','r53');
    		return false;
    	}
    	else{
    		$("#lbpassword1_regis").text('');
    	}
    	if(pass2==null || pass2==""){
    		$opso_get(function(out,mes){
				$("#lbpassword2_regis").text(mes);
				$("#txtpassword2_regis").focus();				
			},'dialog','r38');
    		return false;
    	}
    	else{
    		$("#lbpassword2_regis").text('');
    	}
    	if(pass2.length < 6){
    		$opso_get(function(out,mes){
				$("#lbpassword2_regis").text(mes);
				$("#txtpassword1_regis").focus();
			},'dialog','r53');
    		return false;
    	}
    	else{
    		$("#lbpassword1_regis").text('');
    	}
    	if(pass1!=pass2){
    		$opso_get(function(out,mes){
				$("#lbpassword2_regis").text(mes);				
			},'dialog','r39');
    		return false;
    	}
    	else{
    		$("#lbpassword2_regis").text('');
    	}

    	if(shopname==null || shopname==""){
    		$opso_get(function(out,mes){
				$("#lbshopname_regis").text(mes);
				$("#txtshopname_regis").focus();				
			},'dialog','r40');    		
    		return false;
    	}
    	else{
    		$("#lbshopname_regis").text('');
    	}
    	if(add==null || add==""){
    		$opso_get(function(out,mes){
				$("#lb_address_regis").text(mes);
				$("#txt_address_regis").focus();				
			},'dialog','r42');  
    		return false;
    	}
    	else{
    		$("#lb_address_regis").text('');
    	}   
    	if(!c_provice()||!c_city()||!c_district()){
    		return false;
    	}
    	return true;  	
    }
	$('#slprovince').on('change', function() {
		 return c_provice();
	});
	$('#slcity').on('change', function() {
		 return c_city();
	});
	$('#sldistrict').on('change', function() {
		return c_district();
	});
	function c_provice(){
		var provice=$("#distpicker_e").find('#slprovince').val();
		if(provice.trim()==""){
    		$opso_get(function(out,mes){$("#lbdistpicker_e").text(mes);},'dialog','r54');  
    		return false;
    	}else{$("#lbdistpicker_e").text('');return true;}
	}
	function c_city(){
		var city=$("#distpicker_e").find('#slcity').val();
		if(city.trim()==""){
    		$opso_get(function(out,mes){$("#lbdistpicker_e").text(mes);},'dialog','r55');  
    		return false;}else{$("#lbdistpicker_e").text('');return true;}
	}
	function c_district(){
		var district=$("#distpicker_e").find('#sldistrict').val();
		if(district.trim()==""){
    		$opso_get(function(out,mes){$("#lbdistpicker_e").text(mes);},'dialog','r56');  
    		return false;
    	}else{$("#lbdistpicker_e").text('');return true;}
	}
	function clear_content_r(){
		$("#txtemail_regis").val('');
    	$("#txtpassword1_regis").val('');
    	$("#txtpassword2_regis").val('');
    	$("#txtshopname_regis").val('');    	
    	$("#txtphone_regis").val('');    	
    	$("#txt_year_es_regis").val('');
    	$("#txt_total_employ_regis").val('');
    	$("#txt_total_revenue_regis").val('');
    	$("#txtcompany_regis").val('');
    	$("#id_upload_success_picture_company").val('');
    	$("#id_upload_success_certificate").val('');
    	$("#id_upload_success_license").val('');
    	$("#txt_address_regis").val('');
    	$("#id_upload_success_license_strong_message").text('');
    	$("#txtcapchar_regis").val('');
		create_capchar_r();		
	}
	
	
	function check_capchar_r(callback) {
		  var capchar = $("#txtcapchar_regis").val();
	        if (capchar != null && capchar != "") {
	            var pdata = {
	                'strcapchar': capchar,
	                'captcharegister':'register'
	            };
	            Return_get("CapcharController", "check_capchar_register", pdata, "GET", function(data) {
	                if (data != null) {
	                    if (parseInt(data) == 0) {
	                        $("#lbcapchar_regis").text("");
	                        callback(true);
	                    } else {
	                    	$opso_get(function(out,mes){
	            				$("#lbcapchar_regis").text(mes);
	            				$("#txtcapchar_regis").focus();	
	            			},'dialog','r51'); 
	                                                
	                        callback(false);
	                    }
	                } else {
	                    $("#lbcapchar_regis").text('Error');
	                    callback(false);
	                }
	            });
	        } else {
	        	$opso_get(function(out,mes){
    				$("#lbcapchar_regis").text(mes);
    				$("#txtcapchar_regis").focus();	
    			},'dialog','r52');	            
	            callback(false);
	        }
    }
	
});
