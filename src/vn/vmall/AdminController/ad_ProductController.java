package vn.vmall.AdminController;

import java.util.HashMap;
import java.util.Map;

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







import vn.vmall.Entity.Product_Entity;
import vn.vmall.Helper.ErrorMesage;
import vn.vmall.Helper.ErrorMessageModel;
import vn.vmall.Helper.JsonDataGripModel;
import vn.vmall.Helper.SearchPaggModel;
import vn.vmall.Interface.ProductInterface;
import vn.vmall.SessionModel.LangSessionModel;
import vn.vmall.SessionModel.UserSessionModel;



@RestController
@RequestMapping(value="ad/ProductController")
public class ad_ProductController {

	 @Autowired
	 @Qualifier("ProductImp")
	 private ProductInterface ProductImp;
	 
	 

	 
	 @RequestMapping(value="/get_Productbyid",
				method=RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public Product_Entity get_Productbyid(@RequestParam(value ="product_id",required = true) String product_id){		
			return  ProductImp.get_Productbyid(product_id);
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
			JsonDataGripModel<Product_Entity> GripModel =new JsonDataGripModel<Product_Entity>();
			GripModel.setRows(ProductImp.get_list_search_pagg(searchmodel,userm));
			GripModel.setTotal(ProductImp.count_get_list_search_pagg(searchmodel,userm));
			return GripModel;
		}
	 @RequestMapping(value="/SaveProduct",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel SaveProduct(
				@RequestParam(value ="ptype",required = true) String ptype,
				@RequestParam(value ="product_id",required = true) String product_id,
				@RequestParam(value ="product_name",required = true) String product_name,
				@RequestParam(value ="product_price",required = true) float product_price,
				@RequestParam(value ="product_image",required = false,defaultValue="") String product_image,
				@RequestParam(value ="product_description",required = false,defaultValue="") String product_description,
				@RequestParam(value ="lang_code",required = true) String lang_code,
				HttpSession session){
		 UserSessionModel userm =new UserSessionModel();
			userm=userm.getss_thisMODEL(session);
			 LangSessionModel langmodel=new LangSessionModel();
			 langmodel=langmodel.getss_thisMODEL(session);
		 Product_Entity d=new Product_Entity();
			d.setLang_code(lang_code);
			d.setProduct_description(product_description);
			d.setProduct_id(product_id);
			d.setProduct_image(product_image);
			d.setProduct_name(product_name);
			d.setProduct_price(product_price);
			d.setSeller_id(userm.getUser_seller().getEmail());
			
			Map<String,Object> mapOfObjects = new HashMap<String,Object>();
			mapOfObjects =  ProductImp.SaveProduct(ptype, d,userm);
			ErrorMessageModel e=new ErrorMessageModel();
				e=ErrorMesage.get_json_mes_error(Integer.parseInt(mapOfObjects.get("f").toString()),langmodel.getLang_value());
				e.setOut_id(mapOfObjects.get("out_id").toString());
			return e;
		}
	 @RequestMapping(value="/visivled_product",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel visivled_product(
				@RequestParam(value ="str_id",required = true) String str_id,
				@RequestParam(value ="visible",required = true) String visible,
				HttpSession session){
		 UserSessionModel userm =new UserSessionModel();
			userm=userm.getss_thisMODEL(session);
			 LangSessionModel langmodel=new LangSessionModel();
			 langmodel=langmodel.getss_thisMODEL(session);
			ErrorMessageModel e=new ErrorMessageModel();
			int f= ProductImp.visivled_product(str_id,visible,userm);
			System.out.println(langmodel.getLang_value());
			e=ErrorMesage.get_json_mes_error(f,langmodel.getLang_value());
			return e;
		}
		@RequestMapping(value="/delete_multi_product",
				method=RequestMethod.POST,
				produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
		
		public ErrorMessageModel delete_multi_product(
				@RequestParam(value ="str_id",required = true) String str_id,
				HttpSession session){	
			 UserSessionModel userm =new UserSessionModel();
				userm=userm.getss_thisMODEL(session);
				 LangSessionModel langmodel=new LangSessionModel();
				 langmodel=langmodel.getss_thisMODEL(session);
			ErrorMessageModel e=new ErrorMessageModel();
			int f= ProductImp.delete_multi_product(str_id,userm);
			e=ErrorMesage.get_json_mes_error(f,langmodel.getLang_value());
			return e;
		}
}
