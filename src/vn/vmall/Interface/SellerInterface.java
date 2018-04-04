package vn.vmall.Interface;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestParam;

import vn.vmall.Entity.Product_Entity;
import vn.vmall.Entity.SellerBuyer_Entity;
import vn.vmall.Helper.SearchPaggModel;
import vn.vmall.SessionModel.UserSessionModel;

public interface SellerInterface {

	SellerBuyer_Entity getInfoSeller(HttpServletRequest request); 
	int editInfoSeller(String email,String phone,String yeares,int total_empl,int total_revenue,String company_des,String company_pic,String certificate,String license); 
	int changePassSeller(String email,String oldpass,String newpass);
	SellerBuyer_Entity getseller(String email); 
	int count_seller_byemail(String seller_email);
}
