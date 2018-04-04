var url_view = '';
$(function(){
	$("#password").keypress(function(e) {
	    if(e.which == 13) {
	    	singin();
	    }
	});

	$("#btnlogin").click(function(){
		singin();
	});
	function singin(){
		var email = $("#email").val();
		var password = $("#password").val();
		if(email==null || email==''){
			showdialog('dialogmanual',1,'','dialog','r25');
			return;
		}
		if(password==null || password==''){
			showdialog('dialogmanual',1,'','dialog','r26');
			return;
		}
		if(!isValibLengthText(password,6)){
			showdialog('dialogmanual',1,'','dialog','r53');
			return;
		}
		var obj = {
			'email':email,
			'pass':password
		}
		var strobj = JSON.stringify(obj);
		var pdata="{'str':'"+strobj+"'}"; 

		Return_get("CustomerController","check_custome",pdata,"POST",function(data){
		
			if(data!=null){
				var error=parseInt(data);	
				if(error==0){
					window.location.href=ReturnHosing_apache()+'admin/index.html';					
					
				}
				else if(error==-1)
				{
					
					showdialog('dialogmanual',1,'','dialog','r28');
					return;
				}
				else if(error==-2)
				{
					showdialog('dialogmanual',1,'','dialog','r29');
					return;
				}
				else if(error==-3)
				{
					showdialog('dialogmanual',1,'','dialog','r30');
					return;
				}
				else if(error==-4)
				{
					showdialog('dialogmanual',1,'','dialog','r31');
					return;
				}
			}
			else{
				showdialog('dialogmanual',1,'','dialog','r32');
				return;
			}
		});
	}
});
function myCallbackLogin(data){
	if(data.result=='0'){
			window.location.href=url_view;
	}
	else{
		showdialog('dialogmanual',1,'dialog','r33');
		return;
	}
}