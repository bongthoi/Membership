package vn.vmall.Interface;

import java.util.List;

import vn.vmall.Helper.AllcodeModel;



public interface AllcodeInterface {
	List<AllcodeModel> get_allcode_searchbox(String pcdtype,String cdname);
		
}
