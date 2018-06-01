package br.com.fs.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pessoa_fisica")
public class PessoaFisica implements Serializable {
	private static final long serialVersionUID = -6631935949996946256L;

	public PessoaFisica() {

	}

	public PessoaFisica(Long cpf, Long rg, Date dataNascimento) {
		this.cpf = cpf;
		this.rg = rg;
		this.dataNascimento = dataNascimento;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 11)
	private Long cpf;

	@Column(length = 9)
	private Long rg;

	@Column
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	@OneToOne
	@JoinColumn(name = "id_cliente")
	private Cliente pessoaFisica;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public Long getRg() {
		return rg;
	}

	public void setRg(Long rg) {
		this.rg = rg;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@JsonIgnore
	public Cliente getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(Cliente pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

}
