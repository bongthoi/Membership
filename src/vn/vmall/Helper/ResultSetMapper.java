package vn.vmall.Helper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.apache.commons.beanutils.BeanUtils;


public class ResultSetMapper<T> {
	@SuppressWarnings("unchecked")
	public List<T> mapRersultSetToObject(ResultSet rs, Class outputClass) {
		List<T> outputList = null;
		try {
			// make sure resultset is not null
			if (rs != null) {
				// check if outputClass has 'Entity' annotation
				if (outputClass.isAnnotationPresent(Entity.class)) {
					// get the resultset metadata
					ResultSetMetaData rsmd = rs.getMetaData();
					// get all the attributes of outputClass
					Field[] fields = outputClass.getDeclaredFields();
					while (rs.next()) {
						T bean = (T) outputClass.newInstance();
						for (int _iterator = 0; _iterator < rsmd
								.getColumnCount(); _iterator++) {
							// getting the SQL column name
							String columnName = rsmd
									.getColumnName(_iterator + 1).toLowerCase();
							// reading the value of the SQL column
							Object columnValue = rs.getObject(_iterator + 1);
							// iterating over outputClass attributes to check if any attribute has 'Column' annotation with matching 'name' value
							for (Field field : fields) {
								if (field.isAnnotationPresent(Column.class)) {
									Column column = field
											.getAnnotation(Column.class);
									if (column.name().equalsIgnoreCase(
											columnName)
											&& columnValue != null) {
										BeanUtils.setProperty(bean, field
												.getName(), columnValue);
										
										break;
									}
								}
							}
						}
						if (outputList == null) {
							outputList = new ArrayList<T>();
						}
						outputList.add(bean);
					}

				} else {
					// throw some error
				}
			} else {
				return null;
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return outputList;
	}
	
	public T mapRersultSetToObject_singlerow (ResultSet rs, Class outputClass) {
		T bean=null;
		try {
			// make sure resultset is not null
			if (rs != null) {
				// check if outputClass has 'Entity' annotation
				if (outputClass.isAnnotationPresent(Entity.class)) {
					// get the resultset metadata
					ResultSetMetaData rsmd = rs.getMetaData();
					// get all the attributes of outputClass
					Field[] fields = outputClass.getDeclaredFields();
					if (!rs.next()){
						//System.out.println("nulu1");
						return null;
					}
					else{
						rs.first();
						bean = (T) outputClass.newInstance();
						for (int _iterator = 0; _iterator < rsmd
								.getColumnCount(); _iterator++) {
							// getting the SQL column name
							String columnName = rsmd
									.getColumnName(_iterator + 1).toLowerCase();
							// reading the value of the SQL column
							Object columnValue = rs.getObject(_iterator + 1);
							// iterating over outputClass attributes to check if any attribute has 'Column' annotation with matching 'name' value
							for (Field field : fields) {
								if (field.isAnnotationPresent(Column.class)) {
									Column column = field
											.getAnnotation(Column.class);
									if (column.name().equalsIgnoreCase(
											columnName)
											&& columnValue != null) {
										BeanUtils.setProperty(bean, field
												.getName(), columnValue);
										
										break;
									}
								}
							}//end for field
						}//end for interactor
					}
					

				} else {
					// throw some error
					//System.out.println("nulu133");
				}
			} else {
				//System.out.println("nulu1222");
				return null;
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return bean;
		
		
	}
}