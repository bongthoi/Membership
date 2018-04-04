$(function(){
	 $('#txtnewpass,#txtoldpass').passwordbox({
	        prompt: '',
	        showEye: true,
	        required:true,
	        validType:"minLength[6]"	
	    });
	 $('#txtnewpass2').passwordbox({
	        prompt: '',
	        showEye: true,
	        required:true,
	        validType: "confirmPass['#txtnewpass']"
	    }); 
	$("#btnsavepass").click(function(){
		var url=extras_Hosting["tomcatSpring_context"]+"ad/BuyerController/changePassBuyer";
		if($("#frm_editpass").form('validate')){
			$.messager.confirm('确认',"您是否想更换密码？",function(r){
				if(r==true){
					$("#frm_editpass").form('submit',{
					url: url,
					onSubmit: function(){ 
							console.log( $( this ).serialize() );
							return true;
					},
					success: function(result){
						if(result!=null&&result!=""){
							//var data=JSON.parse(result);
							if(result=="0"){
							
								$.messager.alert('通知',"更改密码成功",'info');
								$("#btncancelpass").click();
				        	}else if(result=='-2'){
				        		$.messager.alert('错误',"旧密码不正确",'warning');
							}
							else{	
								$.messager.alert('错误',"故障",'warning');
				        	}
						}
					}
					});//end submit
			}});//emd confim
		}
	 
	});
	$("#btncancelpass").click(function(){
		$("#txtoldpass").passwordbox('setValue','');
		$("#txtnewpass").passwordbox('setValue','');
		$("#txtnewpass2").passwordbox('setValue','');
	});
})
