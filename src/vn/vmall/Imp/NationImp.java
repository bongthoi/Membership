package vn.vmall.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.vmall.DAL.NationDAL;
import vn.vmall.Entity.Nation_Entity;
import vn.vmall.Interface.NationInterface;

@Component(value="NationImp")
public class NationImp  implements NationInterface{

	@Autowired
	private NationDAL nation;
	@Override
	public List<Nation_Entity> get_allnation() {
		// TODO Auto-generated method stub
		return nation.get_allnation();
	}

	@Override
	public Nation_Entity get_nationbyid(String id) {
		// TODO Auto-generated method stub
		return nation.get_nationbyid(id);
	}

	@Override
	public int add_update_nation(String type, Nation_Entity d) {
		// TODO Auto-generated method stub
		return nation.add_update_nation(type, d);
	}

	@Override
	public int detele_single_nation(String str_id) {
		// TODO Auto-generated method stub
		return nation.detele_single_nation(str_id);
	}

}
