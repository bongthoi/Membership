package vn.vmall.AdminController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import vn.vmall.Entity.ProductTrans_Entity;
import vn.vmall.Helper.ErrorMesage;
import vn.vmall.Helper.ErrorMessageModel;
import vn.vmall.Helper.JsonDataGripModel;
import vn.vmall.Helper.SearchPaggModel;
import vn.vmall.Interface.ProductTransInterface;




@RestController
@RequestMapping(value="ad/ProductTransController")
public class ad_ProductTransController {
	 @Autowired
	 @Qualifier("ProductTransImp")
	 private ProductTransInterface ProductTransImp;
	
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

			JsonDataGripModel<ProductTrans_Entity> GripModel =new JsonDataGripModel<ProductTrans_Entity>();
			GripModel.setRows(ProductTransImp.get_list_search_pagg(searchmodel));
			GripModel.setTotal(ProductTransImp.count_get_list_search_pagg(searchmodel));
			return GripModel;
		}
	 @RequestMapping(value="/get_json_translated.json",
				method=RequestMethod.POST,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

		public JsonDataGripModel get_json_translated(
				@RequestParam(value ="page",required = false,defaultValue="1") int page,
				@RequestParam(value ="rows",required = false,defaultValue="10") int rows,
				@RequestParam(value ="col",required = false,defaultValue="") String col,
				@RequestParam(value ="val",required = false,defaultValue="") String val){
			SearchPaggModel searchmodel=new SearchPaggModel();
			searchmodel.setPage(page);
			searchmodel.setRows(rows);
			searchmodel.setCol(col);
			searchmodel.setVal(val);

			JsonDataGripModel<ProductTrans_Entity> GripModel =new JsonDataGripModel<ProductTrans_Entity>();
			GripModel.setRows(ProductTransImp.get_list_search_translated(searchmodel));
			GripModel.setTotal(ProductTransImp.count_get_list_search_translated(searchmodel));
			return GripModel;
		}
	 
	 
	 @RequestMapping(value="/get_product_info",method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ProductTrans_Entity get_product_info(@RequestParam(value="product_id",required = true) String product_id){
		 
		 return ProductTransImp.get_product_info(product_id);
	 }
	 @RequestMapping(value="/trans_product",method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public int trans_product(
			 @RequestParam(value="product_id",required = true) String product_id,
			 @RequestParam(value="product_name",required = true) String product_name,
			 @RequestParam(value="product_img",required = true) String product_img,
			 @RequestParam(value="product_des",required = true) String product_des,
			 @RequestParam(value="product_price",required = true) Float product_price,
			 @RequestParam(value="seller_id",required = true) String seller_id,
			 HttpServletRequest request
			 ){
		 
		 return ProductTransImp.trans_product(product_id,product_name,product_img,product_des,product_price,seller_id,request);
	 }
	 
	 @RequestMapping(value="/get_product_translated_info",method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ProductTrans_Entity get_product_translated_info(@RequestParam(value="product_id",required = true) String product_id){
		 
		 return ProductTransImp.get_product_translated_info(product_id);
	 }
	 @RequestMapping(value="/update_product_content",method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public int update_product_content(
			 @RequestParam(value="product_id",required = true) String product_id,
			 @RequestParam(value="product_name",required = true) String product_name,
			 @RequestParam(value="product_des",required = true) String product_des,
			 HttpServletRequest request
			 ){
		 
		 return ProductTransImp.update_product_content(product_id,product_name,product_des,request);
	 }
}
