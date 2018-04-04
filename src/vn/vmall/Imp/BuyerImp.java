package vn.vmall.Imp;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.vmall.DAL.BuyerDAL;
import vn.vmall.Entity.SellerBuyer_Entity;
import vn.vmall.Interface.BuyerInterface;
import vn.vmall.SessionModel.UserSessionModel;


@Component("BuyerImp")
public class BuyerImp  implements BuyerInterface{
	
	@Autowired
	private BuyerDAL dal;
	
	@Override
	public SellerBuyer_Entity getInfoBuyer(HttpServletRequest request) {
		String email="";
		HttpSession session = request.getSession();
		 UserSessionModel u=new UserSessionModel();
		 u=(UserSessionModel)session.getAttribute("user");
		 email= u.getUser_seller().getEmail();
		return dal.getInfoBuyer(email);
	}
	
	@Override
	public int editInfoBuyer(SellerBuyer_Entity a) {
		
		return dal.editInfoBuyer(a);
	}

	@Override
	public int changePassBuyer(String email, String oldpass, String newpass) {
		// TODO Auto-generated method stub
		return dal.changePassBuyer(email,oldpass,newpass);
	}
}
