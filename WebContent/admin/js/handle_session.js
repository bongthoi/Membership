$(document).ajaxError(function(e,jqXHR,ajaxSettings,thrownError) {
    // if 403 - check if user still has active session - if not redirect to login page
    if(jqXHR.status == '403'){
    	 alert("会话超时或访问被拒绝！重定向到登! 重定向到登录页面.");
    	 window.location.href = 'admin/login.html';
    }
    else{
        //notification('error','Error Encountered',thrownError+' (HTTP CODE: '+jqXHR.status+')'+ajaxSettings.url);
        console.log('Error Encountered',thrownError+' (HTTP CODE: '+jqXHR.status+')'+ajaxSettings.url);
        console.log(e,'event');
        console.log(jqXHR,'xhr response');
        console.log(ajaxSettings,'$.ajax settings');
    }
});