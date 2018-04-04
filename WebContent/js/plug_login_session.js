var array_hosting=['http://tech.vivmall.net/WvivmallT/','http://seashell.vivmall.net/WseashellT/','http://www.vivmall.net/vmall/','http://coconut.vivmall.net/WCoconutT/'];


function is_different_host(hos){
	if(hos==ReturnHosing_tomcat()){
		return false;
	}else{
		return true;
	}
}

function call_login_all_hosting(useremail,passw){
	for(var i=0;i<array_hosting.length;i++){
		if(is_different_host(array_hosting[i])){
		var im='<img style="display:none;" src="'+array_hosting[i]+'MemberController/check_login.htm?email='+useremail+'&pass='+passw+'&cookies=N">';
		$("body").append(im);
		
		}

	}
	
}

