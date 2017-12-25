package com.example.geekmoney.ecxeptionhandler;

//classe anonima para geral o jason com msg para o desenvolvedor e usuario
	public class Erro {

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
