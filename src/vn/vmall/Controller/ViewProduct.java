package vn.vmall.Controller;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;







import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import vn.vmall.Entity.Product_Entity;
import vn.vmall.Entity.SellerBuyer_Entity;
import vn.vmall.Helper.Pagination;
import vn.vmall.Helper.UrlHellper;

import vn.vmall.Interface.ProductInterface;
import vn.vmall.Interface.SellerInterface;


@Controller
public class ViewProduct {
	
	final String _u="u";// seller email
	final String _page="page";// int page
	final String _p="p";//prduct id
	final String _mapp_viewlist="/view-product.html";
	final String _mapp_viewdetail="/detail-product.html";
	
	 @Autowired
	   private 	HttpServletRequest request;
	
	 @Autowired
	 @Qualifier("ProductImp")
	 private ProductInterface ProductImp;
	 
	 @Autowired
	 @Qualifier("SellerImp")
	 private SellerInterface SellerImp;
	 
	@RequestMapping(value=_mapp_viewlist)
	public ModelAndView index(@RequestParam(value= _u, required = true) String email,
			@RequestParam(value=_page, required = false,defaultValue="1") int currentpage) throws URISyntaxException {
		/*System.out.println(request.getContextPath());
		System.out.println(request.getPathInfo());
		System.out.println(request.getRequestURI());
		System.out.println(request.getRequestURL());
		System.out.println(request.getPathTranslated());
		System.out.println(request.getServletPath());
		System.out.println(request.getLocalName());
		System.out.println(request.getScheme());
		System.out.println(request.getLocale());*/
		String path_product_img=request.getContextPath()+"/upload/product/";
		 String path_logo=request.getContextPath()+"/upload/company/";
		ModelAndView mav = new ModelAndView();
		int total_record=0;
		total_record=ProductImp.count_product_byseller(email);
		if(total_record>0){
			Map<String,String> mapparam= new HashMap<String, String>();
			mapparam.put(_u, email);
			mapparam.put(_page,"replace_numberpage");
			Map<String,String> mapparam2= new HashMap<String, String>();
			mapparam2.put(_u, email);
			int current_page=currentpage;
		//	System.out.println("currentpag"+current_page);
			int limit=11;
			String link_full=UrlHellper.get_url(request,mapparam);
			String link_first=UrlHellper.get_url(request,mapparam2);
			//System.out.println("total:"+total_record);
			Pagination pagg=new Pagination(current_page,total_record,limit,link_full,link_first);
			int start= pagg.getStart();
			//System.out.println("out put :"+start);
			List<Product_Entity> list_product=ProductImp.get__product_byseller(email, start, limit);
			String html_pagg=pagg.html();
			SellerBuyer_Entity seller=SellerImp.getseller(email);
			
			String get_url_detailproduct=UrlHellper.get_uribylastpath(request, _mapp_viewdetail)+"?"+_u+"="+ email +"&"+_p+"={id}";
			//System.out.println(get_url_detailproduct);		
			
			mav.setViewName("product");
			mav.addObject("Pagetitle","Product of "+seller.getCompany_name());//title
			mav.addObject("description_tag",seller.getCompany_name());
			mav.addObject("seller",seller);
			mav.addObject("path_product_img",path_product_img);
			mav.addObject("path_logo",path_logo);
			mav.addObject("list_product",list_product);
			mav.addObject("pagging",html_pagg);
			mav.addObject("url_detailproduct",get_url_detailproduct);
			
			
		}else{
			mav.setViewName("no_product");
		}
	
		//return new ModelAndView("personList", persons);
		return mav;
	}
	@RequestMapping(value=_mapp_viewdetail)
	public ModelAndView viewdetail(@RequestParam(value=_u, required = true) String email,
			@RequestParam(value=_p, required = true) String  id) {
		int count_product=0;
		int count_seller=0;
		 count_seller=SellerImp.count_seller_byemail(email);
		 count_product=ProductImp.count_product_detail_byid(id);
		 String path_logo=request.getContextPath()+"/upload/company/";
		String path_product_img=request.getContextPath()+"/upload/product/";
		ModelAndView mav = new ModelAndView();
		if(count_seller>0&&count_product>0){
			SellerBuyer_Entity seller=SellerImp.getseller(email);
			Product_Entity product=ProductImp.get_productdetail_byid(id);
			mav.addObject("Pagetitle",product.getProduct_name());//title
			mav.addObject("description_tag",product.getProduct_description());
			mav.addObject("path_logo",path_logo);
			mav.addObject("path_product_img",path_product_img);
			mav.addObject("seller",seller);
			mav.addObject("product",product);
			mav.setViewName("detailproduct");
		}else{
			mav.setViewName("no_product");
		}
		return mav;
		
	}
	
	
	
	
}
