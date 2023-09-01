package com.moda.apitestecassio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.moda.apitestecassio.dto.RespostaApiDto;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(value = IllegalArgumentException.class)
	public ResponseEntity<RespostaApiDto> illegalArgumentExceptionHandler(IllegalArgumentException e) {

		RespostaApiDto respostaDto = new RespostaApiDto();
		respostaDto.setStatus(HttpStatus.BAD_REQUEST.value());
		respostaDto.setMsg(e.getMessage());
		return ResponseEntity.status(respostaDto.getStatus()).body(respostaDto);
	}
	
	@ExceptionHandler(value = HttpMediaTypeNotAcceptableException.class)
	public ResponseEntity<RespostaApiDto> notAcceptableException(HttpMediaTypeNotAcceptableException e) {
		
		RespostaApiDto respostaDto = new RespostaApiDto();
		respostaDto.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
		respostaDto.setMsg(e.getMessage());
		return ResponseEntity.status(respostaDto.getStatus()).body(respostaDto);
	}

	@ExceptionHandler(ApiException.class)
	public ResponseEntity<RespostaApiDto> resourceNotFound(ApiException e) {

		RespostaApiDto respostaDto = new RespostaApiDto();
		respostaDto.setStatus(e.getStatus());
		respostaDto.setMsg(e.getMessage());
		return ResponseEntity.status(e.getStatus()).body(respostaDto);
	}
}
