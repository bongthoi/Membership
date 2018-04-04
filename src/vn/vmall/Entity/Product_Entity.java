package vn.vmall.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Product_Entity {

	@Column(name="lang_code")
	private String lang_code;
	
	@Column(name="product_id")
	private String product_id;
	
	@Column(name="product_name")
	private String product_name;
	
	@Column(name="product_image")
	private String product_image;
	
	@Column(name="product_description")
	private String product_description;
	
	@Column(name="product_price")
	private float product_price;
	
	@Column(name="seller_id")
	private String seller_id;
	
	@Column(name="istrans")
	private int istrans;
	
	@Column(name="nation_name")
	private String nation_name;
	
	@Column(name="isvisible")
	private int isvisible;
	
	public String getLang_code() {
		return lang_code;
	}
	public void setLang_code(String lang_code) {
		this.lang_code = lang_code;
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
	public float getProduct_price() {
		return product_price;
	}
	public void setProduct_price(float product_price) {
		this.product_price = product_price;
	}
	public String getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}
	public int getIstrans() {
		return istrans;
	}
	public void setIstrans(int istrans) {
		this.istrans = istrans;
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
	
	
	
}
