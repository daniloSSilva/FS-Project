package br.com.fs.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pessoa_juridica")
public class PessoaJuridica implements Serializable {

	public PessoaJuridica(String razaoSocial, String nomeFantasia, Long cnpj) {
		this.razaoSocial = razaoSocial;
		this.nomeFantasia = nomeFantasia;
		this.cnpj = cnpj;
	}

	public PessoaJuridica() {

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1320201848034274178L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "razao_social")
	private String razaoSocial;

	@Column(name = "nome_fantasia")
	private String nomeFantasia;

	@Column
	private Long cnpj;

	@OneToOne
	@JoinColumn(name = "id_cliente")
	private Cliente pessoaJuridica;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public Long getCnpj() {
		return cnpj;
	}

	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}

	@JsonIgnore
	public Cliente getPessoaJuridica() {
		return pessoaJuridica;
	}

	public void setPessoaJuridica(Cliente pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}

}
