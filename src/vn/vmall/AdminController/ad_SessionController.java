package vn.vmall.AdminController;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import vn.vmall.Helper.SessionModel;


@RestController
@RequestMapping(value="ad/SessionController")
public class ad_SessionController {
	 @RequestMapping(value="/get_session",
				method=RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public SessionModel get_session(){ 
		 SessionModel s=new SessionModel();
		s.setSession_username("u1");
		s.setSession_role(10);
		s.setSession_userid("u1");
			return s;
			
		}

}
