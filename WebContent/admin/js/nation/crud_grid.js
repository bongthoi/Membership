
$(function(){	
	$('#dg').datagrid({
	  		url: extras_Hosting["tomcatSpring_context"]+'ad/NationController/get_allnation',
			toolbar: '#tbar',
		    idField:'nation_id',
		 	singleSelect:true,
		    remoteSort:false,
		    method:'get',
		    columns:[[
		              {field:'ck',checkbox:true},
				        {field:'nation_id',title:'ID#',width:100,halign:'center',align:'left',sortable:true},
				        {field:'nation_name',title:'Nation Name',width:150,halign:'center',align:'left',sortable:true},
				        {field:'lang_code',title:'Lang Code',width:150,halign:'center',align:'left',sortable:true},
				        {field:'isvisible',title:'Is Visibled',width:150,halign:'center',align:'center',sortable:true,formatter:extras_formatstatus_datagrid},
				        {field:'isdelete',title:'Is Delete',width:150,halign:'center',align:'center',sortable:true,formatter:extras_formatstatus_datagrid}
				    ]],
	    view: detailview,
	    detailFormatter:function(index,row){
	        return '<div class="ddv"></div>';
	    },
	    onExpandRow: function(index,row){
	    	    close_collapse(index);
	    	  	var ddv = $(this).datagrid('getRowDetail',index).find('div.ddv');
	        	$.get(extras_Hosting["apache"] +"admin/views/Nation/detail_edit_grid.html",function(data){
	    				//data=data.replace(/@id/g,id);
	        		   if(row.isNewRecord){
	        			   data=data.replace(/@nation_id_value/g,'0');
	        			   data=data.replace(/@nation_name_value/g,'');
	        			   data=data.replace(/@lang_code_value/g,'');
	        		   }else{
	        			   data=data.replace(/@nation_id_value/g,row.nation_id);
	        			   data=data.replace(/@nation_name_value/g,row.nation_name);
	        			   data=data.replace(/@lang_code_value/g,row.lang_code);
	        			
	        		   }
	        		   ddv.panel({
	       	            border:false,
	       	            cache:true,
	       	            content:data,
	       	            onLoad:function(){
	       	                $('#dg').datagrid('fixDetailRowHeight',index);
	       	                $('#dg').datagrid('fixRowHeight',index);
	       	                $('#dg').datagrid('selectRow',index);
	       	                //$('#dg').datagrid('getRowDetail',index).find('form').form('load',row);
	       	            }
	       	        });
	       	        $('#dg').datagrid('fixDetailRowHeight',index);
	       	        $('#dg').datagrid('fixRowHeight',index);
	        	});
	    }
	});
	
	
	$("#idappend").click(function(){
		$('#dg').datagrid('appendRow',{isNewRecord:true});
		var index = $('#dg').datagrid('getRows').length - 1;
		$('#dg').datagrid('expandRow', index);
		$('#dg').datagrid('selectRow', index);
	});
	$("#idremove").click(function(){
		var row = $('#dg').datagrid('getSelected');
		if(row){
			var index = $('#dg').datagrid('getRowIndex',row);
			if(row.isNewRecord){
				$('#dg').datagrid('clearChecked');
				$('#dg').datagrid('deleteRow',index);
			}else{
				do_delete_grid_tab2(row.nation_id,index);
				
			}
		}else{
			$.messager.alert('Warning','Select Nation please!','warning');
		}
	
	});
	function do_delete_grid_tab2(Nationid,index_row){
		if(Nationid==""){
			$.messager.alert('Warning','Select Nation please!','warning');
			return;
		}
		$.messager.confirm('Confirm','Are you sure you want to delete record?',function(r){
		    if (r){
		        extras_POST_json(true,"ad/NationController","detele_single_nation",{'str_id':Nationid},function(data){
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