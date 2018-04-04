package vn.vmall.Interface;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import vn.vmall.Entity.ProductTrans_Entity;
import vn.vmall.Helper.SearchPaggModel;

public interface ProductTransInterface {

	List<ProductTrans_Entity> get_list_search_pagg(SearchPaggModel searchmodel);
	int count_get_list_search_pagg(SearchPaggModel searchmodel);
	ProductTrans_Entity get_product_info(String product_id);
	int trans_product(String product_id,String product_name,String product_img,
			String product_des,Float product_price,String seller_id,HttpServletRequest request);
	List<ProductTrans_Entity> get_list_search_translated(SearchPaggModel searchmodel);
	int count_get_list_search_translated(SearchPaggModel searchmodel);
	ProductTrans_Entity get_product_translated_info(String product_id);
	int update_product_content(String product_id,String product_name,String product_des,HttpServletRequest request);
}
