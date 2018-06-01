package br.com.fs.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ClienteDTO {

	private Long id;

	private String nome;

	private PessoaFisicaDTO pessoaFisica;

	private PessoaJuridicaDTO pessoaJuridica;

	private EnderecoDTO endereco;

	private List<ContatoDTO> contatos = new ArrayList<>();

	private List<UsuarioDTO> usuarios = new ArrayList<>();

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

	public PessoaFisicaDTO getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisicaDTO pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public PessoaJuridicaDTO getPessoaJuridica() {
		return pessoaJuridica;
	}

	public void setPessoaJuridica(PessoaJuridicaDTO pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}

	public EnderecoDTO getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoDTO endereco) {
		this.endereco = endereco;
	}

	public List<ContatoDTO> getContatos() {
		return contatos;
	}

	public void setContatos(List<ContatoDTO> contatos) {
		this.contatos = contatos;
	}

	public List<UsuarioDTO> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<UsuarioDTO> usuarios) {
		this.usuarios = usuarios;
	}

}
