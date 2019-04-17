package com.curso.run.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.curso.run.services.Exception.AuthorizationException;
import com.curso.run.services.Exception.DataIntegrityException;
import com.curso.run.services.Exception.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	//retorna o erro quando não encontrar o objeto
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandarError> ObjectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		StandarError err = new StandarError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	//retorna erro quando tentar excluir uma entidade que tiver relacionamento
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandarError> dataIntegrity(DataIntegrityException e, HttpServletRequest request){
		
		StandarError err = new StandarError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	//retorna error de validacao
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandarError> dataIntegrity(MethodArgumentNotValidException e, HttpServletRequest request){
		
		ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de validação", System.currentTimeMillis());
		
		for(FieldError er: e.getBindingResult().getFieldErrors()) {
			err.addError(er.getField(), er.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	//retorna o erro quando O usuario não tem permissão, ou seja, acesso negado
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandarError> Authorization(AuthorizationException e, HttpServletRequest request){
		
		StandarError err = new StandarError(HttpStatus.FORBIDDEN.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}
	
	
	
}
