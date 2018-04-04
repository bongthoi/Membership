package vn.vmall.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Requirements_Entity {

	@Column(name="requirement_id")
	private String requirement_id;
	
	@Column(name="description")
	private String description;
	
	@Column(name="product_image")
	private String product_image;
	
	@Column(name="product_price_limit")
	private float product_price_limit;
	
	@Column(name="quantity")
	private float quantity;
	
	@Column(name="budget")
	private float budget;
	
	@Column(name="isvisible")
	private int isvisible;
	
	@Column(name="isdelete")
	private int isdelete;
	
	@Column(name="buyer_id")
	private String buyer_id;

	public String getRequirement_id() {
		return requirement_id;
	}

	public void setRequirement_id(String requirement_id) {
		this.requirement_id = requirement_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProduct_image() {
		return product_image;
	}

	public void setProduct_image(String product_image) {
		this.product_image = product_image;
	}

	public float getProduct_price_limit() {
		return product_price_limit;
	}

	public void setProduct_price_limit(float product_price_limit) {
		this.product_price_limit = product_price_limit;
	}

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	public float getBudget() {
		return budget;
	}

	public void setBudget(float budget) {
		this.budget = budget;
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

	public String getBuyer_id() {
		return buyer_id;
	}

	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}
	
}
