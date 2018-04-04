package vn.vmall.DAL;

import java.sql.ResultSet;
public class Errordal {

	public static String getMesageError(int error,String lang){
		String query = "";
		String mes = "";
		ResultSet rs = null;
		try{
			String col_mes= "ErrorMsg_"+lang;
			query = "select ID,"+col_mes+" from tb_error where Id='"+error+"'";
			rs = ConnectDB.GetDataReturnResultSet(query);
			while(rs.next()){
				mes = rs.getString(col_mes);
				break;
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return mes;
	}
	public static void main(String[] args) {
		System.out.print(getMesageError(1,"VN"));
	}
}
