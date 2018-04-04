package vn.vmall.Imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.vmall.DAL.ProductDAL;
import vn.vmall.Entity.Product_Entity;
import vn.vmall.Helper.SearchPaggModel;
import vn.vmall.Interface.ProductInterface;
import vn.vmall.SessionModel.UserSessionModel;


@Component("ProductImp")
public class ProductImp  implements ProductInterface{
	
	@Autowired
	private ProductDAL dal;
	
	@Override
	public List<Product_Entity> get_list_search_pagg(SearchPaggModel searchmodel,UserSessionModel userm) {
		// TODO Auto-generated method stub
		return dal.get_list_search_pagg(searchmodel,userm);
	}

	@Override
	public int count_get_list_search_pagg(SearchPaggModel searchmodel,UserSessionModel userm) {
		// TODO Auto-generated method stub
		 return dal.count_get_list_search_pagg(searchmodel,userm);
	}

	@Override
	public Map<String, Object> SaveProduct(String ptype, Product_Entity d,UserSessionModel userm) {
		// TODO Auto-generated method stub
		return dal.SaveProduct(ptype,d,userm);
	}

	@Override
	public Product_Entity get_Productbyid(String product_id) {
		// TODO Auto-generated method stub
		return dal.get_Productbyid(product_id);
	}

	@Override
	public int visivled_product(String str_id, String visible,UserSessionModel userm) {
		// TODO Auto-generated method stub
		return dal.visivled_product(str_id,visible,userm);
	}

	@Override
	public int delete_multi_product(String str_id,UserSessionModel userm) {
		// TODO Auto-generated method stub
		return dal.delete_multi_product(str_id,userm);
	}

	@Override
	public int count_product_byseller(String seller_email) {
		// TODO Auto-generated method stub
		return dal.count_product_byseller(seller_email);
	}

	@Override
	public List<Product_Entity> get__product_byseller(String seller_email,
			int start, int limit) {
		// TODO Auto-generated method stub
		return dal.get__product_byseller(seller_email,start,limit);
	}

	@Override
	public int count_product_detail_byid(String id) {
		// TODO Auto-generated method stub
		return dal.count_product_detail_byid(id);
	}

	@Override
	public Product_Entity get_productdetail_byid(String id) {
		// TODO Auto-generated method stub
		return dal.get_productdetail_byid(id);
	}

}
