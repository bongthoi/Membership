package vn.vmall.AdminController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import vn.vmall.Entity.SellerBuyer_Entity;
import vn.vmall.Helper.ErrorMessageModel;
import vn.vmall.SessionModel.UserSessionModel;




@RestController
@RequestMapping(value="ad/loginController")
public class ad_loginController {
	public final static String KEY_SESSION_USER="user";
	public static final String KEY_LANG="KEY_LANG";
	public final static String ROLE_ADMIN="ADMIN"; 
	public final static String ROLE_SELLER="SELLER"; 
	public final static String ROLE_BUYER="BUYER"; 
	public final static String ROLE_USER="USER"; 

	 @RequestMapping(value="/do_login",
				method=RequestMethod.POST,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel do_login(HttpSession session){
		 SellerBuyer_Entity seller= new SellerBuyer_Entity();
		 seller.setEmail("buyer@vivmall.vn");
		 UserSessionModel u1=new UserSessionModel();
		 u1.setRole(ROLE_ADMIN);
		 u1.setDate_login( new Date());
		 u1.setUser_seller(seller);
		 session.setAttribute(KEY_SESSION_USER,u1);
		 ErrorMessageModel m=new ErrorMessageModel();
		 m.setF(0);
		 m.setMessage("login success!");
		return m;
		 
	 }
	 
	 @RequestMapping(value="/do_logout",
				method=RequestMethod.POST,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ErrorMessageModel do_logout(HttpSession session){
		 session.invalidate();
		 ErrorMessageModel m=new ErrorMessageModel();
		 m.setF(0);
		 m.setMessage("logout success!");
		 return m;
		 
	 }
	 
	 @RequestMapping(value="/get_login",
				method=RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public UserSessionModel get_login(HttpSession session){
		 UserSessionModel u=new UserSessionModel();
		 if(session!=null){
			 System.out.println(new Gson().toJson(session.getAttribute(KEY_SESSION_USER)));
			 u=(UserSessionModel)session.getAttribute(KEY_SESSION_USER);
		 }
		return u;	 
	 }
	 @RequestMapping(value="/get_menutree",
				method=RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public String get_menutree(HttpServletRequest request) throws IOException{
		 String menu="{}";
		 HttpSession session = request.getSession(false);
		 if(session!=null){
			 UserSessionModel u=new UserSessionModel();
			 u=(UserSessionModel)session.getAttribute(KEY_SESSION_USER);
			  String filename_menu=this.get_filename_menubyRole(u.getRole());
			  ServletContext context = request.getServletContext();
			  InputStream is = context.getResourceAsStream(filename_menu);
			   if (is != null) {
		            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
		            BufferedReader reader = new BufferedReader(isr);
		            String text;
		            // We read the file line by line and later will be displayed on the
		            // browser page.
		            menu="";
		            while ((text = reader.readLine()) != null) {
		            	menu=menu.concat(text);
		            }
		        }
			
		 }
		return menu;	 
	 }
	 @RequestMapping(value="/get_menutree2",
				method=RequestMethod.GET,
				produces = MediaType.TEXT_HTML_VALUE+";charset=UTF-8")
		public String get_menutree2(HttpServletRequest request) throws IOException{
		 String menu="";
		 HttpSession session = request.getSession(false);
		 if(session!=null){
			 UserSessionModel u=new UserSessionModel();
			 u=(UserSessionModel)session.getAttribute(KEY_SESSION_USER);
			  String filename_menu=this.get_filename_menubyRole(u.getRole());
			  ServletContext context = request.getServletContext();
			  InputStream is = context.getResourceAsStream(filename_menu);
			   if (is != null) {
		            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
		            BufferedReader reader = new BufferedReader(isr);
		            String text;
		            // We read the file line by line and later will be displayed on the
		            // browser page.
		            menu="";
		            while ((text = reader.readLine()) != null) {
		            	menu=menu.concat(text);
		            }
		        }
			
		 }
		return menu;	 
	 }
	 private String get_filename_menubyRole(String role){
		 String file="";
		 if(role.equals(ROLE_SELLER)){ 
			 file= "/WEB-INF/MENU_ADMIN/menutree_seller.txt";
		 }
		 else if(role.equals(ROLE_BUYER)){
			 file= "/WEB-INF/MENU_ADMIN/menutree_buyer.txt";
		 }else if(role.equals(ROLE_USER)){
			 file= "/WEB-INF/MENU_ADMIN/menutree_user.txt";
		 }else if(role.equals(ROLE_ADMIN)){
			 file= "/WEB-INF/MENU_ADMIN/menutree.txt";
		 }
		return file;
	 }
	 
}
