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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import vn.vmall.AdminController.ad_loginController;
import vn.vmall.Entity.Product_Entity;
import vn.vmall.Helper.ResultSetMapper;
import vn.vmall.Helper.SearchPaggModel;
import vn.vmall.SessionModel.UserSessionModel;

@Repository
public class ProductDAL {
	private final String FILLTER_ALLOW_ALL="`seller_id`";
	
	
	
	private String fillter_role_user(UserSessionModel userm){
		String role=userm.getRole();
		if(role.equals(ad_loginController.ROLE_ADMIN)){
			return this.FILLTER_ALLOW_ALL;
		}else if(role.equals(ad_loginController.ROLE_SELLER)){
			return userm.getUser_seller().getEmail();
		}
		return null;
		
	}
	public List<Product_Entity> get_list_search_pagg(SearchPaggModel searchmodel, UserSessionModel userm) {
		List<Product_Entity> list=null;
		ResultSet rs = null;
		Connection con = null;
		CallableStatement callstore=null;
		try{
			String  p_seller_id=this.fillter_role_user(userm);
			//System.out.println(p_buyer_id);
			if(p_seller_id!=null){
			String spname = "search_product_seller";
			String[] pfield = { "p_offset", "p_rows", "p_col", "p_val","p_seller_id"};
			Object[] pvalues = {searchmodel.getOffset(),searchmodel.getRows(),searchmodel.getCol(),searchmodel.getVal(),p_seller_id};
			con = ConnectDB.getconnection();
			rs=ConnectDB.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			ResultSetMapper<Product_Entity> resultSetMapper = new ResultSetMapper<Product_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,Product_Entity.class);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(rs!=null){
					rs.close();
				}
				if(callstore!=null){
					callstore.close();
				}
				if(con!=null){
					con.close();
				}		
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		if(list==null){
			list=new ArrayList<Product_Entity>();
		}
		return list;
	}

	public int count_get_list_search_pagg(SearchPaggModel searchmodel, UserSessionModel userm) {
		int count=0;
		ResultSet rs = null;
		Connection con = null;
		CallableStatement callstore=null;
		try{
				String  p_seller_id=this.fillter_role_user(userm);
				//System.out.println(p_buyer_id);
				if(p_seller_id!=null){
					String spname = "search_product_seller_count";
					String[] pfield = { "p_col", "p_val","p_seller_id"};
					Object[] pvalues = {searchmodel.getCol(),searchmodel.getVal(),p_seller_id};
					con = ConnectDB.getconnection();
					rs=ConnectDB.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
					rs.first();
					count =rs.getInt("count");
				}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(rs!=null){
					rs.close();
				}
				if(callstore!=null){
					callstore.close();
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

	public Map<String, Object> SaveProduct(String type, Product_Entity d, UserSessionModel userm) {
		Map<String,Object> mapOfObjects = new HashMap<String,Object>();
		try{
			String role=userm.getRole();
			//System.out.println(p_buyer_id);
			if(role.equals(ad_loginController.ROLE_SELLER)){
			String spname="sp_product_insert_update";
			String[] pfield={"f","p_type","p_product_id","p_product_name","p_product_price",
								"p_product_image","p_product_description","p_seller_id","p_lang_code","out_id"};
			String[] ptype={"INT","VARCHAR","VARCHAR","VARCHAR","FLOAT",
								"VARCHAR","TEXT","VARCHAR","VARCHAR","VARCHAR"};
			Object[] pvalues={"",type,d.getProduct_id(),d.getProduct_name(),d.getProduct_price(),
								d.getProduct_image(),d.getProduct_description(),d.getSeller_id(),d.getLang_code(),""};
			int[] pdirec={1,0,0,0,0,
							0,0,0,0,1};
			mapOfObjects = ConnectDB.ExecBoFunctionReturnList(spname, pfield, ptype, pvalues, pdirec);
			}else{
				mapOfObjects.put("f", "28");
				mapOfObjects.put("out_id", "");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			mapOfObjects.put("f", "-1");
			mapOfObjects.put("out_id", "");
		}
		return mapOfObjects;
	}
	
	public Product_Entity get_productdetail_byid(String id) {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		Product_Entity a=new Product_Entity();
		try{
			String query="SELECT  product_id, lang_code, product_name, product_image, "
						+" product_description, product_price, seller_id, istrans "
						+ " FROM tb_product "
						+ " Where product_id='"+id+"'  and isvisible=1 and isdelete=0";
			con = ConnectDB.getconnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			ResultSetMapper<Product_Entity> resultSetMapper = new ResultSetMapper<Product_Entity>();
			a = resultSetMapper.mapRersultSetToObject_singlerow(rs, Product_Entity.class);
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
		
		return a;
	}
	
	public int count_product_detail_byid(String id) {
		int count=0;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		try{
			String query="SELECT  Count(*) as count"
					+ " FROM tb_product "
					+ " Where product_id='"+id+"' and isvisible=1 and isdelete=0";
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
	
	
	public Product_Entity get_Productbyid(String product_id) {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		Product_Entity a=new Product_Entity();
		try{
			String query="SELECT  product_id, lang_code, product_name, product_image, "
						+" product_description, product_price, seller_id, istrans "
						+ " FROM tb_product "
						+ " Where product_id='"+product_id+"'";
			con = ConnectDB.getconnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			ResultSetMapper<Product_Entity> resultSetMapper = new ResultSetMapper<Product_Entity>();
			a = resultSetMapper.mapRersultSetToObject_singlerow(rs, Product_Entity.class);
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
		
		return a;
	}

	public int visivled_product(String str_id, String visible, UserSessionModel userm) {
		int _result=0;
		try{
			String  p_seller_id=this.fillter_role_user(userm);
			//System.out.println(p_buyer_id);
			if(p_seller_id!=null){
			String spname="sp_product_visibled";
			String[] pfield={"f","p_str_id","p_visible"};
			String[] ptype={"INT","VARCHAR","VARCHAR"};
			Object[] pvalues={"",str_id,visible};
			int[] pdirec={1,0,0};
			Map<String,Object> mapOfObjects = new HashMap<String,Object>();
			mapOfObjects = ConnectDB.ExecBoFunctionReturnList(spname, pfield, ptype, pvalues, pdirec);
			_result = Integer.parseInt(mapOfObjects.get("f").toString());
			}else{
				_result=28;//role not allow
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			_result=-1;
		}
		return _result;
	}

	public int delete_multi_product(String str_id, UserSessionModel userm) {
		int _result=0;
		try{
			String  p_seller_id=this.fillter_role_user(userm);
			//System.out.println(p_buyer_id);
			if(p_seller_id!=null){
			String spname="sp_product_delete";
			String[] pfield={"f","p_str_id"};
			String[] ptype={"INT","VARCHAR"};
			Object[] pvalues={"",str_id};
			int[] pdirec={1,0};
			Map<String,Object> mapOfObjects = new HashMap<String,Object>();
			mapOfObjects = ConnectDB.ExecBoFunctionReturnList(spname, pfield, ptype, pvalues, pdirec);
			_result = Integer.parseInt(mapOfObjects.get("f").toString());
				}else{
					_result=28;//role not allow
				}
		}catch(Exception e)
		{
			e.printStackTrace();
			_result=-1;
		}
		return _result;
	}
	public List<Product_Entity> get__product_byseller(String seller_email,
			int start, int limit) {
		List<Product_Entity> list=null;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		try{
			String query="SELECT  * "
					+ " FROM tb_product "
					+ " Where seller_id='"+seller_email+"' and isvisible=1 and isdelete=0 ORDER BY product_id DESC "
					+" limit "+start+","+limit;
					//System.out.println(query);
					con = ConnectDB.getconnection();
					ps = con.prepareStatement(query);
					rs = ps.executeQuery();
					ResultSetMapper<Product_Entity> resultSetMapper = new ResultSetMapper<Product_Entity>();
					list = resultSetMapper.mapRersultSetToObject(rs,Product_Entity.class);
			
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
		if(list==null){
			list=new ArrayList<Product_Entity>();
		}
		return list;
		
	}
	public int count_product_byseller(String seller_email) {
		int count=0;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		try{
			String query="SELECT  Count(*) as count"
					+ " FROM tb_product "
					+ " Where seller_id='"+seller_email+"' and isvisible=1 and isdelete=0";
			
			//System.out.println(query);
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
		//System.out.println("count:"+count);
		return count;
	}
	

}
