
$(function(){	
	$('#dg').datagrid({
	  		//url: extras_Hosting["tomcatSpring_context"]+'ad/branchController/get_datagrip_byproducttypeid.json?product_type_id=0401',
			title:'List city ',
			toolbar: '#tbar_tab2',
		 	singleSelect:true,
		    idField:'location_id',
		    remoteSort:false,
		    height:500,
		    columns:[[
		              {field:'location_id',title:'ID#',width:150,halign:'center',sortable:true},
		      	        {field:'location_name',title:' city Name',width:180,align:'left',halign:'center',sortable:true},
		      	        {field:'isvisible',title:'Is visibled',width:100,align:'center',halign:'center',sortable:true,formatter:extras_formatstatus_datagrid}
				    ]],
		pagination:true,
	    view: detailview,
	    detailFormatter:function(index,row){
	        return '<div class="ddv"></div>';
	    },
	    onExpandRow: function(index,row){
	    	    close_collapse(index);
	    	  	var ddv = $(this).datagrid('getRowDetail',index).find('div.ddv');
	        	$.get(extras_Hosting["apache"] +"admin/views/Location/detail_edit_grid.html",function(data){
	    				//data=data.replace(/@id/g,id);
	        		   if(row.isNewRecord){
	        			   data=data.replace(/@locationid_value/g,'0');
	        			   data=data.replace(/@locationname_value/g,'');
	        			
	        		   }else{
	        			   data=data.replace(/@locationid_value/g,row.location_id);
	        			   data=data.replace(/@locationname_value/g,row.location_name);
	        		   }
	        		   ddv.panel({
	       	            border:false,
	       	            cache:true,
	       	            content:data,
	       	            onLoad:function(){
	       	                $('#dg').datagrid('fixDetailRowHeight',index);
	       	                $('#dg').datagrid('selectRow',index);
	       	                //$('#dg').datagrid('getRowDetail',index).find('form').form('load',row);
	       	            }
	       	        });
	       	        $('#dg').datagrid('fixDetailRowHeight',index);
	        	});
	    }
	});
	$("#idappend_tab2").click(function(){
		var city= $("#cc_city").combogrid('getValue');
		if(city==""){
			  $.messager.alert('Warning', 'Please select city!!!', 'warning');
			  return false;
		}
		$('#dg').datagrid('appendRow',{isNewRecord:true});
		var index = $('#dg').datagrid('getRows').length - 1;
		$('#dg').datagrid('expandRow', index);
		$('#dg').datagrid('selectRow', index);
	});
	$("#idremove_tab2").click(function(){
		var city= $("#cc_city").combogrid('getValue');
		if(city==""){
			  $.messager.alert('Warning', 'Please select city!!!', 'warning');
			  return false;
		}
		var row = $('#dg').datagrid('getSelected');
		var index = $('#dg').datagrid('getRowIndex',row);
		if(row.isNewRecord){
			$('#dg').datagrid('clearChecked');
			$('#dg').datagrid('deleteRow',index);
		}else{
			var location_id=row.location_id;
			do_delete_grid_tab2(location_id,index);
			
		}
	});
	function do_delete_grid_tab2(location_id,index_row){
		if(location_id==""){
			$.messager.alert('Warning','Select District please!','warning');
			return;
		}
		$.messager.confirm('Confirm','Are you sure you want to delete record?',function(r){
		    if (r){
		    	var phuong="'"+location_id+"'";
		    	var pdata={"strthanhpho":"","strquan":"","strphuong":phuong};
		    	//var url= extras_Hosting["tomcatSpring_context"]+"ad/locationController/delete_multi_location";
		    	 extras_POST_json(true,"ad/locationController","delete_multi_location",pdata,function(data){
			        	$.messager.alert('Result',data.message);
			        	if(data.f=="0"){
			        		$('#dg').datagrid('clearChecked');
			    			$('#dg').datagrid('deleteRow',index_row);
			        	}
			        });     
		    	}
		});
	}
	function close_collapse(current_index){
		var allrow=$('#dg').datagrid('getRows');
		for(var i=0;i<allrow.length;i++){
			var index = $('#dg').datagrid('getRowIndex',allrow[i]);
			if(index==current_index){
				$('#dg').datagrid('expandRow', current_index);
				$('#dg').datagrid('selectRow', current_index);
			}else{
				$('#dg').datagrid('collapseRow',index);
			}
		}
	}
});