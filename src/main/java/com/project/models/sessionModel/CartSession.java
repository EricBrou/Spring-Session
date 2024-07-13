package com.project.models.sessionModel;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class CartSession implements Serializable {

	private static final long serialVersionUID = 8119739054444299170L;
	
	private List<Long> products;
	
	public CartSession() {
		super();
	}

	public List<Long> getProducts() {
		return products;
	}

	public void setProducts(List<Long> products) {
		this.products = products;
	}

	@Override
	public int hashCode() {
		return Objects.hash(products);
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
		return Objects.equals(products, other.products);
	}

	@Override
	public String toString() {
		return "CartSession [products=" + products + "]";
	}
	
}
