package com.project.web.swagger;

import org.springframework.http.ResponseEntity;

public interface IGenericRestController<T, ID> {
	

	public ResponseEntity<?> list();
	public ResponseEntity<?> get(ID id);
	public ResponseEntity<?> save(T entity);
	public ResponseEntity<?> update(ID id, T entity);
	public ResponseEntity<?> delete(ID id);

}
