package vn.vmall.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import vn.vmall.Entity.Location_Entity;
import vn.vmall.Helper.ResultSetMapper;
import vn.vmall.Helper.SearchPaggModel;


@Repository
public class LocationDAL {


	public List<Location_Entity> get_list_search_pagg(
			SearchPaggModel searchmodel) {
		List<Location_Entity> list=null;
		//String query="select * from tb_democrud limit "+searchmodel.getOffset()+","+searchmodel.getRows();
		try{
			ResultSet rs = null;
			String spname = "search_location";
			String[] pfield = { "p_offset", "p_rows", "p_col", "p_val" };
			Object[] pvalues = {searchmodel.getOffset(),searchmodel.getRows(),searchmodel.getCol(),searchmodel.getVal() };
			rs =ConnectDB.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			//rs = ConnectDB.GetDataReturnResultSet(query);
			ResultSetMapper<Location_Entity> resultSetMapper = new ResultSetMapper<Location_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,Location_Entity.class);
			
		}catch(Exception ex){
			//System.out.println("LocationDAL error:"+ex);
		}
		if(list==null){
			list=new ArrayList<Location_Entity>();
		}
		return list;
		
		
	}
	public int count_get_list_search_pagg(SearchPaggModel searchmodel){
		int count=0;
		try{
			ResultSet rs = null;
			String spname = "search_location_count_total";
			String[] pfield = { "p_col", "p_val" };
			Object[] pvalues = {searchmodel.getCol(),searchmodel.getVal()};
			rs =ConnectDB.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			//rs = ConnectDB.GetDataReturnResultSet(query);
			rs.first();
			count =rs.getInt("count");
			
		}catch(Exception ex){
			//System.out.println("LocationDAL error:"+ex);
		}
		return count;
	}
	public int add_update_location(String type, Location_Entity d){
		int _result=0;
		try{
			String spname="sp_location_insert_update";
			String[] pfield={"f","p_type","p_location_id","p_location_name","p_parent"};
			String[] ptype={"INT","VARCHAR","VARCHAR","VARCHAR","VARCHAR"};
			Object[] pvalues={"",type,d.getLocation_id(),d.getLocation_name(),d.getParent()};
			int[] pdirec={1,0,0,0,0};
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
	public Location_Entity get_location_by_id(String id){
		Location_Entity m= null;
		String query ="SELECT product_type_vmall, product_type_name, category_img, title_img, isvisible, isdelete, isstate, issyn "
				+ " FROM tb_product_type_vmall where product_type_vmall='"+id+"'";
		try{
			ResultSet rs = null;
			ResultSetMapper<Location_Entity> resultSetMapper = new ResultSetMapper<Location_Entity>();
			rs = ConnectDB.GetDataReturnResultSet(query);
			m = resultSetMapper.mapRersultSetToObject_singlerow(rs, Location_Entity.class);
			
		}catch(Exception ex){
			
		}
		
		//System.out.print(mem.toString());
		return m;
		
	}

	public int visivled_location(String strthanhpho,String strquan,String strphuong,String visible)
			throws InstantiationException, ClassNotFoundException, SQLException{
		Connection dbConnection = null;
		PreparedStatement preparedStatementUpdate1 = null;
		PreparedStatement preparedStatementUpdate2 = null;
		
		try {
		dbConnection=ConnectDB.getconnection();
		dbConnection.setAutoCommit(false);
	    if(strquan.equals("")==false){
			//System.out.println("strquan.equals()==false");
			String updateQuery_1 = "update  tb_location"
					+ " set isvisible = "+ visible
					+ " where location_id in ("+strquan+")";
			
			preparedStatementUpdate1 = dbConnection.prepareStatement(updateQuery_1);
			preparedStatementUpdate1.executeUpdate();
		}else if(strthanhpho.equals("")==false){
			//System.out.println("strthanhpho.equals()==false");
			///System.out.println(strthanhpho);
			String updateQuery_1 = "update  tb_location"
					+ " set isvisible = "+ visible
					+ " where location_id in ("+strthanhpho+")";
			
			String updateQuery_2 = "update  tb_location"
					+ " set isvisible = "+ visible
					+ " where parent in ("+strthanhpho+")";
			preparedStatementUpdate1 = dbConnection.prepareStatement(updateQuery_1);
			preparedStatementUpdate2 = dbConnection.prepareStatement(updateQuery_2);
			preparedStatementUpdate1.executeUpdate();
			preparedStatementUpdate2.executeUpdate();
			
		}
			dbConnection.commit();
			System.out.println("Update Location Done!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			dbConnection.rollback();
			return -1;
		} finally {
			if (preparedStatementUpdate1 != null) {
				preparedStatementUpdate1.close();
			}
			if (preparedStatementUpdate2 != null) {
				preparedStatementUpdate2.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return 0;
	}
	public List<Location_Entity> get_alllocation() {
		List<Location_Entity> list=null;
		String query ="SELECT product_type_vmall, product_type_name, category_img, title_img, isvisible, isdelete, isstate, issyn "
				+ " FROM tb_product_type_vmall"
				+ " WHERE isvisible= 1";
		try{
			ResultSet rs = null;
		
			rs = ConnectDB.GetDataReturnResultSet(query);
			ResultSetMapper<Location_Entity> resultSetMapper = new ResultSetMapper<Location_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,Location_Entity.class);
			
		}catch(Exception ex){
			//System.out.println("LocationDAL error:"+ex);
		}
		
		if(list==null){
			list=new ArrayList<Location_Entity>();
		}
		return list;
	}
	public List<Location_Entity> get_datagrip_byparentid(
			SearchPaggModel searchmodel) {
		List<Location_Entity> list=null;
		String query ="SELECT tb1.ID as id, tb1.product_type_sub_id, tb1.location_name,"
				+ " tb1.isvisible, tb1.isdelete, tb1.parent "
				+" FROM tb_location tb1 where tb1.parent='"+searchmodel.getCustom_value()+"' "
				+ " limit "+searchmodel.getOffset()+","+searchmodel.getRows();
		try{
			ResultSet rs = null;
			rs = ConnectDB.GetDataReturnResultSet(query);
			ResultSetMapper<Location_Entity> resultSetMapper = new ResultSetMapper<Location_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,Location_Entity.class);
			
		}catch(Exception ex){
			//System.out.println("LocationDAL error:"+ex);
		}
		if(list==null){
			list=new ArrayList<Location_Entity>();
		}
		return list;
	}
	public int count_get_datagrip_byparentid(SearchPaggModel searchmodel) {
		int count=0;
		String query ="select count(*) as count  from (SELECT tb1.ID as id, tb1.product_type_sub_id, tb1.location_name, "
				+ " tb1.isvisible, tb1.isdelete, tb1.parent "
				+" FROM tb_location tb1  where tb1.parent='"+searchmodel.getCustom_value()+"')A";
				
		try{
			ResultSet rs = null;
			rs = ConnectDB.GetDataReturnResultSet(query);
			rs.first();
			count =rs.getInt("count");
		}catch(Exception ex){
			//System.out.println("LocationDAL error:"+ex);
		}
		return count;
	}
	public int detele_single_location(String str_id) {
		int _result=0;
		try{
			String spname="sp_location_delete_single";
			String[] pfield={"f","p_str_id"};
			String[] ptype={"INT","VARCHAR"};
			Object[] pvalues={"",str_id};
			int[] pdirec={1,0};
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
	public int delete_multi_location(String strthanhpho,String strquan,String  strphuong)
			throws InstantiationException, ClassNotFoundException, SQLException{
		Connection dbConnection = null;
		PreparedStatement preparedStatementUpdate1 = null;
		PreparedStatement preparedStatementUpdate2 = null;
		try {
		dbConnection=ConnectDB.getconnection();
		dbConnection.setAutoCommit(false);
		 if(strquan.equals("")==false){
			//System.out.println("strquan.equals()==false");
			String updateQuery_1 = "DELETE FROM   tb_location"
					+ " where location_id in ("+strquan+")";
			preparedStatementUpdate1 = dbConnection.prepareStatement(updateQuery_1);
			preparedStatementUpdate1.executeUpdate();
		
		}else if(strthanhpho.equals("")==false){
			//System.out.println("strthanhpho.equals()==false");
			///System.out.println(strthanhpho);
			String updateQuery_1 = "DELETE FROM   tb_location"
					+ " where location_id in ("+strthanhpho+")";
			
			String updateQuery_2 = "DELETE FROM   tb_location"
					+ " where parent in ("+strthanhpho+")";
			preparedStatementUpdate1 = dbConnection.prepareStatement(updateQuery_1);
			preparedStatementUpdate2 = dbConnection.prepareStatement(updateQuery_2);
			preparedStatementUpdate1.executeUpdate();
			preparedStatementUpdate2.executeUpdate();
		}
			dbConnection.commit();
			System.out.println("Delete Location Done!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			dbConnection.rollback();
			return -1;
		} finally {
			if (preparedStatementUpdate1 != null) {
				preparedStatementUpdate1.close();
			}
			if (preparedStatementUpdate2 != null) {
				preparedStatementUpdate2.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return 0;
	}
	public List<Location_Entity> get_location_byparent(
			String paren_id) {
		List<Location_Entity> list=null;
		String query ="SELECT tp.location_id, tp.location_name, tp.parent, tp.isvisible, tp.isdelete"
						+" FROM tb_location tp "
						+" where  tp.parent='"+paren_id+"'";
		try{
			ResultSet rs = null;
		
			rs = ConnectDB.GetDataReturnResultSet(query);
			ResultSetMapper<Location_Entity> resultSetMapper = new ResultSetMapper<Location_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,Location_Entity.class);
			
		}catch(Exception ex){
			//System.out.println("LocationDAL error:"+ex);
		}
		
		if(list==null){
			list=new ArrayList<Location_Entity>();
		}
		return list;
	}
	public List<Location_Entity> get_district_bycity(
			String city) {
		List<Location_Entity> list=null;
		String query ="SELECT tp.location_id, tp.location_name, tp.parent, tp.isvisible, tp.isdelete"
						+" FROM tb_location tp "
						+" where  tp.parent='"+city+"' and  tp.isvisible =1 ";
		try{
			ResultSet rs = null;
		
			rs = ConnectDB.GetDataReturnResultSet(query);
			ResultSetMapper<Location_Entity> resultSetMapper = new ResultSetMapper<Location_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,Location_Entity.class);
			
		}catch(Exception ex){
			//System.out.println("LocationDAL error:"+ex);
		}
		
		if(list==null){
			list=new ArrayList<Location_Entity>();
		}
		return list;
	}
	public List<Location_Entity> get_allCity() {
		// TODO Auto-generated method stub
		List<Location_Entity> list=null;
		String query ="SELECT tp.location_id, tp.location_name, tp.parent, tp.isvisible, tp.isdelete"
						+" FROM tb_location tp "
						+" where  tp.parent='0' and tp.isvisible =1 ";
		try{
			ResultSet rs = null;
		
			rs = ConnectDB.GetDataReturnResultSet(query);
			ResultSetMapper<Location_Entity> resultSetMapper = new ResultSetMapper<Location_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,Location_Entity.class);
			
		}catch(Exception ex){
			//System.out.println("LocationDAL error:"+ex);
		}
		
		if(list==null){
			list=new ArrayList<Location_Entity>();
		}
		return list;
	}
	
}
