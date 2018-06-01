package br.com.fs.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4446532820886153056L;

	public Cliente() {

	}

	public Cliente(String nome, PessoaFisica pessoaFisica, Endereco endereco, List<Contato> contatos,
			List<Usuario> usuarios) {
		this.nome = nome;
		this.pessoaFisica = pessoaFisica;
		this.endereco = endereco;
		this.contatos = contatos;
		this.usuarios = usuarios;
	}

	public Cliente(String nome, PessoaJuridica pessoaJuridica, Endereco endereco, List<Contato> contatos,
			List<Usuario> usuarios) {
		this.nome = nome;
		this.pessoaJuridica = pessoaJuridica;
		this.endereco = endereco;
		this.contatos = contatos;
		this.usuarios = usuarios;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_cliente")
	private Long id;

	@Column(length = 50)
	private String nome;

	@OneToOne(mappedBy = "pessoaFisica", orphanRemoval = true, cascade = CascadeType.ALL)
	private PessoaFisica pessoaFisica;

	@OneToOne(mappedBy = "pessoaJuridica", orphanRemoval = true, cascade = CascadeType.ALL)
	private PessoaJuridica pessoaJuridica;

	@OneToOne(mappedBy = "cliente", orphanRemoval = true, cascade = CascadeType.ALL)
	private Endereco endereco;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "cliente")
	private List<Contato> contatos = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
	private List<Usuario> usuarios = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "cliente")
	private List<Compra> compras = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public PessoaJuridica getPessoaJuridica() {
		return pessoaJuridica;
	}

	public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

}
