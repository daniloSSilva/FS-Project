package br.com.fs.dto;

import java.math.BigInteger;

import org.springframework.stereotype.Component;

import br.com.fs.model.Estado;

@Component
public class EnderecoDTO {

	private Long id;

	private String rua;

	private Long numero;

	private BigInteger cep;

	private Estado estado;

	private String cidade;

	private String pais;

	private String complemento;

	private String bairro;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}
