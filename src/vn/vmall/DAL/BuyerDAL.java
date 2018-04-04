package vn.vmall.DAL;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;



import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import vn.vmall.Entity.SellerBuyer_Entity;
import vn.vmall.Helper.ResultSetMapper;

@Repository
public class BuyerDAL {
	public static SellerBuyer_Entity getInfoBuyer(String email){
		SellerBuyer_Entity user = new SellerBuyer_Entity();
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		try{
		String query = "SELECT email,company_name,address,phone,year_established, total_employees, total_annual_revenue,"
				+ " main_products,picture_company, certificate, license";
		query+= " FROM tb_seller_buyer WHERE email='"+email+"'";
		
		con = ConnectDB.getconnection();
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		ResultSetMapper<SellerBuyer_Entity> resultSetMapper = new ResultSetMapper<SellerBuyer_Entity>();
		user = resultSetMapper.mapRersultSetToObject_singlerow(rs, SellerBuyer_Entity.class);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null){
					rs.close();
				}
				if(ps!=null){
					ps.close();
				}
				if(con!=null){
					con.close();
				}		
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		//System.out.println(new Gson().toJson(user));
		return user;
	}
	public static void main(String[] args) {
		//System.out.println(getInfoBuyer("seller@vinhsang.vn").getPhone());
	}
	public int editInfoBuyer(SellerBuyer_Entity a) {
		
		int _result=0;
		try{
			String spname="sp_edit_info_buyer";
			String[] pfield={"f","p_email","p_phone","p_yeares","p_total_empl","p_total_revenue",
					"p_company_des","p_company_pic","p_certificate","p_license"};
			String[] ptype={"INT","VARCHAR","VARCHAR","VARCHAR","VARCHAR","INT","INT",
					"VARCHAR","VARCHAR","VARCHAR","VARCHAR"};
			Object[] pvalues={"",a.getEmail(),a.getPhone(),a.getYear_established(),a.getTotal_employees(),a.getTotal_annual_revenue(),
								a.getMain_products(),a.getPicture_company(),a.getCertificate(),a.getLicense()};
			int[] pdirec={1,0,0,0,0,0,
							0,0,0,0};
			Map<String,Object> mapOfObjects = new HashMap<String,Object>();
			mapOfObjects = ConnectDB.ExecBoFunctionReturnList(spname, pfield, ptype, pvalues, pdirec);
			_result = Integer.parseInt(mapOfObjects.get("f").toString());
			
		}catch(Exception e)
		{
			e.printStackTrace();
			_result=-1;
		}
		return _result;

	}
	public int changePassBuyer(String email, String oldpass, String newpass) {
		int _result= -1;
		try{
		
		String spname="sp_change_pass_buyer";
		String[] pfield={"f","p_email","p_oldpass","p_newpass"};
		String[] ptype={"INT","VARCHAR","VARCHAR","VARCHAR"};
		Object[] pvalues={"",email,oldpass,newpass};
		int[] pdirec={1,0,0,0};
		Map<String,Object> mapOfObjects = new HashMap<String,Object>();
		mapOfObjects = ConnectDB.ExecBoFunctionReturnList(spname, pfield, ptype, pvalues, pdirec);
		_result = Integer.parseInt(mapOfObjects.get("f").toString());
		
		}catch(Exception e)
		{
			e.printStackTrace();
			_result=-1;
		}
	
	return _result;
	}
}
