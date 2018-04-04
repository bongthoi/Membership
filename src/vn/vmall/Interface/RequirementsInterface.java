package vn.vmall.Interface;

import java.util.List;
import java.util.Map;

import vn.vmall.Entity.Requirements_Entity;
import vn.vmall.Helper.SearchPaggModel;
import vn.vmall.SessionModel.UserSessionModel;

public interface RequirementsInterface {

	Requirements_Entity get_Requirementsbyid(String requirement_id);

	List<Requirements_Entity> get_list_search_pagg(SearchPaggModel searchmodel, UserSessionModel userm);

	int count_get_list_search_pagg(SearchPaggModel searchmodel, UserSessionModel userm);

	Map<String, Object> SaveRequirements(String ptype, Requirements_Entity d, UserSessionModel userm);

	int visivled_requirement(String str_id, String visible, UserSessionModel userm);

	int delete_multi_requirement(String str_id,UserSessionModel userm);

}
