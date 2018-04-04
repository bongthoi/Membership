package vn.vmall.Interface;

import java.util.List;
import java.util.Map;

import vn.vmall.Entity.Product_Entity;
import vn.vmall.Helper.SearchPaggModel;
import vn.vmall.SessionModel.UserSessionModel;

public interface ProductInterface {

	List<Product_Entity> get_list_search_pagg(SearchPaggModel searchmodel, UserSessionModel userm);

	int count_get_list_search_pagg(SearchPaggModel searchmodel, UserSessionModel userm);

	Map<String, Object> SaveProduct(String ptype, Product_Entity d, UserSessionModel userm);

	Product_Entity get_Productbyid(String product_id);

	int visivled_product(String str_id, String visible, UserSessionModel userm);

	int delete_multi_product(String str_id, UserSessionModel userm);
	
	int count_product_byseller(String seller_email);
	
	List<Product_Entity> get__product_byseller(String seller_email,int start,int limit);

	int count_product_detail_byid(String id);

	Product_Entity get_productdetail_byid(String id);


}
