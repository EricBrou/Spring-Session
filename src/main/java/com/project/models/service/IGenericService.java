package com.project.models.service;

import java.util.List;

public interface IGenericService<D,O,ID> {

	D save(O entity);
	D update(ID id, O entity);
	void delete(ID id);
	D get(ID id);
	List<D> list();
	
}
