package vn.vmall.Helper;


import java.util.Map;

public class SearchPaggModel {
	private final int default_recore_perpage=10;
	private int page;
	private int rows;
	private int offset;
	private String fromDate;
	private String toDate;
	private String col;
	private String val;
	
	private String parent;
	
	private String custom_value;
	
	public String getParent() {
		return parent;
	}



	public void setParent(String parent) {
		this.parent = parent;
	}



	private Map<String,String> col_and_val_search;

	public int getPage() {
		return page;
	}

	

	public int getRows() {
		return rows;
	}



	public void setPage(int page) {
		this.page = page;
	}



	public void setRows(int rows) {
		this.rows = rows;
	}

	
	
	

	public String getCol() {
		return col;
	}



	public void setCol(String col) {
		this.col = col;
	}



	public String getVal() {
		return val;
	}



	public void setVal(String val) {
		this.val = val;
	}



	public int getOffset() {
		return (this.page-1)*this.rows;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String post_param_fromDate) {
		this.fromDate = isset(post_param_fromDate) ? (post_param_fromDate) : "";
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String post_param_toDate) {
		this.toDate = isset(post_param_toDate) ? (post_param_toDate) :"";
	}

	public Map<String, String> getCol_and_val_search() {
		return col_and_val_search;
	}

	public void setCol_and_val_search(Map<String, String> col_and_val_search) {
		this.col_and_val_search = col_and_val_search;
	}
	
	
	
	public static boolean isset(String fieldName)   {
	   // String fieldValue = request.getParameter(fieldName);
	    if (fieldName == null || fieldName.trim().isEmpty()) {
	       return false;
	    }
	    return true;
	}



	public String getCustom_value() {
		return custom_value;
	}



	public void setCustom_value(String custom_value) {
		this.custom_value = custom_value;
	}
	

}
