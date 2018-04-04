package vn.vmall.Imp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.vmall.DAL.ProductTransDAL;
import vn.vmall.Entity.ProductTrans_Entity;
import vn.vmall.Helper.SearchPaggModel;
import vn.vmall.Interface.ProductTransInterface;
import vn.vmall.SessionModel.UserSessionModel;

@Component(value="ProductTransImp")
public class ProductTransImp  implements ProductTransInterface{

	@Autowired
	private ProductTransDAL transDAL;
	
	@Override
	public List<ProductTrans_Entity> get_list_search_pagg(SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return transDAL.get_list_search_pagg(searchmodel);
	}

	@Override
	public int count_get_list_search_pagg(SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return transDAL.count_get_list_search_pagg(searchmodel);
	}

	@Override
	public ProductTrans_Entity get_product_info(String product_id) {
		// TODO Auto-generated method stub
		return transDAL.get_product_info(product_id);
	}

	@Override
	public int trans_product(String product_id, String product_name, String product_img, String product_des,
			Float product_price, String seller_id,HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		UserSessionModel u=new UserSessionModel();
		u=(UserSessionModel)session.getAttribute("user");
		String user_trans= u.getUser_seller().getEmail();
		
		return transDAL.trans_product(product_id,product_name,product_img,product_des,product_price,seller_id,user_trans);
	}

	@Override
	public List<ProductTrans_Entity> get_list_search_translated(SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return transDAL.get_list_search_translated(searchmodel);
	}

	@Override
	public int count_get_list_search_translated(SearchPaggModel searchmodel) {
		// TODO Auto-generated method stub
		return transDAL.count_get_list_search_translated(searchmodel);
	}
	
	@Override
	public ProductTrans_Entity get_product_translated_info(String product_id) {
		// TODO Auto-generated method stub
		return transDAL.get_product_translated_info(product_id);
	}

	@Override
	public int update_product_content(String product_id, String product_name, String product_des,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserSessionModel u=new UserSessionModel();
		u=(UserSessionModel)session.getAttribute("user");
		String user_trans= u.getUser_seller().getEmail();
		
		return transDAL.update_product_content(product_id,product_name,product_des,user_trans);
	}
}
