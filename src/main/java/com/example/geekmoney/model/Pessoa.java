package com.example.geekmoney.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity // anoção para o jpa saber que a classse é uma entidade
@Table(name = "pessoa") //referencia a uma @table com o nome descrito no campo NAME
public class Pessoa {
	
	
	@Id // informa que que a variavel é um ID
	@GeneratedValue(strategy = GenerationType.IDENTITY) // configura id para auto incremento
	private Long codigo;
	
	@NotNull
	private String nome;
	
	@Embedded // diz que o objeto esta EMBUTIDO na classe, sem precisar criar mais uma tabela no banco
	private Endereco endereco;
	
	@NotNull
	private Boolean ativo;
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
