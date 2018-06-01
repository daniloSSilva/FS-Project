package br.com.fs.dto;

import org.springframework.stereotype.Component;

@Component
public class ProdutoDTO {

	private Long id;

	private String nomeSoftware;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeSoftware() {
		return nomeSoftware;
	}

	public void setNomeSoftware(String nomeSoftware) {
		this.nomeSoftware = nomeSoftware;
	}

}
