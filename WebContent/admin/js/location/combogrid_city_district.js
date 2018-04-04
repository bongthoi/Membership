
$(function(){
	$('#cc_city').combogrid({
		 url: extras_Hosting["tomcatSpring_context"]+'ad/locationController/get_allCity',
		  method: 'get',
        panelWidth: 600,				      
        idField: 'location_id',
        textField: 'location_name',
        editable:false,
        columns: [[
                  {field:'location_id',title:'ID#',width:150,halign:'center',sortable:true},
       	        {field:'location_name',title:' City Name',width:180,align:'left',halign:'center',sortable:true},
       	        {field:'isvisible',title:'Is visibled',width:100,align:'center',halign:'center',sortable:true,formatter:extras_formatstatus_datagrid},
       	        {field:'action',title:'Action',width:80,align:'center',
					formatter:function(value,row,index){
						if (row.editing){
							var s = '<a href="javascript:void(0);" onclick="saverow_city('+index+')" class="easyui-linkbutton" iconCls="icon-save" plain="true">Save</a> ';
							var c = '<a href="javascript:void(0);" onclick="cancelrow_city('+index+')" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">Cancel</a>';
							return s+c;
						} else {
							var e = '<a href="javascript:void(0);" onclick="editrow_city('+index+')" class="easyui-linkbutton" iconCls="icon-edit" plain="true">Edit</a> ';
							var d = '<a href="javascript:void(0);" onclick="deleterow_city('+index+')" class="easyui-linkbutton" iconCls="icon-remove" plain="true">Delete</a>';
							return e+d;
						}
					}
				}
         ]],
   
         onHidePanel: function () {  
             var t = $(this).combogrid('getValue');  
          if(check_action_click_cc_city){
        	  $(this).combogrid('setValue', '');  
        	  $('#dg').datagrid('clearSelections');
       		  $('#dg').datagrid("loadData",[]);
          }               
          check_action_click_cc_city=false;
         },  
         onChange: function(newValue,oldValue){
        	 var cityid = $("#cc_city").combogrid('getValue');
        	 if(cityid!=""&&cityid!=null){
        	 var url_get_district= extras_Hosting["tomcatSpring_context"]+'ad/locationController/get_district_bycity?city='+cityid;
        	  $('#dg').datagrid({url:url_get_district});
        	  $('#dg').datagrid('clearSelections');
        	 }else{
        		  $('#dg').datagrid('clearSelections');
           		  $('#dg').datagrid("loadData",[]);
        	 }
 
         }
    });
});