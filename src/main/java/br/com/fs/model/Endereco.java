package br.com.fs.model;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "endereco")
public class Endereco {

	public Endereco() {
	}

	public Endereco(String rua, Long numero, Estado estado, String cidade, String pais, String complemento,
			String bairro, BigInteger cep) {
		this.rua = rua;
		this.numero = numero;
		this.estado = estado;
		this.cidade = cidade;
		this.pais = pais;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String rua;

	@Column
	private Long numero;

	@Column
	private BigInteger cep;

	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private Estado estado;

	@Column
	private String cidade;

	@Column
	private String pais;

	@Column
	private String complemento;

	@Column
	private String bairro;

	@OneToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public BigInteger getCep() {
		return cep;
	}

	public void setCep(BigInteger cep) {
		this.cep = cep;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstados(Estado estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonIgnore
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}
