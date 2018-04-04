package vn.vmall.Interface;



import javax.servlet.http.HttpServletRequest;

import vn.vmall.Entity.SellerBuyer_Entity;

public interface BuyerInterface {

	SellerBuyer_Entity getInfoBuyer(HttpServletRequest request); 
	int editInfoBuyer(SellerBuyer_Entity a); 
	int changePassBuyer(String email,String oldpass,String newpass);
}
