package vn.vmall.AdminController;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import vn.vmall.Entity.Requirements_Entity;
import vn.vmall.Helper.ErrorMesage;
import vn.vmall.Helper.ErrorMessageModel;
import vn.vmall.Helper.JsonDataGripModel;
import vn.vmall.Helper.SearchPaggModel;
import vn.vmall.Interface.RequirementsInterface;
import vn.vmall.SessionModel.LangSessionModel;
import vn.vmall.SessionModel.UserSessionModel;

@RestController
@RequestMapping(value="ad/RequirementsController")
public class ad_RequirementsController {

	 @Autowired
	 @Qualifier("RequirementsImp")
	 private RequirementsInterface RequirementsImp;
	 
	 
	 @RequestMapping(value="/get_Requirementsbyid",
				method=RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public Requirements_Entity get_Requirementsbyid(@RequestParam(value ="requirement_id",required = true) String requirement_id){		
			return  RequirementsImp.get_Requirementsbyid(requirement_id);
	 }
	 
	 @RequestMapping(value="/get_json_append_to_datagrip.json",
				method=RequestMethod.POST,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

		public JsonDataGripModel get_json_append_to_datagrip(
				@RequestParam(value ="page",required = false,defaultValue="1") int page,
				@RequestParam(value ="rows",required = false,defaultValue="10") int rows,
				@RequestParam(value ="col",required = false,defaultValue="") String col,
				@RequestParam(value ="val",required = false,defaultValue="") String val,
				HttpSession session){
			SearchPaggModel searchmodel=new SearchPaggModel();
			searchmodel.setPage(page);
			searchmodel.setRows(rows);
			searchmodel.setCol(col);
			searchmodel.setVal(val);
			 UserSessionModel userm =new UserSessionModel();
			 userm=userm.getss_thisMODEL(session);
			JsonDataGripModel<Requirements_Entity> GripModel =new JsonDataGripModel<Requirements_Entity>();
			GripModel.setRows(RequirementsImp.get_list_search_pagg(searchmodel,userm));
			GripModel.setTotal(RequirementsImp.count_get_list_search_pagg(searchmodel,userm));
			return GripModel;
		}
	 @RequestMapping(value="/SaveRequirements",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel SaveRequirements(
				@RequestParam(value ="ptype",required = true) String ptype,
				@RequestParam(value ="requirement_id",required = true) String requirement_id,
				@RequestParam(value ="product_price_limit",required = true) float product_price_limit,
				@RequestParam(value ="quantity",required = true) float quantity,
				@RequestParam(value ="product_image",required = false,defaultValue="") String product_image,
				@RequestParam(value ="description",required = false,defaultValue="") String description,
				@RequestParam(value ="budget",required = true) float budget,
				HttpSession session
			    ){
		 Requirements_Entity d=new Requirements_Entity();
		 d.setBudget(budget);
		 d.setDescription(description);
		 d.setProduct_image(product_image);
		 d.setProduct_price_limit(product_price_limit);
		 d.setQuantity(quantity);
		 d.setRequirement_id(requirement_id);
		 	UserSessionModel userm =new UserSessionModel();
			userm=userm.getss_thisMODEL(session);
			 LangSessionModel langmodel=new LangSessionModel();
			 langmodel=langmodel.getss_thisMODEL(session);
			//System.out.println(userm.getId() + " "+ userm.getName());
			//System.out.print(ptype+"-"+product_type_id+"-"+parentlocation+"-"+locationid+"-"+locationname);
			Map<String,Object> mapOfObjects = new HashMap<String,Object>();
			mapOfObjects =  RequirementsImp.SaveRequirements(ptype, d,userm);
			ErrorMessageModel e=new ErrorMessageModel();
				e=ErrorMesage.get_json_mes_error(Integer.parseInt(mapOfObjects.get("f").toString()),langmodel.getLang_value());
				e.setOut_id(mapOfObjects.get("out_id").toString());
			return e;
		}
	 @RequestMapping(value="/visivled_requirement",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel visivled_requirement(
				@RequestParam(value ="str_id",required = true) String str_id,
				@RequestParam(value ="visible",required = true) String visible,
				HttpSession session
				){
			UserSessionModel userm =new UserSessionModel();
			userm=userm.getss_thisMODEL(session);
			 LangSessionModel langmodel=new LangSessionModel();
			 langmodel=langmodel.getss_thisMODEL(session);
			ErrorMessageModel e=new ErrorMessageModel();
			int f= RequirementsImp.visivled_requirement(str_id,visible,userm);
			e=ErrorMesage.get_json_mes_error(f,langmodel.getLang_value());
			return e;
		}
		@RequestMapping(value="/delete_multi_requirement",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel delete_multi_requirement(
				@RequestParam(value ="str_id",required = true) String str_id,
				HttpSession session
				){	
			UserSessionModel userm =new UserSessionModel();
			userm=userm.getss_thisMODEL(session);
			 LangSessionModel langmodel=new LangSessionModel();
			 langmodel=langmodel.getss_thisMODEL(session);
			ErrorMessageModel e=new ErrorMessageModel();
			int f= RequirementsImp.delete_multi_requirement(str_id,userm);
			e=ErrorMesage.get_json_mes_error(f,langmodel.getLang_value());
			return e;
		}
}
