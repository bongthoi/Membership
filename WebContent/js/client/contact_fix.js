$(function() {
	$("#btnrefresh").click(function() {
		create_capchar();
	});

	function create_capchar() {
		var url = ReturnHosing_tomcat();
		url = url + "CapcharController/createcapchar";
		$("#captcha img").attr("src",url + "?" + Math.random()+'&keymap=contact' );
	};
	create_capchar();
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
				        	txtemail: {
				                required: true,
				                email: true,
				            },
				          
				            txtfullname: {
				                required: true
				            },
				            txtphone:{
				            	 required: true
				            },
				            txttitle:{
				            	required: true
				            },
				            txtcapchar:{
				            	required: true
				            }
				        },//end rules
				        messages: {
				        	txtemail:{required: get_lang_injson(textdata,"r16"),
				        					email: get_lang_injson(textdata,"r58")},
							txtfullname:{required: get_lang_injson(textdata,"r17")},	
							txtphone:{required: get_lang_injson(textdata,"r18")},	
							txttitle:{required: get_lang_injson(textdata,"r19")},	
							txtcapchar:{required:  get_lang_injson(textdata,"r15")},	
		                  }//end message
				    };//end obj rules
			 
			 var form = $( "#contactForm" );
				form.validate(objrules);
				$( "#btnsend" ).click(function() {
					var frm_valib=form.valid();
					if(frm_valib){
						 var capchar = $("#txtcapchar").val();
						  var pdata = {
								  'strcapchar': capchar
					            };
						  var check_capchar_r=$.get(ReturnHosing_tomcat()+"CapcharController/check_capchar",
								  						pdata,function(data){},"text");
						  $.when( check_capchar_r).then( function(datacapcha){
							  if(datacapcha==="0"){
								  $("#lberrorcapchar").text('').hide();
								  do_post_contact(textdata);
							  }else if(datacapcha==="-1"){
								  $("#lberrorcapchar").text( get_lang_injson(textdata,"r14")).show();
								   $("#txtcapchar").focus();
							  }
						  });
					}//end if frm_valib
				});//end btn click
		}//end if out
	},"dialog");
	
	function do_post_contact(textdata){
		var email = $("#txtemail").val();
		var fullname = $("#txtfullname").val();
		var phone = $("#txtphone").val();
		var title = $("#txttitle").val();
		var content = $("#txtcontent").val();
		//alert(content);
		var obj={
				'email':email,
				'fullname':fullname,
				'phone':phone,
				'title':title,
				'content':content
		};
		var pdata = {
					'str':JSON.stringify(obj)	
			};
		$.post(ReturnHosing_tomcat()+"ContactController/send_contact",
			pdata,function(data){
			if(data!=null){
				var error=parseInt(data);
				if(error==0){
				
					showdialog('dialogmanual',0,get_lang_injson(textdata,"r11"),'dialog','r11');
					create_capchar();
					clear_content();
				}
				else{
					showdialog('dialogmanual',0,get_lang_injson(textdata,"r12"),'dialog','r12');							
				}
			}
			else{
				showdialog('dialogmanual',0,get_lang_injson(textdata,"r13"),'dialog','r13');
				
			}
			
		},"text");
	}
	
	function clear_content(){
		$("#txtemail").val('');
		$("#txtfullname").val('');
		$("#txtphone").val('');
		$("#txttitle").val('');
		$("#txtcontent").val('');
		$("#txtcapchar").val('');
	
	}
});	