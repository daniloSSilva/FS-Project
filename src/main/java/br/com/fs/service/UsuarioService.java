package br.com.fs.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fs.dto.ProdutoDTO;
import br.com.fs.model.Usuario;
import br.com.fs.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private ModelMapper modelMapper;
	private UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public List<Usuario> getAll() {
		List<Usuario> produtos = usuarioRepository.findAll();
		return produtos;
	}


	public void saveAll(List<Usuario> usuarios) {
		usuarioRepository.saveAll(usuarios);
	}

	public void save(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	public void delete(Long id) {
		usuarioRepository.deleteById(id);
	}

	public Optional<Usuario> getById(Long id) {
		return usuarioRepository.findById(id);
	}

	public ProdutoDTO convertToDTO(Usuario usuario) {
		ProdutoDTO produtoDTO = modelMapper.map(usuario, ProdutoDTO.class);
		return produtoDTO;
	}

	public Usuario convertToEntity(ProdutoDTO produtoDTO) {
		Usuario usuario = modelMapper.map(produtoDTO, Usuario.class);
		// populaRelacionamentos(usuario);
		return usuario;
	}

	// public void populaRelacionamentos(Usuario usuario) {
	// usuario.set
	// }
}
