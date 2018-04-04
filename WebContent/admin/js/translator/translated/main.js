$(function(){
	var tabindex=0;
	var title_="Product translate"
		$('title').text(title_);
	$("#tt").tabs({
		onSelect:function(title,index){
			$("#idcurrent").val(title);
			//alert(index);
		},
		onClose:function(title,index){
			var MCEeditor=tinymce.get('productdes_'+title);
			
			var text_aria_selector=MCEeditor.getElement();
			MCEeditor.remove();
			//$(text_aria_selector).remove();
			//alert($(text_aria_selector).val());
			  //var stab=$('#tt').tabs('getTab',title);
			  //alert(title);
			//alert('#productdes_'+title);
			//  tinymce.remove('productdes_'+title);
			//$("#subtypeid_"+index).remove();
			/*var nameinput="subtypename_"+index;
			alert(nameinput);
			$("input[name='"+nameinput+"']").remove();*/
		}
	});
	tinymce.remove();
	load_combogrid_product();
	
	function load_combogrid_product(){
		$('#dg').datagrid({
			toolbar: '#tbar',
		    url: extras_Hosting["tomcatSpring_context"]+'ad/ProductTransController/get_json_translated.json',
		    //width:600,
		   // height:300,
		   // fitColumns:true,
		    idField:'product_id',
		    remoteSort:false,
		    singleSelect:true,
		    columns:[[
		        {field:'ck',checkbox:true},
		        {field:'product_id',title:'Product ID',width:180,halign:'left',sortable:true},
		        {field:'product_name',title:'Product Name',width:150,halign:'center',sortable:true},
		        {field:'product_image',title:'Image',width:150,halign:'center',sortable:true},
		        {field:'product_description',title:'Description',width:280,halign:'center',align:'center',sortable:true},
		        {field:'product_price',title:'Price',width:80,halign:'center',align:'right',sortable:true},
		        {field:'seller_id',title:'Seller',width:120,halign:'center',align:'right',sortable:true},
		        {field:'date_trans',title:'Date',width:80,halign:'left',sortable:true},
		        {field:'user_trans',title:'User',width:80,halign:'left',sortable:true}
		    ]],
		    pagination:true
		});
	}
	load_mm_selector(function(out){
		if(out==true){
			$('#ss').searchbox({
			    searcher:function(value,name){
			      
			    	$('#dg').datagrid('clearChecked');
			    	  $('#dg').datagrid('load',{
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
		var pdata={'pcdtype':'TRANSLATED','pcdname':'SEARCH'};
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
	$("#idupdate").click(function(){
		var id=get_single_row_select();
		if(id==""){
			$.messager.alert('Warning','Select row please!','warning');
			return;
		}
		var tabtitle =  $('#dg').datagrid('getSelected').product_name;
		  var stab=$('#tt').tabs('getTab',tabtitle);
		  
			  if(stab==null){
					create_panel(id,tabtitle);
			  }else{
				  $('#tt').tabs('select',tabtitle);
			  }
		  $('#dg').datagrid('clearChecked');
	});
	
	function create_panel(id,tabtitle){
		$.get(extras_Hosting["apache"] +"admin/views/Translator/Translated/detailedit.html",function(data){
			if(data!=null){
				data=data.replace(/@id/g,id);
				$('#tt').tabs('add',{
						id:'tab_'+id,
			            title:id,
			            content: '<div style="padding:5px;">'+data+'</div>',
			            closable: true, 
			    });
			}
		});
	}
	
	function get_single_row_select(){
		 var checkedItems = $('#dg').datagrid('getChecked');
		 if(checkedItems.length>0){
			 return checkedItems[0].product_id;
		 }
		return "";
	}
});
