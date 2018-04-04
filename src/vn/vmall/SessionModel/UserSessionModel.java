package vn.vmall.SessionModel;

import java.util.Date;

import javax.servlet.http.HttpSession;

import vn.vmall.AdminController.ad_loginController;
import vn.vmall.Entity.SellerBuyer_Entity;

public class UserSessionModel {
	
	private SellerBuyer_Entity user_seller;
	
	private String role;
	private String jsession;
	private Date date_login;
	private int  f;
	
	public int getF() {
		return f;
	}
	public void setF(int f) {
		this.f = f;
	}

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getJsession() {
		return jsession;
	}
	public void setJsession(String jsession) {
		this.jsession = jsession;
	}
	public Date getDate_login() {
		return date_login;
	}
	public void setDate_login(Date date) {
		this.date_login = date;
	}
	public SellerBuyer_Entity getUser_seller() {
		return user_seller;
	}
	public void setUser_seller(SellerBuyer_Entity user_seller) {
		this.user_seller = user_seller;
	}
	
	public UserSessionModel getss_thisMODEL(HttpSession session){
		if(session!=null){
			UserSessionModel a=(UserSessionModel)session.getAttribute(ad_loginController.KEY_SESSION_USER);
			return a;
		}
		return null;
	}
	
	
	
}
