package com.proton.services.exceptions;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Object;

//Classe de exceção personalizada, para personalizar erros do tipo de recursos não encontrados
public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(Object id){
		super("Resource not found. Id: " + id);
	}
	public ResourceNotFoundException(Integer id){
		super("Resource not found. Id: " + id);
	}

}
