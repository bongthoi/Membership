package vn.vmall.Imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.vmall.DAL.RequirementsDAL;
import vn.vmall.Entity.Requirements_Entity;
import vn.vmall.Helper.SearchPaggModel;
import vn.vmall.Interface.RequirementsInterface;
import vn.vmall.SessionModel.UserSessionModel;

@Component("RequirementsImp")
public class RequirementsImp implements RequirementsInterface{

	@Autowired
	private RequirementsDAL dal;
	
	@Override
	public Requirements_Entity get_Requirementsbyid(String requirement_id) {
		// TODO Auto-generated method stub
		return dal.get_Requirementsbyid(requirement_id);
	}
	
	@Override
	public Map<String, Object> SaveRequirements(String ptype,
			Requirements_Entity d, UserSessionModel userm) {
		// TODO Auto-generated method stub
		return dal.SaveRequirements(ptype, d,userm);
	}

	@Override
	public int visivled_requirement(String str_id, String visible,UserSessionModel userm) {
		// TODO Auto-generated method stub
		return dal.visivled_requirements(str_id, visible,userm);
	}

	@Override
	public int delete_multi_requirement(String str_id,UserSessionModel userm) {
		// TODO Auto-generated method stub
		return dal.delete_multi_requirements(str_id,userm);
	}

	@Override
	public List<Requirements_Entity> get_list_search_pagg(
			SearchPaggModel searchmodel, UserSessionModel userm) {
		// TODO Auto-generated method stub
		return dal.get_list_search_pagg(searchmodel, userm);
	}

	@Override
	public int count_get_list_search_pagg(SearchPaggModel searchmodel,
			UserSessionModel userm) {
		// TODO Auto-generated method stub
		return dal.count_get_list_search_pagg(searchmodel,userm);
	}
	

}
