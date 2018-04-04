$(function(){
	$('#menutree').tree({
		url:extras_Hosting["tomcatSpring_context"] +"ad/loginController/get_menutree",
		method:'GET',
			onClick: function(node){
				//alert(node.text);  // alert node text property when clicked
				var obj=node.attributes;
				//alert(obj.viahref);
				if(obj.viahref!=null&&obj.viahref!=""){
					extras_viahref(obj.viahref); 
				}else{
					return;
				}
			}	
	});
	var via = new Via(extra_views);
	
		
	
		var h = window.innerHeight;
		//$("#cclayout").css('height','500px');
		$('#cclayout').layout('resize', {
			width:'100%',
			height:h-130
		})
	
		
		$("#idbtnlogout").click(function(){
			 extras_POST_json(true,"ad/loginController","do_logout","",function(data){
				 if(data.f=="0"){
				  alert('Info:'+data.message);
				   window.location.href="admin/login.html";
				 }
			 });
				 
		});
		
	$.get(extras_Hosting["tomcatSpring_context"] +"ad/loginController/get_login", 
			function(data){
				if(data!=null){
					$("#liuser").text("Chào: "+data.user_seller.email);
					var d=extras_getnowdate_mysql();
					$("#lidate").text(d);
				}
		     
			});
		
});