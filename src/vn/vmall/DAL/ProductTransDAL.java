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

import vn.vmall.Entity.Nation_Entity;
import vn.vmall.Entity.ProductTrans_Entity;
import vn.vmall.Helper.ResultSetMapper;
import vn.vmall.Helper.SearchPaggModel;

@Repository
public class ProductTransDAL {


	
	public List<ProductTrans_Entity> get_list_search_pagg(
			SearchPaggModel searchmodel) {
		List<ProductTrans_Entity> list=null;
		ResultSet rs = null;
		Connection con = null;
		CallableStatement callstore=null;
		try{
			String spname = "search_product_trans";
			String[] pfield = { "p_offset", "p_rows", "p_col", "p_val"};
			Object[] pvalues = {searchmodel.getOffset(),searchmodel.getRows(),searchmodel.getCol(),searchmodel.getVal()};
			
			rs=ConnectDB.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			ResultSetMapper<ProductTrans_Entity> resultSetMapper = new ResultSetMapper<ProductTrans_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,ProductTrans_Entity.class);
			
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
			list=new ArrayList<ProductTrans_Entity>();
		}
		return list;

	}
	public int count_get_list_search_pagg(SearchPaggModel searchmodel){
		int count=0;
		ResultSet rs = null;
		Connection con = null;
		CallableStatement callstore=null;
		try{
			
			String spname = "search_product_trans_count";
			String[] pfield = { "p_col", "p_val"};
			Object[] pvalues = {searchmodel.getCol(),searchmodel.getVal()};
			rs=ConnectDB.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			rs.first();
			count =rs.getInt("count");
			
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
	public ProductTrans_Entity get_product_info(String product_id) {
		ProductTrans_Entity item = new ProductTrans_Entity();
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		String query = "SELECT * FROM tb_product"
				+ " WHERE product_id='"+product_id+"'";
		try{
			con = ConnectDB.getconnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			ResultSetMapper<ProductTrans_Entity> resultSetMapper = new ResultSetMapper<ProductTrans_Entity>();
			item = resultSetMapper.mapRersultSetToObject_singlerow(rs,ProductTrans_Entity.class);
		}catch(Exception ex){
			System.out.println("Product Trans DAL error:"+ex);
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
		return item ;
	}
	public int trans_product(String product_id, String product_name, String product_img, String product_des,
			Float product_price, String seller_id,String user_trans) {
		int result=-1;
		ResultSet rs = null;
		Connection con = null;
		CallableStatement callstore=null;
		try{
			
			String spname = "sp_trans_product";
			String[] pfield = { "f","p_product_id", "p_product_name","p_product_img","p_product_des","p_product_price",
					"p_seller_id","p_user_trans"};
			String[] ptype={"INT","VARCHAR","VARCHAR","VARCHAR","VARCHAR","FLOAT","VARCHAR","VARCHAR"};
			Object[] pvalues = {"",product_id,product_name,product_img,product_des,product_price,seller_id,user_trans};
			int[] pdirec={1,0,0,0,0,0,0,0};
			
			Map<String,Object> mapOfObjects = new HashMap<String,Object>();
			mapOfObjects = ConnectDB.ExecBoFunctionReturnList(spname, pfield, ptype, pvalues, pdirec);
			result = Integer.parseInt(mapOfObjects.get("f").toString());
			
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
		
		return result;
	}
	public List<ProductTrans_Entity> get_list_search_translated(SearchPaggModel searchmodel) {
		List<ProductTrans_Entity> list=null;
		ResultSet rs = null;
		Connection con = null;
		CallableStatement callstore=null;
		try{
			String spname = "sp_search_product_translated";
			String[] pfield = { "p_offset", "p_rows", "p_col", "p_val"};
			Object[] pvalues = {searchmodel.getOffset(),searchmodel.getRows(),searchmodel.getCol(),searchmodel.getVal()};
			rs=ConnectDB.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			ResultSetMapper<ProductTrans_Entity> resultSetMapper = new ResultSetMapper<ProductTrans_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,ProductTrans_Entity.class);
			
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
			list=new ArrayList<ProductTrans_Entity>();
		}
		return list;
	}
	public int count_get_list_search_translated(SearchPaggModel searchmodel) {
		int count=0;
		ResultSet rs = null;
		Connection con = null;
		CallableStatement callstore=null;
		try{
			
			String spname = "sp_search_product_translated_count";
			String[] pfield = { "p_col", "p_val"};
			Object[] pvalues = {searchmodel.getCol(),searchmodel.getVal()};
			rs=ConnectDB.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			rs.first();
			count =rs.getInt("count");
			
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
	public ProductTrans_Entity get_product_translated_info(String product_id) {
		ProductTrans_Entity item = new ProductTrans_Entity();
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		String query = "SELECT * FROM tb_product_trans"
				+ " WHERE product_id='"+product_id+"'";
		try{
			con = ConnectDB.getconnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			ResultSetMapper<ProductTrans_Entity> resultSetMapper = new ResultSetMapper<ProductTrans_Entity>();
			item = resultSetMapper.mapRersultSetToObject_singlerow(rs,ProductTrans_Entity.class);
		}catch(Exception ex){
			System.out.println("Product Trans DAL error:"+ex);
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
		return item ;
	}
	public int update_product_content(String product_id, String product_name, String product_des,String user_trans) {
		int result=-1;
		ResultSet rs = null;
		Connection con = null;
		CallableStatement callstore=null;
		try{
			
			String spname = "sp_update_product_content";
			String[] pfield = { "f","p_product_id", "p_product_name","p_product_des","p_user_trans"};
			String[] ptype={"INT","VARCHAR","VARCHAR","VARCHAR","VARCHAR"};
			Object[] pvalues = {"",product_id,product_name,product_des,user_trans};
			int[] pdirec={1,0,0,0,0};
			
			Map<String,Object> mapOfObjects = new HashMap<String,Object>();
			mapOfObjects = ConnectDB.ExecBoFunctionReturnList(spname, pfield, ptype, pvalues, pdirec);
			result = Integer.parseInt(mapOfObjects.get("f").toString());
			
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
		
		return result;
	}

}
