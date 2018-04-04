package vn.vmall.Helper;

public class SessionModel {

	private String session_id;
	private String session_name;
	private int session_timeout;
	
	private int session_role;
	private String  session_userid;
	private String  session_username;
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public String getSession_name() {
		return session_name;
	}
	public void setSession_name(String session_name) {
		this.session_name = session_name;
	}
	public int getSession_timeout() {
		return session_timeout;
	}
	public void setSession_timeout(int session_timeout) {
		this.session_timeout = session_timeout;
	}
	public int getSession_role() {
		return session_role;
	}
	public void setSession_role(int session_role) {
		this.session_role = session_role;
	}
	public String getSession_userid() {
		return session_userid;
	}
	public void setSession_userid(String session_userid) {
		this.session_userid = session_userid;
	}
	public String getSession_username() {
		return session_username;
	}
	public void setSession_username(String session_username) {
		this.session_username = session_username;
	}
	
	
	
}
