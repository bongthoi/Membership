$(function(){
	extras_GET_json(true,"ad/BuyerController","getInfoBuyer","",function(data){
		if(data!=null){
			$('#frm_editinfo').form('load',data);
			$("#txtemail2").textbox('setValue',data.email);
			
			
			var certi=data.certificate;
			var hrefcerti=extras_Hosting["tomcat"]+'upload/company/'+data.certificate;
			if(certi==null ||certi==""){
				certi="沒有“證書!";
				hrefcerti="javascript:void(0);";
			}
			var license=data.license;
			var hreflicense=extras_Hosting["tomcat"]+'upload/company/'+data.license;
			if(license==null ||license==""){
				license="没有“许可证!";
				hreflicense="javascript:void(0);";
			}
			var pic=data.picture_company;
			var hrefpic=extras_Hosting["tomcat"]+'upload/company/'+data.picture_company;
			if(pic==null ||pic==""){
				pic="没有“公司图片!";
				hrefpic="javascript:void(0);";
			}
			$("#link_cert").attr('href',hrefcerti);
			$("#link_cert").text(certi);
			$("#link_license").attr('href',hreflicense);
			$("#link_license").text(license);
			$("#link_picture").attr('href',hrefpic);
			$("#link_picture").text(pic);

		}
	});
	
	function _valib_numberbox(){
		var namthanhlap=$("#txt_year_es_regis").numberbox('getValue');
		var sonhanvien=$("#txt_total_employ_regis").numberbox('getValue');
		var doanhthu=$("#txt_total_revenue_regis").numberbox('getValue');
		if(namthanhlap.length>4){
			$.messager.alert('注意',"成立年份最多4个字符 ",'warning');
			return false;
		}
		if(sonhanvien.length>10){
			$.messager.alert('注意',"员工总数必须小於1.000.000.000 ",'warning');
			return false;
		}
		if(doanhthu.length>12){
			$.messager.alert('注意',"营业额必须小於 1.000.000.000.000 ",'warning');
			return false;
		}
		return true;
	}
	
	$("#btnsubmit").click(function(){
		var url=extras_Hosting["tomcatSpring_context"]+"ad/BuyerController/editInfoBuyer";
		if($("#frm_editinfo").form('validate')&&_valib_numberbox()){
			$.messager.confirm('确认',"您是否想更改信息？",function(r){
				if(r==true){
					$("#frm_editinfo").form('submit',{
					url: url,
					onSubmit: function(){ 
							console.log( $( this ).serialize() );
							return true;
					},
					success: function(result){
						if(result!=null&&result!=""){
							var data=JSON.parse(result);
							if(data.f=="0"){
							
								$.messager.alert('通知',"保存成功!",'info');
				        	}else{	
				        		$.messager.alert('警报',"失败!",'警报');
				        	}
						}
					}
					});//end submit
			}});//emd confim
		}

	});
})