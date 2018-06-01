package br.com.fs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fs.dto.CompraDTO;
import br.com.fs.exceptions.ParametroObrigatorioException;
import br.com.fs.exceptions.ResourceNotFoundException;
import br.com.fs.model.Compra;
import br.com.fs.repository.CompraRepository;

@Service
public class CompraService {
	@Autowired
	private ModelMapper modelMapper;
	private CompraRepository compraRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(CompraService.class);

	public CompraService(CompraRepository compraRepository) {
		this.compraRepository = compraRepository;
	}

	public List<Compra> getAll() throws ResourceNotFoundException {
		List<Compra> compras = compraRepository.findAll();
		return compras;
	}

	public void save(Compra compra) throws ResourceNotFoundException, ParametroObrigatorioException {
		compraRepository.save(compra);
	}

	public void delete(Long id) throws ResourceNotFoundException, ParametroObrigatorioException {
		if (id == null) {
			throw new ParametroObrigatorioException("Parametro id obrigatorio.");
		}
		compraRepository.deleteById(id);
	}

	public Optional<Compra> getById(Long id) throws ResourceNotFoundException, ParametroObrigatorioException {
		if (id == null) {
			throw new ParametroObrigatorioException("Parametro id obrigatorio.");
		}

		return compraRepository.findById(id);
	}

	public CompraDTO convertToDTO(Compra compra) throws MappingException {
		CompraDTO compraDTO = null;
		try {
			compraDTO = modelMapper.map(compra, CompraDTO.class);
		} catch (MappingException e) {
			LOGGER.info("Erro de conversao de objetos", e.getMessage());
			e.printStackTrace();
		}
		return compraDTO;
	}

	public Compra convertToEntity(CompraDTO compraDTO) throws MappingException {
		Compra compra = modelMapper.map(compraDTO, Compra.class);
		return compra;
	}
	
	/**
	 * Retorna uma compra por id.
	 * 
	 * @param id.
	 *            id da compra.
	 * @return retorna a compra pelo id.
	 * @throws ResourceNotFoundException
	 *             error 404 não encontado.
	 * @throws ParametroObrigatorioException
	 *             error parametro obrigatório.
	 */
	public void populaRelacionamentos(Compra compra) {
		List<Compra> compras = new ArrayList<>();
		compras.add(compra);
		compra.getCliente().setCompras(compras);
		compra.getCliente().getEndereco().setCliente(compra.getCliente());
		compra.getCliente().getContatos().forEach(contato -> contato.setCliente(compra.getCliente()));
//
		if (compra.getCliente().getPessoaFisica() != null) {
			compra.getCliente().getPessoaFisica().setPessoaFisica(compra.getCliente());
		} else {
			compra.getCliente().getPessoaJuridica().setPessoaJuridica(compra.getCliente());
		}

		compra.getCliente().getUsuarios().forEach(usuario -> usuario.setCliente(compra.getCliente()));
		compra.getCliente().getUsuarios().forEach(usuario -> usuario.setId(null));
		compra.getProduto().setCompras(compras);
	}
}
