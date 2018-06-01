package br.com.fs.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto implements Serializable {
	public Produto() {

	}

	public Produto(String nomeSoftware) {
		this.nomeSoftware = nomeSoftware;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -922513855679951184L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String nomeSoftware;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "cliente")
	private List<Compra> compras;
	
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

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}


}
