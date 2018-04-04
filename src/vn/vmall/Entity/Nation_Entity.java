package vn.vmall.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Nation_Entity {

	@Column(name="nation_id")
	private String nation_id;
	@Column(name="nation_name")
	private String nation_name;
	@Column(name="isvisible")
	private int isvisible;
	@Column(name="isdelete")
	private int isdelete;
	@Column(name="lang_code")
	private String lang_code;
	
	public String getNation_id() {
		return nation_id;
	}
	public void setNation_id(String nation_id) {
		this.nation_id = nation_id;
	}
	public String getNation_name() {
		return nation_name;
	}
	public void setNation_name(String nation_name) {
		this.nation_name = nation_name;
	}
	public int getIsvisible() {
		return isvisible;
	}
	public void setIsvisible(int isvisible) {
		this.isvisible = isvisible;
	}
	public int getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(int isdelete) {
		this.isdelete = isdelete;
	}
	public String getLang_code() {
		return lang_code;
	}
	public void setLang_code(String lang_code) {
		this.lang_code = lang_code;
	}
	
	
}
