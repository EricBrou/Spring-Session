package com.project.models.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_SELLER")
public class Seller extends User{

	private static final long serialVersionUID = 3318697249665673178L;

}
