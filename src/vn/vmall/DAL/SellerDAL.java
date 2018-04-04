package vn.vmall.DAL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;

import vn.vmall.AdminController.ad_loginController;
import vn.vmall.Entity.Product_Entity;
import vn.vmall.Entity.SellerBuyer_Entity;
import vn.vmall.Helper.ResultSetMapper;
import vn.vmall.Helper.SearchPaggModel;
import vn.vmall.SessionModel.UserSessionModel;
@Repository
public class SellerDAL {
	public int count_seller_byemail(String seller_email) {
		int count=0;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		try{
			String query="SELECT  Count(*) as count"
					+ " FROM tb_seller_buyer "
					+ " Where email='"+seller_email+"'";
					con = ConnectDB.getconnection();
					ps = con.prepareStatement(query);
					rs = ps.executeQuery();
					rs.first();
					count =rs.getInt("count");
			
		}catch(Exception ex){
			ex.printStackTrace();
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
		return count;
	}
	
	public  SellerBuyer_Entity getInfoSeller(String email){
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
		return user;
	}

	public int editInfoSeller(String email, String phone, String yeares, int total_empl, int total_revenue,
			String company_des, String company_pic, String certificate, String license) {
		
		int _result=0;
		try{
		
		
			
			String spname="sp_edit_info_seller";
			String[] pfield={"f","p_email","p_phone","p_yeares","p_total_empl","p_total_revenue",
					"p_company_des","p_company_pic","p_certificate","p_license"};
			String[] ptype={"INT","VARCHAR","VARCHAR","VARCHAR","VARCHAR","INT","INT",
					"VARCHAR","VARCHAR","VARCHAR","VARCHAR"};
			Object[] pvalues={"",email,phone,yeares,total_empl,total_revenue,
					company_des,company_pic,certificate,license};
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
	public int changePassSeller(String email, String oldpass, String newpass) {
		int _result= -1;
			try{
			
			String spname="sp_change_pass_seller";
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
