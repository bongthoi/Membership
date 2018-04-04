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
import vn.vmall.Helper.ResultSetMapper;

@Repository
public class NationDAL {



	public List<Nation_Entity> get_nation_demo() {
		List<Nation_Entity> list = null;
		ResultSet rs = null;
		Connection con = null;
		CallableStatement callstore=null;
		try {
			String id="001";
			String spname = "ADEMO_select";
			String[] pfield = { "p_id"};
			Object[] pvalues = { id };

			rs=ConnectDB.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			ResultSetMapper<Nation_Entity> resultSetMapper = new ResultSetMapper<Nation_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs, Nation_Entity.class);

		} catch (Exception ex) {
			ex.printStackTrace();
			 /*System.out.println("NationDAL error:"+ex);*/
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
		if (list == null) {
			list = new ArrayList<Nation_Entity>();
		}
		return list;

	}
	
	/*public List<Nation_Entity> get_nation_demo() {
		List<Nation_Entity> list = null;
		ResultSet rs = null;
		Connection con = null;
		//CallableStatement callstore=null;
		try {
			String id="001";
			String spname = "ADEMO_select";
			String[] pfield = { "p_id"};
			Object[] pvalues = { id };
			//con = ConnectDB.getConnection();
			rs = ConnectDB.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			// rs = ConnectDB.GetDataReturnResultSet(query);
			ResultSetMapper<Nation_Entity> resultSetMapper = new ResultSetMapper<Nation_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs, Nation_Entity.class);

		} catch (Exception ex) {
			ex.printStackTrace();
			 System.out.println("NationDAL error:"+ex);
		}finally{
			try{
				if(rs!=null){
					rs.close();
				}
				if(con!=null){
					con.close();
				}		
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		if (list == null) {
			list = new ArrayList<Nation_Entity>();
		}
		return list;

	}*/
	
	public List<Nation_Entity> get_allnation() {
		List<Nation_Entity> list=null;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		String query ="SELECT nation_id, nation_name, isvisible, isdelete,lang_code"
				+ " FROM tb_nation";
		try{
			rs = ConnectDB.GetDataReturnResultSet(query);
			ResultSetMapper<Nation_Entity> resultSetMapper = new ResultSetMapper<Nation_Entity>();
			list = resultSetMapper.mapRersultSetToObject(rs,Nation_Entity.class);
		}catch(Exception ex){
			System.out.println("NationDAL error:"+ex);
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
			list=new ArrayList<Nation_Entity>();
		}
		return list;
		}
	
	public Nation_Entity get_nationbyid(String id){
		Nation_Entity o=new Nation_Entity();
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		String query ="SELECT nation_id, nation_name, isvisible, isdelete,lang_code"
				+ " FROM tb_nation Where nation_id='"+id+"'";
		try{
	
			rs = ConnectDB.GetDataReturnResultSet(query);
			ResultSetMapper<Nation_Entity> resultSetMapper = new ResultSetMapper<Nation_Entity>();
			o = resultSetMapper.mapRersultSetToObject_singlerow(rs, Nation_Entity.class);
		}catch(Exception ex){
			System.out.println("NationDAL error:"+ex);
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
		return o;
	}
	
	
	public int add_update_nation(String type, Nation_Entity d) {
		int _result=0;
		try{
			String spname="sp_nation_insert_update";
			String[] pfield={"f","p_type","p_nation_id","p_nation_name","p_lang_code"};
			String[] ptype={"INT","VARCHAR","VARCHAR","VARCHAR","VARCHAR"};
			Object[] pvalues={"",type,d.getNation_id(),d.getNation_name(),d.getLang_code()};
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
	public int detele_single_nation(String str_id) {
		int _result=0;
		try{
			String spname="sp_nation_delete_single";
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

}
