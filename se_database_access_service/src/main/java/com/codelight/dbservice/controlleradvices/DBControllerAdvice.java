package com.codelight.dbservice.controlleradvices;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.codelight.dbservice.dbexceptions.DbException;
import com.codelight.dbservice.errorobjects.DbErrorObj;
import com.michael.documentation.resources.response.DbResponse;

@ControllerAdvice
public class DBControllerAdvice {

	@ExceptionHandler(value = DbException.class)
	public ResponseEntity<DbResponse<DbErrorObj>> dbExceptionHandler(DbErrorObj errorObj){
		DbResponse<DbErrorObj> response = new DbResponse<>();
		response.setT(errorObj);
		return ResponseEntity.badRequest().body(response);
	}
}
