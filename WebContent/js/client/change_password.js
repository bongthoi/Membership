$(function(){
	confirm_change_pass();
	function confirm_change_pass(){
		var cofirm_re=getUrlParameter('cofirm');
		if(cofirm_re==undefined || cofirm_re ==""){
			return;
		}
		if(cofirm_re!='CHANGE'){
			return;
		}
		var email = getUrlParameter('email');
		if(email==undefined){
			$opso_get(function(out,mes){
				$("#content_confirm_change_pass").text(mes);
				$("#myModal_change_password").modal('show');
			},'dialog','r1');			
			return;
		}
		
		var pdata = {
				'email':email				
			};
			Return_get('CustomerController','confirm_change_password',pdata,'GET',function(data){				
				if(data!=null){					
					var error =  parseInt(data.result);
					if(error==2){	
						$opso_get(function(out,mes){
							$("#content_confirm_change_pass").text(mes);
							$('#myModalsignin').modal('hide')
							$("#myModal_change_password").modal('show');
						},'dialog','r2');
						
					}
					else if(error==3){
						$opso_get(function(out,mes){
							$("#content_confirm_change_pass").text(mes);
							$('#myModalsignin').modal('hide')
							$("#myModal_change_password").modal('show');
						},'dialog','r3');						
					}
					else if(error==1){	
						$opso_get(function(out,mes){
							$("#content_confirm_change_pass").text(mes);
							$('#myModalsignin').modal('hide')
							$("#myModal_change_password").modal('show');
						},'dialog','r4');						
					}
					else if(error==0){
						$opso_get(function(out,mes){
							$("#content_confirm_change_pass").text(mes);	
							$('#myModalsignin').modal('hide')
							$("#myModal_change_password").modal('show');
						},'dialog','r5');						
					}
					else{
						$opso_get(function(out,mes){
							$("#content_confirm_change_pass").text(mes);
							$('#myModalsignin').modal('hide')
							$("#myModal_change_password").modal('show');
						},'dialog','r6');						
					}
					
				}
			});
	}
});