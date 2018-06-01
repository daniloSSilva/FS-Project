package br.com.fs.dto;

import org.springframework.stereotype.Component;

@Component
public class CompraDTO {

	private Long id;

	private ClienteDTO cliente;

	private ProdutoDTO produto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}

	public ProdutoDTO getProduto() {
		return produto;
	}

	public void setProduto(ProdutoDTO produto) {
		this.produto = produto;
	}

}
