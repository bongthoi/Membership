function blockbg(){
	
	$.blockUI({
		   message: '<img src="img/loading.gif" style="width:150px;height:100px;" />',
		   css: { 
                border: 'none', 
                background: 'none' 
    			} 
	});
}
function unblockbg(){
	$.unblockUI();	
}