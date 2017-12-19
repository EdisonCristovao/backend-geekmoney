package com.example.geekmoney.ecxeptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GeekmoneyEcxeptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String msgUsuario = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String msgDesenvolvedor = ex.getCause().toString();
		return handleExceptionInternal(ex, new Erro(msgDesenvolvedor, msgUsuario), headers, HttpStatus.BAD_REQUEST, request);
		//return super.handleHttpMessageNotReadable(ex, headers, status, request);
	}
	
	public static class Erro {
		
		private String msgDesenvolvedor;
		private String msgUsuario;
		
		public Erro(String msgDesenvolvedor, String msgUsuario) {
			this.msgDesenvolvedor = msgDesenvolvedor;
			this.msgUsuario = msgUsuario;
		}

		public String getMsgDesenvolvedor() {
			return msgDesenvolvedor;
		}

		public void setMsgDesenvolvedor(String msgDesenvolvedor) {
			this.msgDesenvolvedor = msgDesenvolvedor;
		}

		public String getMsgUsuario() {
			return msgUsuario;
		}

		public void setMsgUsuario(String msgUsuario) {
			this.msgUsuario = msgUsuario;
		}
		
		
	}
}
