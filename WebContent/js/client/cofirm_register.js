$(function() {
	
	confirm_create_shop();
	function confirm_create_shop(){
		var cofirm_re=getUrlParameter('cofirm');
		if(cofirm_re==undefined || cofirm_re ==""){
			return;
		}
		var email = getUrlParameter('email');
		if(email==undefined || email ==""){
			return;
		}
		
		if(cofirm_re=="REGISTER"&&email!=""){
			var pdata = {
					'email':email				
				};
			
				Return_get('CustomerController','confirm_create_shop',pdata,'GET',function(data){
					if(data!=null){				
						$("#content_confirm a").attr("href","");
						var error = data.result;
						if(error==""){
							return;
						}
						var ierror=parseInt(error);
						if(ierror!=2){	//==2//$("#content_confirm").text('Không tồn tại email');
							showtext_error(function(out){
								console.log(out);
								Return_get("CustomerController","get_info_customer",pdata,"GET",function(data){
									if(data!=null){
										$("#name1_cofirm").text(data.shop_name);
										$("#name2_cofirm").text(data.shop_name);
										$("#name3_cofirm").text(data.shop_name);
										$("#email_cofirm").text(data.email);
										$("#pass_cofirm").text(data.pass);
										$("#url_cofirm").text(data.shop_url);
										$('#myModalsignin').modal('hide')
										$("#myModal").modal('show');
										
									}
								});
							},ierror);
							
						}//end if eeor!=2
					}
				});
		}//end if
	}//end function
	
	function showtext_error(callback,ierror){
		 if(ierror==3){	
			 $opso_get(function(out,mes){
					$("#content_confirm").text(mes);
					console.log(mes);
					callback(true);
			 },'dialog','r7');	
			
		}
		else if(ierror==1){	
			 $opso_get(function(out,mes){
					$("#content_confirm").text(mes);
					console.log(mes);
					callback(true);
			 },'dialog','r8');
			
		}
		else if(ierror==0){	
			 $opso_get(function(out,mes){
					$("#content_confirm").text(mes);
					console.log(mes);
					callback(true);
			 },'dialog','r9');			
		}
		else{
			 $opso_get(function(out,mes){
					$("#content_confirm").text(mes);
					console.log(mes);
					callback(true);
			 },'dialog','r10');			
		}
	}
});