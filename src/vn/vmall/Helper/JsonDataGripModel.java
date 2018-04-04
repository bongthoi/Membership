package vn.vmall.Helper;

import java.util.List;

public class JsonDataGripModel<T> {
	
	private int total =0; //total row count all result query
	
	private List<T> rows;//data list of query result


	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
	
	
}
