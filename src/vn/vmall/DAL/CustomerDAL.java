package vn.vmall.DAL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import vn.vmall.AdminController.ad_loginController;
import vn.vmall.DBconnect.Connect_membershipdb;
import vn.vmall.Entity.ItemCustomer;
import vn.vmall.Entity.ItemNation;
import vn.vmall.Entity.SellerBuyer_Entity;
import vn.vmall.Helper.Extra;
import vn.vmall.SessionModel.UserSessionModel;

@Repository
public class CustomerDAL {
	public static int check_customer(String email, String pass) {
		int rs = -1;
		try{
			String query = "SELECT isconfirm FROM tb_seller_buyer where email = '" + email
					+ "' and pass = '" + pass + "'";
			ResultSet _rs  = null;
			_rs = ConnectDB.GetDataReturnResultSet(query);
			int exists = 0;
			String confirm = "";
			while(_rs.next()){
				exists = 1;
				confirm = _rs.getString("isconfirm");
			}
			if(exists==0){
				query = "SELECT * FROM tb_user where user='"+email+"'";
				exists = 0;
				_rs = ConnectDB.GetDataReturnResultSet(query);
				while (_rs.next()) {							
					exists =1;
				}
				if(exists==0){
					rs= -1;
					return rs;
				}
			}
			else{
				int isconfirm =Integer.parseInt(confirm);
				if (isconfirm == 0) {
					rs = -3;
					return rs;
				}	
			}
			
		}catch(Exception ex){
			
		}		
		return 0;
	}
	public static ArrayList<ItemNation> get_nation() {

		ArrayList<ItemNation> data = new ArrayList<ItemNation>();
		String query = "SELECT nation_id,nation_name FROM tb_nation where isvisible = true and isdelete = false;";
		ResultSet rs = null;
		rs = ConnectDB.GetDataReturnResultSet(query);
		try {
			while (rs.next()) {
				ItemNation item = new ItemNation();
				item.setNation_id(rs.getString("nation_id").toString());
				item.setNation_name(rs.getString("nation_name").toString());
				data.add(item);
			}
		} catch (Exception ex) {

		}
		return data;

	}
	public static int add_customer_dao(ItemCustomer a) {
		int _result = 0;
		try {
			String spname = "sp_insert_seller_buyer";
			String[] pfield = { "f", "p_email", "p_pass", "p_company_name", "p_country",
					"p_address", "p_phone","p_year_established","p_picture_company","p_main_products" ,
					"p_certificate","p_license","p_total_employees","p_total_annual_revenue","p_type",
					"p_provice","p_district","p_city"};
			String[] ptype = { "INT", "VARCHAR", "VARCHAR", "VARCHAR", "VARCHAR",
							"VARCHAR","VARCHAR","VARCHAR","VARCHAR","VARCHAR",
							"VARCHAR","VARCHAR","VARCHAR","VARCHAR","VARCHAR",
							"VARCHAR","VARCHAR","VARCHAR"};
			Object[] pvalues = {"",a.getEmail(),a.getPass(),a.getShop_name(),a.getNation(),
					a.getAddress(),a.getSdt(),a.getYear(),a.getPicture_company(),a.getCompany(),
					a.getCertificate(),a.getLicense(),a.getTotal_empl(),a.getTotal_reven(),a.getType(),
					a.getProvice(),a.getDistrict(),a.getCity()};
			int[] pdirec = { 1, 0, 0, 0, 0,
							0,0,0,0,0,
							0,0,0,0,0,
							0,0,0};			
			Map<String, Object> mapOfObjects = new HashMap<String, Object>();
			mapOfObjects = ConnectDB.ExecBoFunctionReturnList(spname, pfield, ptype, pvalues, pdirec);
			_result = Integer.parseInt(mapOfObjects.get("f").toString());
		} catch (Exception e) {
			e.printStackTrace();
			_result = -1;
		}
		return _result;
	}
	public static ItemCustomer get_info_customer(String email)
			throws ClassNotFoundException, InstantiationException, SQLException {
		ResultSet rs=null;
		ItemCustomer cus = new ItemCustomer();
		String query = "SELECT email,pass,company_name FROM tb_seller_buyer where email = '"+email+"'";
		 rs = ConnectDB.GetDataReturnResultSet(query);
		while (rs.next()) {
			cus.setEmail(rs.getString("email"));
			cus.setPass(rs.getString("pass"));
			cus.setShop_name(rs.getString("company_name"));
		}
		return cus;
	}
	public static Map<String, Object> confirm_create_shop(String email) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String spname = "sp_confirm_create_shop";
			String[] pfield = { "f", "p_email"

			};
			String[] ptype = { "INT", "VARCHAR" };
			Object[] pvalue = { "", email };
			int[] pdirec = { 1, 0 };			
			result = ConnectDB.ExecBoFunctionReturnList(spname, pfield, ptype, pvalue, pdirec);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	public static Map<String, Object> change_password_forget(String email) {
		int result = -1;
		Map<String, Object> item = new HashMap<String, Object>();
		try {
			ItemCustomer itemcus = new ItemCustomer();
			itemcus = get_info_customer(email);
			if (itemcus.getEmail() != null) {
				String pass = Extra.getRandomString(3, 6);
				String query = "update tb_seller_buyer set confirm_forget=0,pass_forget='" + pass + "' where email='"
						+ email + "'";				
				int rs  = ConnectDB.ExecUpdate(query);
				result = Integer.parseInt(String.valueOf(rs));
				item.put("result", String.valueOf(result));
				item.put("password", pass);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			result = -1;
		}
		return item;
	}

	public static Map<String, Object> confirm_change_password(String email) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String spname = "sp_confirm_change";
			String[] pfield = { "f", "p_email"

			};
			String[] ptype = { "INT", "VARCHAR" };
			Object[] pvalue = { "", email };
			int[] pdirec = { 1, 0 };
			result = ConnectDB.ExecBoFunctionReturnList(spname, pfield, ptype, pvalue, pdirec);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	public static UserSessionModel get_info_role(String email)
			throws ClassNotFoundException, InstantiationException, SQLException {
		ResultSet rs=null;
		UserSessionModel cus = new UserSessionModel();
		try{
			SellerBuyer_Entity i = new SellerBuyer_Entity();
			i.setEmail(email);
			cus.setUser_seller(i);
			String query = "SELECT type FROM tb_seller_buyer where email='"+email+"'";
			 rs = ConnectDB.GetDataReturnResultSet(query);
			int exists = 0;
			while (rs.next()) {
				String type = rs.getString("type");			
				if(type.equals("001")){ // seller
					cus.setRole(ad_loginController.ROLE_SELLER);
				}
				if(type.equals("002")){ // buyer
					cus.setRole(ad_loginController.ROLE_BUYER);
				}
				exists = 1;
			}
			if(exists==0){
				query = "SELECT * FROM tb_user where user='"+email+"'";
				rs = ConnectDB.GetDataReturnResultSet(query);
				while (rs.next()) {							
					cus.setRole(ad_loginController.ROLE_ADMIN); //user
				}
			}
		}catch(Exception ex){
			
		}		
		return cus;
	}
	public static ArrayList<ItemNation> get_pro()
			throws ClassNotFoundException, InstantiationException, SQLException {
		ResultSet rs=null;
		ArrayList<ItemNation> list = new ArrayList<ItemNation>();
		String query = "SELECT location_id,location_name FROM tb_location where parent = '0' and isvisible = true and isdelete  = false;";
		 rs = ConnectDB.GetDataReturnResultSet(query);
		while (rs.next()) {
			ItemNation item = new ItemNation();
			item.setNation_id(rs.getString("location_id"));
			item.setNation_name(rs.getString("location_name"));
			list.add(item);
		}
		return list;
	}
	public static ArrayList<ItemNation> get_ward(String dis)
			throws ClassNotFoundException, InstantiationException, SQLException {
		ResultSet rs=null;
		ArrayList<ItemNation> list = new ArrayList<ItemNation>();
		String query = "SELECT location_id,location_name FROM tb_location where parent = '"+dis+"' and isvisible = true and isdelete  = false;";
		 rs = ConnectDB.GetDataReturnResultSet(query);
		while (rs.next()) {
			ItemNation item = new ItemNation();
			item.setNation_id(rs.getString("location_id"));
			item.setNation_name(rs.getString("location_name"));
			list.add(item);
		}
		return list;
	}
}
