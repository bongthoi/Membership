$(function(){
	$("#menu_add").click(function(){
		$('#form_Requirements').form('reset');
		$("#form_Requirements").find("input[name=ptype]").val('A');
		$("#form_Requirements").find("input[name=requirement_id]").val('0');
	});
	$("#menu_reload").click(function(){
		var ptype=$("#form_Requirements").find("input[name=ptype]").val();
		var req_id=$("#form_Requirements").find("input[name=requirement_id]").val();
		if(ptype=="A"){
			return;
		}
		if(req_id=="0"){
			return;
		}
		extras_GET_json(true,"ad/RequirementsController","get_Requirementsbyid",{'requirement_id':req_id},function(data){
			if(data !== null && typeof data === "object"){
				$("#form_Requirements").find("input[name=ptype]").val('E');
				$("#form_Requirements").find("input[name=requirement_id]").val(data.requirement_id);
				$("#idproduct_price_limit").numberbox('setValue',data.product_price_limit);
				$("#idquantity").numberbox('setValue',data.quantity);
				$("#idbudget").numberbox('setValue',data.budget);
				$("#my_editor_description").val(data.description);
				$('#dialog_product_img').find('#idproductimg').val(data.product_image);
				 $('#dialog_product_img').find('img').attr('src',extras_Hosting["tomcat"]+'upload/product/'+data.product_image);
			}
		});
	});
	$("#menu_save").click(function(){
		var url=extras_Hosting["tomcatSpring_context"]+"ad/RequirementsController/SaveRequirements";
		var _mes_confirm="Save this Requirements ,Are you sure?";
		var ptype=$("#form_Requirements").find("input[name=ptype]").val();
		var req_id=$("#form_Requirements").find("input[name=requirement_id]").val();
		if(ptype=="E"){
			_mes_confirm="Modify this '"+ req_id +"',Are you sure?"
		}
		$.messager.confirm('Confirm',_mes_confirm,function(r){
			if(r==true){
				$("#form_Requirements").form('submit',{
					url: url,
					onSubmit: function(){ 
						console.log( $( this ).serialize() );
							return $(this).form('validate');
					},
					success: function(result){
						if(result!=null&&result!=""){
							var data=JSON.parse(result);
							if(data.f=="0"){
								$("#form_Requirements").find("input[name=ptype]").val('E');
								$("#form_Requirements").find("input[name=requirement_id]").val(data.out_id);
								$.messager.alert('Result',"Save successfull!",'info');
				        	}else{	
				        		$.messager.alert('Result',data.message,'info');
				        	}
						}
					}
				});
			}
		});
	});
	
	
	$('#dialog_Requirements_img').dialog({
	    title: 'Product Image ',
	    width: 400,
	    closed: true,
	    cache: false,
	    modal: true,
	    buttons: [{
            text: 'Close',
            iconCls: 'icon-cancel',
            handler: function() {
                $("#dialog_Requirements_img").dialog('close');
            }
        }]
    });
	$('#id_btn1_view_img').click(function(){
		$('#dialog_Requirements_img').dialog('open').dialog('center');

	});
	
	$('input[name="donewsimg"]').ajaxfileupload({
	    'action':  extras_Hosting["tomcatSpring_context"]+"ad/UploadController/upload_image_normal_withparam?folder=save_image_product",
	    'onComplete': function(response) {	
	        if (response.status==false) {
	        	$.messager.alert('Error',response.message,'error');
	        }
	        if (response.status==true) {
	        	//$.messager.alert('Info',response.message,'info');
	            var pram = response.pram;
	            $('#form_Requirements').find('#idRequirementsimg').val(pram);
	            $('#dialog_Requirements_img').find('img').attr('src',extras_Hosting["tomcat"]+'upload/product/'+pram);
	        }

	    },
	    'onStart': function() {
	    	var fileSize = this.get(0).files[0].size;
	     	if(fileSize>1024*1000){
	     		$.messager.alert('Warning','File này có kích cỡ lớn hơn cho phép. Xin chọn hình ảnh khác!!','warning');
	     		 //$("#idfm_brandimg").val(pram);
	     		return false;
	     	}
	    }
	});
	
});