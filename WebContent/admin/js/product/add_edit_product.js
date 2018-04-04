$(function(){
	$("#menu_add").click(function(){
	
  		$("#my_editor_product").val("");
  		$("#idproduct_name").data('ptype','A').data('productid','').textbox('setValue','');
  		$('#cc_nation').combogrid('setValue',''); 
  		$("#idprice").numberbox('setValue','');
  		$('#dialog_product_img').find('#idproductimg').val('');
  		$('#dialog_product_img').find('img').attr('src',extras_Hosting["tomcat"]+'upload/no_image.jpg');
	});
	$("#menu_reload").click(function(){
		 load_content_and_set_it_to_MCE();
	});
	
	$("#menu_save").click(function(){
		save_content_toDB_MCE();
	});
	
	
	
	/*tinymce.remove();
	tinymce.init({
	    //selector: '#my_editor_question,#my_editor_guide_buy,#my_editor_smart_buy,#my_editor_safe_buy,#my_editor_pay_method',
		//mode : "exact",
		selector:'#my_editor_product',
	    height : 350,
	    menubar: 'file edit insert view format table tools',
	    setup: function(editor) {

	      },
	   menu: {
	        file: {title: 'File', items: 'newdocument'},
	        edit: {title: 'Edit', items: 'undo redo | cut copy paste pastetext | selectall'},
	        insert: {title: 'Insert', items: 'link media | template hr'},
	        view: {title: 'View', items: 'visualaid'},
	        format: {title: 'Format', items: 'bold italic underline strikethrough superscript subscript | formats | removeformat'},
	        table: {title: 'Table', items: 'inserttable tableprops deletetable | cell row column'},
	        tools: {title: 'Tools', items: 'spellchecker code'}
	      },
	    toolbar: 'undo redo | bold italic | fontselect fontsizeselect | forecolor backcolor |alignleft aligncenter alignright  alignjustify | bullist numlist indent outdent | link unlink image',
	    plugins: ["image","link","textcolor","code","charmap","searchreplace","visualblocks","preview","fullscreen","table","lists"],
	    file_browser_callback_types: 'image',
	    file_browser_callback: function(field_name, url, type, win) {
	        if(type=='image'){
	        	$('#my_form input').click();
	        }
	    }
	});*/
	function load_content_and_set_it_to_MCE(){
		var ptype=$("#idproduct_name").data('ptype');
		var productid=$("#idproduct_name").data('productid');
		if(ptype=="A"){
			return;
		}
		if(productid==""){
			return;
		}
		extras_GET_json(true,"ad/ProductController","get_Productbyid",{'product_id':productid},function(data){
			if(data !== null && typeof data === "object"){
				$("#my_editor_product").val(data.product_description);			
				$('#cc_nation').combogrid('setValue',data.lang_code); 
				$("#idproduct_name").textbox('setValue',data.product_name);
				$("#idprice").numberbox('setValue',data.product_price);
				$('#dialog_product_img').find('#idproductimg').val(data.product_image);
				 $('#dialog_product_img').find('img').attr('src',extras_Hosting["tomcat"]+'upload/product/'+data.product_image);
			}
		});
	}
	function save_content_toDB_MCE(){
					if(check_befor_save()){
						var _mes_confirm="您是否确定保存该产品?";
						var ptype=$("#idproduct_name").data('ptype');
						var productid=$("#idproduct_name").data('productid');
						if(ptype=="E"){
							_mes_confirm="修改 '"+ productid +"', 你是否确定?"
						}
			 $.messager.confirm('确认',_mes_confirm,function(r){
				    if (r){
				    	var lang_code='CN'
						var product_name=$("#idproduct_name").textbox('getValue');
						var product_price=$("#idprice").numberbox('getValue');
						var product_image=$('#dialog_product_img').find('#idproductimg').val();
						//if(product_image.length>0){
							//product_image=extras_Hosting["tomcat"]+"upload/product/"+product_image;
							
						//}
						var product_description=$("#my_editor_product").val();
						var pdata={'ptype':ptype,
								'product_id':productid,
								'product_name':product_name,
								'product_price':product_price,
								'product_image':product_image,
								'product_description':product_description,
								'lang_code':lang_code};
						extras_POST_json(true,"ad/ProductController","SaveProduct",pdata,function(data){
							if(data !== null && typeof data === "object"){
								if(data.f=="0"){
									$("#idproduct_name").data('ptype','E');
									$("#idproduct_name").data('productid',data.out_id);
									$('#iddatagrip').datagrid('clearChecked');
					        		$('#iddatagrip').datagrid('reload');
									$.messager.alert('结果',"保存成功!",'信息');
					        	}else{	
					        		$.messager.alert('结果',data.message,'信息');
					        	}
							}
						});
				    }
				});//end $.messager
		}//end check
		
	}
	function check_befor_save(){
//		var lang_code=$('#cc_nation').combogrid('getValue'); 
//		if(lang_code==null || lang_code==""){
//			$.messager.alert('注意','请选择语言','注意');
//			return false;
//		}
		if(!$("#idproduct_name").textbox('isValid')){
			$("#idproduct_name").next().find('input').focus();

			return false;
		}
		if(!$("#idprice").numberbox('isValid')){
			$("#idprice").next().find('input').focus();
			return false;
		}
		return true;
		//var title_item=$("#idproduct_name").textbox('getValue');
		//var shortdescription_item=$("#idprice").textbox('getValue');
	}
	$('input[name="txtimage"]').ajaxfileupload({
		'valid_extensions' : ['gif','png','jpg','jpeg'],
	    'action':  extras_Hosting["tomcatSpring_context"]+"ad/UploadController/upload_image_normal_withparam?folder=saveeditor",
	    'onComplete': function(response) {	
	        if (response.status==false) {
	        	$.messager.alert('错误',response.message,'error');
	        	 //$("#strongmessage").html("<font color='red'>" + JSON.stringify(response.message) + " </font>");
	        }
	        if (response.status==true) {
	        
	            var pram = response.pram;
	            var url_img=  extras_Hosting["path_img_editor"]+pram;
	            top.$('.mce-btn.mce-open').parent().find('.mce-textbox').val(url_img);
	         
	        }
	    },
	    'onStart': function() {
	    	var fileSize = this.get(0).files[0].size;
	     	if(fileSize>1024*1000){
	     		$.messager.alert('注意','该图片已超过允许尺寸，请选择其他图片!!','注意');
	     		//$("#id_uploadsuccess").val('');
	     		return false;
	     	}
	    }
	});
	
});