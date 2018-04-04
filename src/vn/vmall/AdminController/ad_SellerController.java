package vn.vmall.AdminController;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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

import vn.vmall.Entity.SellerBuyer_Entity;
import vn.vmall.Helper.ErrorMesage;
import vn.vmall.Helper.ErrorMessageModel;
import vn.vmall.Helper.JsonDataGripModel;
import vn.vmall.Helper.SearchPaggModel;
import vn.vmall.Interface.ProductInterface;
import vn.vmall.Interface.SellerInterface;
import vn.vmall.SessionModel.LangSessionModel;
import vn.vmall.SessionModel.UserSessionModel;



@RestController
@RequestMapping(value="ad/SellerController")
public class ad_SellerController {

	 @Autowired
	 @Qualifier("SellerImp")
	 private SellerInterface SellerImp;
	 
	 

	 
	 @RequestMapping(value="/getInfoSeller",
				method=RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public SellerBuyer_Entity getInfoSeller(HttpServletRequest request){		
			return  SellerImp.getInfoSeller(request);
	 }
	 
	 @RequestMapping(value="/editInfoSeller",
				method=RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public int editInfoSeller(
				@RequestParam(value="email") String email,
				@RequestParam(value="phone") String phone,
				@RequestParam(value="yeares") String yeares,
				@RequestParam(value="total_empl") int total_empl,
				@RequestParam(value="total_revenue") int total_revenue,
				@RequestParam(value="company_des") String company_des,
				@RequestParam(value="company_pic") String company_pic,
				@RequestParam(value="certificate") String certificate,
				@RequestParam(value="license") String license){		
			return  SellerImp.editInfoSeller(email,phone,yeares,total_empl,total_revenue,company_des,company_pic,certificate,license);
	 }
	 @RequestMapping(value="/changePassSeller",
				method=RequestMethod.POST,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public int changePassSeller(
				@RequestParam(value="user") String email,
				@RequestParam(value="oldpass") String oldpass,
				@RequestParam(value="newpass") String newpass,
				HttpServletRequest request){		
			return  SellerImp.changePassSeller(email, oldpass, newpass);
	 }
}
