
package vn.vmall.Imp;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.vmall.DAL.LocationDAL;
import vn.vmall.Entity.Location_Entity;
import vn.vmall.Helper.SearchPaggModel;
import vn.vmall.Interface.LocationInterface;


@Component(value="LocationImp")
public class  LocationImp implements LocationInterface {

	@Autowired
	private LocationDAL DAL;
	
	@Override
	public List<Location_Entity> get_list_search_pagg(SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return DAL.get_list_search_pagg(searchmodel);
	}

	@Override
	public int count_get_list_search_pagg(SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return DAL.count_get_list_search_pagg(searchmodel);
	}

	@Override
	public int add_update_location(String ptype, Location_Entity d) {
		// TODO Auto-generated method stub
		return DAL.add_update_location(ptype, d);

	}

	@Override
	public Location_Entity get_location_by_id(String id) {
		// TODO Auto-generated method stub
		return DAL.get_location_by_id(id);
	}



	@Override
	public int visivled_location(String strproductype,String strparentid,String strlocationids,String visible) 
			throws InstantiationException, ClassNotFoundException, SQLException{
		// TODO Auto-generated method stub
		return DAL.visivled_location( strproductype, strparentid, strlocationids, visible);
	}

	@Override
	public List<Location_Entity> get_alllocation() {
		// TODO Auto-generated method stub
		return DAL.get_alllocation();
	}

	@Override
	public List<Location_Entity> get_datagrip_byparentid(
			SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return DAL.get_datagrip_byparentid(searchmodel);
	}

	@Override
	public int count_get_datagrip_byparentid(SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return DAL.count_get_datagrip_byparentid(searchmodel);
	}

	@Override
	public int detele_single_location(String str_id) {
		// TODO Auto-generated method stub
		 return DAL.detele_single_location( str_id);
	}

	@Override
	public int delete_multi_location(String strproductype,String strparentid,String  strlocationids)
			throws InstantiationException, ClassNotFoundException, SQLException{
		// TODO Auto-generated method stub
		 return DAL.delete_multi_location(  strproductype, strparentid,  strlocationids);
	}

	@Override
	public List<Location_Entity> get_location_byparent(
			String parent_id) {
		// TODO Auto-generated method stub
		return DAL.get_location_byparent(parent_id);
	}

	@Override
	public List<Location_Entity> get_allCity() {
		// TODO Auto-generated method stub
		return DAL.get_allCity();
	}

	@Override
	public List<Location_Entity> get_district_bycity(String city) {
		// TODO Auto-generated method stub
		return DAL.get_district_bycity(city);
	}
		
}