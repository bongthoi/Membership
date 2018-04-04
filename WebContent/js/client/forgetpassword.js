$(function(){
	
	$("#btnforget").click(function(){
		if(check_login()){
			blockbg();
			var email = $("#txtemail_forgot").val();			
			var pdata = {
				'email':email				
			};
			Return_get('CustomerController','change_forget_password',pdata,'GET',function(data){
				unblockbg();
				if(data!=null){
					var error = data;
					if(parseInt(error)==0){	
						$('#myModal_master').modal('hide')
						$("#txtemail_forgot").val('');
						showdialog('dialogmanual',1, '', 'dialog', 'r22');
					}
					else{
						showdialog('dialogmanual',1, '', 'dialog', 'r23');						
					}
				}
			});
		}
	});
	function check_login(){
		var email = $("#txtemail_forgot").val();
		if(email==null || email==''){
			 $opso_get(function(out,mes){
					$("#lbemail_forgot").text(mes);					
			 },'dialog','r24');
			 return false;
			
		}
		else{
			$("#lbemail_forgot").text('');
		}
		
		return true;
	}
});