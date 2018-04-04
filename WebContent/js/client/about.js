$(function(){
		var pdata={"lang":"VN"};
		Return_get("AboutController","get_about",pdata, 'GET', function(data) {
			if(data!=null){
				$("#loadabout").html(data.desc);
			}
		});

});