package vn.vmall.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class SellerBuyer_Entity {

	@Column(name="email")
	private String email;
	
	@Column(name="pass")
	private String pass;
	
	@Column(name="company_name")
	private String company_name;
	
	@Column(name="country")
	private String country;
	
	@Column(name="address")
	private String address;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="year_established")
	private String year_established;
	
	@Column(name="picture_company")
	private String picture_company;
	
	@Column(name="main_products")
	private String main_products;
	
	@Column(name="certificate")
	private String certificate;
	
	@Column(name="license")
	private String license;
	
	@Column(name="total_employees")
	private int total_employees;
	
	@Column(name="total_annual_revenue")
	private int total_annual_revenue;
	
	@Column(name="isconfirm")
	private int isconfirm;
	
	@Column(name="type")
	private String type;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getYear_established() {
		return year_established;
	}

	public void setYear_established(String year_established) {
		this.year_established = year_established;
	}

	public String getPicture_company() {
		return picture_company;
	}

	public void setPicture_company(String picture_company) {
		this.picture_company = picture_company;
	}

	public String getMain_products() {
		return main_products;
	}

	public void setMain_products(String main_products) {
		this.main_products = main_products;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public int getTotal_employees() {
		return total_employees;
	}

	public void setTotal_employees(int total_employees) {
		this.total_employees = total_employees;
	}

	public int getTotal_annual_revenue() {
		return total_annual_revenue;
	}

	public void setTotal_annual_revenue(int total_annual_revenue) {
		this.total_annual_revenue = total_annual_revenue;
	}

	public int getIsconfirm() {
		return isconfirm;
	}

	public void setIsconfirm(int isconfirm) {
		this.isconfirm = isconfirm;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
