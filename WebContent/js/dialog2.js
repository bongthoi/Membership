var _mobile = ismobile();
var arr_btn = [
               	{
               		'index' : 1,
               		'message_vn':'Thông báo',
               		'message_cn':'通知',
               	},
               	{
               		'index' : 2,
               		'message_vn':'Đóng',
               		'message_cn':'关闭',
               	},
            	{
               		'index' : 3,
               		'message_vn':'Chấp nhận',
               		'message_cn':' 接受',
               	},
               	{
               		'index' : 4,
               		'message_vn':'Get Message Error',
               		'message_cn':' 获取信',
               	}
              ];
function get_message_btn(index){
	for(var i=0;i<arr_btn.length;i++){
		if(arr_btn[i].index==index){
			if(get_lang_current=='VN'){
				return arr_btn[i].message_vn;
			}
			else{
				return arr_btn[i].message_cn;
			}
		}
	}
}
var btn_alert_close= get_message_btn(1);
function showdialog(div,type,mes,data_file,data_key ) {		
	if(_mobile==false){
		if(type==1){
			$opso_get(function(output,text){
				if(output==true){
					$("#content_message" ).text(text);
					$("#"+div ).dialog({
						
						 height: 250,
						 width: 500,
						 modal: true ,
						 autoOpen: true ,
						 title:btn_alert_close,
						 resizable: false ,
						 buttons: {	   			
							"Đóng": function() {
							   $(this).dialog("close");
						   }
						}
					});											
				}
				else{
					$("#content_message" ).text('Get Message Error');
					$("#"+div ).dialog({
						 height: 250,
						 width: 500,
						 modal: true ,
						 autoOpen: true ,
						 title:btn_alert_close,
						 resizable: false ,
						 buttons: {	   			
							"Đóng": function() {
							   $(this).dialog("close");
						   }
						}
					});
					
				}
			},data_file,data_key);
		}
		else{
			$("#content_message" ).text(mes);
			$("#"+div ).dialog({
				 height: 250,
				 width: 500,
				 modal: true ,
				 autoOpen: true ,
				 title:btn_alert_close,
				 resizable: false ,
				 buttons: {	   			
					"Đóng": function() {
					   $(this).dialog("close");
				   }
				}
			});
		}
	}
	else{
		if(type==1){
			$opso_get(function(output,text){
				alert(text);
			},data_file,data_key);
		}
		else{
			alert(mes);
		}
	}	
}
function show_message_basic(div,mes){
	$("#content_message1" ).text(mes);
	$("#"+div ).dialog({
		 height: 250,
		 width: 500,
		 modal: true ,
		 autoOpen: true ,
		 title:btn_alert_close,
		 resizable: false ,
		 buttons: {	   			
			"Đóng": function() {
			   $(this).dialog("close");
		   }
		}
	});
}
function showdialogexit(div, message) {	
	if(_mobile==false){
		$("#"+div).text(message);
		$("#"+div).dialog({
			resizable : false,
			autoOpen : true,
			height : 140,
			modal : true		
		});
	}
	else{
		alert(message);
	}
	
}
function showdialogfunctionok(div,mes,functinok)
{
	if(_mobile==false){
		$("#"+div).text(mes);			
		$("#"+div).dialog(
		{
			resizable : false,
			autoOpen : true,
			title:btn_alert_close,
			height: 250,
			width: 500,
			modal : true,			   
			minHeight: 80,			    
			buttons: {
			  "Chấp nhận": functinok	      
			}
		});	
	}
	else{
		var r = confirm(mes);
		if(r==true){
			functinok();
		}
	}
	
}
function showdialogfunctionclose(div,mes)
{		
	if(_mobile==false){
		$(div).text(mes);			
		$(div).dialog(
		{
			resizable : false,
			autoOpen : true,
			height : 140,
			width:400,
			modal : true,			   
			minHeight: 100,			    
			buttons: {
			  "Đóng": function(){
				  $(div).dialog('close');
			  }	      
			}
		});	
	}
	else{
		alert(mes);
	}
	
}
function showdialogconfirm(mes){
	$("#contentdialogconfirmmanual").text(mes);
	$("#dialogconfirmmanual").window('open');
}
