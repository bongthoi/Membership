package vn.vmall.AdminController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.vmall.Helper.AllcodeModel;
import vn.vmall.Interface.AllcodeInterface;




@RestController
@RequestMapping(value="ad/AllcodeController")
public class ad_AllcodeController {
	
	 @Autowired
	 @Qualifier("AllcodeImp")
	 private AllcodeInterface AllcodeImp;
	 
	 @RequestMapping(value="/get_allcode_searchbox.json",
				method=RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_VALUE)
		public List<AllcodeModel>  get_allcode_searchbox(
				@RequestParam(value ="pcdtype",required = true) String pcdtype,
				@RequestParam(value ="pcdname",required = true) String pcdname){
		 return AllcodeImp.get_allcode_searchbox(pcdtype, pcdname);
		// return null;
		 
	 }
	 
}
