
function ReturnHosing_tomcat(){
	var port = location.port;
	var url ='http://'+window.location.hostname+':'+port+'/Membership/spring/';
	return url;
}
function ReturnHosing_apache(){
	var port = location.port;	
	var url ='http://'+window.location.hostname+':'+port+'/Membership/';
	return url;
}

function Return_get(pcontroller,pmethod,pdata,ptype,callback){
	switch(ptype.toUpperCase()){
		case 'GET':
			_get(pcontroller,pmethod,pdata,'GET',callback);
			break;
		case 'POST':
			_post(pcontroller,pmethod,pdata,'POST',callback);
			break;
	}
}
function _get(pcontroller,pmethod,pdata,ptype,callback){
	var purl = ReturnHosing_tomcat()+pcontroller+"/"+pmethod;	
	$.ajax({
		url : purl,
		headers : {
			'Accept' : 'application/json',
			'Content-Type': 'application/json'				
		},
		type:ptype,
		dataType : 'json',
		data:pdata,			
		success : function(data) {
			callback(data);
		},
		 error:function(data,status,er) { 
			 callback('');
		    }
	});
}
function _post(pcontroller,pmethod,pdata,ptype,callback){
	var purl = ReturnHosing_tomcat()+pcontroller+"/"+pmethod;	
	$.ajax({
		url: purl, 
	    type: ptype, 
	    dataType: 'json', 
	    data: pdata, 
	    contentType: 'application/json',
	    mimeType: 'application/json',
	    success: function(data) { 
	       callback(data);
	    },
	    error:function(data,status,er) { 
	      //  alert("Exception:"+er);
	    }
	});
}
function formatcurrency(num){	
    var str = num.toString().replace("$", ""), parts = false, output = [], i = 1, formatted = null;
    if(str.indexOf(".") > 0) {
        parts = str.split(".");
        str = parts[0];
    }
    str = str.split("").reverse();
    for(var j = 0, len = str.length; j < len; j++) {
        if(str[j] != ",") {
            output.push(str[j]);
            if(i%3 == 0 && j < (len - 1)) {
                output.push(",");
            }
            i++;
        }
    }
    formatted = output.reverse().join("");
    return(formatted + ((parts) ? "." + parts[1].substr(0, 2) : ""));
};
function getUrlParameter(sParam)
{
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++) 
    {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam) 
        {
            return sParameterName[1];
        }
    }
}
function ReturnUrlHeader(){
	var url =ReturnHosing_apache()+'header.html';	
	return url;
}
function ReturnUrlFooter(){
	var url =ReturnHosing_apache()+'footer.html';	
	return url;
}
function get_url_header(){
	var mobile = ismobile();
	var ipad = isipad();
	var url = '';
	if(mobile==true){
		url = ReturnUrlHeaderMobile();
	}
	else if(ipad==true){
		url = ReturnUrlHeaderIpad();
	}
	else{
		url = ReturnUrlHeader();
	}
	return url;
}
function exe_load_header(callback){	
	$("#header").load(get_url_header(), function (responseText, textStatus, XMLHttpRequest) {		
		$("#footer").load(ReturnUrlFooter(), function (responseText, textStatus, XMLHttpRequest) {
			fix_monitor();
			callback(true);
		});		
	});	
}

function loadScript(url,callback) {
	   
    var script = document.createElement("script");
    script.type = "text/javascript";
    script.src = url;
    document.getElementsByTagName("addsrc")[0].appendChild(script);
    if (script.readyState) { //IE
        script.onreadystatechange = function () {
            if (script.readyState == "loaded" || script.readyState == "complete") {
                script.onreadystatechange = null;
                callback(true);             
                
            }
        };
    } else { //Others
        script.onload = function () {
        	callback(true);
        };
    }		         
}
function get_lang_current(){
	var lang = Cookies.get('curlange');
	if(lang!=null && lang!='' && lang != undefined){
		return lang;
	}
	else{
		var lang_s = 'CN';
		Cookies.set('curlange',lang_s);
		$.post(ReturnHosing_tomcat()+"CustomerController/set_lang",{"lang":lang_s},function(data){});
		return lang_s;	
	}
		
}
function change_lang(lang){
	Cookies.set('curlange',lang);
	$.post(ReturnHosing_tomcat()+"CustomerController/set_lang",{"lang":lang},function(data){
		location.reload();
	});
	
}
function set_menu_active(menu){
	$.session.set('menuactive',menu);
}


