package vn.vmall.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class ProductTrans_Entity {

	@Column(name="product_id")
	private String product_id;
	
	@Column(name="product_name")
	private String product_name;
	
	@Column(name="lang_code")
	private String lang_code;
	
	@Column(name="product_image")
	private String product_image;
	
	@Column(name="product_description")
	private String product_description;
	
	@Column(name="product_price")
	private Float product_price;
	
	@Column(name="seller_id")
	private String seller_id;
	
	@Column(name="istrans")
	private String istrans;
	
	@Column(name="date_trans")
	private String date_trans;
	
	@Column(name="user_trans")
	private String user_trans;

	
	
	public String getDate_trans() {
		return date_trans;
	}

	public void setDate_trans(String date_trans) {
		this.date_trans = date_trans;
	}

	public String getUser_trans() {
		return user_trans;
	}

	public void setUser_trans(String user_trans) {
		this.user_trans = user_trans;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getLang_code() {
		return lang_code;
	}

	public void setLang_code(String lang_code) {
		this.lang_code = lang_code;
	}

	public String getProduct_image() {
		return product_image;
	}

	public void setProduct_image(String product_image) {
		this.product_image = product_image;
	}

	public String getProduct_description() {
		return product_description;
	}

	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}

	public Float getProduct_price() {
		return product_price;
	}

	public void setProduct_price(Float product_price) {
		this.product_price = product_price;
	}

	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

	public String getIstrans() {
		return istrans;
	}

	public void setIstrans(String istrans) {
		this.istrans = istrans;
	}
	
	
}
