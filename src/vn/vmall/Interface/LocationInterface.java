package vn.vmall.Interface;

import java.sql.SQLException;
import java.util.List;

import vn.vmall.Entity.Location_Entity;
import vn.vmall.Helper.SearchPaggModel;



public interface LocationInterface {
	List<Location_Entity> get_list_search_pagg(SearchPaggModel searchmodel);
	 int count_get_list_search_pagg(SearchPaggModel searchmodel);
	 int add_update_location(String ptype, Location_Entity d);
	 Location_Entity get_location_by_id(String id);
	 int visivled_location(String strproductype,String strparentid,String strlocationids,String visible)
			 	throws InstantiationException, ClassNotFoundException, SQLException;
	 List<Location_Entity> get_alllocation();
	List<Location_Entity> get_datagrip_byparentid(SearchPaggModel searchmodel);
	int count_get_datagrip_byparentid(SearchPaggModel searchmodel);
	int detele_single_location(String str_id);
	int delete_multi_location(String strproductype,String strparentid,String  strlocationids)
		throws InstantiationException, ClassNotFoundException, SQLException;
	List<Location_Entity> get_location_byparent(String parent_id);
	List<Location_Entity> get_district_bycity(
			String city);
	List<Location_Entity> get_allCity();
}
