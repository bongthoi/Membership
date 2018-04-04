
$(function(){	
	$opso_get(function(out,mes){		
	},'dialog','r1');
	$opso_get(function(out,mes){
		$(".opso_language").map(function(){
			var _data = $(this);
			var data_opso =_data.attr('data_opso');		
			
			var _arr = data_opso.split(',');
			if(_arr.length>0){
				var data_file = '';
				var data_key = '';
				for(var i=0;i<_arr.length;i++){
					var _arr_ = _arr[i].split(':');
					if(_arr_.length>0){
						switch(_arr_[0]){
							case "data_file":
								data_file=_arr_[1];
								break;
							case "data_key":
								data_key=_arr_[1];
								break;
						}
					}
				}
				if(data_file!='' && data_file!='undefine'){
					if(data_key!='' && data_key!='undefine'){					
						$opso_get(function(output,text){
							if(output==true){
								var _leng_text = $(_data).find('.l-btn-text');
								var _leng_tab = $(_data).find('.tabs-title');
								if(_leng_text.length >0){
									$(_data).find('.l-btn-text').text(text);
								}
								else if(_leng_tab.length >0){
									$(_data).find('.tabs-title').text(text);
								}
								else{
									$(_data).text(text);
								}														
							}
						},data_file,data_key);			
					}
				}
			}		
		}).get();
		$(".opso_placeholder").map(function(){
			var _data = $(this);
			var data_opso =_data.attr('data_opso');		
			
			var _arr = data_opso.split(',');
			if(_arr.length>0){
				var data_file = '';
				var data_key = '';
				for(var i=0;i<_arr.length;i++){
					var _arr_ = _arr[i].split(':');
					if(_arr_.length>0){
						switch(_arr_[0]){
							case "data_file":
								data_file=_arr_[1];
								break;
							case "data_key":
								data_key=_arr_[1];
								break;
						}
					}
				}
				if(data_file!='' && data_file!='undefine'){
					if(data_key!='' && data_key!='undefine'){					
						$opso_get(function(output,text){
							if(output==true){
								var _leng_text = $(_data).find('.l-btn-text');
								var _leng_tab = $(_data).find('.tabs-title');
								if(_leng_text.length >0){
									$(_data).find('.l-btn-text').text(text);
								}
								else if(_leng_tab.length >0){
									$(_data).find('.tabs-title').text(text);
								}
								else{
									$(_data).attr('placeholder',text);	
								}														
							}
						},data_file,data_key);			
					}
				}
			}		
		}).get();
	},'index','r1'); 
	
//	var arr_ele= [];
//	var current = 0;
//	$(".opso_language").map(function(){
//		var _data = $(this);
//		var item = {
//				'data':_data,
//				'type':'N'
//		}
//		arr_ele.push(item);
//	}).get();
//	$(".opso_placeholder").map(function(){
//		var _data = $(this);	
//		var item = {
//				'data':_data,
//				'type':'P'
//		}
//		arr_ele.push(item);
//	}).get();
//	load_trans();
	function load_trans(){		
		
		var check_load = false;
		var myVar = setInterval(function(){
			
			if(current < arr_ele.length){
				if(check_load==false){
					check_load = true;
					var _data= arr_ele[current].data;			
					var data_opso =_data.attr('data_opso');
					var _arr = data_opso.split(',');
					if(_arr.length>0){
						var data_file = '';
						var data_key = '';
						for(var i=0;i<_arr.length;i++){
							var _arr_ = _arr[i].split(':');
							if(_arr_.length>0){
								switch(_arr_[0]){
									case "data_file":
										data_file=_arr_[1];
										break;
									case "data_key":
										data_key=_arr_[1];
										break;
								}
							}
						}
						if(data_file!='' && data_file!='undefine'){
							if(data_key!='' && data_key!='undefine'){					
								$opso_get(function(output,text){
									if(output==true){
										var _leng_text = $(_data).find('.l-btn-text');
										var _leng_tab = $(_data).find('.tabs-title');
										if(_leng_text.length >0){
											$(_data).find('.l-btn-text').text(text);
										}
										else if(_leng_tab.length >0){
											$(_data).find('.tabs-title').text(text);
										}
										else{
											if(arr_ele[current].type=='N'){
												$(_data).text(text);	
											}
											else if(arr_ele[current].type=='P'){
												$(_data).attr('placeholder',text);	
											}
											
											check_load = false;
											current+=1;
										}														
									}
								},data_file,data_key);			
							}
						}
					}	
					
				}	
			}
			else{
				clearInterval(myVar);
				check_load_lang = true;
			}
					
		
		},100);				
	}
});
var arr_lang=[];
var check_load_lang = false;
function $opso_get(callback,data_file,data_key)
{
	try{
		if(!check_file_exists(data_file)){
			
			var _lang = get_lang_current()+".txt";
			var url = ReturnHosing_apache()+"language/"+data_file+"_"+_lang;
			
			$.get(url,function(data){						
				var obj = JSON.parse(data);	
				var itemadd = {
						'data_file':data_file,
						'list_text':obj
				};
				arr_lang.push(itemadd);
				
				for(var i=0;i<obj.length;i++){							
					if(obj[i].data_key==data_key){					
						callback(true,obj[i].text);
						break;
					}
				}
							
			});
		}
		else{
			get_text(function(output,text){
				if(output==true){
					callback(true,text);
				}
			},data_file,data_key);
		}
	}catch(ex){
		console.log(data_key);
	}
	
}
/*
 0: co dau tiep ngu
 1: khong co dau tiep ngu
 */
function $opso_langgrid(_gridname,_type,_nametype,datafile){
	var cols = $("#"+_gridname).jqxGrid('columns');			
	get_file(function(output,list){
		if(output==true){					
			for(var i=0;i<list.length;i++){	
				for(var j=0;j<cols.records.length;j++){
					var data_key = '';
					if(_type == 0){
						data_key=_nametype+'_'+cols.records[j].datafield;
					}
					else{
						data_key=cols.records[j].datafield;
					}
					if(list[i].data_key==data_key){					
						$("#"+_gridname).jqxGrid('setcolumnproperty',cols.records[j].datafield,'text',list[i].text);
						break;
					}
				}						
			}
		}
	},datafile);
}
function get_file(callback,data_file){
	var _lang = get_lang_current()+".txt";
	var url = ReturnHosing_apache()+"language/"+data_file+"_"+_lang;	
	$.get(url,function(data){						
		var obj = JSON.parse(data);	
		callback(true,obj);					
	});
}
function get_text(callback,data_file,data_key){
	for(var i=0;i<arr_lang.length;i++){
		if(arr_lang[i].data_file==data_file){			
			for(var j=0;j<arr_lang[i].list_text.length;j++){				
				if(arr_lang[i].list_text[j].data_key==data_key){
					
					callback(true,arr_lang[i].list_text[j].text);
					break;
				}
			}
			break;
		}
	}
}
function check_file_exists(data_file){
	if(arr_lang.length==0)
		return false;
	for(var i=0;i<arr_lang.length;i++){
		if(arr_lang[i].data_file==data_file){
			return true;
		}
	}
	return false;
}
function load_file(data_file){
	var _lang = get_lang_current()+".txt";
	var url = ReturnHosing_apache()+"language/"+data_file+"_"+_lang;			
	$.get(url,function(data){						
		var obj = JSON.parse(data);	
		var itemadd = {
				'data_file':data_file,
				'list_text':obj
		};
		arr_lang.push(itemadd);
		check_load_lang=true;			
	});
}