function fn_see_product(src){	
	
	var href =$(src).attr('opso_link');
	var arr = href.split('_');
	var id = arr[1];
	var action = $(src).attr('opso_slide');
	if(action=='down'){
		$(src).attr('opso_slide','up');
		$("#bottomsp_"+id).slideDown("slow");	
	}	
	else{
		$(src).attr('opso_slide','down');
		$("#bottomsp_"+id).slideUp("slow");
	}
}
function showdlgcart(id){
	var pdata =  {
			'product_id':id,
			'product_quantity':1
	};
	Return_get("ShoppingCartController","add_shopping_cart",pdata,"GET",function(data){
		if (data != null) {
			Showdialogshoppingcart('partial_shoppingcart.html');
		}
	});
	
}
function ValidateDate(dtValue)
{
	var check = false;	
	var rxDatePattern =/^(\d{1,2})(\/|-)(\d{1,2})(\/|-)(\d{4})$/;
	try
	{
		var dtArray = dtValue.match(rxDatePattern); 
	    if (dtArray == null) 
	        return false;
	    dtMonth = dtArray[3];
	    dtDay= dtArray[1];
	    dtYear = dtArray[5];        
	    
	    if (dtMonth < 1 || dtMonth > 12) 
	        return false;
	    else if (dtDay < 1 || dtDay> 31) 
	        return false;
	    else if ((dtMonth==4 || dtMonth==6 || dtMonth==9 || dtMonth==11) && dtDay ==31) 
	        return false;
	    else if (dtMonth == 2) 
	    {
	        var isleap = (dtYear % 4 == 0 && (dtYear % 100 != 0 || dtYear % 400 == 0));
	        if (dtDay> 29 || (dtDay ==29 && !isleap)) 
	                return false;
	    }
	    check = true;
	}
	catch(err)
	{
		check = false;
	}	
	return check;
}
function htmlUnescape(value){
    return String(value)
        .replace(/&quot;/g, '"')
        .replace(/&#39;/g, "'")
        .replace(/&lt;/g, '<')
        .replace(/&gt;/g, '>')
        .replace(/&amp;/g, '&');
}
function loadScript(callback,url) {
	 
    var script = document.createElement("script");
    script.type = "text/javascript";

    if (script.readyState) { //IE
        script.onreadystatechange = function () {
            if (script.readyState == "loaded" || script.readyState == "complete") {
                script.onreadystatechange = null;
               callback(true);
            }
        };
    } else { //Others
        script.onload = function () {
        	callback(true);
        };
    }	 
    script.src = url;
    document.getElementsByTagName("body")[0].appendChild(script);
}
function Dom_Get(pcontroller,pmethod,pdata,ptype,callback){
	switch(ptype.toUpperCase()){
		case 'GET':
			_getDom(pcontroller,pmethod,pdata,'GET',callback);
			break;
	}
}
function _getDom(pcontroller,pmethod,pdata,ptype,callback){
	var purl = ReturnHosing_tomcat()+pcontroller+"/"+pmethod+".htm?";	
	var i=0;
	$.map(pdata, function(value, key) {
		if(i==0){
			purl +=key+"="+value;	
		}
		else{
			purl +="&"+value+"="+key;
		}
		
		i++;
	});
	var rawFile = new XMLHttpRequest();
	 rawFile.open("GET", purl, false);
	 rawFile.onreadystatechange = function ()
	 {
	     if(rawFile.readyState === 4)
	     {
	        if(rawFile.status === 200 || rawFile.status == 0)
	         {
	             var text = rawFile.responseText;
	             callback(text);
	         }
	      }
	 };	
	 rawFile.send(null);
}

function loadScriptadv(url,callback) {
		 
	        var script = document.createElement("script");
	        script.type = "text/javascript";
	 
	        if (script.readyState) { //IE
	            script.onreadystatechange = function () {
	                if (script.readyState == "loaded" || script.readyState == "complete") {
	                    script.onreadystatechange = null;
	                   callback(true);
	                }
	            };
	        } else { //Others
	            script.onload = function () {
	            	callback(true);
	            };
	        }	 
	        script.src = url;
	        document.getElementsByTagName("body")[0].appendChild(script);
}
function ismobile(){
	var windowWidth = window.screen.width < window.outerWidth ?
                  window.screen.width : window.outerWidth;
	var mobile = windowWidth < 500 ?true : false;
	return mobile;
}
function isipad(){
	var windowWidth = window.screen.width < window.outerWidth ?
                  window.screen.width : window.outerWidth;
	if(windowWidth >500 && windowWidth < 1024){
		return true;
	}
	else{
		return false;
	}
}
function isPc(){
	if(ismobile() || isipad()){
		return false;
	}
	else{
		return true;
	}
}
function get_skype(){
	Skype.ui({
	      "name": "chat",
	      "element": "SkypeButton_Call_hnphung.2002_1",
	      "participants": ["hnphung.2002"],
	      "imageSize": 24
	});
	Skype.ui({
	      "name": "chat",
	      "element": "SkypeButton_Call_hyundai2611_1",
	      "participants": ["hyundai2611"],
	      "imageSize": 24
	    });
}
function load_skype(){
	$("#contain-skype").load(ReturnUrlSkype(), function (responseText, textStatus, XMLHttpRequest) {
		
		
	});	
}
function load_social(){
	$("#contain-social").load(ReturnUrlSocial(), function (responseText, textStatus, XMLHttpRequest) {
		
		
	});	
}
function hidden_content_page(){
	
	$("#idloading").css('display','none');
	$("#prevpage").css('display','none');
	$("#nextpage").css('display','none');
}
function fix_monitor(){
	var windowWidth = window.screen.width;
	if(windowWidth<=500){
		$(".fix-container").addClass('container');
	}
	else { 
		$(".fix-container").addClass('container-fluid');
	}
}
function removeEclass(callback){
	var windowWidth = window.screen.width;
	if(windowWidth<=992){//man hinh pho bien hien nay
		$(".cssmobile").remove();
	}
	else { // man hinh lon 1922
		$(".csspc").remove();

	}
	callback(true);
}
function get_width_monitor(){
	var windowWidth = window.screen.width;
	return windowWidth;
}
function check_info_login(callback){
	
	Return_get('MemberController','check_get_info_login','','GET',function(data){		
		if(data.result=='-1'){
			callback(false);			
		}
		else{
			callback(true);
		}
		
	});
}
function formatdate(dateObject) {
    var d = new Date(dateObject);
    var day = d.getDate();
    var month = d.getMonth() + 1;
    var year = d.getFullYear();
    if (day < 10) {
        day = "0" + day;
    }
    if (month < 10) {
        month = "0" + month;
    }
    var date = day + "/" + month + "/" + year;

    return date;
};
function append_loading(div){
	var str = '<div class="row" style="text-align:center">';
		str	+='<div class="col-md-12">';
		str	+='<img alt="" src="images/wait_loading.gif" style="height:295px;width:294px;">'
		str	+='</div>';
		str	+='</div>';
	$(div).append(str);	
}
function isValidEmailAddress(emailAddress) {
    var pattern = /^([a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+(\.[a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+)*|"((([ \t]*\r\n)?[ \t]+)?([\x01-\x08\x0b\x0c\x0e-\x1f\x7f\x21\x23-\x5b\x5d-\x7e\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|\\[\x01-\x09\x0b\x0c\x0d-\x7f\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))*(([ \t]*\r\n)?[ \t]+)?")@(([a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.)+([a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.?$/i;
    return pattern.test(emailAddress);
};
function PopupCenter(url, title, w, h) {
    // Fixes dual-screen position                         Most browsers      Firefox
    var dualScreenLeft = window.screenLeft != undefined ? window.screenLeft : screen.left;
    var dualScreenTop = window.screenTop != undefined ? window.screenTop : screen.top;

    width = window.innerWidth ? window.innerWidth : document.documentElement.clientWidth ? document.documentElement.clientWidth : screen.width;
    height = window.innerHeight ? window.innerHeight : document.documentElement.clientHeight ? document.documentElement.clientHeight : screen.height;

    var left = ((width / 2) - (w / 2)) + dualScreenLeft;
    var top = ((height / 2) - (h / 2)) + dualScreenTop;
    var newWindow = window.open(url, title, 'scrollbars=yes, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);

    // Puts focus on the newWindow
    if (window.focus) {
        newWindow.focus();
    }
}
function isValibLengthText(text,leng){
	if(text.length<leng){
		return false;
	}
	return true;
}
function extras_convertbyte_toM(inbyte){
	var tmp_KB=inbyte/1024;
	var tmp_M=tmp_KB/1024;
	return tmp_M;
}
function extras_ValidateSingleInput(sFileName) {
	var _validFileExtensions = [".jpg", ".jpeg", ".bmp", ".gif", ".png"];   
         if (sFileName.length > 0) {
            var blnValid = false;
            for (var j = 0; j < _validFileExtensions.length; j++) {
                var sCurExtension = _validFileExtensions[j];
                if (sFileName.substr(sFileName.length - sCurExtension.length, sCurExtension.length).toLowerCase() == sCurExtension.toLowerCase()) {
                    blnValid = true;
                    break;
                }
            }//endfor
             
            /*if (!blnValid) {
                alert("Sorry, " + sFileName + " is invalid, allowed extensions are: " + _validFileExtensions.join(", "));
                oInput.value = "";
                return false;
            }*/
            if(!blnValid){
            	 return false;
            }
        }else{
        	 return false;
        }
    return true;
}
function extras_ValidatePDF(sFileName) {
	var _validFileExtensions = [".pdf"];   
         if (sFileName.length > 0) {
            var blnValid = false;
            for (var j = 0; j < _validFileExtensions.length; j++) {
                var sCurExtension = _validFileExtensions[j];
                if (sFileName.substr(sFileName.length - sCurExtension.length, sCurExtension.length).toLowerCase() == sCurExtension.toLowerCase()) {
                    blnValid = true;
                    break;
                }
            }//endfor
             
            /*if (!blnValid) {
                alert("Sorry, " + sFileName + " is invalid, allowed extensions are: " + _validFileExtensions.join(", "));
                oInput.value = "";
                return false;
            }*/
            if(!blnValid){
            	 return false;
            }
        }else{
        	 return false;
        }
    return true;
}