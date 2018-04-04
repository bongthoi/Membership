


var extras_Hosting = [];
extras_Hosting["tomcat"] = 'http://'+window.location.hostname+':8080/Nvmall/';
extras_Hosting["tomcatSpring_context"] = 'http://'+window.location.hostname+':8080/Nvmall/spring/';
extras_Hosting["apache"] = 'http://'+window.location.hostname+':8080/Nvmall/';
extras_Hosting["service"] = 'http://'+window.location.hostname+':8080/Nvmall/';
extras_Hosting["other_hosting"]='';
var extra_views= {
	 index: [{
            selector: "#main_body_content",
            templateUrl: 'admin/views/index_inner.html'
        }
    ],
    member: [{
        selector: "#main_body_content",
        templateUrl: 'admin/views/member_inner.html'
    	}
    ],  
    democrud: [{
        selector: "#main_body_content",
        templateUrl: 'admin/views/democrud_inner.html'
    	}
    ],
    add_demo_crud: [{
        selector: "#main_body_content",
        templateUrl: 'admin/views/add_demo_crud.html'
    	}
    ],
    about: [{
            selector: "#main_body_content",
            templateUrl: 'admin/views/about_inner.html'
        }
    ],
    contact: [{
            selector: "#main_body_content",
            templateUrl: 'admin/views/contact_inner.html'
        // }, {
        //     selector: "#content",
        //     templateUrl: 'views/contact-content.php'
        },
    ],
    
    catgorymanager: [{
        selector: "#main_body_content",
        templateUrl: 'admin/views/catgory_inner.html'
    	}
    ],
    defaultView: {
        //view: 'index'
    }
};



function extras_GET_json(isloading,controller,action,pdata,callback){
	var purl=extras_Hosting["tomcatSpring_context"]+""+controller+"/"+action;
	jQuery.ajax({
			global: isloading,
		    url: purl,
		    type: "GET",
		    dataType: "json",
		    data:pdata,
		    success: function(data) {
		        callback(data);
		     },
		    error: function(x, t, m) {
		    	console.log("GET "+purl +"error:" +m);
		    	callback("");
		    }
		});
}

function extras_POST_json(isloading,controller,action,pdata,callback){
	var purl=extras_Hosting["tomcatSpring_context"]+""+controller+"/"+action;
	jQuery.ajax({
			global: isloading,
		    url: purl,
		    type: "POST",
		    dataType: "json",
		    data:pdata,
		    success: function(data) {
		        callback(data);
		     },
		    error: function(x, t, m) {
		    	console.log("POST "+purl +"error:" +m);
		    	callback("");
		    }
		});
}

function extras_viahref(viahref){
	
	var atvihref=document.createAttribute("via-href");
	atvihref.value = viahref;  
	
	var  a = document.createElement("a");
		a.setAttributeNode(atvihref);
        document.body.appendChild(a);
        a.click();
        document.body.removeChild(a);
}

function ReturnHosing(){
	var url =extras_Hosting["tomcatSpring_context"];
	return url;
}
function ReturnHostingCMS(){
	var url =extras_Hosting["tomcat"] +'/admin/';
	return url;
}
function ReturnHosing_apache(){
	var url =extras_Hosting["apache"] ;
	return url;
}
function ReturnHosing_service_w3pl(){
	var url ='http://'+window.location.hostname+':8080/WS3PL/';
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
	purl = ReturnHosing()+pcontroller+"/"+pmethod;
	
	$.ajax({
		url : purl,
		headers : {
			'Accept' : 'application/json',
			'Content-Type': 'application/json,charset=UTF-8'				
		},
		type:ptype,
		dataType : 'json',
		data:pdata,			
		success : function(data) {	
			
			callback(data);
		}
	});
}
function _post(pcontroller,pmethod,pdata,ptype,callback){
	var purl = ReturnHosing()+pcontroller+"/"+pmethod;	
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
	        alert("Exception:"+er);
	    }
	});
}


function getUrlParameter_VIA(sParam)
{
	
    var sPageURL = window.location.hash.substring(1);   
    var sURLVariables = sPageURL.split('?');
    if(sURLVariables.length<2){
    	return "";
    }
    
    
    sURLVariables = sURLVariables[1].split('&');
    for (var i = 0; i < sURLVariables.length; i++) 
    {
    	
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam) 
        {
            return sParameterName[1];
        }
    }
}

function getUrlParameter(sParam)
{
	  var sPageURL = window.location.hash.substring(1);   
	    var sURLVariables = sPageURL.split('?');
	    if(sURLVariables.length<2){
	    	return "";
	    }
	    
	    
	    sURLVariables = sURLVariables[1].split('&');
	    for (var i = 0; i < sURLVariables.length; i++) 
	    {
	    	
	        var sParameterName = sURLVariables[i].split('=');
	        if (sParameterName[0] == sParam) 
	        {
	            return sParameterName[1];
	        }
	    }
}
var check_menu_load  = true;
function get_check_load(){
	return check_menu_load;
}
function exe_load_header(callback){
	var urlhead=ReturnHostingCMS()+"Header.html";
	$("#header").load(urlhead, function (responseText, textStatus, XMLHttpRequest) {			
		callback(true);		
	});	
}
function get_height(){
	var height = $(window).height();
	height =height-300;
	return height;
}
function get_width(){
	var width = $(window).width();
	width = width-60;
	return width;
}
function myformatter(date){
	
	try{
		var y = date.getFullYear();
		var m = date.getMonth()+1;
		var d = date.getDate();
		return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);	
	}
	catch(ex){
		return date;
	}
	
}
function myparser(s){
	if (!s) return new Date();
	var ss = (s.split('-'));
	var y = parseInt(ss[0],10);
	var m = parseInt(ss[1],10);
	var d = parseInt(ss[2],10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
		return new Date(y,m-1,d);
	} else {
		return new Date();
	}
}
function get_lang_current(){
	//alert('EN');
	var lang =  $.session.get("curlange");
	if(lang==null||lang==undefined||lang==''){
	   return "EN";
	}
    else
        return lang;
}
function change_lang_vn(){	
    $.session.set("curlange","VN");
    location.reload();
}
function change_lang_en(){	

                        $.session.set("curlange","EN");
                        location.reload();           	 
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
function formatedate(dateObject) {
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