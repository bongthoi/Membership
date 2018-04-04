package vn.vmall.SessionModel;

import javax.servlet.http.HttpSession;

import vn.vmall.AdminController.ad_loginController;

public class LangSessionModel {
	private String lang_value = "VN";
	public String getLang_value() {
		return lang_value;
	}
	public void setLang_value(String lang_value) {
		this.lang_value = lang_value;
	}

	public LangSessionModel getss_thisMODEL(HttpSession session){
		LangSessionModel a =new LangSessionModel();
		if(session!=null){
			if(session.getAttribute(ad_loginController.KEY_LANG)!=null)
			 a.setLang_value(session.getAttribute(ad_loginController.KEY_LANG).toString());
			else{
				session.setAttribute(ad_loginController.KEY_LANG, this.lang_value);
			}
		}
		return a;
	}
	
}
