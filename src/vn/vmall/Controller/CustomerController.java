package vn.vmall.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import vn.vmall.AdminController.ad_loginController;
import vn.vmall.DAL.CustomerDAL;
import vn.vmall.DAL.Errordal;
import vn.vmall.Entity.ItemCustomer;
import vn.vmall.Entity.ItemNation;
import vn.vmall.Helper.Extra;
import vn.vmall.Service.ReadServiceMail;
import vn.vmall.SessionModel.LangSessionModel;
import vn.vmall.SessionModel.UserSessionModel;

@RestController
@RequestMapping(value = "CustomerController")
public class CustomerController {
	@RequestMapping(value="check_custome")
	@ResponseBody
	public int send_contact(@RequestBody final String str,
			HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, InstantiationException, SQLException{
		ItemCustomer item = new ItemCustomer();
		Map<String, String> Json_decore= new Gson().fromJson(str,  Map.class);		 
		String s=Json_decore.get("str");		
		item=new Gson().fromJson( s, ItemCustomer.class);
		int rs = CustomerDAL.check_customer(item.getEmail(),item.getPass());
		if(rs==0){
			UserSessionModel cus = new UserSessionModel();
			cus = CustomerDAL.get_info_role(item.getEmail());
			HttpSession sess = request.getSession();
			sess.setAttribute(ad_loginController.KEY_SESSION_USER,cus);
			System.out.println(new Gson().toJson(cus));
			System.out.println(new Gson().toJson(sess.getAttribute(ad_loginController.KEY_SESSION_USER)));
		}
		return rs;
	}
	@RequestMapping(value="set_lang",method=RequestMethod.POST,produces =MediaType.TEXT_PLAIN_VALUE)
	public String set_lang(@RequestParam(value ="lang",required = true) String lang,
			HttpServletRequest request) {
		 HttpSession session = request.getSession(true);
		 if(session!=null){
			 session.setAttribute(ad_loginController.KEY_LANG,lang);
			 
		 }
			return lang;	
	}
	@RequestMapping(value = "get_nation", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<ItemNation> get_nation(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		ArrayList<ItemNation> data = new ArrayList<ItemNation>();
		data = CustomerDAL.get_nation();
		return data;
	}

	@RequestMapping(value = "add_custome", method = RequestMethod.POST)
	@ResponseBody
	public String add_custome( @RequestParam("str") String str, HttpServletRequest request,
			HttpServletResponse response) {		
		String result = add_customer(str, request);
		return result;
	}

	public static String add_customer(String fromjson, HttpServletRequest request) {

		Map<String, String> obj = new HashMap<String, String>();
		Gson gson = new Gson();
		ItemCustomer cus = new ItemCustomer();
		cus = gson.fromJson(fromjson, ItemCustomer.class);
		int result = CustomerDAL.add_customer_dao(cus);
		if (result == 0) {

			int rs_send_mail = send_mail_to_customer(cus.getEmail(),cus.getLang(), request);

		}
		obj.put("result", String.valueOf(result));
		if (result > 0) {
			 LangSessionModel l=new LangSessionModel();
			 HttpSession session = request.getSession(false);
			 l=l.getss_thisMODEL(session);
			String mes = Errordal.getMesageError(result,l.getLang_value());
			obj.put("message", mes);
		}
		String json = new Gson().toJson(obj);
		return json;

	}

	public static int send_mail_to_customer(String email,String lang, HttpServletRequest request) {
		try {
			ServletContext servl = request.getServletContext();
			String urltext = request.getRealPath("/upload/txt");
			urltext += "/confirm_create_shop_"+lang+".txt";
			String content_text = Extra.readFile(urltext);
			// get info order
			ItemCustomer item_cus = new ItemCustomer();
			item_cus = CustomerDAL.get_info_customer(email);// DAL get info
			//

			content_text = content_text.replace("@shopname1", item_cus.getShop_name());
			content_text = content_text.replace("@shopname2", item_cus.getShop_name());
			content_text = content_text.replace("@shopname3", item_cus.getShop_name());
			content_text = content_text.replace("@emai", email);
			content_text = content_text.replace("@password", item_cus.getPass());

			String url = servl.getInitParameter("urlconfirmcreateshop");

			url = url.replace("@email", email);
			content_text = content_text.replace("@href", url);
			content_text = content_text.replace("@url", url);
			content_text = content_text.replace("??", "&");

			//
			String title = "激活客户注册账户 " + email;
			int rs = ReadServiceMail.SendingFromgmail(email, title, content_text, request);
			System.out.println("SEND EMAIL !");
			return rs;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 1;
	}
	@RequestMapping(value = "confirm_create_shop", method = RequestMethod.GET)
	@ResponseBody
	public String confirm_create_shop(@RequestParam("email") String email, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String jsontext = "null";
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, String> obj = new HashMap<String, String>();
		result = CustomerDAL.confirm_create_shop(email);
		if (result != null) {
			if (result.size() > 0) {
				String f = result.get("f").toString();
				 LangSessionModel l=new LangSessionModel();
				 HttpSession session = request.getSession(false);
				 l=l.getss_thisMODEL(session);
				String mes = Errordal.getMesageError(Integer.parseInt(f),l.getLang_value());
				obj.put("result", f);
				obj.put("message", mes);
			}
		}

		String json = new Gson().toJson(obj);
		return json;
	}
	@RequestMapping(value="get_info_customer",method=RequestMethod.GET)
	@ResponseBody
	public ItemCustomer get_info_customer(@RequestParam("email") String email,
			HttpServletRequest request,
			HttpServletResponse response) throws IOException, ClassNotFoundException, InstantiationException, SQLException{
		ItemCustomer item = new ItemCustomer();
		 item = CustomerDAL.get_info_customer(email);
		return item;
	}
	@RequestMapping(value = "change_forget_password", method = RequestMethod.GET)
	@ResponseBody
	public int change_forget_password(@RequestParam("email") String email, HttpServletRequest request,
			HttpServletResponse response) {
		int rs = -1;
		Map<String, Object> item = new HashMap<String, Object>();
		item = CustomerDAL.change_password_forget(email);
		try {
			if (item != null) {
				rs = Integer.parseInt(item.get("result").toString());
				if (rs == 0) {
					// send email
					ServletContext servl = request.getServletContext();
					String url = servl.getInitParameter("urlconfirmforgetshop");
					url = url.replace("@email", email);
					url = url.replace("@password", item.get("password").toString());
					url = url.replace("??", "&");
					//System.out.println(url);
					String urltext = request.getRealPath("/upload/txt");
					urltext += "/forget_password.txt";
					String content_text = Extra.readFile(urltext);
					content_text = content_text.replace("@username", email);
					content_text = content_text.replace("@url", url);
					content_text = content_text.replace("@pass", item.get("password").toString());
					//System.out.println(content_text);
					rs = ReadServiceMail.SendingFromgmail(email, "更改密码确认", content_text, request);
				}
			}
		} catch (Exception ex) {

		}
		return rs;
	}
	@RequestMapping(value = "confirm_change_password", method = RequestMethod.GET)
	@ResponseBody
	public String confirm_change_password(@RequestParam("email") String email, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String jsontext = "null";
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, String> obj = new HashMap<String, String>();
		result = CustomerDAL.confirm_change_password(email);
		if (result != null) {
			if (result.size() > 0) {
				 LangSessionModel l=new LangSessionModel();
				 HttpSession session = request.getSession(false);
				 l=l.getss_thisMODEL(session);
				String f = result.get("f").toString();
				String mes = Errordal.getMesageError(Integer.parseInt(f),l.getLang_value());
				obj.put("result", f);
				obj.put("message", mes);
			}
		}
		
		String json = new Gson().toJson(obj);
		return json;
	}
	@RequestMapping(value="get_pro",method=RequestMethod.GET)
	@ResponseBody
	public ArrayList<ItemNation> get_pro(
			HttpServletRequest request,
			HttpServletResponse response) throws IOException, ClassNotFoundException, InstantiationException, SQLException{
		ArrayList<ItemNation> list = new ArrayList<ItemNation>();		
		list = CustomerDAL.get_pro();
		return list;
	}	
	@RequestMapping(value="get_ward",method=RequestMethod.GET)
	@ResponseBody
	public ArrayList<ItemNation> get_ward(
			@RequestParam("id") String dis,
			HttpServletRequest request,
			HttpServletResponse response) throws IOException, ClassNotFoundException, InstantiationException, SQLException{
		ArrayList<ItemNation> list = new ArrayList<ItemNation>();		
		list = CustomerDAL.get_ward(dis);
		return list;
	}	
}
