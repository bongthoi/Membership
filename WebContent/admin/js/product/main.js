$(function(){
	var title_="Product Manager"
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
	    url: extras_Hosting["tomcatSpring_context"]+'ad/ProductController/get_json_append_to_datagrip.json',
	    idField:'product_id',
	    multiSort:true,
	    remoteSort:false,
	    columns:[[
	        {field:'ck',checkbox:true},
	        {field:'seller_id',title:'卖家 #',align:'left',halign:'center',sortable:true},
	        {field:'product_id',title:'编码 #',width:180,halign:'center',sortable:true},
	        {field:'product_name',title:'名称',width:200,align:'left',halign:'center',sortable:true},
	        {field:'product_image',title:'图片',width:150,align:'left',halign:'center',sortable:true,formatter:extras_image_product},
	        {field:'product_price',title:'价格',width:80,align:'right',halign:'center',sortable:true},  
	      /*  {field:'product_description',title:'描述',width:200,align:'center',halign:'center',sortable:true,formatter:extras_description},*/
	        {field:'isvisible',title:'显示',align:'center',halign:'center',sortable:true,formatter:extras_formatstatus_datagrid}
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
			    prompt:'请输入价值'
			});

		}
	});
	function load_mm_selector(callback){
		$("#mm").html('');   	
		var pdata={'pcdtype':'PRODUCT','pcdname':'SEARCH'};
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
		$("#my_editor_product").val("");
		$("#idproduct_name").data('ptype','A').data('productid','').textbox('setValue','');
  		$('#cc_nation').combogrid('setValue',''); 
  		$("#idprice").numberbox('setValue','');
  		$('#dialog_product_img').find('#idproductimg').val('');
  		$('#dialog_product_img').find('img').attr('src',extras_Hosting["tomcat"]+'upload/no_image.jpg');
		 $('#tt').tabs('select',1);
	});
	
	$("#idedit").click(function(){
		var ids=get_single_row_select();
		if(ids==""){
			$.messager.alert('注意','请选择单行!','注意');
			return;
		}
		extras_GET_json(true,"ad/ProductController","get_Productbyid",{'product_id':ids},function(data){
			if(data !== null && typeof data === "object"){
	
				$("#my_editor_product").val(data.product_description);
				$("#idproduct_name").data('ptype','E').data('productid',data.product_id).textbox('setValue',data.product_name);
				$('#cc_nation').combogrid('setValue',data.lang_code); 
				$("#idprice").numberbox('setValue',data.product_price);
				$('#dialog_product_img').find('#idproductimg').val(data.product_image);
				 $('#dialog_product_img').find('img').attr('src',extras_Hosting["tomcat"]+'upload/product/'+data.product_image);
				$('#tt').tabs('select',1);
			}
		});
	});
	$("#idremove").click(function(){
		//extras_viahref("add_demo_crud");
		var ids=get_multi_row_select();
		if(ids==""){
			$.messager.alert('注意','请选择行列!','注意');
			return;
		}
		$.messager.confirm('确认','您是否确定删除行列?',function(r){
		    if (r){
		        //alert('ok');
		        extras_POST_json(true,"ad/ProductController","delete_multi_product",{'str_id':ids},function(data){
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
	$("#btnpreview").click(function(){
		var selleremail=$("#id_liuser").val(); 
		 var url = extras_Hosting["tomcatSpring_context"]+"view-product.html?u="+selleremail;
		    window.open(url, '_blank');
	});
	function visibled_and_unvisibled(visibled){
		 var status_=0;
		 var alert_="";
		 if(visibled){
			 status_=1;
			 alert_="您是否确定要看到此行列?"
			 
		 }else{
			 status_=0;
			 alert_="您是否确定隐藏此行列?"
		 }
		var ids=get_multi_row_select();
		if(ids==""){
				$.messager.alert('注意','请选择行列!','warning');
				return;
		}
		 $.messager.confirm('确认',alert_,function(r){
			    if (r){
			        extras_POST_json(true,"ad/ProductController","visivled_product",{'str_id':ids,'visible':status_},function(data){
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
			 return checkedItems[0].product_id;
		 }
		return "";
	}
	function get_multi_row_select(){
		 var checkedItems = $('#iddatagrip').datagrid('getChecked');
		 if(checkedItems.length>0){
			 var ids = [];
		        $.each(checkedItems, function(index, item){
		        	ids.push(item.product_id);
		        });                
		   return ids.join(",");
		 }
		 return ""; 
	}
	
});
