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

import vn.vmall.AdminController.ad_loginController;
import vn.vmall.Entity.Requirements_Entity;
import vn.vmall.Helper.ResultSetMapper;
import vn.vmall.Helper.SearchPaggModel;
import vn.vmall.SessionModel.UserSessionModel;

@Component
public class RequirementsDAL {
	private final String FILLTER_ALLOW_ALL="`buyer_id`";
	
	
	private String fillter_role_user(UserSessionModel userm){
		String role=userm.getRole();
		if(role.equals(ad_loginController.ROLE_ADMIN)){
			return this.FILLTER_ALLOW_ALL;
		}else if(role.equals(ad_loginController.ROLE_BUYER)){
			return userm.getUser_seller().getEmail();
		}
		return null;
		
	}
	
	public List<Requirements_Entity> get_list_search_pagg(SearchPaggModel searchmodel,UserSessionModel userm) {
		List<Requirements_Entity> list=null;
		ResultSet rs = null;
		Connection con = null;
		CallableStatement callstore=null;
		try{
			String  p_buyer_id=this.fillter_role_user(userm);
			//System.out.println(p_buyer_id);
			if(p_buyer_id!=null){
				String spname = "search_requirements";
				String[] pfield = { "p_offset", "p_rows", "p_col", "p_val","p_buyer_id"};
				Object[] pvalues = {searchmodel.getOffset(),searchmodel.getRows(),searchmodel.getCol(),searchmodel.getVal(),p_buyer_id};
				rs=ConnectDB.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
				ResultSetMapper<Requirements_Entity> resultSetMapper = new ResultSetMapper<Requirements_Entity>();
				list = resultSetMapper.mapRersultSetToObject(rs,Requirements_Entity.class);
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
			list=new ArrayList<Requirements_Entity>();
		}
		return list;
	}

	public int count_get_list_search_pagg(SearchPaggModel searchmodel,UserSessionModel userm) {
		int count=0;
		ResultSet rs = null;
		Connection con = null;
		CallableStatement callstore=null;
		try{
			String  p_buyer_id=this.fillter_role_user(userm);
			if(p_buyer_id!=null){
			String spname = "search_requirements_count";
			String[] pfield = { "p_col", "p_val","p_buyer_id"};
			Object[] pvalues = {searchmodel.getCol(),searchmodel.getVal(),p_buyer_id};
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

	public Map<String, Object> SaveRequirements(String type, Requirements_Entity d, UserSessionModel userm) {
		Map<String,Object> mapOfObjects = new HashMap<String,Object>();
		try{
			String role=userm.getRole();
			//System.out.println(p_buyer_id);
			if(role.equals(ad_loginController.ROLE_BUYER)){
			String spname="sp_requirements_insert_update";
			String[] pfield={"f","p_type","p_requirement_id","p_product_price_limit","p_product_image",
								"p_description","p_budget","p_quantity","p_buyer_id","out_id"};
			String[] ptype={"INT","VARCHAR","VARCHAR","FLOAT","VARCHAR",
								"TEXT","FLOAT","FLOAT","VARCHAR","VARCHAR"};
			Object[] pvalues={"",type,d.getRequirement_id(),d.getProduct_price_limit(),d.getProduct_image(),
								d.getDescription(),d.getBudget(),d.getQuantity(),userm.getUser_seller().getEmail(),""};
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

	public Requirements_Entity get_Requirementsbyid(String requirement_id) {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		Requirements_Entity a=new Requirements_Entity();
		try{
			String query="SELECT  requirement_id, description, product_image, product_price_limit, quantity, "
						+"  budget, isvisible, isdelete, buyer_id "
						+ " FROM tb_requirements "
						+ " Where requirement_id='"+requirement_id+"'";
			con = ConnectDB.getconnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			ResultSetMapper<Requirements_Entity> resultSetMapper = new ResultSetMapper<Requirements_Entity>();
			a = resultSetMapper.mapRersultSetToObject_singlerow(rs, Requirements_Entity.class);
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

	public int visivled_requirements(String str_id, String visible,UserSessionModel userm) {
		int _result=0;
		try{
			String  p_buyer_id=this.fillter_role_user(userm);
			//System.out.println(p_buyer_id);
			if(p_buyer_id!=null){
			String spname="sp_requirements_visibled";
			String[] pfield={"f","p_str_id","p_visible","p_buyer_id"};
			String[] ptype={"INT","VARCHAR","VARCHAR","VARCHAR"};
			Object[] pvalues={"",str_id,visible,p_buyer_id};
			int[] pdirec={1,0,0,0};
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

	public int delete_multi_requirements(String str_id,UserSessionModel userm) {
		int _result=0;
		try{
			String  p_buyer_id=this.fillter_role_user(userm);
			//System.out.println(p_buyer_id);
			if(p_buyer_id!=null){
			String spname="sp_requirements_delete";
			String[] pfield={"f","p_str_id","p_buyer_id"};
			String[] ptype={"INT","VARCHAR","VARCHAR"};
			Object[] pvalues={"",str_id,p_buyer_id};
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

}