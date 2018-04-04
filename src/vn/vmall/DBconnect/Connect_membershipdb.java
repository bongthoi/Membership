/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.vmall.DBconnect;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


@Component
public class Connect_membershipdb {

	@Autowired 
	@Qualifier("dataSource_membershipdb")
	private DataSource  dataSource;
	public Connection getConnection(){
		try {
			return this.dataSource.getConnection();
		}catch (Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public CallableStatement get_callstore(String spname, String[] p_field, Object[] p_values,Connection con ) throws SQLException{
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
		CallableStatement callstore = con.prepareCall(query);
		// set value
		for (int i = 0; i < p_field.length; i++) {
			callstore.setObject(p_field[i], p_values[i]);
		}
		return callstore;
	}

	
	public  Map<String, Object> ExecBoFunctionReturnList(String spname, String[] p_field, String[] p_type,
			Object[] p_values, int[] p_direction) throws SQLException, ClassNotFoundException, InstantiationException {
		Map<String, Object> mapOfObjects = new HashMap<String, Object>();
		Connection conn = null;
		CallableStatement callstore = null;
		try {
			
			String query = null;
			query = "{call " + spname;
			if (conn == null || conn.isClosed()) {
				conn=this.dataSource.getConnection();
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
	
}
