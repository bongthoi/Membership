/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.vmall.DAL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectDB {
	static  String url_china="jdbc:mysql://114.119.9.64:3306/";
	static  String url_vn= "jdbc:mysql://10.0.10.44:3306/";
	
	static  String url =url_china;
	static  String dbName = "membershipdb";
	static  String driver = "com.mysql.jdbc.Driver";
	static  String userName = "vinhsang";
	static  String password = "44vssql@11042017";
	static  Connection conn = null;
	static  CallableStatement callstore = null;

	public ConnectDB() {
	}

	public static void LoadDriver()  {
		try {
			String jdbc = url + dbName + "?user=" + userName + "&password=" + password + "";
			String jdbcutf8 = jdbc + "&useUnicode=true&characterEncoding=utf8";
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(jdbcutf8);
		} catch (Exception ex) {
			Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		// conn = DriverManager.getConnection(url+dbName,userName,password);
		
	}

	public static Connection getconnection() throws InstantiationException, ClassNotFoundException, SQLException{
		try {
			Class.forName(driver).newInstance();
		} catch (IllegalAccessException ex) {
			Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
		}
		String jdbc = url + dbName + "?user=" + userName + "&password=" + password + "";
		String jdbcutf8 = jdbc + "&useUnicode=true&characterEncoding=utf8";
		Connection	conn2 = DriverManager.getConnection(jdbcutf8);
		return conn2;
	}
	public static ResultSet GetDataReturnResultSet(String query){
		ResultSet rs = null;		
		try {
			if (conn == null || conn.isClosed()) {
				LoadDriver();
			}
			Statement st = conn.createStatement();
			rs = st.executeQuery(query);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}
	public static ResultSet GetDataDirecReturnResultSet(String IDS, String[] p_field, String[] p_values )
			throws SQLException, ClassNotFoundException, InstantiationException {
		ResultSet rs = null;
		if (conn == null || conn.isClosed()) {
			LoadDriver();
		}
		try {
			String query ="SELECT content FROM tb_store where ID = '"+IDS+"'";			
			rs = ConnectDB.GetDataReturnResultSet(query);
			String content_query="";
			while(rs.next()){
				content_query = rs.getString("content");
				break;
			}
			
			if(content_query.length()>0){
				for(int i=0;i<p_field.length;i++){
					content_query = content_query.replace(p_field[i], p_values[i]);
				}	
				
				rs = ConnectDB.GetDataReturnResultSet(content_query);
			}
			else{
				rs = null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}
	public static int ExecUpdate(String query) {
		int result = -1;
		try {
			if (conn == null || conn.isClosed()) {
				LoadDriver();
			}
			Statement st = conn.createStatement();
			st.executeUpdate(query);
			result = 0;
		} catch (Exception ex) {
			result = -1;
		}
		return result;
	}

	public static CallableStatement get_CallableStatement_procedure_normal(String spname, String[] p_field, Object[] p_values,Connection conect)
		 {
		try {
			String query = null;
			query = "{call " + spname;
			if (p_field.length != p_values.length)
				return null;
			// set field
			for (int i = 0; i < p_field.length; i++) {
				if (i == 0) {
					query += "(";
				}
				query += "?";
				if (i + 1 != p_field.length) {
					query += ",";
				}
			}
			query += ")}";
			CallableStatement call= conect.prepareCall(query);;
			for (int i = 0; i < p_field.length; i++) {
				call.setObject(p_field[i], p_values[i]);
			}
			return call;	

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	
	}

	
	
	public static ResultSet ExecBoFunctionReturnResutlSet(String spname, String[] p_field, Object[] p_values)
			throws SQLException, ClassNotFoundException, InstantiationException {
		ResultSet rs = null;
		if (conn == null || conn.isClosed()) {
			LoadDriver();
		}
		try {
			String query = null;
			query = "{call " + spname;
			if (p_field.length != p_values.length)
				return null;
			// set field
			for (int i = 0; i < p_field.length; i++) {
				if (i == 0) {
					query += "(";
				}
				query += "?";
				if (i + 1 != p_field.length) {
					query += ",";
				}
			}
			query += ")}";
			//System.out.println(query);
			callstore = conn.prepareCall(query);
			// set value
			for (int i = 0; i < p_field.length; i++) {
				callstore.setObject(p_field[i], p_values[i]);
			}
			rs = callstore.executeQuery();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return rs;
	}

	public static ResultSet ExecBoFunctionReturnResutlSet(String spname)
			throws SQLException, ClassNotFoundException, InstantiationException {
		String query = null;
		ResultSet rs = null;
		query = "{call " + spname + "}";
		if (conn == null || conn.isClosed()) {
			LoadDriver();
		}
		try {
			callstore = conn.prepareCall(query);
			rs = callstore.executeQuery();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}

	public static int ExecBoFunction(String spname, String[] p_field, Object[] p_values)
			throws SQLException, ClassNotFoundException, InstantiationException {
		int _result = 0;
		if (conn == null || conn.isClosed()) {
			LoadDriver();
		}
		try {
			String query = null;
			query = "{call " + spname;
			if (p_field.length != p_values.length)
				return -1;
			// set field
			for (int i = 0; i < p_field.length; i++) {
				if (i == 0) {
					query += "(";
				}
				query += "?";
				if (i + 1 != p_field.length) {
					query += ",";
				}
			}
			query += ")}";

			callstore = conn.prepareCall(query);
			// set value
			for (int i = 0; i < p_field.length; i++) {
				callstore.setObject(p_field[i], p_values[i]);
			}
			callstore.executeQuery();// insert,update,delete

		} catch (Exception ex) {
			ex.printStackTrace();
			_result = -1;
		}

		return _result;
	}

	public static Map<String, Object> ExecBoFunctionReturnList(String spname, String[] p_field, String[] p_type,
			Object[] p_values, int[] p_direction) throws SQLException, ClassNotFoundException, InstantiationException {
		Map<String, Object> mapOfObjects = new HashMap<String, Object>();
		try {
			String query = null;
			query = "{call " + spname;
			if (conn == null || conn.isClosed()) {
				LoadDriver();
			}
			if (p_field.length != p_values.length)
				return null;
			for (int i = 0; i < p_field.length; i++) {
				if (i == 0) {
					query += "(";
				}
				query += "?";
				if (i + 1 != p_field.length) {
					query += ",";
				}
			}
			query += ")}";

			callstore = conn.prepareCall(query);
			for (int i = 0; i < p_field.length; i++) {
				if (p_direction[i] == 0) {
					callstore.setObject(p_field[i], p_values[i]);
				} else if (p_direction[i] == 1) {
					switch (p_type[i]) {
					case "BIGINT":
						callstore.registerOutParameter(p_field[i], java.sql.Types.BIGINT);
						break;
					case "BINARY":
						callstore.registerOutParameter(p_field[i], java.sql.Types.BINARY);
						break;
					case "CHAR":
						callstore.registerOutParameter(p_field[i], java.sql.Types.CHAR);
						break;
					case "DATE":
						callstore.registerOutParameter(p_field[i], java.sql.Types.DATE);
						break;
					case "DECIMAL":
						callstore.registerOutParameter(p_field[i], java.sql.Types.DECIMAL);
						break;
					case "DOUBLE":
						callstore.registerOutParameter(p_field[i], java.sql.Types.DOUBLE);
						break;
					case "FLOAT":
						callstore.registerOutParameter(p_field[i], java.sql.Types.FLOAT);
						break;
					case "INT":
						callstore.registerOutParameter(p_field[i], java.sql.Types.INTEGER);
						break;
					case "VARCHAR":
						callstore.registerOutParameter(p_field[i], java.sql.Types.VARCHAR);
						break;
					default:
						callstore.registerOutParameter(p_field[i], java.sql.Types.NULL);
						break;
					}
				}
			}
			callstore.executeQuery();
			for (int i = 0; i < p_direction.length; i++) {
				if (p_direction[i] == 1) {
					//
					mapOfObjects.put(p_field[i], callstore.getObject(p_field[i]));
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			try{
				if(callstore!=null){
					callstore.close();
				}
				if(conn!=null){
					conn.close();
				}		
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		return mapOfObjects;
	}
	
	public static Map<String, Object> call_procedure_outlist(String spname, String[] p_field, String[] p_type,
			Object[] p_values, int[] p_direction,Connection con) throws SQLException, ClassNotFoundException, InstantiationException {
		Map<String, Object> mapOfObjects = new HashMap<String, Object>();
		CallableStatement call=null;
		try {
			String query = null;
			query = "{call " + spname;
			if (p_field.length != p_values.length)
				return null;
			for (int i = 0; i < p_field.length; i++) {
				if (i == 0) {
					query += "(";
				}
				query += "?";
				if (i + 1 != p_field.length) {
					query += ",";
				}
			}
			query += ")}";
			call = con.prepareCall(query);
			for (int i = 0; i < p_field.length; i++) {
				if (p_direction[i] == 0) {
					call.setObject(p_field[i], p_values[i]);
				} else if (p_direction[i] == 1) {
					switch (p_type[i]) {
					case "BIGINT":
						call.registerOutParameter(p_field[i], java.sql.Types.BIGINT);
						break;
					case "BINARY":
						call.registerOutParameter(p_field[i], java.sql.Types.BINARY);
						break;
					case "CHAR":
						call.registerOutParameter(p_field[i], java.sql.Types.CHAR);
						break;
					case "DATE":
						call.registerOutParameter(p_field[i], java.sql.Types.DATE);
						break;
					case "DECIMAL":
						call.registerOutParameter(p_field[i], java.sql.Types.DECIMAL);
						break;
					case "DOUBLE":
						call.registerOutParameter(p_field[i], java.sql.Types.DOUBLE);
						break;
					case "FLOAT":
						call.registerOutParameter(p_field[i], java.sql.Types.FLOAT);
						break;
					case "INT":
						call.registerOutParameter(p_field[i], java.sql.Types.INTEGER);
						break;
					case "VARCHAR":
						call.registerOutParameter(p_field[i], java.sql.Types.VARCHAR);
						break;
					default:
						call.registerOutParameter(p_field[i], java.sql.Types.NULL);
						break;
					}
				}
			}
			call.executeQuery();
			for (int i = 0; i < p_direction.length; i++) {
				if (p_direction[i] == 1) {
					//
					mapOfObjects.put(p_field[i], call.getObject(p_field[i]));
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			try{
				if(call!=null){
					call.close();
				}
				if(con!=null){
					con.close();
				}		
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		
		return mapOfObjects;
	}
	
}
