package com.project.web.response;

import org.springframework.stereotype.Component;

@Component
public class SystemMessage<T> {

	private Integer status;
	private String message;
	private T object;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}
	
	public SystemMessage<T> showMessage(
			String message,
			Integer status,
			T object
			){
		
		this.setMessage(message);
		this.setObject(object);
		this.setStatus(status);
		
		return this;
		
	}
	
}
