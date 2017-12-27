package com.example.geekmoney.ecxeptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// essa classe cuida de notificar erros e necessita ter o decorator ControllerAdvice
@ControllerAdvice
public class GeekmoneyEcxeptionHandler extends ResponseEntityExceptionHandler {

	// ijeta uma instancia no objeto messageSource
	@Autowired
	private MessageSource messageSource;

	@Override // handleHttpMessageNotReadable // quando o json nao pode ser interpretado
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		// pega msgs do arquivo txt cam o campo mensagem,invalida
		String msgUsuario = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String msgDesenvolvedor = ex.getCause().toString();

		// ja que o objeto é visto facilmente em um json, colocamos as 2 menssagens em
		// um objeto e criamos uma lista
		List<Erro> erros = Arrays.asList(new Erro(msgDesenvolvedor, msgUsuario));

		// responsavel por emitir os erros
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
		// return super.handleHttpMessageNotReadable(ex, headers, status, request);
	}

	@Override // handleMethodArgumentNotValid // quando cai num argumento null, size invalido
				// etc, fatores que o banco nao aceita
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		// cria a lista de erros, os erros tem tipo FielError e estao no campo
		List<Erro> erros = criaListaErros(ex.getBindingResult());

		// responsavel por emitir os erros
		return handleExceptionInternal(ex, erros, headers, status, request);
		// return super.handleMethodArgumentNotValid(ex, headers, status, request);
	}

	// decorator para mostrar que ecxeptions cairam nesse metodo, pode se adicionar
	// mais colocando uma ',' e o proximo ecxeption
	// retorna status 404 not found
	// exception ativada quaando o dado a ser excluido pelo Long codigo nao existe
	
	// @ResponseStatus(HttpStatus.NOT_FOUND)	
	@ExceptionHandler({ EmptyResultDataAccessException.class })
	protected ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
			WebRequest request) {
		// pega msgs do arquivo txt cam o campo mensagem,invalida
		String msgUsuario = messageSource.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
		String msgDesenvolvedor = ExceptionUtils.getRootCause(ex).getMessage(); // nao consegui usar o commons.lang 

		// ja que o objeto é visto facilmente em um json, colocamos as 2 menssagens em
		// um objeto e criamos uma lista
		List<Erro> erros = Arrays.asList(new Erro(msgDesenvolvedor, msgUsuario));

		// responsavel por emitir os erros
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler({DataIntegrityViolationException.class})
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
			WebRequest request) {
		// pega msgs do arquivo txt cam o campo mensagem,invalida
		String msgUsuario = messageSource.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
		String msgDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);

		// ja que o objeto é visto facilmente em um json, colocamos as 2 menssagens em
		// um objeto e criamos uma lista
		List<Erro> erros = Arrays.asList(new Erro(msgDesenvolvedor, msgUsuario));

		// responsavel por emitir os erros
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	private List<Erro> criaListaErros(BindingResult bindingResult) {
		List<Erro> erros = new ArrayList<>();

		// bindingResult.getFieldErrors() pega todos os erros
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			String msgUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String msgDesenvolvedor = fieldError.toString();
			erros.add(new Erro(msgDesenvolvedor, msgUsuario));
		}

		return erros;
	}

}
