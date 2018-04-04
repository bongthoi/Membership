package vn.vmall.AdminController;



import javax.servlet.http.HttpServletRequest;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.vmall.Entity.SellerBuyer_Entity;
import vn.vmall.Helper.ErrorMesage;
import vn.vmall.Helper.ErrorMessageModel;
import vn.vmall.Interface.BuyerInterface;
import vn.vmall.SessionModel.LangSessionModel;


@RestController
@RequestMapping(value="ad/BuyerController")
public class ad_BuyerController {

	 @Autowired
	 @Qualifier("BuyerImp")
	 private BuyerInterface BuyerImp;
	 
	 

	 
	 @RequestMapping(value="/getInfoBuyer",
				method=RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public SellerBuyer_Entity getInfoBuyer(HttpServletRequest request){		
			return  BuyerImp.getInfoBuyer(request);
	 }
	 
	 @RequestMapping(value="/editInfoBuyer",
				method=RequestMethod.POST,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel editInfoBuyer(
				@RequestParam(value="email", required = true) String email,
				@RequestParam(value="phone",required = false,defaultValue="") String phone,
				@RequestParam(value="year_established",required = false,defaultValue="") String year_established,
				@RequestParam(value="total_employees",required = false,defaultValue="0") int total_employees,
				@RequestParam(value="total_annual_revenue",required = false,defaultValue="0") int total_annual_revenue,
				@RequestParam(value="main_products",required = false,defaultValue="") String main_products,
				@RequestParam(value="picture_company",required = false,defaultValue="") String picture_company,
				@RequestParam(value="certificate",required = false,defaultValue="") String certificate,
				@RequestParam(value="license",required = false,defaultValue="") String license,
				HttpSession session){		
		 	SellerBuyer_Entity a=new SellerBuyer_Entity();
		 	a.setEmail(email);
		 	a.setPhone(phone);
		 	a.setYear_established(year_established);
		 	a.setTotal_annual_revenue(total_annual_revenue);
		 	a.setTotal_employees(total_employees);
		 	a.setMain_products(main_products);
		 	a.setCertificate(certificate);
		 	a.setLicense(license);
		 	a.setPicture_company(picture_company);
		 	int f=  BuyerImp.editInfoBuyer(a);
			ErrorMessageModel e=new ErrorMessageModel();
			 LangSessionModel langmodel=new LangSessionModel();
			 langmodel=langmodel.getss_thisMODEL(session);
			e=ErrorMesage.get_json_mes_error(f,langmodel.getLang_value());
			return e;
	 }
	 @RequestMapping(value="/changePassBuyer",
				method=RequestMethod.POST,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public int changePassSeller(
				@RequestParam(value="email",required = true) String email,
				@RequestParam(value="oldpass",required = true) String oldpass,
				@RequestParam(value="newpass",required = true) String newpass,
				HttpSession session){		
			return  BuyerImp.changePassBuyer(email, oldpass, newpass);
	 }
}
