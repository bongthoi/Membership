$(function(){
	var title_="Location Manager"
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
		  title:'City',
		toolbar: '#tbar',
	    url: extras_Hosting["tomcatSpring_context"]+'ad/locationController/get_json_append_to_datagrip.json',
	    height:650,
	    rownumbers:true,
	    autoRowHeight:true,
	    view: detailview,
        detailFormatter:function(index,row){
            return '<div style="padding:2px"><table class="ddv"></table></div>';
        },
        /*rowStyler: function(index,row){
                return 'background-color:#6293BB;color:#fff;font-weight:bold;cursor:pointer';
        },*/
	    idField:'location_id',
	    multiSort:true,
	    remoteSort:false,
	    columns:[[
	        {field:'ck',checkbox:true},
	        {field:'location_id',title:'ID#',width:250,halign:'center',sortable:true},
	        {field:'location_name',title:'City Name',width:360,align:'left',halign:'center',sortable:true},
	        {field:'isvisible',title:'Is visibled',width:100,align:'center',halign:'center',sortable:true,formatter:extras_formatstatus_datagrid},
	        {field:'isdelete',title:'Is delete',width:100,align:'center',halign:'center',sortable:true,formatter:extras_formatstatus_datagrid}
	    ]],
	    pagination:true,
	    onExpandRow: function(index,row){
	        var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
	        subgrid_1(ddv,row.location_id,index);
            $('#iddatagrip').datagrid('fixDetailRowHeight',index);
	    },
	    onDblClickRow:	 function(index,row){
  	    	$('#cc_city').combogrid('setValue',row.location_id);
  	    	$('#tt').tabs('select',1);
	    }
	});
	function subgrid_1(ddv,paren_id,indexdatagrid){
		  ddv.datagrid({
			  title:'District',
              url:extras_Hosting["tomcatSpring_context"]+'ad/locationController/get_location_byparent.json?paren_id='+paren_id,
              fitColumns:true,
              autoRowHeight:true,
              multiSort:true,
              remoteSort:false,
              idField:'location_id',
              loadMsg:'',
              height:'auto',
              /*rowStyler: function(index,row){
                  return 'background-color:#98b2e6;color:#fff;font-weight:bold;cursor:pointer';
              },*/
              columns:[[
                   {field:'ck',checkbox:true},
                   {field:'location_id',title:'ID#',width:200,halign:'center',sortable:true},
       	        {field:'location_name',title:'District Name',width:310,align:'left',halign:'center',sortable:true},
       	        {field:'isvisible',title:'Is visibled',width:90,align:'center',halign:'center',sortable:true,formatter:extras_formatstatus_datagrid},
       	        {field:'isdelete',title:'Is delete',width:90,align:'center',halign:'center',sortable:true,formatter:extras_formatstatus_datagrid}
              ]],
              onResize:function(){
                 $('#iddatagrip').datagrid('fixDetailRowHeight',indexdatagrid);
                
              },
              onLoadSuccess:function(){
                  setTimeout(function(){
                      $('#iddatagrip').datagrid('fixDetailRowHeight',indexdatagrid);
                  },0);
              },
      	    onDblClickRow:	 function(index,row){
      	    	var rows = $('#iddatagrip').datagrid('getRows'); 
      	    	var row_iddatagrip = rows[indexdatagrid]; 
      	    	$('#cc_city').combogrid('setValue',row_iddatagrip.location_id);
      	    	$('#tt').tabs('select',1);
      	    }
          });
	}
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
		var pdata={'pcdtype':'LOCATION','pcdname':'SEARCH'};
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
		  $('#cc_city').combogrid('setValue','');
		  $('#tt').tabs('select',1);
	});
	$("#idedit").click(function(){
		$.messager.alert('Warning','Double click Row city or district  to edit!','warning');
		return;
	});
	$("#idremove").click(function(){
		//extras_viahref("add_demo_crud");
		var ids=get_multi_row_select();
		if(ids.length==0){
				$.messager.alert('Warning','Select row please!','warning');
				return;
		}
		$.messager.confirm('Confirm','Are you sure you want to delete records?',function(r){
		    if (r){
		     	var thanhpho=ids.thanhpho;
		    	var quan=ids.quan;
		    	var phuong=ids.phuong;
		    	var pdata={'strthanhpho':thanhpho,'strquan':quan,'strphuong':phuong}
		        extras_POST_json(true,"ad/locationController","delete_multi_location",pdata,function(data){
		        	$.messager.alert('Result',data.message);
		        	if(data.f=="0"){
		        		$('#iddatagrip').datagrid('clearChecked');
		        		$('#iddatagrip').datagrid('reload');
		        		reload_cc_city();
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
			 alert_="Are you sure you want to visibled ?"
			 
		 }else{
			 status_=0;
			 alert_="Are you sure you want to unvisibled ?"
		 }
		var ids=get_multi_row_select();
		if(ids.length==0){
				$.messager.alert('Warning','Select row please!','warning');
				return;
		}
		//alert(JSON.stringify(ids));
	
		 $.messager.confirm('Confirm',alert_,function(r){
			    if (r){
			    	var thanhpho=ids.thanhpho;
			    	var quan=ids.quan;
			    	var phuong=ids.phuong;
			    	var pdata={'strthanhpho':thanhpho,'strquan':quan,'strphuong':phuong,'visible':status_}
			        extras_POST_json(true,"ad/locationController","visivled_location_3option",pdata,function(data){
			        	$.messager.alert('Result',data.message);
			        	if(data.f=="0"){
							$('#iddatagrip').datagrid('clearChecked');
							$('#iddatagrip').datagrid('reload');
							reload_cc_city();
			        	}
			        });

			    }
			});
	}
	
	function get_single_row_select(){
		 var checkedItems = $('#iddatagrip').datagrid('getChecked');
		 if(checkedItems.length>0){
			 return checkedItems[0].id;
		 }
		return "";
	}
	function _get_multi_row_select(){
		 var checkedItems = $('#iddatagrip').datagrid('getChecked');
		 if(checkedItems.length>0){
			 var ids = [];
		        $.each(checkedItems, function(index, item){
		        	ids.push("'"+item.id+"'");
		        });                
		   return ids.join(",");
		 }
		 return ""; 
	}
	
	function get_multi_row_select(){
		var selected={};	
			selected.thanhpho='';	
			selected.quan='';	
			selected.phuong='';	
		 var checkedItems = $('#iddatagrip').datagrid('getChecked');
		 if(checkedItems.length>0){
			 var thanhpho_s = [];
			 var quan_s=[];
			 var phuong_s=[];
		        $.each(checkedItems, function(index, item){
		        	thanhpho_s.push("'"+item.location_id+"'");
		        	var index=$('#iddatagrip').datagrid('getRowIndex',item);
		        	var expander = $('#iddatagrip').datagrid('getExpander', index);
		        	if(expander.hasClass('datagrid-row-collapse')){
		        		 var grid_quan = $('#iddatagrip').datagrid('getRowDetail',index).find('table.ddv');
		        		 var quan_checkedItems = $(grid_quan).datagrid('getChecked');
		        		 if(quan_checkedItems.length>0){
		        			 for(var i=0;i<quan_checkedItems.length;i++){
		        				 quan_s.push("'"+quan_checkedItems[i].location_id+"'");
		        			 }//end for quan_checkedItems
		        		 }//end if quan_checkedItems.length>0
		        	}//end if expant iddatagrip
		        });                
		        selected.thanhpho= thanhpho_s.join(",");
		       
		        if(quan_s.length>0){
		        	selected.quan=quan_s.join(",");
		        }
		        if(phuong_s.length>0){
		        	selected.phuong=phuong_s.join(",");
		        }
		        return selected;
		 }else{
			 return []; 
		 }
	}
	function get_checked_0(selectordatagrid){
		 var checkedItems = $(selectordatagrid).datagrid('getChecked');
		 if(checkedItems.length>0){
			 var ids=[];
			    $.each(checkedItems, function(index, item){
			    	ids.push("'"+item.location_id+"'");
			    });
			    return ids.join(",");
		 }
		 return ""; 
	}
	
	
	function reload_cc_city(){
		$('#cc_city').combogrid('grid').datagrid('reload');
		$('#cc_city').combogrid('setValue','');
		
	}
	
});
