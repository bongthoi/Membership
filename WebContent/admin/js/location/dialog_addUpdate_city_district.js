$(function(){
	$('#dialog_ae_city').dialog({
	    title: 'New City ',
	    width: 400,
	    closed: true,
	    cache: false,
	    modal: true,
	    buttons: [{
            text: 'Save',
            iconCls: 'icon-save',
            handler: function() {
            	//var  product_type_id=$("#idchooseproductype").textbox('getValue');
            	var url = extras_Hosting["tomcatSpring_context"]
				+'ad/locationController/add_update_city';
            	$('#fm_city').form('submit',{
            		url: url,
            		onSubmit: function(){
            			return $(this).form('validate');
            		},
            		success: function(result){
            			var data=JSON.parse(result);
            	        $.messager.alert('Info', data.message, 'info');
            	        if(data.f=="0"){
            	        	 $("#dialog_ae_city").dialog('close');
            	        		$('#fm_city').form('clear');
            	        		$('#cc_city').combogrid('grid').datagrid('reload');
            	        		$('#iddatagrip').datagrid('clearChecked');
    							$('#iddatagrip').datagrid('reload');
            	        }
            		}
            	});
            }
        }, {
            text: 'Cancel',
            iconCls: 'icon-cancel',
            handler: function() {
                $("#dialog_ae_city").dialog('close');
            }
        }]
    });
	
	$('#id_btn1_addnew_city').click(function(){
			$('#dialog_ae_city').dialog('open').dialog('center').dialog('setTitle','Add New City');
			$('#fm_city').form('clear');
			$('#fm_city').find('input[name=ptype]').val('A');
			$('#fm_city').find('input[name=cityid]').val('0');
			//$('#idfm_cityname').textbox({readonly:false});
	});
	
	$('#id_btn1_edit_city').click(function(){
		var row=$('#cc_city').combogrid('grid').datagrid('getSelected');
		if(row){
			//alert(row.id+"_"+row.property_name+"_"+row.product_type_sub_id);
			$('#dialog_ae_city').dialog('open').dialog('center').dialog('setTitle','Edit City '+ row.location_id);
			$('#fm_city').form('clear');
			$('#fm_city').find('input[name=ptype]').val('E');
			$('#fm_city').find('input[name=cityid]').val(row.location_id);
			$('#idfm_cityname').textbox('setValue',row.location_name);
		}else{
			$.messager.alert('Warning','Select City please!','warning');
		}
	});
	
	
});