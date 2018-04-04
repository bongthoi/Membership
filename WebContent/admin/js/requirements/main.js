$(function(){
	var title_="Requirememts Manager"
		$('title').text(title_);
	$("#tt").tabs({
		onSelect:function(title,index){
			//$("#idcurrent").val(title);
		},
		onClose:function(title,index){
			//$("#subtypeid_"+title).remove();
		}
	});
	
	
	$('#iddatagrip').datagrid({
		toolbar: '#tbar',
	    url: extras_Hosting["tomcatSpring_context"]+'ad/RequirementsController/get_json_append_to_datagrip.json',
	    idField:'requirement_id',
	    multiSort:true,
	    remoteSort:false,
	    columns:[[
	        {field:'ck',checkbox:true},
	        {field:'buyer_id',title:'Buyer #',align:'left',halign:'center',sortable:true},
	        {field:'requirement_id',title:'ID #',halign:'center',sortable:true},
	        {field:'product_price_limit',title:'Limit Price',align:'right',halign:'center',sortable:true},
	        {field:'quantity',title:'Quantity',align:'right',halign:'center',sortable:true},
	        {field:'budget',title:'Budget',align:'right',halign:'center',sortable:true},
	        {field:'isdelete',title:'Is Delete',align:'center',halign:'center',sortable:true,formatter:extras_formatstatus_datagrid},
	        {field:'isvisible',title:'Is visibled',align:'center',halign:'center',sortable:true,formatter:extras_formatstatus_datagrid}
	    ]],
	    pagination:true
	  
	});
	load_mm_selector(function(out){
		if(out==true){
			$('#ss').searchbox({
			    searcher:function(value,name){
			       // alert(value + "," + name);
			    	  $('#iddatagrip').datagrid('load',{
			    		  	col:name,
			    		  	val:value
			    	    });
			    },
			    menu:'#mm',
			    prompt:'Please Input Value'
			});

		}
	});
	function load_mm_selector(callback){
		$("#mm").html('');   	
		var pdata={'pcdtype':'REQUIREMENTS','pcdname':'SEARCH'};
    	extras_GET_json(true,"ad/AllcodeController","get_allcode_searchbox.json",pdata,function(data){
    		if (data != null) {
	    		var s1 = '';
	            $.map(data, function(item) {
					s1 += '<div data-options="name:' + item.cdval + '">'
					+ item.cdcontent + '</div>';
				});
	        	$('#mm').html(s1);
	        	callback(true);
	       
    		}
    	});
	}
	$("#idadd").click(function(){
		$('#menu_add').click();
		 $('#tt').tabs('select',1);
	});
	
	$("#idedit").click(function(){
		var ids=get_single_row_select();
		if(ids==""){
			$.messager.alert('Warning','Select single row please!','warning');
			return;
		}
		$("#form_Requirements").find("input[name=ptype]").val('E');
		$("#form_Requirements").find("input[name=requirement_id]").val(ids);
		$("#menu_reload").click();
		 $('#tt').tabs('select',1);
		
	});
	$("#idremove").click(function(){
		//extras_viahref("add_demo_crud");
		var ids=get_multi_row_select();
		if(ids==""){
			$.messager.alert('Warning','Select row please!','warning');
			return;
		}
		$.messager.confirm('Confirm','Are you sure you want to delete rows?',function(r){
		    if (r){
		        //alert('ok');
		        extras_POST_json(true,"ad/RequirememtsController","delete_multi_Requirememts",{'str_id':ids},function(data){
		        	$.messager.alert('Result',data.message);
		        	if(data.f=="0"){
		        		$('#iddatagrip').datagrid('clearChecked');
		        		$('#iddatagrip').datagrid('reload');
		        	}
		        });
		        	
		        
		    }
		});
		
	});
	 $("#btnvisible").click(function() {
		 visibled_and_unvisibled(true);
	 });
	 $("#btnunvisible").click(function() {
		 visibled_and_unvisibled(false);
	 });
	
	function visibled_and_unvisibled(visibled){
		 var status_=0;
		 var alert_="";
		 if(visibled){
			 status_=1;
			 alert_="Are you sure you want to visibled this rows?"
			 
		 }else{
			 status_=0;
			 alert_="Are you sure you want to unvisibled this rows?"
		 }
		var ids=get_multi_row_select();
		if(ids==""){
				$.messager.alert('Warning','Select row please!','warning');
				return;
		}
		 $.messager.confirm('Confirm',alert_,function(r){
			    if (r){
			        extras_POST_json(true,"ad/RequirementsController","visivled_requirement",{'str_id':ids,'visible':status_},function(data){
			        	$.messager.alert('Result',data.message);
			        	if(data.f=="0"){
							$('#iddatagrip').datagrid('clearChecked');
							$('#iddatagrip').datagrid('reload');
			        	}
			        });

			    }
			});
	}

	function get_single_row_select(){
		 var checkedItems = $('#iddatagrip').datagrid('getChecked');
		 if(checkedItems.length>0){
			 return checkedItems[0].requirement_id;
		 }
		return "";
	}
	function get_multi_row_select(){
		 var checkedItems = $('#iddatagrip').datagrid('getChecked');
		 if(checkedItems.length>0){
			 var ids = [];
		        $.each(checkedItems, function(index, item){
		        	ids.push("'"+item.requirement_id+"'");
		        });                
		   return ids.join(",");
		 }
		 return ""; 
	}
	
	
});
