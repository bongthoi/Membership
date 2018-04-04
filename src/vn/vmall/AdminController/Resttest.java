package vn.vmall.AdminController;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import vn.vmall.Helper.SearchPaggModel;
import vn.vmall.SessionModel.UserSessionModel;




@RestController
@RequestMapping(value="t")
@SessionAttributes(value = "user", types = {UserSessionModel.class})
public class Resttest {
	/*cau hinh produces  de lay data theo json hoac xml hoac text ....hoac ket hop ca 2 json,xml,text "text/plain; charset=UTF-8"*/
	@RequestMapping("/a")
	public  Map<String,String> get_map(){
		Map<String,String> m=new HashMap<String,String>();
		m.put("a", "maprest");
		return m;
	}
	@RequestMapping(value="/get_map",method=RequestMethod.GET,produces = "application/json")
	public SearchPaggModel get_map2(){
		SearchPaggModel m=new SearchPaggModel();
		m.setToDate("ồ ố ô ộ");
		m.setFromDate("2016");
		return m;
		
	}
	
	

	@RequestMapping(value="/text8",method=RequestMethod.GET,produces = MediaType.TEXT_PLAIN_VALUE+";charset=UTF-8")
	public  String get_text(){
		return "ồ ố ô ộ";
	}
	
	@RequestMapping(value="/post_map",method=RequestMethod.POST,produces = "application/json")
	public SearchPaggModel post_map(@RequestParam("pa1") String pa1,@RequestParam("pa1") String pa2,HttpServletRequest req ){
		SearchPaggModel m=new SearchPaggModel();
		//HttpRequest
		m.setToDate(pa1);
		m.setFromDate(req.getParameter("pa3"));
		//m.set
		//m.setRows(req.getParameter("pa3"));
		return m;
		
	}
	
	@RequestMapping(value="/get_session",method=RequestMethod.GET,produces = "application/json")
	public UserSessionModel get_session(@ModelAttribute("user") UserSessionModel user){
		
		return user;
		
	}
	

}
