$(function() {

    display_profile_admin();	
	
    $("#alogout").click(function() {

        Return_get("Admin_loginController", "logout", {
            'logout': 'LOG_OUT'
        }, 'GET', function(data) {
            if (data.isSessionAdmin === "logout_success") {

                $.session.clear();
                window.location.href = "admin/login.html";
                // alert(data.isSessionAdmin);

            }
        }); //end ajax p
    }); //end logout click    

    function display_profile_admin() {
        Return_get("Admin_loginController", "getSession_admin", "", 'GET', function(data) {
            if (data != null) {
                if (data.isSessionAdmin == "error_getsession_profile_admin") {
					//$.session.set("profile_session_admin", data.isSessionAdmin);
                    //$.session.set("session_lang", data.lang);
                    window.location.href = "admin/login.html";
                } else {

                    $.session.set("profile_session_admin", data.isSessionAdmin);
                    $.session.set("adminemail",data.adminemail);
                   // $.session.set("session_lang", data.lang);
                    //$("#welcomeadmin").html("Chào:"+data.isSessionAdmin);
                    var url_change_pass = "admin/User/changepassword.html?user_id=" + data.isSessionAdmin;
                    $("#url_change_pass_admin").attr("href", url_change_pass);

                }
            }
        }); //end ajax post	 
    }
    $("#getprofile_admin").click(function() {
        //  Return_get("Admin_loginController","getSession_admin","",'GET',function(data){ 
        //   alert(data.isSessionAdmin);                                                     
        // });//end ajax post	 
        alert($.session.get("profile_session_admin") );
    });
    $("#id_lang_vn").click(function() {
        Return_get("Admin_loginController", "change_language", {
            'lang': lang_vn
        }, 'GET', function(data) {
            if (data != null) {

               // $.session.set("session_lang", data.lang);
                location.reload();
                // alert(data.isSessionAdmin);
            }
        }); //end ajax p
    });
    $("#id_lang_cn").click(function() {
        Return_get("Admin_loginController", "change_language", {
            'lang': lang_cn
        }, 'GET', function(data) {
            if (data != null) {

                $.session.set("session_lang", data.lang);
                location.reload();
                // alert(data.isSessionAdmin);
            }
        }); //end ajax p
    });

});