package vn.vmall.Imp;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.vmall.DAL.ProductDAL;
import vn.vmall.DAL.SellerDAL;
import vn.vmall.Entity.Product_Entity;
import vn.vmall.Entity.SellerBuyer_Entity;
import vn.vmall.Helper.SearchPaggModel;
import vn.vmall.Interface.ProductInterface;
import vn.vmall.Interface.SellerInterface;
import vn.vmall.SessionModel.UserSessionModel;


@Component("SellerImp")
public class SellerImp  implements SellerInterface{
	
	@Autowired
	private SellerDAL dal;
	
	@Override
	public SellerBuyer_Entity getInfoSeller(HttpServletRequest request) {
		String email="";
		HttpSession session = request.getSession();
		 UserSessionModel u=new UserSessionModel();
		 u=(UserSessionModel)session.getAttribute("user");
		 email= u.getUser_seller().getEmail();
		return dal.getInfoSeller(email);
	}
	
	@Override
	public int editInfoSeller(String email,String phone,String yeares,int total_empl,int total_revenue,String company_des,String company_pic,String certificate,String license) {
		
		return dal.editInfoSeller(email,phone,yeares,total_empl,total_revenue,company_des,company_pic,certificate,license);
	}

	@Override
	public int changePassSeller(String email, String oldpass, String newpass) {
		// TODO Auto-generated method stub
		return dal.changePassSeller(email,oldpass,newpass);
	}

	@Override
	public SellerBuyer_Entity getseller(String email) {
		// TODO Auto-generated method stub
		return dal.getInfoSeller(email);
	}

	@Override
	public int count_seller_byemail(String seller_email) {
		// TODO Auto-generated method stub
		return dal.count_seller_byemail(seller_email);
	}
}
