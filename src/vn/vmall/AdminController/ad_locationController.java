
package vn.vmall.AdminController;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import vn.vmall.Entity.Location_Entity;


import vn.vmall.Helper.ErrorMesage;
import vn.vmall.Helper.ErrorMessageModel;
import vn.vmall.Helper.JsonDataGripModel;
import vn.vmall.Helper.SearchPaggModel;
import vn.vmall.Interface.LocationInterface;
import vn.vmall.SessionModel.LangSessionModel;



@RestController
@RequestMapping(value="ad/locationController")
//@SessionAttributes(value = "user", types = {UserSessionModel.class})
public class ad_locationController {
		
	 @Autowired
	 @Qualifier("LocationImp")
	 private LocationInterface  LocationImp;
	 
	 
	 @RequestMapping(value="/get_location_by_id",
				method=RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

		public Location_Entity get_location_by_id(	
				@RequestParam(value ="id",required = true) String id){		
			return  LocationImp.get_location_by_id(id);
			}
		
		@RequestMapping(value="/get_json_append_to_datagrip.json",
				method=RequestMethod.POST,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

		public JsonDataGripModel get_json_append_to_datagrip(
				@RequestParam(value ="page",required = false,defaultValue="1") int page,
				@RequestParam(value ="rows",required = false,defaultValue="10") int rows,
				@RequestParam(value ="col",required = false,defaultValue="") String col,
				@RequestParam(value ="val",required = false,defaultValue="") String val){
			SearchPaggModel searchmodel=new SearchPaggModel();
			searchmodel.setPage(page);
			searchmodel.setRows(rows);
			searchmodel.setCol(col);
			searchmodel.setVal(val);
			JsonDataGripModel<Location_Entity> GripModel =new JsonDataGripModel<Location_Entity>();
			GripModel.setRows( LocationImp.get_list_search_pagg(searchmodel));
			GripModel.setTotal( LocationImp.count_get_list_search_pagg(searchmodel));
			return GripModel;
		}
		@RequestMapping(value="/get_datagrip_byparentid.json",
				method=RequestMethod.POST,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

		public JsonDataGripModel get_datagrip_byproducttypeid(
				@RequestParam(value ="page",required = false,defaultValue="1") int page,
				@RequestParam(value ="rows",required = false,defaultValue="10") int rows,
				@RequestParam(value ="parentid",required = false,defaultValue="") String parentid){
			SearchPaggModel searchmodel=new SearchPaggModel();
			searchmodel.setPage(page);
			searchmodel.setRows(rows);
			searchmodel.setCustom_value(parentid);
			JsonDataGripModel<Location_Entity> GripModel =new JsonDataGripModel<Location_Entity>();
			GripModel.setRows(LocationImp.get_datagrip_byparentid(searchmodel));
			GripModel.setTotal(LocationImp.count_get_datagrip_byparentid(searchmodel));
			return GripModel;
		}
		
		
		@RequestMapping(value="/add_update_location",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel add_update_location(
				//@ModelAttribute("user") UserSessionModel user,
				@RequestParam(value ="ptype",required = true) String ptype,
				@RequestParam(value ="parent",required = true) String parent,
				@RequestParam(value ="locationid",required = true) String locationid,
				@RequestParam(value ="locationname",required = true) String locationname,
				HttpSession session){
			 LangSessionModel langmodel=new LangSessionModel();
			 langmodel=langmodel.getss_thisMODEL(session);
			Location_Entity d=new Location_Entity();
			d.setParent(parent);
			d.setLocation_id(locationid);
			d.setLocation_name(locationname);
			//System.out.print(ptype+"-"+product_type_id+"-"+parentlocation+"-"+locationid+"-"+locationname);
			ErrorMessageModel e=new ErrorMessageModel();
			int f=  LocationImp.add_update_location(ptype, d);
			e=ErrorMesage.get_json_mes_error(f,langmodel.getLang_value());
			return e;
		}
		
		@RequestMapping(value="/add_update_city",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel add_update_city(
				//@ModelAttribute("user") UserSessionModel user,
				@RequestParam(value ="ptype",required = true) String ptype,
				@RequestParam(value ="cityid",required = true) String cityid,
				@RequestParam(value ="cityname",required = true) String cityname,
				HttpSession session){
			 LangSessionModel langmodel=new LangSessionModel();
			 langmodel=langmodel.getss_thisMODEL(session);
			Location_Entity d=new Location_Entity();
			d.setLocation_id(cityid);
			d.setLocation_name(cityname);
			d.setParent("0");
			ErrorMessageModel e=new ErrorMessageModel();
			int f=  LocationImp.add_update_location(ptype, d);
			
			e=ErrorMesage.get_json_mes_error(f,langmodel.getLang_value());
			
			return e;
		}
		
		@RequestMapping(value="/delete_multi_location",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel delete_multi_location(
				@RequestParam(value ="strthanhpho",required = true) String strthanhpho,
				@RequestParam(value ="strquan",required = true) String strquan,
				@RequestParam(value ="strphuong",required = true) String strphuong,
				HttpSession session)
						throws InstantiationException, ClassNotFoundException, SQLException	{
			 LangSessionModel langmodel=new LangSessionModel();
			 langmodel=langmodel.getss_thisMODEL(session);
			ErrorMessageModel e=new ErrorMessageModel();
			int f= LocationImp.delete_multi_location(strthanhpho,strquan,strphuong);
			e=ErrorMesage.get_json_mes_error(f,langmodel.getLang_value());
			return e;
			
			
		}
		
		@RequestMapping(value="/detele_single_location",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel detele_single_location(@RequestParam(value ="str_id",required = true) String str_id
				,HttpSession session){
			 LangSessionModel langmodel=new LangSessionModel();
			 langmodel=langmodel.getss_thisMODEL(session);
			ErrorMessageModel e=new ErrorMessageModel();
			int f= LocationImp.detele_single_location(str_id);
			e=ErrorMesage.get_json_mes_error(f,langmodel.getLang_value());
			return e;
			
			
		}
		
		@RequestMapping(value="/visivled_location_3option",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel visivled_location(
				@RequestParam(value ="strthanhpho",required = true) String strthanhpho,
				@RequestParam(value ="strquan",required = true) String strquan,
				@RequestParam(value ="strphuong",required = true) String strphuong,
				@RequestParam(value ="visible",required = true) String visible
				,HttpSession session)
						throws InstantiationException, ClassNotFoundException, SQLException{
			 LangSessionModel langmodel=new LangSessionModel();
			 langmodel=langmodel.getss_thisMODEL(session);
			ErrorMessageModel e=new ErrorMessageModel();
			int f= LocationImp.visivled_location(strthanhpho,strquan,strphuong,visible);
			e=ErrorMesage.get_json_mes_error(f,langmodel.getLang_value());
			return e;
			
			
		}
		
		@RequestMapping(value="/get_district_bycity",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public List<Location_Entity> get_location_parent_byproducttypeid(
				@RequestParam(value ="city",required = true) String city){
		return  LocationImp.get_district_bycity(city);
	}		
		@RequestMapping(value="/get_ward_bydistrict",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public List<Location_Entity> get_ward_bydistrict(
				@RequestParam(value ="district",required = true) String district){
		return  LocationImp.get_district_bycity(district);
	}		
	@RequestMapping(value="/get_location_byparent",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public List<Location_Entity> get_location_byparent(
				@RequestParam(value ="paren_id",required = true) String paren_id){
		return  LocationImp.get_location_byparent(paren_id);
		
	}	
		
		
	@RequestMapping(value="/get_alllocation",
				method=RequestMethod.GET,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public List<Location_Entity> get_alllocation(){
		return  LocationImp.get_alllocation();
		
	}
	@RequestMapping(value="/get_allCity",
				method=RequestMethod.GET,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public List<Location_Entity> get_allCity(){
		return  LocationImp.get_allCity();
		
	}
		
		
}