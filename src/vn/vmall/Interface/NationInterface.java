package vn.vmall.Interface;

import java.util.List;

import vn.vmall.Entity.Nation_Entity;

public interface NationInterface {

	List<Nation_Entity> get_allnation();
	Nation_Entity get_nationbyid(String id);
	int add_update_nation(String type, Nation_Entity d);
	int detele_single_nation(String str_id);
	
}
