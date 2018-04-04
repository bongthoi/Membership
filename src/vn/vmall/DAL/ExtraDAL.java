package vn.vmall.DAL;

import java.sql.ResultSet;

public class ExtraDAL {
	public static Boolean is_publish(){
		String query = "";
		ResultSet rs = null;
		try{
			query = "SELECT *  FROM dbvmall.tb_config where id = 'PUBLISH' and isvisible = true;";
			rs = ConnectDB.GetDataReturnResultSet(query);
			while(rs.next()){
				return true;
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}
}
