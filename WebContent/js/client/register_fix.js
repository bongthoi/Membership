$(function(){
	create_capchar_r();
	$('#distpicker_e').distpicker({
		  province: '---- 所在省 ----',
		  city: '---- 所在市 ----',
		  district: '---- 所在区 ----',
		  autoselect:'3'
		});
	$("#btnrefresh_r").click(function() {
		create_capchar_r();
	});	
	function create_capchar_r() {
		var url = ReturnHosing_tomcat();
		url = url + "CapcharController/createcapchar_register";
		$("#captcha_r img").attr("src", url  + "?" + Math.random()+'&keymap=register'  );
	};
	function get_lang_injson(textdata,key){
		for(var j=0;j<textdata.length;j++){				
			if(textdata[j].data_key==key){
				return textdata[j].text;
			}
		}
	}
	
	get_file(function(out,textdata){
		if(out){
			 var objrules = {
					 invalidHandler: function(form, validator) {
					        var errors = validator.numberOfInvalids();
					        if (errors) {                    
					            validator.errorList[0].element.focus();
					        }
					    } ,
				        rules : {
				        	txtemail_regis: {
				                required: true,
				                email: true,
				            },
				            txtpassword1_regis: {
				                required: true,
				                minlength: 6
				            },
				            txtpassword2_regis: {
				                required: true,
				                equalTo:"#txtpassword1_regis"
				            },
				            txtshopname_regis: {
				                required: true
				            },
				            txt_address_regis:{
				            	 required: true
				            },
				            txtphone_regis:{
				            	required: true
				            },
				            txtcapchar_regis:{
				            	required: true
				            }
				        },//end rules
				        messages: {
				        	txtemail_regis:{required: get_lang_injson(textdata,"r36"),
				        					email: get_lang_injson(textdata,"r58")},
				        	txtpassword1_regis:{required:  get_lang_injson(textdata,"r37"),
					        				minlength: get_lang_injson(textdata,"r53")},
					        txtpassword2_regis:{required:  get_lang_injson(textdata,"r38"),
											equalTo: get_lang_injson(textdata,"r39")}	,
							txtshopname_regis:{required: get_lang_injson(textdata,"r40")},	
							txtphone_regis:{required: get_lang_injson(textdata,"r36")},	
							txt_address_regis:{required: get_lang_injson(textdata,"r42")},	
							txtcapchar_regis:{required:  get_lang_injson(textdata,"r52")},	
		                  }//end message
				    };//end obj rules
			 var form = $( "#frm-regiterfix" );
				form.validate(objrules);
				$( "#btnregister" ).click(function() {
					var frm_valib=form.valid();
					if(frm_valib&&c_all_dispicker(textdata)){
						 var capchar = $("#txtcapchar_regis").val();
						  var pdata = {
					                'strcapchar': capchar,
					                'captcharegister':'register'
					            };
						  var check_capchar_r=$.get(ReturnHosing_tomcat()+"CapcharController/check_capchar_register",
								  						pdata,function(data){},"text");
						  $.when( check_capchar_r).then( function(datacapcha){
							
							  if(datacapcha==="0"){
								  $("#txtcapchar_regis-error").text('').hide();
								  do_post_register(textdata);
							  }else if(datacapcha==="-1"){
								  $("#txtcapchar_regis-error").text( get_lang_injson(textdata,"r51")).show();
								   $("#txtcapchar_regis").focus();
							  }
						  });
					}//end if frm_valib
				});
		}//end if out
	},"dialog");
	
	
	function c_all_dispicker(textdata){
		if(!c_provice(textdata)||!c_city(textdata)||!c_district(textdata)){
    		return false;
    	}
    	return true;  	
	}
	function c_provice(textdata){
		var provice=$("#distpicker_e").find('#slprovince').val();
		if(provice.trim()==""){
    		$("#lbdistpicker_e-error").text(get_lang_injson(textdata,"r54")).show();  
    		return false;
    	}else{$("#lbdistpicker_e-error").text('').hide();return true;}
	}
	function c_city(textdata){
		var city=$("#distpicker_e").find('#slcity').val();
		if(city.trim()==""){
    		$("#lbdistpicker_e-error").text(get_lang_injson(textdata,"r55")).show(); 
    		return false;}else{$("#lbdistpicker_e-error").text('').hide();return true;}
	}
	function c_district(textdata){
		var district=$("#distpicker_e").find('#sldistrict').val();
		if(district.trim()==""){
    		$("#lbdistpicker_e-error").text(get_lang_injson(textdata,"r56")).show();
    		return false;
    	}else{$("#lbdistpicker_e-error").text('').hide();return true;}
	}
	function do_post_register(textdata){
		var email=$("#txtemail_regis").val();
    	var pass1=$("#txtpassword1_regis").val();
    	var type = '001';// SELLER
    	var shopname=$("#txtshopname_regis").val();
    	var nation=$("#nation").val();
    	var address = $("#txt_address_regis").val();
    	var sdt=$("#txtphone_regis").val();    
    	var pro = $("#txt_pro").val();
    	var dist = $("#txt_dist").val();
    	var license = $("#id_upload_success_license").val();
    	var lang = get_lang_current();
    	var provice=$("#distpicker_e").find('#slprovince').val();
    	var city=$("#distpicker_e").find('#slcity').val();
    	var district=$("#distpicker_e").find('#sldistrict').val();
		var obj={
				'email':email,
				'pass':pass1,
				'type':type,
				'shop_name':shopname,
				'nation':nation,
				'address':address,
				'sdt':sdt,
				'license':license,
				'lang':lang,
				'city':city,
				'provice':provice,
				'district':district
				
		};
		var pdata = {
					'str':JSON.stringify(obj)	
			};
		var urlpost=ReturnHosing_tomcat()+"CustomerController/add_custome";
		blockbg();
		$.post(urlpost,pdata,function(data){
			unblockbg();
			if(data!=null){
				var error=data.result;
				console.log(error);
				if(error==='0'){
						showdialog('dialogmanual',0,get_lang_injson(textdata,"r34"),'dialog','r34');
					create_capchar_r();
					clear_content_r();
				}else if(error==="27"){	
						showdialog('dialogmanual',0,get_lang_injson(textdata,"r57"),'dialog','r57');
				}
				else{
						showdialog('dialogmanual',0,get_lang_injson(textdata,"r34"),'dialog','r34');			
				}
			}
			else{
					$("#erorr_register").text(get_lang_injson(textdata,"r35"));			
					showdialog('dialogmanual',0,get_lang_injson(textdata,"r35"),'dialog','r35');
			}
		},'json');//end p[ost
	}

	function clear_content_r(){
		$("#txtemail_regis").val('');
    	$("#txtpassword1_regis").val('');
    	$("#txtpassword2_regis").val('');
    	$("#txtshopname_regis").val('');    	
    	$("#txtphone_regis").val('');    	
    	$("#txt_year_es_regis").val('');
    	$("#txt_total_employ_regis").val('');
    	$("#txt_total_revenue_regis").val('');
    	$("#txtcompany_regis").val('');
    	$("#id_upload_success_picture_company").val('');
    	$("#id_upload_success_certificate").val('');
    	$("#id_upload_success_license").val('');
    	$("#txt_address_regis").val('');
    	$("#id_upload_success_license_strong_message").text('');
    	$("#txtcapchar_regis").val('');
		//create_capchar_r();		
	}
});