package vn.vmall.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.vmall.DAL.AllcodeDAL;
import vn.vmall.Helper.AllcodeModel;
import vn.vmall.Interface.AllcodeInterface;

@Component(value="AllcodeImp")
public class AllcodeImp implements AllcodeInterface {
	@Autowired
	private AllcodeDAL DAL;
	@Override
	public List<AllcodeModel> get_allcode_searchbox(String pcdtype,
			String cdname) {
		// TODO Auto-generated method stub
		return DAL.get_allcode_searchbox(pcdtype, cdname);
	}

	
	
}
