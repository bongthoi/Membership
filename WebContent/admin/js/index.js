

$(function(){
	var urltree=extras_Hosting["tomcatSpring_context"] +"ad/loginController/get_menutree2";
	$('#submenutree').load(urltree);
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
				 // alert('报告:'+data.message);
				   window.location.href="admin/login.html";
				 }
			 });
				 
		});
		
	$.get(extras_Hosting["tomcatSpring_context"] +"ad/loginController/get_login", 
			function(data){
				if(data!=null){
					$("#liuser").text("欢迎: "+data.user_seller.email);
					$("#id_liuser").val(data.user_seller.email);
					var d=extras_getnowdate_mysql();
					$("#lidate").text(d);
					if(data.role == "SELLER"){
						var taglocation=window.location.hash.substring(1);
						if(taglocation==""){
							extras_viahref("editinfo");
						}
						
					}
				}
			});
		
});