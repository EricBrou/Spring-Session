package com.project.models.sessionModel;

import java.util.List;
import java.util.Objects;

import com.project.models.model.Product;

public class CartSession {

	private Long id_user;
	private List<Product> products;
	
	public CartSession() {
		super();
	}

	public CartSession(Long id_user, List<Product> products) {
		super();
		this.id_user = id_user;
		this.products = products;
	}

	public Long getId_user() {
		return id_user;
	}
	
	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}
	
	public List<Product> getProducts() {
		return products;
	}
	
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_user, products);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartSession other = (CartSession) obj;
		return Objects.equals(id_user, other.id_user) && Objects.equals(products, other.products);
	}

	@Override
	public String toString() {
		return "CartSession [id_user=" + id_user + ", products=" + products + "]";
	}
	
}
