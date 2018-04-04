package vn.vmall.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import vn.vmall.Helper.AllcodeModel;


@Component
public class AllcodeDAL {


	public List<AllcodeModel> get_allcode_searchbox(String pcdtype,String cdname){
		List<AllcodeModel>  list= new ArrayList<AllcodeModel>();
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		String query = "SELECT t1.CDTYPE, t1.CDNAME, t1.CDVAL, t1.CDCONTENT, t1.ORDERBY, t1.LOCK, t1.id_a "
				+ "FROM tb_allcode t1 where "
				+ " t1.CDTYPE='"+pcdtype+"' and  t1.CDNAME='"+cdname+"'";
		try{
			con = ConnectDB.getconnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
			
				AllcodeModel item=new AllcodeModel();
				item.setCdcontent(rs.getString("CDCONTENT"));
				item.setCdname(rs.getString("CDNAME"));
				item.setCdtype(rs.getString("CDTYPE"));
				item.setCdval(rs.getString("CDVAL"));
				item.setId_a(rs.getString("id_a"));
				item.setLock(rs.getString("LOCK"));
				item.setOrderby(rs.getString("ORDERBY"));
				list.add(item);
			}
		
		}catch(Exception ex){
			
		}
		//System.out.print(list.size());
		return list;
	}
	public List<AllcodeModel> get_url_nganluong(){
		List<AllcodeModel>  list= new ArrayList<AllcodeModel>();
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		String query = "SELECT t1.CDTYPE, t1.CDNAME, t1.CDVAL, t1.CDCONTENT, t1.ORDERBY, t1.LOCK, t1.id_a "
				+ "FROM tb_allcode t1 where "
				+ " t1.CDTYPE='NL' and  t1.CDNAME='URL'";
		try{
			con = ConnectDB.getconnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
			
				AllcodeModel item=new AllcodeModel();
				item.setCdcontent(rs.getString("CDCONTENT"));
				item.setCdname(rs.getString("CDNAME"));
				item.setCdtype(rs.getString("CDTYPE"));
				item.setCdval(rs.getString("CDVAL"));
				item.setId_a(rs.getString("id_a"));
				item.setLock(rs.getString("LOCK"));
				item.setOrderby(rs.getString("ORDERBY"));
				list.add(item);
			}
		}catch(Exception ex){
			
		}
		return list;
	}
	public int update_munti_config_nganluong(List<AllcodeModel> list)
			throws InstantiationException, ClassNotFoundException, SQLException{
		Connection dbConnection = null;
		PreparedStatement preparedStatementUpdate = null;
		String sql = "UPDATE `tb_allcode` SET "
				+ " `CDVAL` = ? ,`CDCONTENT` = ? "
				+ " WHERE `id_a` = ? ";
		try {
		dbConnection=ConnectDB.getconnection();
		dbConnection.setAutoCommit(false);
		preparedStatementUpdate = dbConnection.prepareStatement(sql);
			for(int i=0;i<list.size();i++){
				//System.out.println(listLevel.get(i).getId());
				preparedStatementUpdate.setString(1,list.get(i).getCdval());
				preparedStatementUpdate.setString(2,list.get(i).getCdcontent());
				preparedStatementUpdate.setInt(3,Integer.parseInt(list.get(i).getId_a()));
				preparedStatementUpdate.addBatch();
			}
			preparedStatementUpdate.executeBatch();
			dbConnection.commit();
			System.out.println("update_munti_config_nganluong  Done!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			dbConnection.rollback();
			return -1;
		} finally {
			if (preparedStatementUpdate != null) {
				preparedStatementUpdate.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return 0;
	}
	
}
