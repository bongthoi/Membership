
$(function(){
	$('#cc_nation').combogrid({
		 url: extras_Hosting["tomcatSpring_context"]+'ad/NationController/get_allnation',
		  method: 'get',
        panelWidth: 600,				      
        idField: 'lang_code',
        textField: 'nation_name',
        editable:false,
        columns: [[
                   {field:'nation_id',title:'ID#',width:100,halign:'center',align:'left',sortable:true},
				        {field:'nation_name',title:'Nation Name',width:150,halign:'center',align:'left',sortable:true},
				        {field:'lang_code',title:'Lang Code',width:150,halign:'center',align:'left',sortable:true},
				        {field:'isvisible',title:'Is Visibled',width:150,halign:'center',align:'center',sortable:true,formatter:extras_formatstatus_datagrid}
         ]],
        /* onSelect:function(index,row){
        	/* var parentid=row.id;
        	 var url_property_sub_context= extras_Hosting["tomcatSpring_context"]+'ad/propertyController/get_datagrip_byparentid.json?parentid='+parentid;
        	 $('#dg').datagrid('clearChecked');
        	 $('#dg').datagrid({url:url_property_sub_context});
        	 alert("onselect");
        
        	 $(this).combogrid('setValue', '');  
         },*/
        
         onChange: function(newValue,oldValue){
        	/* var cityid = $("#cc_catgorynews").combogrid('getValue');
        	 $("#cc_district").combogrid('clear');
        	 if(cityid!=""&&cityid!=null){
        	 var url_get_district= extras_Hosting["tomcatSpring_context"]+'ad/locationController/get_district_bycity?city='+cityid;
        	  $("#cc_district").combogrid({url:url_get_district});
   
        	 }else{
        		  $("#cc_district").combogrid("grid").datagrid("loadData",[]);		
        	 }*/
 
         }
    });
	
	$('#dialog_product_img').dialog({
	    title: '上传图片',
	    width: 400,
	    closed: true,
	    cache: false,
	    modal: true,
	    buttons: [{
            text: '关闭',
            iconCls: 'icon-cancel',
            handler: function() {
                $("#dialog_product_img").dialog('close');
            }
        }]
    });
	$('#id_btn1_view_img').click(function(){
		$('#dialog_product_img').dialog('open').dialog('center');
		//$('#dialog_product_img').find('img').attr('src','upload/no_image.jpg');
		//$('#idfm_catgorynewsname').textbox({readonly:false});
	});
	
	$('input[name="donewsimg"]').ajaxfileupload({
	    'action':  extras_Hosting["tomcatSpring_context"]+"ad/UploadController/upload_image_normal_withparam?folder=save_image_product",
	    'valid_extensions' :  ["jpg", "jpeg", "bmp", "gif", "png"],
	    'onComplete': function(response) {	
	        if (response.status==false) {
	        	$.messager.alert('错误',response.message,'error');
	        }
	        if (response.status==true) {
	        	//$.messager.alert('Info',response.message,'info');
	            var pram = response.pram;
	            if(extras_ValidateSingleInput(pram)){
	            	$('#dialog_product_img').find('#idproductimg').val(pram);
	 	            $('#dialog_product_img').find('img').attr('src',extras_Hosting["tomcat"]+'upload/product/'+pram);
	 	            $("#dialog_product_img").dialog('close');
	 	            $.messager.alert('通知',"您的图片已上传成功!",'info');
	            }else{
	            	$.messager.alert('错误',"上传失败！!",'error');
	            }
	           
	        }

	    },
	    'onStart': function() {
	    	var fileSize = this.get(0).files[0].size;
	     	if(fileSize>1024*1000){
	     		$.messager.alert('注意','该档案已超过允许尺寸，请选择其他图片！','warning');
	     		 //$("#idfm_brandimg").val(pram);
	     		return false;
	     	}
	    }
	});
	
	
});